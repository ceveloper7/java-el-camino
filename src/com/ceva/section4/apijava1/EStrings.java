package com.ceva.section4.apijava1;

/**
 * Una variable de tipo String es conocida como una variable inmutable, es decir, su valor
 * no puede ser modificado.
 * La desventaja de string inmutable es que por cada operador java de crear un nuevo string
 */
public class EStrings {
    public static void main(String[] args) {
        // las variable inmutables tienen la ventaja de poder ser compartidas con seguridad
        String t = "Hola";
        System.out.println(t);
        t = "Hola mundo"; // se crea un nuevo string con el nuevo texto
        System.out.println(t);

        /**
         * para realizar el siguiente codigo, java necesitara crear 3 variables
         * 1era almacena el valor 10
         * 2da almacena el total es
         * 3era almacena el total es 10
         */
        String total = "10";
        String s = "El total es: " + total;

        /**
         * 2do ejemplo inficiente de manejo String
         * 1ero construye un cadena con el valor: el total es 10
         * 2do construye una cadena con el valor: el total es 10 a nombre de:
         * 3era construye una cadena con el valor: el total es 10 a nombre de: Pedro
         * 4to asigar la cadena a text
         */
        String amount = "10";
        String name = "Pedro";
        String text = "El total es " + amount + " a nombre de: " + name;

        /**
         * Java puede convertir valores numericos a su contraparte alfanumerica
         */
        int i = 10;
        String g = "El total es: " + i; // internamente el entero 10 sera convertido en cadena

        /**
         *
         */
        int j = 10;
        int k = 20;
        // no se puede asignar un int a un String, pero si convertir a string y concaternarlo
        String t1 = "" + (j+k);
        System.out.println(t1); // 30
         t1 = "" + j + k;
        System.out.println(t1); // 1020
    }
}
