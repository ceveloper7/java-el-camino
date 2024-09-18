package com.ceva.buildingblocks.basics1;

/**
 * var se utiliza unicamente en variable locales.
 * No se puede usar var como parametros de un metodo - ERROR -> private void suma(var num1, var numa2){}
 */
public class DUsingVar {
    // error - no se puede usar var con variables de instancia.
    // var baseSalary = 10000;
    public static void main(String[] args) {
        var name = "hello"; // compilador infiere que es tipo String
        var pi = 3.14; // compilador infiere que es tipo double
        var active = false; // compilador infiere que es tipo boolean

        // error - una vez que se le identifico el tipo boolean no se le puede cambiar el tipo de dato
        // active = 1;

        // error - una vez que se define una variable local usando var, hay que asignarle un valor.
        // var status;

        // error
        // int a, var b = 3;

        // ok
        int a, b, c = 3;

        // error
        // var num = null;
    }
}
