package com.ceva.section17.multithread;

public class NotifyVSNotifyAll {
    Object lock = new Object();
    private class RunnableTest implements Runnable{
        @Override
        public void run() {
            // para invoca al metodo wait(), el Thread que ejecuta el metodo run() debe tener
            // el control del lock. Por lo que siempre debe estar en un metodo o bloque synchronized
            synchronized (lock) // tomamos control de lock
            {
                try{
                    // el Thread se pasa a un estao de wait
                    lock.wait();
                }
                catch(InterruptedException e){}
            }
            System.out.println("El " + Thread.currentThread().getName() + " ha comenzado");
        }
    }

    private void notifyDemo(){
        // Creamos los Thread con una instancia de RunnableTest y un nombre para el thread
        Thread t1 = new Thread(new RunnableTest(), "Thread 1");
        Thread t2 = new Thread(new RunnableTest(), "Thread 2");
        Thread t3 = new Thread(new RunnableTest(), "Thread 3");
        Thread t4 = new Thread(new RunnableTest(), "Thread 4");

        // inicializamos los thread. Cuando el Thread inicie toma control de lock, llama lock.wait()
        //y el thread pasa al estado wait indefinidamente. se queda "congelado" sin hacer nada
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // hacemos que el programa espere 2 segundos, para dar tiempo que los threads (t1,t2,t3,t4)
        // empiecen.
        try{
            Thread.sleep(2000); // MALISIMA PRACTICA!!!!!
        }
        catch(InterruptedException e){}

        // Tomamos control del lock
        synchronized (lock){
            // despetamos a uno de los threads (t1,t2,t3,t4)
            lock.notify();
        }
        System.out.println("Main ha terminado");
    }

    private void notifyAllDemo(){
        // Creamos los Thread con una instancia de RunnableTest y un nombre para el thread
        Thread t1 = new Thread(new RunnableTest(), "Thread 1");
        Thread t2 = new Thread(new RunnableTest(), "Thread 2");
        Thread t3 = new Thread(new RunnableTest(), "Thread 3");
        Thread t4 = new Thread(new RunnableTest(), "Thread 4");

        // inicializamos los thread. Cuando el Thread inicie toma control de lock, llama lock.wait()
        //y el thread pasa al estado wait indefinidamente. se queda "congelado" sin hacer nada
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // hacemos que el programa espere 2 segundos, para dar tiempo que los threads (t1,t2,t3,t4)
        // empiecen.
        try{
            Thread.sleep(2000); // MALISIMA PRACTICA!!!!!
        }
        catch(InterruptedException e){}

        // Tomamos control del lock
        synchronized (lock){
            // despetamos a todos los threads (t1,t2,t3,t4)
            lock.notifyAll();
        }
        System.out.println("Main ha terminado");
    }

    public static void main(String[] args) {
        new NotifyVSNotifyAll().notifyAllDemo();
    }
}
