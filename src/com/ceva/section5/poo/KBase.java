package com.ceva.section5.poo;

/**
 * Clase anidada static
 */
public class KBase {
    private String field;

    public KBase(){
        field = "Hello";
    }

    // cuando la nested class es static no se tiene acceso a los atributos de la base class
    public static class Nested{
        public void run(){
            System.out.println("Run method dentro de Nested Class");
        }
    }
}
