package com.ceva.section17.multithread;

/**
 * 2DA Forma de crear un programa thread, heredamos la clase Thread
 */
public class BMultiThreadTest2 extends Thread{
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Prueba " + i);
        }
    }

    public static void main(String[] args) {
        Thread t = new BMultiThreadTest2();
        t.start();
        System.out.println("Programa finalizado.");
    }
}
