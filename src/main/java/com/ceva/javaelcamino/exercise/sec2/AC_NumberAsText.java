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
 * @author PC
 */
public class AC_NumberAsText {
    private static final String[] numbers = {
        "UNO ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE"};
    
    private String inputData() throws IOException {
        System.out.println("Ingrese un numero entero ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String input = userInput.readLine();
        if (!Utils.isNumber(input)) {
            System.out.println("Dato invalido");
        }
        return String.valueOf(input);
    }
    
    private String numberAsText(String input){
        String text = "";
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            if(!Utils.isDigit(ch)){
                break;
            }
            int value = Integer.valueOf(String.valueOf(ch));
            if(value == 0){
                text += "CERO ";
            }
            else{
                text += numbers[value-1];
            }
        }
        return text;
    }
    
    private void output(String str){
        System.out.println("Numeros en letras: " + str);
    }
    
    private void perform()throws IOException{
        String input = inputData();
        String result = numberAsText(input);
        output(result);
    }
    
    public static void main(String[] args) throws IOException{
        AC_NumberAsText test = new AC_NumberAsText();
        test.perform();
    }
}
