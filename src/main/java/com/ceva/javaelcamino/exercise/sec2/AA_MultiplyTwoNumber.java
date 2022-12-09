/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.exercise.sec2;

import com.ceva.javaelcamino.util.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */
public class AA_MultiplyTwoNumber {
    private static void calc(int num1, int num2){
        int resultado;
        resultado = ((num1 * num2 / 2) % 7);
        System.out.println("El resultado es: " + resultado);
    }
    
    public static void main(String[] args)throws IOException {
        System.out.println("Ingrese el primer numero ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String input1 = userInput.readLine();
        
        System.out.println("Ingrese el segundo numero ");
        String input2 = userInput.readLine();
        
        if((Utils.isNumber(input1) && (Utils.isNumber(input2)))){
            calc(Integer.valueOf(input1), Integer.valueOf(input2));   
        }
    }
}
