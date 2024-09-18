package com.ceva.buildingblocks.operators2;

/*
 * Trabajando con Unary operators
 */
public class Lesson2 {

    private void logicalComponent(){
        boolean happy = false;
        System.out.println("is happy: " + happy);

        System.out.println("is happy: " + !happy);
    }

    private void bitsComplement(){
        // el valor de binario de 3 es 0011
        int value = 3;

        // el bit complement de 3 es los ceros a uno y los uno a cero, por lo que el bit complement de 3 es 1100
        int bitCmp = ~value;

        System.out.println("the bit complement of " + value + " is " + bitCmp);
    }

    private void increDecr(){
        int people = 0;
        System.out.println("People arround: " + people);

        // pre-increment -> incrementa en 1 y retorna el nuevo valor
        System.out.println("People after one hour " + ++people);
    }

    public static void main(String[] args) {
        Lesson2 lesson = new Lesson2();
        lesson.increDecr();
        //lesson.bitsComplement();
        //lesson.logicalComponent();
    }
}
