package com.ceva.section17.multithread;

import java.util.concurrent.CountDownLatch;

public class LatchTest {
    private CountDownLatch latch;

    private class TestRunnable implements Runnable{
        @Override
        public void run() {
            try{
                latch.await();
            }
            catch(InterruptedException ex){}
            System.out.println("El Thread " + Thread.currentThread().getName() + " ha iniciado");
        }
    }
    private void test(){
        latch =  new CountDownLatch(1);
        for(int n = 0; n < 3; n++){
            Thread t = new Thread(new TestRunnable(), "Thread " + n);
            t.start();
        }
        System.out.println("Iniciando...");
        // Como es 1, en el momento de countDown() se vuelve cero y salen los threads disparados
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ex){}
        latch.countDown();
    }
    public static void main(String[] args) {
        new LatchTest().test();
    }
}
