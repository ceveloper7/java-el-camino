/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.exercise.sec2;

import com.ceva.javaelcamino.util.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class AD_PerfectNumbers {
    private int inputData()throws IOException{
        System.out.println("Ingrese un valor entero ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String input = userInput.readLine();
        if(!Utils.isNumber(input)){
            System.out.println("valor ingresado incorrecto");
        }
        return Integer.valueOf(input);
    }
    
    private List<Integer> calcPerfectNumber(int input){
        List<Integer> divisores = new ArrayList<>();
        
        if(input == 0){
            System.exit(0);
        }
        
        if(!Utils.isNumber(String.valueOf(input))){
            System.exit(0);
        }
        
        for(int i = 1; i < input; i++){
            
            if(input % i == 0){
                divisores.add(i);
            }
        }
        
        return divisores;
    }
    
    private void printResult(List<Integer> values){
        int suma = 0;
        for(int i : values){
            suma += i;
            System.out.print(i + " ");
        }
        System.out.print(" = " + suma);
        System.out.println();
    }
    
    private void perform()throws IOException{
        int input = inputData();
        printResult(calcPerfectNumber(input));
    }
    
    public static void main(String[] args)throws IOException {
        AD_PerfectNumbers test = new AD_PerfectNumbers();
        test.perform();
    }
}
