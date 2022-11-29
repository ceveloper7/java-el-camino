/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author PC
 */
public class AF_GenericsMethod {
    /**
     * Funcion que copia los elementos de un Set a un List
     * <T> parametro de tipo T que es usado en el resto de la definicion del metodo
     */
    public static <T> List<T> copySetToList(Set<T> set, List<T> list){
        list.clear();
        for(T element : set){
            list.add(element);
        }
        return list;
    }
    
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("uno");
        set.add("dos");
        set.add("tres");
        
        List<String> list = new LinkedList<>();
        list = AF_GenericsMethod.<String>copySetToList(set, list);
        for(String s : list){
            System.out.println(s);
        }
    }
}
