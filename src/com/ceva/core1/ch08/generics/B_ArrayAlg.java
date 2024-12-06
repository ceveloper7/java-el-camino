package com.ceva.core1.ch08.generics;

public class B_ArrayAlg {
    /**
     * Gets the minimum and maximum of an array of strings.
     * @param a an array of strings
     * @return a pair with the min and max values, or null if a is null or empty
     */
    public static A_Pair<String> minmax(String[] a) throws IllegalArgumentException{
        if(a==null || a.length==0){
            throw new IllegalArgumentException("Error: null variable");
        }
        String min = a[0];
        String max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            // compareTo(): return 0 si ambas cadenas son iguales
            // return negativo si la primera cadena es menor o viene antes que la segunda
            // return positivo si la primera cadena es mayor o viene despues que la segunda.
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new A_Pair<>(min, max);
    }
}
