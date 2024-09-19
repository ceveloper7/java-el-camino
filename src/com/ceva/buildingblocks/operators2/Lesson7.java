package com.ceva.buildingblocks.operators2;

/*
 * Operador ternario
 */
public class Lesson7 {

    private void case1(){
        int a = 5;
        int b = a < 2 ? 3 : 4; // b = 4
        System.out.println(b);

        int c = 7;
        int d = c > 5 ? 21 : 0; // d =  21
        System.out.println(d);

        int e = 1;
        int f = 1;
        int g = e < 10 ? e++ : f++;
        System.out.println(g); // g = 1 , e=2
    }

    public static void main(String[] args) {
        Lesson7 lesson7 = new Lesson7();
        lesson7.case1();
    }
}
