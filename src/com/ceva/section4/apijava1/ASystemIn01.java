package com.ceva.section4.apijava1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ASystemIn01 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ingrese su nombre: ");
        String name = br.readLine();

        System.out.println("Saludos " + name + "!");
    }
}
