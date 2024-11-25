package com.ceva.core1.ch03.datatype;

/*
 * Conversiones sin perdida de datos
 * De byte a short
 * De short a int
 * De char a int
 * De int a long
 * De int a double
 * De float a double
 *
 * Conversiones con perdida de datos
 * De int a float -> cuando el int se convierte a float se pierde alguna precision.
 * De long a float
 * De long a double
 *
 * Conversion de ambos operandos a un tipo comun:
 * Si queremos por ejemplo sumar dos valores uno de tipo int y otro de tipo float. Ambos operandos son
 * convertidos a un tipo comun antes de realizar la operacion.
 *
 * Si uno de los operando es de tipo double, el otro operador sera convertido a double
 * Si uno de los operando es de tipo float, el otro operando sera convertido a float
 * Si uno de los operando es de tipo long, el otro operando sera convertido a long
 * En otro caso, ambos operandos seran convertidos a int.
 */

public class E_Conversions {
    private static void sample1(){
        int v = 123456789;
        float z = v;

        System.out.println(z); // print 1.2345679E8

        int y = 5;
        double zz = 6.0;

        System.out.println(y + zz);
    }

    /*
     * Las conversiones numericas se puede realizar pero se pueden producir perdidas de informacion.
     */
    private static void cast(){
        // sample 1. Conversion donde la perdidas de informacion es posible, se hacem mediante cast
        double a = 9.997;
        int b = (int) a; // b contiene la parte entera (9) y se pierde la parte decimal.
        System.out.println("con perdida de informacion: " + b);

        // en casos como el anterior lo recomendando es primero redondear el punto flotante a su entero mas proximo
        int c = (int )Math.round(a); // print 10
        System.out.println("Redondeo al entero proximo " + c);
    }

    private static void assigment(){
        int a = 1;
        a += 4; // 5

        int b = 1;
        int c = b += 4;
        System.out.println(c); // print 5;
    }

    public static void main(String[] args) {
        cast();
        assigment();
    }
}
