package com.ceva.core1.ch10.forkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * Counter extends RecursiveTask<Integer> tarea recursiva que retorna un Integer
 */

public class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter){
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    /**
     * Tarea recursiva
     *
     */
    @Override
    protected Integer compute() {
        // validamos si debemos dividir la tarea
        if (to - from < THRESHOLD){
            // calculamos la tarea directamente
            int count = 0;
            for (int i = from; i < to; i++)
            {
                // para cada elemento aplicamos el test (x) -> x > 0.5, si lo es, incrementamos coount
                if (filter.test(values[i])) count++;
            }
            return count;
        }
        // La diferencia entre (to-from) es mayor o igual a THRESHOLD entonces dividimos la tarea
        else{
            // mid -> indice medio del subarray
            int mid = from + (to - from) / 2;
            // creamos dos subtareas
            var first = new Counter(values, from, mid, filter); // procesamos primera mitad de from a mid
            var second = new Counter(values, mid, to, filter); // procesamos segunda mitad de mid a to

            // ejecutamos las subtareas en paralelo
            invokeAll(first, second);
            // esperamos que ambas subtareas terminen. se suman los elementos que cumplen el test en ambas mitades
            return first.join() + second.join();
        }
    }
}
