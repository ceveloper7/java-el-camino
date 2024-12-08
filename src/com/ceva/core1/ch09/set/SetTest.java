package com.ceva.core1.ch09.set;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class SetTest {
    public static void time(Set<String> wordSet, List<String> wordList, int repetitions){
        long totalTime = 0;
        for (int i = 1; i <= repetitions; i++)
        {
            for (String word : wordList)
            {
                long start = System.nanoTime();
                wordSet.add(word);
                long end = System.nanoTime();
                totalTime += end - start;
            }
        }

        Iterator<String> iter = wordSet.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++)
            System.out.print(iter.next() + " ");
        System.out.println("...");
        System.out.printf("%s: %d words, %d distinct, %.3f seconds.%n",
                wordSet.getClass().getSimpleName(), wordList.size(), wordSet.size(),
                totalTime * 1E-9);
    }

    public static void main(String[] args)throws IOException {
        Class<?> cl = SetTest.class;
        List<String> words = new ArrayList<String>();
        try(InputStream input = cl.getResourceAsStream("data/crsto10.txt")) {
            assert input != null;
            try(InputStreamReader isr = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(isr);){
                String linea="";
               while ((linea = br.readLine()) != null){
                   StringTokenizer st = new StringTokenizer(" ", linea);
                   while (st.hasMoreTokens()){
                       String word = st.nextToken();
                       words.add(word);
                   }
               }
            }
        }
        time(new HashSet<>(), words, 100);
        time(new TreeSet<>(), words, 100);
    }
}
