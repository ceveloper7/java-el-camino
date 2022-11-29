/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author PC
 */
public class AA9_Collections {
    static class Person{
        int primaryKey;
        String name;
        int age;

        @Override
        public boolean equals(Object o2) {
            Person p2 = (Person)o2;
            // si las llaves primarias son diferentes de 0 significa que ambos objetos estan en la BD
            // por lo que solo basta comparar sus llaves primarias para saber si son iguales
            if((primaryKey != 0) && (p2.primaryKey != 0)){
                return primaryKey == p2.primaryKey;
            }
            // Si almenos uno de los objetos tiene primaryKey igual a 0 entonces comparamos el resto de sus campos
            // comparamos el nombre y la edad para determinar si son iguales
            // el punto aqui es que somos nosotros los que debemos determinar de que manera dos objetos van a ser iguales
            // que campos necesitan ser comparados y cuales no
            if((name.equals(p2.name)) && (age == p2.age)){
                return true;
            }
            return false;
        }
    }
    
    private static void testSet(){
        Set s = new HashSet();
        s.add("uno");
        s.add("dos");
        s.add("tres");
        s.add("dos");
        s.add("uno");
        System.out.println("s.size() = " + s.size());
    }
    
    private static void equalsTest(){
        Object i1 = new Integer(1);
        Object i2 = new Integer(1);
        
        System.out.println("i1 es igual a i2? " + i1.equals(i2));
    }
    
    private static void personTest(){
        // p1 imaginamos que es un instancia recuperada desde una BD
        Person p1 = new Person();
        p1.primaryKey = 100;
        p1.name = "Raul";
        p1.age = 21;
        
        // p2 imaginamos que es una instancia que queremos almacenar en la BD
        Person p2 = new Person();
        p2.primaryKey = 0;
        p2.name = "Raul";
        p2.age = 21;   
    }
    
    public static void main(String[] args) {
        testSet();
        equalsTest();
    }
}
