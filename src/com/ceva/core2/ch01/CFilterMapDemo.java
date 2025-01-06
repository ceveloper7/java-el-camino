package com.ceva.core2.ch01;

import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class CFilterMapDemo {
    private static final Class<?> cl = BCreatingStreams.class;

    public static <T> void show(String title, Stream<T> stream)
    {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).toList();
        System.out.print(title + ": ");
        if (firstElements.size() <= SIZE)
            System.out.println(firstElements);
        else
        {
            String out = firstElements.subList(0, SIZE).toString();
            System.out.println(out.substring(0, out.length() - 1) + ", ...]");
        }
    }

    @SuppressWarnings("resource")
    public static Stream<String> graphemeClusters(String s)
    {
        return new Scanner(s).useDelimiter("\\b{g}").tokens();
    }

    public static void main(String[] args) throws IOException {
        InputStream input = cl.getResourceAsStream("data" + File.separator + "alice.txt");
        assert input != null;
        String contents = new String(input.readAllBytes());
        List<String> words = List.of(contents.split("\\PL+"));

        // filter
        Stream<String> longWords = words.stream()
                .filter(w -> w.length() > 12);
        show("longWords", longWords);

        // method references: toLowerCase()
        Stream<String> lowercaseWords = words.stream()
                .map(String::toLowerCase);
        show("lowercaseWords", lowercaseWords);

        // method references charAt()
        String[] song = { "row", "row", "row", "your", "boat", "gently", "down",
                "the", "stream" };
        Stream<Character> firstCodeUnits = Stream.of(song)
                .filter(w -> w.length() > 0).map(s -> s.charAt(0));
        show("firstCodeUnits", firstCodeUnits);

        Stream<String> letters = Stream.of(song).flatMap(w -> graphemeClusters(w));
        show("letters", letters);

        // iterator que va a dividir caracter por caracter cada palabra proporcionada.
        BreakIterator iter = BreakIterator.getCharacterInstance();
        Stream<String> result = words.stream()
                // en lugar de producir un stream de resultados el resultado lo pasamos  a un Consumer
                .mapMulti((s, collector) ->
                    {
                        iter.setText(s);
                        int start = iter.first();
                        int end = iter.next();
                        while (end != BreakIterator.DONE)
                        {
                            String gc = s.substring(start, end);
                            start = end;
                            end = iter.next();
                            collector.accept(gc);
                        }
                    });
        show("result", result);
    }
}
