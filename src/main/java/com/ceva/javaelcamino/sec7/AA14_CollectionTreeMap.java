/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author PC
 */
public class AA14_CollectionTreeMap {
    private static void treeMapTest(){
        Map map = new TreeMap();
        map.put("x", "jflbjdf");
        map.put("c", "mjspwef");
        map.put("w", "qwdfadf");
        map.put("a", "flgn485f");
        
        for(Iterator it = map.entrySet().iterator(); it.hasNext();){
            Map.Entry entries = (Map.Entry)it.next();
            System.out.println("Key: " + entries.getKey() + " Value: " + entries.getValue());
        }
    }
    
    public static void main(String[] args) {
        treeMapTest();
    }
}
