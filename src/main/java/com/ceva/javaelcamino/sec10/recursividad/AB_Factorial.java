/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec10.recursividad;

/**
 * el factorial de 4 es : 1 * 2 * 3 * 4 = 24
 * @author PC
 */
public class AB_Factorial {
    // version no recursiva para obtener el factoria
    public static long factorial0(long n){
        long res = 1;
        for(long x = 1; x <= n; x++){
            res = res * x;
        }
        return res;
    }
    
    // version recursiva para obtener el factorial
    public static long factorial(long n){
        if(n <= 1L){
            return 1L;
        }
        return n * factorial(n-1);
    }
    
    public static void main(String[] args) {
        for(long n = 0; n <= 20; n++){
            System.out.printf("%d! = %d\n", n, factorial0(n));
        }
        System.out.println();
        
        for(long n = 0; n <= 20; n++){
            System.out.printf("%d! = %d\n", n, factorial(n));
        }
        System.out.println();
    }
}
