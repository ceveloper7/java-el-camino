/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.exercise.sec2;

import com.ceva.javaelcamino.util.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 */
public class AB_Statistics {
    private int inputData()throws IOException{
        System.out.println("Ingrese un numero entero ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String input = userInput.readLine();
        if(!Utils.isNumber(input)){
            System.out.println("Dato invalido");
            return 0;
        }
        return Integer.valueOf(input);
    }
    
    public int[] calcSumAndCountAllNumbersDivBy_2_Or_7(int num){
        int divBy_2_Or_7[] = new int[num];
        int index = 0;
        for(int i = 2; i < num; i=i+2 ){
            divBy_2_Or_7[index] = i;
            index += 1;
        }
        
        for(int j = 7; j < num; j = j + 7){
            divBy_2_Or_7[index] = j;
            index += 1;
        }
        
        int result[] = new int[index];
        for(int k = 0; k < divBy_2_Or_7.length; k++){
            if(divBy_2_Or_7[k] > 0){
                result[k] = divBy_2_Or_7[k];
            }
        }
        Arrays.sort(result);
        return result;
    }
    
    public void process()throws IOException{
        int input = inputData();
        int[] rpta = calcSumAndCountAllNumbersDivBy_2_Or_7(input);
        outputResult(rpta);
    }
    
    private void outputResult(int[] result){
        int count = 0;
        int suma = 0;
        int test;
        int index = 0;
        
        for(int k = 0; k < result.length; k++){
            System.out.print(result[k] + " ");
        }
        System.out.println();
        
        for(int i = 0; i < result.length; i++){
            
        }
        
        System.out.println("Numero de divisores " + count);
        System.out.println("Suma de divisores " + suma);
    }
    
    public static void main(String[] args)throws IOException{
        AB_Statistics test = new AB_Statistics();
        test.process();
    }
}
