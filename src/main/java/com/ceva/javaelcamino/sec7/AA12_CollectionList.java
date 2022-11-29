/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author PC
 */
public class AA12_CollectionList {
    private static void listTest(){
        List list = new ArrayList<String>();
        list.add("Uno");
        list.add("Dos");
        list.add("Tres");
        list.add("Cuatro");
        list.add("Cinco");
        
        for(Object el : list){
            String s = (String)el;
            System.out.println(s);
        }
        System.out.println();
        
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
        System.out.println();
        
        for(Iterator it = list.iterator(); it.hasNext();){
            String el = (String)it.next();
            System.out.println(el);
        }
    }
    
    public static void main(String[] args) {
        listTest();
    }
}
