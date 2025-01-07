package com.ceva.core2.ch01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class DCreatingSubStreams {
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
        Stream<Double> randoms = Stream.generate(Math::random)
                .limit(100);
        show("Limit stream", randoms);

        InputStream input = cl.getResourceAsStream("data" + File.separator + "alice.txt");
        assert input != null;
        String contents = new String(input.readAllBytes());
        // saltamos el primer elemento del stream
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);
        show("Skip: ", words);


        Stream<String> withoutInitialWhiteSpace =
                graphemeClusters(contents)
                        .dropWhile((s) -> s.length() == 0);
        show("dropWhile", withoutInitialWhiteSpace);

        Stream<String> combined = Stream.concat(graphemeClusters("Hello"), graphemeClusters("World"));
        show("concat", combined);

    }
}
