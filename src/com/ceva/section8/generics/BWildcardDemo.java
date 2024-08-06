package com.ceva.section8.generics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BWildcardDemo {

    /**
     * Upper Bounded Wildcards: comodin de acotado superior
     * process() acepta un argument List<> cuyo tipo extienda de Number como Integer
     */
    public static void process(List<? extends Number> list) {
        /* ... */
    }

    /**
     * Lower Bounded Wildcards: comodin de acotado inferior
     * process2() recibe como parametro tipos List<Integer>, List<Number>, List<Object> es decir  que
     * process2() recibe como parametro una lista de cualquier super tipo de Integer
     */
    public static void process2(List<? super Integer> list) {
        /* ,,, */
    }

    public static void processTest() {
        List<Integer> list = new LinkedList<>();
        process(list); // OK
    }

    /**
     * Comodin puro (?): el comodin puro se representa unicamente con un signo de interrogacion List<?>
     * nullCount() es una funcion que devuelve el numero de elementos que son null en cualquier coleccion.
     */
    public static int nullCount(Collection<?> list) {
        if (list == null)
            return 0;
        int count = 0;
        for (Object o : list) {
            if (o == null)
                count++;
        }
        return count;
    }

    public static void nullCountTest() {
        List<Integer> list = new LinkedList<>();
        int n = nullCount(list); // OK
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        process(list); // error: incompatible types: List<Integer> cannot be converted to List<Number>
    }
}
