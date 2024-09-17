package com.ceva.section17.multithread;

/**
 * 3era forma de trabajar con Thread
 * esta variante se puede utilizar en casos donde el codigo a implementar en un Thread es pequeno y por lo tanto
 * mas practico de incluir.
 */
public class CMultiThreadTest3 {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<100; i++) {
                    System.out.println("Prueba " + i);
                }
            }
        });
        t.start();
        System.out.println("Programa finalizado.");
    }
}
