package com.ceva.core1.ch08.generics;

import java.util.Date;

/*
 * Definicion de una Tipo generico A_Pair<T>.
 * Un tipo generico es un reference type que tiene uno o mas type parameters
 * T first -> type variable o type parameter
 * T second -> type variable o type parameter
 * estos type parameters seran reemplazados por type arguments cuando el generic type Pair es instanciado.
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

    public static void main(String[] args) {
        // Instanciacion del tipo generico Pair<T>
        A_Pair<String> pair = new A_Pair<>();
    }
}
