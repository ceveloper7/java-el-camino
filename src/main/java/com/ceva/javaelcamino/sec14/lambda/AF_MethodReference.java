/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda;

import java.util.Arrays;

/**
 *
 * @author PC
 */
public class AF_MethodReference {
    public static void main(String[] args) {
        String arr[] = {"c", "D", "x", "W", "A", "a", "b", "e", "F"};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(arr));
    }
        
}
