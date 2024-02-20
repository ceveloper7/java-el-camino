package com.ceva.section4.apijava1;

/**
 * Autoboxing es la conversion automatica que java hace entre tipos de datos primitivos
 * y su correspondiente clase.
 * Convertir un tipo de dato primitivo a clase se llama boxing
 * Convertir una clase a tipo de dato primitivo se llama unboxing
 */
public class DAutoBoxing{
    public static void main(String[] args) {
        //boxing
        Integer oInt = 100; // a un objeto Integer le asignamos un valor primitivo 100
        oInt = oInt + 1; // a un objeto Integer le sumamos un valor primitivo
        //unboxing
        int i = oInt; // el valor de un objeto Integer lo asignamos a un tipo de dato int primitivo
    }
}
