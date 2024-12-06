package com.ceva.core1.ch08.generics;

/*
 * Definicion de una simple clase generica A_Pair<T>.
 * <T> -> type variable
 */
public class A_Pair<T> {
    /*
     * type variable se utilza a lo largo de la definicion de la clase para especificar los tipos
     * de retorno y los tipos de campos y variables locales.
     */
    private T first;
    private T second;

    public A_Pair() { first = null; second = null; }
    public A_Pair(T first, T second) { this.first = first;  this.second = second; }

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T newValue) { first = newValue; }
    public void setSecond(T newValue) { second = newValue; }
}
