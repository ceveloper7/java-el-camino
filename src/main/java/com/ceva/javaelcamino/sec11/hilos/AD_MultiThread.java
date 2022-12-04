/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

/**
 *
 * @author PC
 */
public class AD_MultiThread implements Runnable{
    
    private int count = 0;
    
    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            // seccion critica porque solo puede ser ejecutada por 1 hilo a la vez
            // para garantizar que no se presente un acceso simultaneo
            count += 1;
            System.out.println("Count = " + count);
            try{
                Thread.sleep(10);
            }
            catch(InterruptedException ex){}
        }
    }
    
    public static void main(String[] args) {
        AD_MultiThread test = new AD_MultiThread();
        
        Thread hilo1 = new Thread(test);
        Thread hilo2 = new Thread(test);
        
        hilo1.start();
        hilo2.start();
        
        System.out.println("Programa finalizados. Count = " + test.count);
    }
}