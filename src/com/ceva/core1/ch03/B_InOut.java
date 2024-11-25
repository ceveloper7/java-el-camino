package com.ceva.core1.ch03;

import java.util.Locale;
import java.util.Scanner;

public class B_InOut {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        readInput();
    }

    private static void readInput(){
        System.out.print("What is your name: ");
        String name = in.nextLine(); // lee input que puede contener espacios en blanco

        System.out.print("How old are you? ");
        int age = in.nextInt(); // lee un solo valor

        System.out.print("How tall are you? ");
        double size = in.nextDouble();

        System.out.print("What is your salary? ");
        double salary = in.nextDouble();

        formatingOutput(name, age, size, salary);

    }

    /*
     * %s -> for string
     * %d -> for decimal integer
     * %f -> for double, %5.2f -> 5 espacios parte entera, 2 espacios parte decimal
     * Flags
     * , -> agrega separadores de miles
     * # -> siempre incluye punto decimal, asi el valor no lo tenga
     */
    private static void formatingOutput(String name, int age, double size, double salary){
        System.out.printf("Hello, %s. Next year, you will be %d, %nsize %.2f, salary %,#5.2f", name, (age+1), size, salary);

        // String formatted se puede almacenar
        String outFmt = String.format("Hello, %s. Next year, you will be %d, %nsize %.2f, salary %,#5.2f", name, (age+1), size, salary);
        String outFmt1 = "Hello, %s. Next year, you will be %d, %nsize %.2f, salary %,#5.2f".formatted(name, (age+1), size, salary);
    }

    private static void printValues(String name, int age, double size){
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, ").append(name).append(". Next year, you will be ").append(age+1)
                .append(", size: ").append(size);
        System.out.println(sb.toString());
    }
}
