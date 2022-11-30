/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec10.recursividad;

/**
 *
 * @author PC
 */
public class AG_NumberToLetters {
    private static final String[] table = new String[]{
        "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez",
        "once", "doce", "trece", "catroce", "quince", "dieciseis", "diecisiete", "dieciocho", "diecinueve", "veinte"
    };
  
    private static final String[] decena = new String[]{
        "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa", "cien"
    };
    
    private static final String[] centena = new String[]{
        "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setencientos",
        "ochocientos", "novecientos"
    };
    
    public static String convertNumberToLetters(int number){
        if(number < 0)
            return "menos " + convertNumberToLetters(-number);
        if(number == 0)
            return "cero";
        
        if(number <= 20)
            return table[number-1];
        else if(number <= 29)
            return "veinti" + convertNumberToLetters(number % 10);
        else if(number <= 100){
            // obtenemos la palabra en decenas
            int d = number / 10;
            String s = decena[d - 1];
            // si el residuo del numero es mayor a 0
            if((number % 10) > 0)
                s += " y " + convertNumberToLetters(number % 10);
            return s;
        }else if(number < 1_000){
            int a = number / 100;
            int b = number % 100;
            String s = centena[a - 1];
            if(b > 0){
                s += " " + convertNumberToLetters(b);
            }
            return s;
        }else if(number < 1_000_000){
            int a = number / 1000;
            int b = number % 1000;
            String s = "";
            if(a > 1){
                String ss = convertNumberToLetters(a);
                if(((a % 10) == 1) && (a > 11))
                    s = ss.substring(0, ss.length() - 1);
                s += ss + " mil";
            }else{
                s += "mil";
            }
            if(b > 0){
                s += " " + convertNumberToLetters(b);
            }
            return s;
        }else{
            int a = number / 1_000_000;
            int b = number % 1_000_000;
            String s = convertNumberToLetters(a);
            if(((a % 10) == 1 ) && (a != 11) && ((a % 100) != 11)){
                s = s.substring(0, s.length() - 1);
            }
            s += " millon";
            if(a > 1)
                s += "es";
            if(b > 0)
                s += " " + convertNumberToLetters(b);
            return s;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(convertNumberToLetters(Integer.MAX_VALUE));
    }
}
