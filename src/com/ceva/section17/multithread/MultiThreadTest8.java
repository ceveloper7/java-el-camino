package com.ceva.section17.multithread;

public class MultiThreadTest8 {
    private static int count = 0;
    // variable que funciona como objeto de sincronizacion
    private static Object lock = new Object();

    public static void main(String[] args) {
        MultiThreadTest8 test4 = new MultiThreadTest8();

        /**
         * a cada thread pasamos una instancia del objeto Runnable
         * ambos thread trabajan con una instancia distinta de Runnable por lo que cada thread
         * trabaja con su propio lock. Para lograra la sincronizacion necesitamos utilizar el
         * mismo lock o elemento de sincronizacion en ambos thread
         */
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++) {
                    // en el bloque sincronizado incrementamos la variable de clase count
                    // this -> representa la instancia de Runnable, la cambiamos por el elemento
                    // de sincronizacion (lock): synchronized(lock)
                    synchronized(lock) {
                        count += 1;
                        System.out.println("count = " + count);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++) {
                    synchronized(lock) {
                        count += 1;
                        System.out.println("count = " + count);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        // iniciamos los thread
        thread1.start();
        thread2.start();
        try {
            // esperamos que los thread terminen
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Programa finalizado. count = " + test4.count);
    }
}
