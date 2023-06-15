package com.ceva.section17.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problema:
 * En una oficina hay 2 impresoras disponibles pero 4 personas necesitan imprimir. Cada persona
 * va a imprimir 3 documentos.
 * Por medio de un semaforo vamos a coordinar el uso de las impresoras entre todos los usuarios
 */
public class SemaphoreDemo {
    // para identificar el nro de thread
    private static AtomicInteger count = new AtomicInteger();
    // simulamos la impresora. arreglo 2 elementos, si es true la impresora esta ocupada
    boolean printer[] = new boolean[2];

    // indica que impresora podemos obtener
    // el semaforo se encarga de la concurrencia
    private synchronized int getPrinter(){
        if(!printer[0]){
            printer[0] = true;
            return 1;
        }else{
            printer[1] = true;
            return 2;
        }
    }

    // liberamos la impresora
    private synchronized void releasePrinter(int nPrinter){
        printer[nPrinter-1] = false;
    }
    private void test(){
        // inicializamos el semaforo con dos permisos disponibles y true
        // para que permite el acceso a los permisos de manera mas justa
        Semaphore semaphore = new Semaphore(2, true);
        // creamos un thread para cada empleado
        for(int i = 0; i < 4; i++){
            new Thread(()->{
                int nThread = count.incrementAndGet();
                // cada empleado (iteracion) imprime 3 documentos
                for(int n = 0; n < 3; n++){
                    try{
                        semaphore.acquire();
                    }
                    catch(InterruptedException ex){}
                   try{
                       // ya que tenemos el permiso, obtenemos la impresora
                       int nPrinter = getPrinter();
                       System.out.println("Thread " + nThread + " , usando impresora " + nPrinter + " , documento " + n);
                       try{
                           // retornamos un valor entre 500 y 1000
                           Thread.sleep(500 + (int)(Math.random() * 500));
                       }
                       catch (InterruptedException e){}
                       releasePrinter(nPrinter);
                   }// no importa lo que pase dentro del try, simpre se va a liberar el semaphore
                   finally {
                       semaphore.release();
                   }
                }
            }).start();
        }
    }
    public static void main(String[] args) {
        new SemaphoreDemo().test();
    }
}
