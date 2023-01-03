/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PC
 */
public class AG_MethodReference {
    interface Factory<T>{
        List<T> get();
    }
    
    /**
     * 
     * @param <T>
     * @param arr
     * @param factory interface
     * @return 
     */
    private static <T> List<T> createList(T arr[], Factory factory){
        // equivale a decir Lisy<T> result = new ArrayList<>()
        List<T> result = factory.get();
        for(T element : arr){
            // copiamos los elementos de array a la coleccion
            result.add(element);
        }
        return result;
    }
    
    public static void main(String[] args) {
        String arr[] = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez"};
        // createList nos retorna una coleccion de tipo List, indicamos el tpo de coleccion
        List<String> list = createList(arr, ArrayList::new);
        System.out.println(list.getClass() + " : " + list);
        
        list = createList(arr, LinkedList::new);
        System.out.println(list.getClass() + " : " + list);
    }
}
