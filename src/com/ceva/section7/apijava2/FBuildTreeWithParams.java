package com.ceva.section7.apijava2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FBuildTreeWithParams {
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

    public static void main(String[] args) throws IOException {
        System.out.print("Niveles del pino: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();

        int size = Integer.parseInt(str) * 2;
        printTree(size);
    }
}
