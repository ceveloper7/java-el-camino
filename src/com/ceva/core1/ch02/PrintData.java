package com.ceva.core1.ch02;

public class PrintData {
    public static void main(String[] args) {
        dspSimpleText();

        var v = new String("hola");

        System.out.println("hola" == v);
    }

    private static void dspSimpleText(){
        System.out.printf("%s%n%s%n", "Welcome to ", "Java Programming");
    }
}
