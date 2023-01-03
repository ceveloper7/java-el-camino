/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda;

/**
 *
 * @author PC
 */
public class AD_Scope {
    int x = 0;
    
    private interface Eval{
        public void evaluate(int x);
    }
    
    private void test(){
        x = 1;
        System.out.println("x = " + x);
        
        Eval e = (t) -> {
            t++;
            System.out.println("t = " + t);
            x++;
        };
        e.evaluate(x);
        System.out.println("x = " + x);
    }
    
    public static void main(String[] args) {
        new AD_Scope().test();
    }
}
