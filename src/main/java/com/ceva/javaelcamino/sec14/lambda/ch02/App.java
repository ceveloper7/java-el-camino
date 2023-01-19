/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author PC
 */
public class App {
    public List<Apple> filter(List<Apple>inventory, AppleWeightPredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED));
        
        App app = new App();
        List<Apple> heavyApples = app.filter(inventory, new AppleWeightPredicate());
        
        heavyApples.stream()
                .forEach(System.out::println);
    }
}
