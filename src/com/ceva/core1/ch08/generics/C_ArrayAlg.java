package com.ceva.core1.ch08.generics;

public class C_ArrayAlg {
    /**
     Al metodo minmax le ponemos un limite al type variables. El tipo T debe
     implementar la interface Comparable <T extends Comparable>
     Gets the minimum and maximum of an array of objects of type T.
     @param a an array of objects of type T
     @return a pair with the min and max values, or null if a is null or empty
     */
    public static <T extends Comparable> B_Pair<T> minmax(T[] a)
    {
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new B_Pair<>(min, max);
    }
}
