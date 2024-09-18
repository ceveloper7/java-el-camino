package com.ceva.buildingblocks.operators2;

/*
 * Asignando valores
 */
public class Lesson4 {

    private void case1(){

        // cuando se trata de valores literales el compilador hace una excepcion y como 5 esta dentro del rango de un short lo permite.
        short a = 5;
        short b = 5;

        // en operaciones con variables de tipo byte,short,char estas son promovidas a int
        // no compila xq variables short son promovidas as int y el valor de una operacion int no puede ser asignado a un short
        //short c = a * b;

        // rango short: -32768 - 32767
        short c = (short) (a * b);
    }

    private void case2(){
        // rango byte: -128 - 127
        byte a = 1;
        // byte b = 128;

        //short c = 2 + a; no compila, 2 + a es promovido a int

        long d = 10;
        int e = 5;

        // e = e * d; no compila.

        // pero esto si funcion
        e *= d; // el operador compuesto *= aplica un cast, la multiplicacion es de tipo long y ese valor lo castea a int

        long f = 10;
        long g = (f = 5);

        System.out.println(g);

        // en un if podemos hacer una asignacion unicamente de tpo boolean
        boolean h = false;
        if(h = true){
            System.out.println("is true");
        }

    }

    public static void main(String[] args) {
        Lesson4 lesson4 = new Lesson4();
        lesson4.case2();
    }
}
