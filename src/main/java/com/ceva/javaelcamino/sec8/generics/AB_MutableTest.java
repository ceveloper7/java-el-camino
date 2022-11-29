/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

/**
 *
 * @author PC
 */
public class AB_MutableTest {
    private static boolean someMethod(AA_MutableObject result){
        result.set("Hola mundo");
        return true;
    }
    
    public static void main(String[] args) {
        String myResult = null;
        AA_MutableObject byRefArg = new AA_MutableObject();
        // pasamos el objeo byRefArg a someMethod() que lo va a modificar
        if(someMethod(byRefArg) == true){
            // con get() obtenemos el resultado del objeto modificado
            myResult = (String)byRefArg.get();
        }
        System.out.println(myResult);
    }
}
