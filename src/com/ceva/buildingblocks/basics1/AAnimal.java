package com.ceva.buildingblocks.basics1;

/**
 * Clases
 * 1. Cuando la clase es publica el archivo debe llamarse igual que la clase
 * 2. podemos tener otras clases dentro del archivo, mientras no sean public
 * 3. Para compilar una clase, desde java11 se puede compilar en memoria con el comando java "nombre_archivo".java pero el archivo debe
 *    contener solo una clase, y no se debe hacer referencia a otras clases.
 */
public class AAnimal {
    // variable de instancia name
    String name;

    // formas validas de declarar un metodo main
    // static public void main(String[] args) {}
    // public static void main(String []args){}
    // public static void main(String... args){}
    // public final static void main(String... args){}
    // public static void main(final String... args){}

    public static void main(String args[]){
        System.out.println("Hello World");
    }
}

class Dog{}

