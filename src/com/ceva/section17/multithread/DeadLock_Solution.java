package com.ceva.section17.multithread;

/**
 * Hay muchas formas de evitar que se produzcan dead lock en nuestros programas una de ellas:
 * Tratando de adquirir los locks en el mismo orden
 */
public class DeadLock_Solution {
    private Object lockA = new Object();
    private Object lockB = new Object();

    private void test(){
        Thread t1 = new Thread(() ->{
            // tomamos el control de lockA
            synchronized (lockA){
                System.out.println("Thread 1, lock A");
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){}
                // tomamos el control de lockB
                synchronized (lockB){
                    System.out.println("Thread 1, Lock A, B");
                }
            }
            System.out.println("Thread 1, Finalizado");
        });
        t1.start();
        Thread t2 = new Thread(()->{
            // manejamos los locks en el mimos orden
            synchronized (lockA){
                System.out.println("Thread 2, lock A");
                try{
                    Thread.sleep(500);
                }
                catch(InterruptedException e){}
                synchronized (lockB){
                    System.out.println("Thread 2, Lock A, B");
                }
            }
            System.out.println("Thread 2, finalizado");
        });
        t2.start();
    }

    public static void main(String[] args) {
        new DeadLock_Solution().test();
    }
}
