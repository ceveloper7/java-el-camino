package com.ceva.core1.ch08.basics;

import java.util.Date;

/*
 * Definicion de una Tipo generico Pair<X,Y> sin limites.
 * Un tipo generico es un reference type que tiene uno o mas type parameters
 * T first -> type variable o type parameter
 * T second -> type variable o type parameter
 * estos type parameters seran reemplazados por type arguments cuando el generic type Pair es instanciado.
 *
 * Los type parameters pueden ser declarados con limites o bounds
 */

public class Pair<X, Y> {
    private X first;
    private Y second;
    public Pair(X a1, Y a2) {
        first  = a1;
        second = a2;
    }
    public X getFirst()  { return first; }
    public Y getSecond() { return second; }
    public void setFirst(X arg)  { first = arg; }
    public void setSecond(Y arg) { second = arg; }

    public void printPair(){
        System.out.println("(" + getFirst() + ", " + getSecond() + ")");
    }

    // tipo parametrizado wildcard
    public void printPair(Pair<?,?> p){
        System.out.println("(" + p.getFirst() + ", " + p.getSecond() + ")");
    }

    public static void main(String[] args) {
        // definimos un tipo parametrizado concreto y puede ser usado conmo tipo de referencia regular
        Pair<String, Double> pair = new Pair<>("Hello Java", 5.50);
        pair.printPair();

        // instanciacion wildcard o tipo parametrizado wildcard
        Pair<?,?> limit = new Pair<String, Double>("Hello wolrd", 8.6);
        limit.printPair();
    }
}
