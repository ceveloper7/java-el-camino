/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author PC
 */
public class CustomTreeWithValidationV2 {
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
            System.out.println("|/|");
        }
    }
    
    private static int readUserInput(BufferedReader br, String message, String onErrorMessage) throws IOException{
        while(true){
            System.out.print(message);
            String str = br.readLine();
            try{
                return Integer.parseInt(str);
            }catch(NumberFormatException ex){
                System.out.println(onErrorMessage);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // contrala si el dato ingresado por el usuario es valido
        boolean validData = false;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int size = readUserInput(br, "Niveles del pino... ", "Ingrese un numero entero valido") * 2;
        printTree(size);
        br.close();
    }
}
