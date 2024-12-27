package com.ceva.core1.ch10.forkJoin;

import java.util.concurrent.*;
import java.util.function.*;

public class ForkJoinTest {
    public static void main(String[] args) {
        final int ARRAY_SIZE = 10000000;
        var numbers = new double[ARRAY_SIZE];

        // Llenamos array numeros entre 0-1
        for (int i = 0; i < ARRAY_SIZE; i++) numbers[i] = Math.random();

        /*
         * numbers -> array
         * from -> start index
         * to -> array size
         * lambda expression: numeros mayores a 0.5
         */
        var counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        // creamos un pool de tareas
        var pool = new ForkJoinPool();
        // ejecutamos la tarea counter
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}
