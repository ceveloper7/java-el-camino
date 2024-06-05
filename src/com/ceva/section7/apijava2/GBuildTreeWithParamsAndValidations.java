package com.ceva.section7.apijava2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GBuildTreeWithParamsAndValidations {
    private static final int DOUBLE_TREE_LENGHT = 2;
    public static void printTree(int size) {
        int n = 1;
        while (n < size) {
            for (int i = 0; i < (size / 2) - (n / 2); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n; i++) {
                System.out.print("*");
            }
            System.out.println();
            n += 2;
        }
        for (n = 0; n < 2; n++) {
            for (int i = 0; i < (size / 2) - 1; i++) {
                System.out.print(" ");
            }
            System.out.println("| |");
        }
    }

    // metodo independiente valida que el dato ingresado por el usuario sea numero.
    private static int readInt(BufferedReader br, String onErrorMessage) throws IOException {
        while (true) {
            String str = br.readLine();
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println(onErrorMessage);
            }
        }
    }

    private void processv1()throws IOException{
        boolean validData = false;
        int size = 1;
        do {
            System.out.print("Niveles del pino: ");

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();

                size = Integer.parseInt(str) * DOUBLE_TREE_LENGHT;
                validData = true;
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Vuelta a ingresar un numero entero valido...");
            }
        } while (!validData);

        printTree(size);
    }

    private void processv2()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Niveles del pino: ");
        int size = readInt(br, "Numero invalido. Vuelta a ingresar un numero entero valido...");
        printTree(size * DOUBLE_TREE_LENGHT);
        br.close();
    }

    public static void main(String[] args) throws IOException {
        GBuildTreeWithParamsAndValidations t = new GBuildTreeWithParamsAndValidations();
        t.processv2();
    }
}
