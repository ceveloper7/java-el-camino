/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

/**
 *
 * @author PC
 */
public class AC_MutableObject<T> {
    private T value;
    
    public AC_MutableObject(){}
    
    public AC_MutableObject(T value){
        this.value = value;
    }
    
    public void set(T value){
        this.value = value;
    }
    
    public T get(){
        return value;
    }
}
