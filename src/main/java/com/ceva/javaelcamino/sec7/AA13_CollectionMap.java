/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author PC
 */
public class AA13_CollectionMap {
    private static void hashMapTest(){
        Map map = new HashMap();
        // el autoboxing permite que el numero 1 se convierta en tipo Integer
        map.put(new Integer(1), "Uno");
        map.put(2, "Dos");
        map.put(3, "Tres");
        map.put(4, "Cuatro");
        map.put(5, "Cinco");
        map.put(6, "Seis");
        
        // impresion de elementos impares
        for(int n = 1; n < 6; n+=2){
            System.out.println(map.get(n));
        }
        System.out.println();
        
        // recorriendo los indices del diccionario
        for(Iterator it = map.keySet().iterator(); it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println();
        
        // recorriendo los indices y obteniendo unicamente el valor
        for(Iterator it = map.keySet().iterator(); it.hasNext();){
            System.out.println(map.get(it.next()));
        }
        System.out.println();
        
        // recorriendo pares indice-valor del diccionario (mas eficiente)
        for(Iterator it = map.entrySet().iterator(); it.hasNext();){
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println("Key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        hashMapTest();
    }
}
