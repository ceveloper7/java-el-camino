package com.ceva.section17.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {
    private static Object lock = new Object();
    private static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        // reunimos a 4 thread antes que todos comiencen
        CyclicBarrier barrier = new CyclicBarrier(4, ()->{
            // si el thread que llama a await es el ultimo que dispara la accion, entonces
            // este tread ejecuta el runnable y luego salen los 4 thread acumulados
            // cada 4 elementos hacemos que se incremente
            count.incrementAndGet();
            synchronized (lock){
                lock.notify();
            }
        });
        int nThread = 1;
        for(int n = 0; n < 12; n++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " listo...");
                // queremos saber a que contador pertenece este thread
                int nGroup = count.get();
                try{
                    // detener a los threads, hasta que se junte los 4 threads indicados
                    barrier.await();
                }
                catch (InterruptedException e){}
                catch (BrokenBarrierException e){
                    System.out.println("Broken Barrier Exception " + Thread.currentThread().getName());
                }
                // el threa ya arranco
                System.out.println("Thread " + Thread.currentThread().getName() + " ha iniciado. Group: " + nGroup);
            }, "T " + (nThread++)).start();
            // si el barrier es de 4, entonces cada 4 elementos hacemos una pausa
            if(((n+1) % 4) == 0){
                // significa que ya iniciamos 4 threads
                synchronized (lock){
                    // nos ponemos en estado wait, hasta q nos despierte el runnable
                    try{
                        lock.wait();
                    }
                    catch (InterruptedException e){}
                }
            }


            // hacemos una pausa
//            try{
//                Thread.sleep(1000);
//            }
//            catch (InterruptedException e){}
        }
    }
}
