/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec10.recursividad;

/**
 *
 * @author PC
 */
public class AA_RecursiveTest {
    
    public static void recursiveCounter(int n){
        if(n < 0){
            return;
        }
        
        recursiveCounter(n - 1);
        System.out.println(n);
    }
    
    public static void main(String[] args) {
        recursiveCounter(10);
    }
}
