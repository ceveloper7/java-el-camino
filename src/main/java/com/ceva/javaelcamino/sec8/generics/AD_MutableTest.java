/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

/**
 *
 * @author PC
 */
public class AD_MutableTest {
    public static boolean someMethod(AC_MutableObject<String> result){
        result.set("Hello world");
        return true;
    }
    
    public static void main(String[] args) {
        String myResult = null;
        
        AC_MutableObject<String> byRefArg = new AC_MutableObject<String>();
        if(someMethod(byRefArg) == true){
            myResult = byRefArg.get();
        }
        System.out.println(myResult);
    }
}
