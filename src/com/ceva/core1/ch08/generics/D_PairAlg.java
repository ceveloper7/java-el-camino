package com.ceva.core1.ch08.generics;

public class D_PairAlg {
    public static boolean hasNull(A_Pair<?> p){
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static <T> void swapHelper(A_Pair<T> p)
    {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }

    public static void swap(A_Pair<?> p) { swapHelper(p); }
}
