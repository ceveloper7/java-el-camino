package com.ceva.section17.multithread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Programa que compara cuanto tiempo toma hacer incrementos con y sin variables atomicas
 */
public class MultiThreadTest10 implements Runnable{
    private AtomicInteger count = new AtomicInteger();
    private int nCount = 0;
    private int numTest = 0;
    private static final int MAXCOUNT = 500_000_000;

    private void atomicCount(){
        for(int i = 0; i < MAXCOUNT; i++){
            count.incrementAndGet();
        }
    }

    private synchronized void incCount(){
        nCount++;
    }

    private void synchronizedCount(){
        for(int i = 0; i < MAXCOUNT; i++){
            incCount();
        }
    }
    @Override
    public void run() {
        if(numTest == 0){
            atomicCount();
        }else{
            synchronizedCount();
        }
    }

    public void startTest(){
        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(this);
        Thread t2 = new Thread(this);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }
        catch (InterruptedException e){}
        long endTime = System.currentTimeMillis();

        if(numTest == 0){
            System.out.printf("Done (%d ms). count=%d\n", (endTime - startTime), count.get());
        }else{
            System.out.printf("Done (%d ms). count=%d\n", (endTime - startTime), nCount);
        }
    }
    public static void main(String[] args) {
        MultiThreadTest10 test = new MultiThreadTest10();

        System.out.println("Synchronized test: ");
        test.numTest = 1;
        test.startTest();

        System.out.println("\nAtomic Tets");
        test.numTest = 0;
        test.startTest();
    }
}
