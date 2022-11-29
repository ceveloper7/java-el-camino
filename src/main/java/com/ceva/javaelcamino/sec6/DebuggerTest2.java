/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec6;

/**
 *
 * @author PC
 */
public class DebuggerTest2 {
    public static void print(int a, int b){
        System.out.printf("%d %d\n", a, b);
    }
    
    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                print(i, j);
            }
        }
    }
}
