/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

/**
 * Clase mutable que permite pasar argumentos por referencia
 * Por ejemplo: necesitamos un metodo que realiza un proceso y como resultado genera un objeto  
 * con el resultado pero tambien necesitamos que el metodo retorne un boolean para saber si se ejecuto
 * con exito, como un metodo solo retorna un valor con mutable objeto podemos retorna la info que estamos
 * constuyendo.
 * @author PC
 */
public class AA_MutableObject {
    private Object value;
    
    public void set(Object value){
        this.value = value;
    }
    
    public Object get(){
        return value;
    }
}
