package com.ceva.section17.multithread;

public class DMultiThreadTest4 implements Runnable {
    private int count = 0;

    public void run() {
        for (int i=0; i<100; i++) {
            count += 1;
            System.out.println("count = " + count);
            try {
                /*
                 * Detenemos el Thread por 1 segundo.
                 * Thread.sleep() puede lanzar la exepcion InterruptedException, eso se puede producir cuando
                 * durante el periodo de espera un Thread externo puede interrumpirla antes de terminar.
                 */
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        DMultiThreadTest4 test4 = new DMultiThreadTest4();

        Thread thread1 = new Thread(test4);
        Thread thread2 = new Thread(test4);
        thread1.start();
        thread2.start();

        System.out.println("Programa finalizado. count = " + test4.count);
    }
}