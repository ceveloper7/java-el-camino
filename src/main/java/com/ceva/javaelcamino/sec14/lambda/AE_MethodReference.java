/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda;

/**
 *
 * @author PC
 */
public class AE_MethodReference {
    private interface Operation{
        public int execute(int a, int b);
    }
    
    // semanticamente cada metodo (add, sub, mul, div) es igual al metodo execute declarado en la interface
    private int add(int a, int b){
        return a+b;
    }
    
    private int sub(int a, int b){
        return a - b;
    }
    
    private int mul(int a, int b){
        return a * b;
    }
    
    private static int div(int a, int b){
        return a / b;
    }
    
    public static void main(String[] args) {
        AE_MethodReference ref = new AE_MethodReference();
        // creamos un array con referencias a los metodos
        Operation arr[] = {
            // los metodo al no ser estaticos, para ser invocados necesitan la referencia al objeto sobre el cual
            // trabajan. ref:: es la referencia al objeto que sera utilizado para la invocacion del metodo
            ref::add,
            ref::sub,
            ref:: mul,
            AE_MethodReference::div
        };
        for(Operation o : arr){
            System.out.println(o.execute(4, 2));
        }
    }
}
