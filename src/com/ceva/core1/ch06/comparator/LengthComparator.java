package com.ceva.core1.ch06.comparator;

import java.util.Comparator;

/*
 * Con la interface Comparable podemos ordenar un array de cadenas ya que String implementa
 * Comparable<String> y el metodo String.compareTo() compara dos cadenas en orden del diccionario.
 */

public class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
