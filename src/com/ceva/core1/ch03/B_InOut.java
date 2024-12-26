package com.ceva.core1.ch03;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

public class B_InOut {
    private static Scanner in = new Scanner(System.in);
    private static final String PATH = "c:\\java-development\\jobs.txt";

    public static void main(String[] args) throws Exception {
        fileInOut();
    }

    private static void fileInOut()throws IOException {
        // 1. Read a file from Scanner class. Antes de Java 18, es necesario indicar el encoding explicitamente.
        Scanner inputFile = new Scanner(Path.of(PATH), StandardCharsets.UTF_8);

        // 2. Write to a file. Cuando no se indica ruta, el archivo se crea segun valor System.getProperty("user.dir")
        PrintWriter outputFile = new PrintWriter("Test.txt");

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
        System.out.printf(Locale.US ,"Hello, %s. Next year, you will be %d, %nsize %.2f, salary %,#5.2f", name, (age+1), size, salary);

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
