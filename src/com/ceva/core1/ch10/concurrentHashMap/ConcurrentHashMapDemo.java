package com.ceva.core1.ch10.concurrentHashMap;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class ConcurrentHashMapDemo {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path file)
    {
        try (var in = new Scanner(file))
        {
            while (in.hasNext())
            {
                String word = in.next();
                map.merge(word, 1L, Long::sum);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns all descendants of a given directory--see Chapters 1 and 2 of Volume II
     * @param rootDir the root directory
     * @return a set of all descendants of the root directory
     */
    public static Set<Path> descendants(Path rootDir) throws IOException
    {
        try (Stream<Path> entries = Files.walk(rootDir))
        {
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args)
            throws InterruptedException, ExecutionException, IOException
    {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        Path pathToRoot = Path.of("C:\\Books\\Programming\\Java\\Core\\TT");
        for (Path p : descendants(pathToRoot))
        {
            if (p.getFileName().toString().endsWith(".txt"))
                executor.execute(() -> process(p));
        }
        executor.close();
        map.forEach((k, v) ->
        {
            if (v >= 1)
                System.out.println(k + " occurs " + v + " times");
        });
    }
}
