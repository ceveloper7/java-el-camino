/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

/**
 *
 * @author PC
 */
public class AF_MultiThread implements Runnable{
     private int count = 0;
     
     /**
      * la palabra reservada synchronized hace garantizar que solamente 1 thread a la vez lo puede ejecutar
      * en caso que otro hilo intente ingresar al metodo cuando este se encuentra en ejecucion, este otro
      * hilo esperara hasta que el bloque synchronized finalice
      * el termino sincronizado no esta relacionado al metodo sino al objeto, es decir, si tenemos dos objetos
      * A y B de la misma clase e invocamos la mismo metoso sincronizado otro hilo no puede tener acceso
      * al metodo del objeto A pero si al metodo del objeto B (si no esta ocupado)
      */
     /*private synchronized void incCount(){
         count += 1;
     }*/
    
    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            // todo lo que esta dentro del bloque synchronized es codigo que se ejecutara por un hilo a la vez
            // con esto se elimina la posibilidad de que count sea alterado mientras lo incrementamos
            synchronized (this) {
                count += 1;
                System.out.println("Count = " + count);   
            }
            try{
                Thread.sleep(10);
            }
            catch(InterruptedException ex){}
        }
    }
    
    public static void main(String[] args) {
        AF_MultiThread test = new AF_MultiThread();
        
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
