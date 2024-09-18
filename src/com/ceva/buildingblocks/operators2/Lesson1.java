package com.ceva.buildingblocks.operators2;

/*
 * La precedencia de los operadores
 */
public class Lesson1 {
    private void sample1(){
        int cookie = 4;
        // por precedencia, se realiza primero el pre-decremento (pre unary operator), luego la multiplicacion y finalmente la suma
        double reward = 3 + 2 * --cookie;
        System.out.println("Zoo animal received " + reward + " rewards point");
    }

    // precedencia de operadores
    private void sample2(){
        double height = 2;
        int lenght = 2;

        // la multiplicacion tiene mayor precedencia que la suma
        var perimeter = 2 * height + 2 * lenght; // 8.0

        System.out.println("the perimeter is " + perimeter);
    }

    public static void main(String[] args) {
        Lesson1 lesson1 = new Lesson1();
        lesson1.sample2();
        //lesson1.sample1();
    }
}
