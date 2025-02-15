package com.ceva.core1.ch10.blockingQueue;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/*
 * El programa busca archivos en un directorio y sub-directorio colocando las lineas que contienen
 * esa palabra que buscamos. Notemos que el programa no utilza un mecanismo de Thread sinchronization
 *
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final Path TERMINATION = Path.of("");
    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file the file to search
     * @param keyword the keyword to search for
     */
    public static void search(Path file, String keyword) throws IOException
    {
        try (var in = new Scanner(file))
        {
            int lineNumber = 0;
            while (in.hasNextLine())
            {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    System.out.printf("%s:%d:%s%n", file, lineNumber, line);
            }
        }
    }

    /**
     * Recursively enumerates all files in a given directory and its subdirectories.
     * See Chapters 1 and 2 of Volume II for the stream and file operations.
     * @param directory the directory in which to start
     */
    public static void enumerate(Path directory) throws IOException, InterruptedException
    {
        try (Stream<Path> children = Files.list(directory))
        {
            for (Path child : children.toList())
            {
                if (Files.isDirectory(child))
                    enumerate(child);
                else
                    queue.put(child);
            }
        }
    }

    public static void main(String[] args) {
        try(var in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /tmp/jdk-21-src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            // Producer Thread
            Runnable enumerator = () ->
            {
                try
                {
                    enumerate(Path.of(directory));
                    // colocamos un objeto de "terminacion" para indicar que se llego al final de la cola
                    queue.put(TERMINATION);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                }
            };
            Thread.ofPlatform().start(enumerator);

            for (int i = 1; i <= SEARCH_THREADS; i++)
            {
                // Consumer Thread
                Runnable searcher = () ->
                {
                    try
                    {
                        boolean done = false;
                        while (!done)
                        {
                            Path file = queue.take();
                            // validamo si el objeto tomado del queue es el objeto que marca la temrinacion
                            if (file == TERMINATION)
                            {
                                queue.put(file);
                                done = true;
                            }
                            else search(file, keyword);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InterruptedException e)
                    {
                    }
                };
                Thread.ofPlatform().start(searcher);
            }
        }
    }
}
