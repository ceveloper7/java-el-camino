package com.ceva.core1.ch10.executor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Como usar Callables & Executors
 */
public class ExecutorDemo {

    //  Counts occurrences of a given word in a file.
    public static long occurrences(String word, Path path){
        try(var in = new Scanner(path)){
            int count = 0;
            while (in.hasNext())
                if (in.next().equals(word)) count++;
            return count;
        }catch (IOException ex){
            return 0;
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException{
        try(Stream<Path> entries = Files.walk(rootDir)){
            return entries
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toSet());
        }
    }

    public static Callable<Path> searchForTask(String word, Path path){
        return () ->
        {
            try (var in = new Scanner(path))
            {
                while (in.hasNext())
                {
                    if (in.next().equals(word)) return path;
                    // verificamos si el status del hilo es interrupted
                    if (Thread.currentThread().isInterrupted())
                    {
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try(var in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /opt/jdk-21-src): ");
            String start = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String word = in.nextLine();

            // Lista de archivos en un directorio
            Set<Path> files = descendants(Path.of(start));
            var tasks = new ArrayList<Callable<Long>>();
            for(Path file : files){
                // nro de ocurrencia de una palabra en un archivo, seperamos cada file en una task
                Callable<Long> task = ()-> occurrences(word, file);
                tasks.add(task);
            }

            // Pasamos todas las tasks al Executor
            ExecutorService executor = Executors.newCachedThreadPool();
            // use a single thread executor instead to see if multiple threads
            // speed up the search
            // ExecutorService executor = Executors.newSingleThreadExecutor();
            // Or try virtual threads
            // ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

            Instant startTime = Instant.now();
            // el resultado de una tarea asincrona es un objeto Future. Lista de objeto Future
            List<Future<Long>> results = executor.invokeAll(tasks);
            Instant endTime = Instant.now();
            long total = 0;
            // procesamos los objeto Future
            for(Future<Long> result : results){
                // adicionamos todos los results, bloqueando hasta que ellos esten disponibles
                total += result.get();
            }
            // print
            System.out.println("Occurrences of " + word + ": " + total);
            System.out.println("Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");

            // BUSCAMOS EL PRIMER ARCHIVO QUE CONTIENE UNA PALABRA DADA
            var searchTasks = new ArrayList<Callable<Path>>();
            for(Path file : files){
                searchTasks.add(searchForTask(word, file));
            }

            startTime = Instant.now();
            // invokeAny permite paralelizar la busqueda. invokeAny termina tan pronto alguna task retorna
            Path found = executor.invokeAny(searchTasks);
            endTime = Instant.now();
            System.out.println(word + " occurs in: " + found);
            System.out.println("Time elapsed: "
                    + Duration.between(startTime, endTime).toMillis() + " ms");


            executor.close();
        }
    }
}
