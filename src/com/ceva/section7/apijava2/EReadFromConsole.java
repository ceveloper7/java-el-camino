package com.ceva.section7.apijava2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EReadFromConsole {
    private void readFromConsole() throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            System.out.print("Enter your name: ");

            String name = br.readLine();

            System.out.println("Hi " + name + ", welcome to Java!");
        }

    }

    private void readFromConsoleUsingScannerClass() throws IOException{
        System.out.print("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Hi " + name + ", welcome to Java!");
    }

    private void readArgumentsFromConsole(String[] args) throws IOException{
        if (args.length > 0) {
            System.out.println("Los argumentos recibidos son ");
            for (String arg : args) {
                System.out.println(arg);
            }
        }else {
            System.out.println("No argumentos recibidos");
        }
    }

    public static void main(String[] args)throws IOException {
        EReadFromConsole reader = new EReadFromConsole();
        reader.readArgumentsFromConsole(new String[]{"-help", "-version"});
        // reader.readFromConsoleUsingScannerClass();
        //reader.readFromConsole();
    }
}
