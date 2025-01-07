package com.ceva.core2.ch01;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class EStreamTransformation {

    public static <T> void show(String title, Stream<T> words){
        final int SIZE = 10;
        List<T> firstElements = words
                .limit(SIZE + 1)
                .toList();
        System.out.print(title + ": ");
        for(int i = 0; i < firstElements.size(); i++){
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "gently")
                .distinct();
        show("distinct ", uniqueWords);

        List<String> words = List.of("pictured", "riper", "would", "Wonderland");
        Stream<String> sorted = words.stream()
                .sorted(Comparator.comparing(String::length));
        show("Sorted ", sorted);
    }
}
