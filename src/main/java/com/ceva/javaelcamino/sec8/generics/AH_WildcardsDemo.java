/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec8.generics;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author PC
 */
public class AH_WildcardsDemo {
    // podemos pasar como parametro una clase Number y todas sus clases derivadas
    public static void process(List<? extends Number> list){}
    
    // Object -> Number -> Integer
    // recibimos como parametro un objeto de tipo List<Integer>, List<Number> o List<Object>
    // es decir cualquier super tipo de Integer
    public static void process2(List<? super Integer> list){}
    
    public static void processTest(){
        List<Integer> list = new LinkedList<>();
        process(list);
    }
    
    private static <T> Set<T> helpConverter(List<T> src){
        Set<T> res = new HashSet<>();
        res.addAll(src);
        return res;
    }
    
    // metodo convierte un objeto tipo List a Set
    // como queremos que funcione con cualquier tipo de List usamos un comodin
    public static Set<?> convertFromListToSet(List<?> src){
        return helpConverter(src);
    }
    
    // metodo utilizando comodin puro (?)
    public static int nullCount(Collection<?> list){
        if(list == null)
            return 0;
        int count = 0;
        for(Object o : list){
            if(o == null)
                count++;
        }
        return count;
    }
    
    public static void nullCountTest(){
        List<Integer> list = new LinkedList<>();
        int n = nullCount(list);
    }
    
    public static void main(String[] args) {
        
    }
}
