/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.util;

/**
 *
 * @author PC
 */
public class Utils {
    private static boolean isDigit(char ch){
        return (ch > '0') && (ch <= '9');
    }
    
    public static boolean isNumber(String str){
        int posicionPuntoDecimal = -1;
        int numeroDigitos = 0;
        
        for(int n = 0; n < str.length(); n++){
            char ch = str.charAt(n);
            if(ch == '.'){
                if((posicionPuntoDecimal != -1) && (n > str.length()-1)){
                    return false;
                }
                posicionPuntoDecimal = n;
            }else if(ch == '-'){
                if(n != 0){
                    return false;
                }
            }else if(!isDigit(ch)){
                return false;
            }else{
                numeroDigitos++;
            }
        }
        return numeroDigitos > 0;
    }
}
