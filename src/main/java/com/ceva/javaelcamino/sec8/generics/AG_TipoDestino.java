/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PC
 */
public class AG_TipoDestino {
    public static <T> List<T> loadData(){
        return new LinkedList<>();
    }
    
    public static void main(String[] args) {
        // vieja forma
        List<String> data = AG_TipoDestino.<String>loadData();
        // since jdk7
        List<String> data1 = loadData(); // el compilador asume que el tipo de dato de loadData es String
    }
}
