package com.ceva.core1.ch08.genericAlgorithms;

import java.util.Arrays;
import java.util.function.IntFunction;

public class GenericAlgorithms {
    public static void main(String[] args) throws ReflectiveOperationException
    {
        Pair<String> p = Pair.makePair(String::new);
        System.out.println(p);

        p = Pair.makePair(String.class);
        System.out.println(p);

        String[] ss = ArrayAlg.minmax("Tom", "Dick", "Harry");
        System.out.println(Arrays.toString(ss));

        ss = ArrayAlg.minmax((IntFunction<String[]>) String[]::new, "Tom", "Dick", "Harry");
        System.out.println(Arrays.toString(ss));
    }
}
