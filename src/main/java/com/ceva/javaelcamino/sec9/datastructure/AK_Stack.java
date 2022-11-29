/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

/**
 *
 * @author PC
 */
public class AK_Stack<T> {
    // definimos los datos del stack
    private Object data[];
    private int sp;

    public AK_Stack(int size) {
        data = new Object[size];
        // sp es -1 porque si fuera 0 significa que estoy en el primer elemento del stack
        sp = -1;
    }
    
    public void push(T element){
        if(sp >= (data.length -1)){
            throw new StackOverflowError();
        }
        
        // incrementamos el stack pointer sp
        sp++;
        data[sp] =  element;
    }
    
    // retornamos el elemento al que apunta el stack pointer
    public T pop(){
        // si es menor a 0 esta vacio el stack
        if(sp < 0){
            throw new IllegalStateException("Empty stack");
        }
        T result = (T)data[sp];
        data[sp] = null; // evitamos fuga de memorias
        sp--;
        return result;
    }
    
    // retornamos el elemento donde se encuentra el stack point
    public T peek(){
        if(sp < 0){
            throw new IllegalStateException("Empty stack");
        }
        return (T) data[sp];
    }
    
    public int size(){
        return sp+1;
    }

    @Override
    public String toString() {
        if(size() == 0){
            return "<< Empty >>";
        }
        StringBuilder sb = new StringBuilder();
        for(int n = 0; n <= sp; n++){
            if(sb.length() > 0){
                sb.append(" ");
            }
            sb.append(data[n]);
        }
        return sb.toString();
    }
    
    
    
    public static void main(String[] args) {
        AK_Stack<Integer> stack = new AK_Stack<>(5);
        for(int i = 0; i < 5; i++){
            stack.push(i);
            System.out.print(i + " ");
        }
        System.out.println();
        // vaciamos el stack
        while(stack.size()> 0){
            int i = stack.pop();
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
