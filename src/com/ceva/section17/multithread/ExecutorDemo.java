package com.ceva.section17.multithread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorDemo {
    private static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        // creamos un pool de thread de tamano 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int n = 0; n < 9; n++){
            // agendamos 9 thread en el executor
            executor.execute(()->{
                int nThread = count.incrementAndGet();
                // cada thread cuenta 3 veces, y en cada cuenta, se tarda 1 segundo
                for(int i = 0; i < 3; i++){
                    System.out.println("Thread " + nThread + ": " + i);
                    try{
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException ex){}
                }
            });
        }

        // finalizamos el servicio
        executor.shutdown();
    }

}
