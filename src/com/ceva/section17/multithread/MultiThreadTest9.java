package com.ceva.section17.multithread;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadTest9 implements Runnable{
    private AtomicInteger count = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            int n = count.incrementAndGet();
            System.out.println("count = " + n);
            try{
                Thread.sleep(10);
            }
            catch (InterruptedException e){}
        }
    }

    public static void main(String[] args) {
        MultiThreadTest9 test = new MultiThreadTest9();

        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }
        catch (InterruptedException e){}

        System.out.println("Programa finalizado. count = " + test.count);
    }
}
