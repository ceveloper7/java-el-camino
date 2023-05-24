package com.ceva.section17.multithread;

public class MultiThreadTest7 {
    private static int count = 0;

    public static void main(String[] args) {
        MultiThreadTest7 test4 = new MultiThreadTest7();

        /**
         * a cada thread pasamos una instancia del objeto Runnable
         * ambos thread trabajan con una instancia distinta de Runnable por lo que cada thread
         * trabaja con su propio lock. Para lograra la sincronizacion necesitamos utilizar el
         * mismo lock en ambos thread
         */
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++) {
                    // en el bloque sincronizado incrementamos la variable de clase count
                    // this -> representa la instancia de Runnable
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
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++) {
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
