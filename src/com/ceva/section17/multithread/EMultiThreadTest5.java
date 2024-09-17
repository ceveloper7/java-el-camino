package com.ceva.section17.multithread;

/*
 * Seccion critica: Una seccion critica es una porcion de codigo que debe ser ejecutado si interferencia.
 */
public class EMultiThreadTest5 implements Runnable {
    // Variable de instancia incrementada
    private int count;

    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            /*
             * esta linea count +=1 es una seccion critica porque necesitamos que sea ejecutada por un Thread a la vez, de esa forma
             * evitamos que se produzca un acceso simultaneo.
             */
            count += 1;
            System.out.println("count = " + count);
            try {
                Thread.sleep(10); // pausa de 10 milisegundos
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        EMultiThreadTest5 test4 = new EMultiThreadTest5();

        Thread thread1 = new Thread(test4);
        Thread thread2 = new Thread(test4);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Programa finalizado. count = " + test4.count);
    }
}