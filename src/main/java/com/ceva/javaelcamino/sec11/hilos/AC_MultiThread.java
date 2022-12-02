/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

/**
 * Tercera forma de implementar hilos: por medio de una clase anonima que se pasa como argumento
 * al constructor del thread
 */
public class AC_MultiThread {
    public static void main(String[] args) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    System.out.println("Prueba " + i);
                }
            }
        });
        hilo.start();
        System.out.println("Programa finalizado");
    }
}
