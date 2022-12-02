/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

/**
 * Segunda forma de crear hilos, extendiendo de la clase Thread
 */
public class AB_MultiThread extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            System.out.println("Prueba " + i);
        }
    }
    
    public static void main(String[] args) {
        Thread hilo = new AB_MultiThread();
        hilo.start();
        System.out.println("Programa finalizado");
    }
}
