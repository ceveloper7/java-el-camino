package com.ceva.section17.multithread;

public class InterruptDemo {
    private static void interruptDemo1(){
        Thread t = new Thread(()->{
            System.out.println("Iniciando Thread");
            for (int n = 0; n < 10; n++){
                System.out.println(n + ", " + Thread.currentThread().isInterrupted());
                try{
                    Thread.sleep(2000);
                }
                // la excepcion se lanza si al momento de la interrupcion el Thread esta en sleep
                catch (InterruptedException e){}
            }
            System.out.println("Thread ha terminado");
        });
        // ejecutamos el thread, que cada 2 seg realiza una iteracion
        t.start();

        try{
            // a los 3 segundos de iniciado el thread invocamos thread.interrupt()
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            System.out.println("Interrupt recibido");
        }
        // si la interrupcion se encuentra dentro de Thread.sleep se lanza InterruptedException
        t.interrupt();
    }

    private static void interruptDemo2(){
        Thread t = new Thread(()->{
            System.out.println("Iniciando Thread");
            for (int n = 0; n < 10; n++){
                System.out.println(n + ", " + Thread.currentThread().isInterrupted());
                long curTime = System.currentTimeMillis();
                while(System.currentTimeMillis() < (curTime + 2000)){
                    ;
                }
            }
            System.out.println("Thread ha terminado");
        });
        // ejecutamos el thread, que cada 2 seg realiza una iteracion
        t.start();

        try{
            // a los 3 segundos de iniciado el thread invocamos thread.interrupt()
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            System.out.println("Interrupt recibido");
        }
        // si la interrupcion se encuentra dentro de Thread.sleep se lanza InterruptedException
        t.interrupt();
    }

    private static volatile boolean stopFlag = false;
    private static void interrupted3(){
        Thread t = new Thread(()->{
            System.out.println("Iniciando Thread");
            for(int n = 0; n < 4; n++){
                System.out.println(n + ", " + Thread.currentThread().isInterrupted());
                long curTime = System.currentTimeMillis();
                while(System.currentTimeMillis() < (curTime+2000)){
                    ;
                }
                if(stopFlag){
                    System.out.println("Thread cancelado...");
                    break;
                }
            }
            System.out.println("Thread ha terminado");
        });
        t.start();
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){

        }
        stopFlag = true;
    }

    public static void main(String[] args) {
        interruptDemo1();
    }
}
