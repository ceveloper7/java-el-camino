package com.ceva.core2.ch01;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;

public class CountLongWords {
    private static final Class<?> cl = CountLongWords.class;

    public static void main(String[] args) throws IOException {
        InputStream stream = cl.getResourceAsStream("data/alice.txt");
        assert stream != null;
        String content = new String(stream.readAllBytes());
        List<String> words = List.of(content.split("\\PL"));

        long count = 0;
        // Procesamiento de la lista de manera imperativa
        for(String word : words){
            if(word.length() > 13){
                count++;
            }
        }

        // Procesamiento de la lista de manera declarativa
        count = words.stream()
                .filter(word -> word.length() > 13)
                .count();
        System.out.println("Number of words greater than 13 characters is " + count);
    }
}
