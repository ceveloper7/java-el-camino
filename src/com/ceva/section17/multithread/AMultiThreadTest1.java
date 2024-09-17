package com.ceva.section17.multithread;

/**
 * EN este primer ejemplo la clase implementa la interface Runnable para crear un programa en hilos
 */
public class AMultiThreadTest1 implements Runnable{
    @Override
    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Prueba " + i);
        }
    }

    /*
     * El programa ahora funcion con 2 Thread.
     * Thread 1 para el metodo main()
     * Thread 2 para ejecutar el ciclo for
     * Se ejecuta el Thread 1 y por ello imprime Programa finalizado luego sigue con el Thread 2
     * y realiza la impresion de los mensajes del ciclo for y al haber terminado el thread main
     * entonces finaliza.
     */
    public static void main(String[] args) {
        Thread t = new Thread(new AMultiThreadTest1());
        t.start();
        System.out.println("Programa finalizado.");
    }
}
