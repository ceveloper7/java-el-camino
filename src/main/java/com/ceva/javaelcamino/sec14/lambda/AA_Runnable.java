/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda;

/**
 *
 * @author PC
 */
public class AA_Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("Hello world"));
        t.start();
    }
}
