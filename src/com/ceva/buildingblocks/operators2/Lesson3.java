package com.ceva.buildingblocks.operators2;

/*
 * Promocion numerica
 */
public class Lesson3 {

    // promocion de tipo regla 1
    private void firstRule(){
        // como i es un tipo mayor que el tipo de s, el resultado sera una variable de mayor tipo, es decir, int
        short s = 5;
        int i = 10;

        int f = s + 10;

        System.out.println(f);
    }

    // promocio de tipo regla 2
    private void secondRule(){
        int x = 1;
        double y = 3.0;

        // z sera de tipo double
        var z = x * y;

        System.out.println(z);
    }

    // los tipos de datos byte, short, char son promovidos a int
    private void thirdRule(){
        short x = 1;
        short y = 1;

        var zz = x * y; // promovido a int

        System.out.println(zz);
    }

    private void fourthRule(){
        short x = 10;
        float y = 3.0f;
        double z = 10.0;

        /*
         * primero: x es promovido a int por la tercer regla
         * segundo: int * float entonces int es promovido a float
         * tercero: float / double, el valor floar es promovido a double
         * cuarto: zz es de tipo double
         */
        var zz = x * y / z;

        System.out.println(zz);
    }

    private void castingCases(){
        /*
         * cast innecesario o redundate porque el default es un int
         */
        int a = (int)5;
        String v = (String)"valor";

        /*
         * el rango de un int permite recibir un valor short por lo que es un casting aceptado
         */
        int b = (short)100;

        /*
         * cuando usamos numeros literales el compilador determina que el valor de la suma esta dentro del rango de short
         * por lo que permite el valor y hace una promocion hacia abajo del valor a short
         */
        short c = (4+10);

        // el casting a la derecha no se permite
        //long d = 10(long);

        // no compila. es necesario hay un cast down a float
        //float e = 2.0 * 4;

        float e = (float)2.0 * 4;

        int f = (int)(5*3L);
        int g = 5 * (int)3L;
    }

    public static void main(String[] args) {
        Lesson3 lesson3 = new Lesson3();
        lesson3.fourthRule();
        //lesson3.firstRule();
        //lesson3.secondRule();
    }
}
