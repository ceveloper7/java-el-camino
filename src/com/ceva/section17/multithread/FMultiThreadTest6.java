package com.ceva.section17.multithread;

public class FMultiThreadTest6 implements Runnable {
    private int count = 0;

    public void run() {
        for (int i=0; i<100; i++) {
            /*
             * Aqui definimos un bloque sincronizado que es una alternativa a usar un metodo private synchronized void incrementCount(){}
             * el bloque sincronizado nos asegura que el codigo critico sea ejecutado por un thread a la vez
             * el parametro que recibe synchronized() representa el elemento de sincronizacion a utilizar.
             */
            synchronized(this) {
                count += 1;
                System.out.println("count = " + count);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        FMultiThreadTest6 test4 = new FMultiThreadTest6();

        Thread thread1 = new Thread(test4);
        Thread thread2 = new Thread(test4);
        thread1.start();
        thread2.start();
        try {
            /*
             * El programa ejecuta 3 Threads (Thread principal para main, thread1 y thread2)
             * join() espera hasta que un thread especifico termine su ejecucion.
             * Cuando se llama a thread1.join() el thread principal (main) se queda en pausa, el metodo join() va a finalizar hasta
             * que thread1 haya finalizado y lo mismo ara thread2
             * EN otras palabra detenemos el thread del main hasta que thread1 y thread2 finalice
             */
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }

        // esta linea de codigo se debe ejecutar cuando thread1 y thread2 hayan finalizado.
        System.out.println("Programa finalizado. count = " + test4.count);
    }
}
