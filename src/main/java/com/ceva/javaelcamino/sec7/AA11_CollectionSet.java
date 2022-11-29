/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author PC
 */
public class AA11_CollectionSet {
    private static void iteratorTest(){
        Set s = new HashSet();
        s.add("uno");
        s.add("dos");
        s.add("tres");
        s.add("cuatro");
        s.add("cinco");
        
        for(Iterator it = s.iterator(); it.hasNext();){
            String el = (String)it.next();
            System.out.println(el);
        }
    }
    
    private static void linkedHashSetTest(){
        Set s = new LinkedHashSet();
        s.add("uno");
        s.add("dos");
        s.add("tres");
        s.add("cuatro");
        s.add("cinco");
        
        for(Iterator it = s.iterator(); it.hasNext();){
            String el = (String)it.next();
            System.out.println(el);
        }
    }
    
    private static void treeSetTest(){
        Set s = new TreeSet();
        s.add("uno");
        s.add("dos");
        s.add("tres");
        s.add("cuatro");
        s.add("cinco");
        
        for(Iterator it = s.iterator(); it.hasNext();){
            String el = (String)it.next();
            System.out.println(el);
        }
    }
    
    public static void main(String[] args) {
        treeSetTest(); // al tratarse se String empieza en orden alfabetico por lo que inicia con la c, d, t, u
    }
}
