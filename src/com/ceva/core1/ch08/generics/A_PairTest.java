package com.ceva.core1.ch08.generics;

public class A_PairTest {
    public static void main(String[] args) {
        String[] words = { "Mary", "had", "a", "little", "lamb" };
        A_Pair<String> mm = B_ArrayAlg.<String>minmax(words);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}
