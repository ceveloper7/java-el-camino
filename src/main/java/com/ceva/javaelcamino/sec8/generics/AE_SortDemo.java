/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author PC
 */
public class AE_SortDemo {
    private static void sortDemo1(){
        String[] arr = {"x","m","c","w","a","f","d"};
        System.out.println("Lista original");
        for(String el : arr){
            System.out.print(el + ", ");
        }
        System.out.println();
        
        System.out.println("Ordenamiento ascendente");
        Arrays.sort(arr);
        for(String el : arr){
            System.out.print(el + ", ");
        }
        System.out.println();
        
        
        // Ordenamiento usando Comparator
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                /**
                 * Compare() retorna un entero que significa:
                 * si retorna un int mayor a cero significa que o1 > o2
                 * si retorna un int menor a cero significa que o1 < o2
                 * si retorna un int igual a cero significa que o1 == o2
                 */
                int cmp = o1.compareTo(o2);
                // ordenamos en modo descendente. Hacemos el comportamiento al reves de la tabla arriba
                if(cmp > 0){
                    cmp = -1;
                }else if(cmp < 0){
                    cmp = 1;
                }
                return cmp;
            }
            
        });
        System.out.println("Ordenamiento descendente");
        for(String el : arr){
            System.out.print(el + ", ");
        }
        System.out.println();
    }
    
    private static void sortDemo2(){
        List<String> list = new ArrayList<>();
        list.add("x");
        list.add("m");
        list.add("c");
        list.add("w");
        list.add("a");
        list.add("f");
        list.add("d");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println();
        System.out.println("Ordenando en modo ascendente Lista usando Collections");
        for(String el : list){
            System.out.print(el + ", ");
        }
        System.out.println();
    }
    
    private static void sortDemo3(){
        List<String> list = new ArrayList<>();
        list.add("x");
        list.add("m");
        list.add("c");
        list.add("w");
        list.add("a");
        list.add("f");
        list.add("d");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int cmp = o1.compareTo(o2);
                // ordenamos en modo descendente. Hacemos el comportamiento al reves de la tabla arriba
                if(cmp > 0){
                    cmp = -1;
                }else if(cmp < 0){
                    cmp = 1;
                }
                return cmp;
            }
        });
        System.out.println();
        System.out.println("Ordenamiento en modo descendente usando Collections");
        for(String el : list){
            System.out.print(el + ", ");
        }
        System.out.println();
    }
    
    private static void sortDemo4(){
        List<String> list = new ArrayList<>();
        list.add("Archivo1.txt");
        list.add("Archivo2.txt");
        list.add("Archivo3.txt");
        list.add("Archivo4.txt");
        list.add("Archivo10.txt");
        list.add("Archivo11.txt");
        list.add("Archivo12.txt");
        list.add("Archivo20.txt");
        list.add("Archivo21.txt");
        
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for(String s : list){
            System.out.println(s);
        }
    }
    
    private static boolean isDigit(char ch){
        return (ch >= '0') && (ch <= '9');
    }
    
    // metodo para ordenar cadenas con numeros
    private static void sortDemo5(){
        List<String> list = new ArrayList<>();
        list.add("T12");
        list.add("T4");
        list.add("T32");
       
        
        list.add("Archivo 1.txt");
        list.add("Archivo 2.txt");
        list.add("Archivo 3.txt");
        list.add("Archivo 4b.txt");
        list.add("Archivo 0004a.txt");
        list.add("Archivo 10.txt");
        list.add("Archivo 11.txt");
        list.add("Archivo 12.txt");
        list.add("Archivo 20.txt");
        list.add("Archivo 21.txt");
        list.add("Archivo 21.21.txt");
        list.add("Archivo 21.2.txt");
        list.add("Archivo 21.12.txt");
        list.add("prueba.txt");
        list.add("prueba.exe");
        
            list.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int idx1 = 0;
                int idx2 = 0;
                while ((idx1 < s1.length()) && (idx2 < s2.length())) {
                    char ch1 = s1.charAt(idx1);
                    char ch2 = s2.charAt(idx2);
                    if (isDigit(ch1) && isDigit(ch2)) {
                        StringBuilder sb1 = new StringBuilder();
                        StringBuilder sb2 = new StringBuilder();
                        while (isDigit(ch1)) {
                            sb1.append(ch1);
                            idx1++;
                            if (idx1 >= s1.length()-1)
                                break;
                            ch1 = s1.charAt(idx1);
                        }
                        while (isDigit(ch2)) {
                            sb2.append(ch2);
                            idx2++;
                            if ((idx2 >= s2.length()-1)) {
                                break;
                            }
                            ch2 = s2.charAt(idx2);
                        }
                        int i1 = Integer.parseInt(sb1.toString());
                        int i2 = Integer.parseInt(sb2.toString());
                        if (i1 > i2)
                            return 1;
                        else if (i1 < i2)
                            return -1;
                        else
                            continue;
                    } else {
                        if (ch1 > ch2){
                            return 1;
                        }else if(ch1 < ch2){
                            return -1;
                        }
                            
                    }
                    idx1++;
                    idx2++;
                }
                if (s1.length() > s2.length())
                    return 1;
                else if (s1.length() < s2.length())
                    return -1;
                return 0;
            }
        });
        
        for(String s : list){
            System.out.println(s);
        }
    }
    
    public static void main(String[] args) {
        // sortDemo1();
        // sortDemo2();
        // sortDemo3();
        // sortDemo4();
        sortDemo5();
    }
}
