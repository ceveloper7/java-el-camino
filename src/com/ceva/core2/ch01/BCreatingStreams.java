package com.ceva.core2.ch01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BCreatingStreams {
    private static final Class<?> cl = BCreatingStreams.class;

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

    public static void main(String[] args) throws IOException {
        InputStream input = cl.getResourceAsStream("data" + File.separator + "alice.txt");
        assert input != null;
        String content = new String(input.readAllBytes());
        Stream<String> words = Pattern.compile("\\PL+").splitAsStream(content);
        show("Words", words);

        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);

        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,
                n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> greetings = "Hello\nGuten Tag\nBonjour".lines();
        show("greetings", greetings);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(content);
        show("wordsAnotherWay", wordsAnotherWay);

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootDirectories);

        Iterator<Path> iterator = Path.of("C:" + File.separator + "java-development").iterator();
        Stream<Path> pathComponents = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.ORDERED), false);
        show("pathComponents", pathComponents);
    }
}
