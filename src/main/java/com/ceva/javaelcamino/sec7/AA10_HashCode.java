/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

/**
 *
 * @author PC
 */
public class AA10_HashCode {
    private static int stringHashCode(String s){
        int result = 1;
        for(int n = 0; n < s.length(); n++){
            result = result * (result + s.charAt(n));
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("Hola = " + stringHashCode("Hola")); // -1030524192
        System.out.println("aloH = " + stringHashCode("aloH")); // 1299313968
    }
}
