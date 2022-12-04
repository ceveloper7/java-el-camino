/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

/**
 *
 * @author PC
 */
public class AE_MultiThread implements Runnable{
    
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
        AE_MultiThread test = new AE_MultiThread();
        
        Thread hilo1 = new Thread(test);
        Thread hilo2 = new Thread(test);
        
        hilo1.start();
        hilo2.start();
        
        // es necesario que ete mensaje se imprima cuando ambos hilos hayn terminado
        // Thread.join() espera a que un Hilo especifico finalice su ejecucion
        try{
            // El hilo principal se detiene, el metodo join() terminal cuando hilo1 haya finalizado
            // en otras palabras, detenemos la ejecucion del metodo main hasta que los hilos finalicen
            hilo1.join();
            hilo2.join();
        }
        catch(InterruptedException ex){}
        
        System.out.println("Programa finalizados. Count = " + test.count);
    }
}