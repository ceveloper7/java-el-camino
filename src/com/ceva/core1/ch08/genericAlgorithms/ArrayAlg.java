package com.ceva.core1.ch08.genericAlgorithms;

import java.lang.reflect.*;
import java.util.function.*;
import java.util.*;

public class ArrayAlg {
    public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr, T... a)
    {
        T[] mm = constr.apply(2);
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return mm;
    }

    public static <T extends Comparable> T[] minmax(T... a)
    {
        T[] mm = (T[]) Array.newInstance(a.getClass().getComponentType(), 2);
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return (T[]) mm; // compiles with warning
    }
}
