package com.ceva.section17.multithread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Detenemos el esquema Producer-Consumer sin utilizar interrupciones
 */
public class ProducerConsumer7 {
    // El producer solo puede producir 5 elementos en la lista. Si ya tengo 5 elementos
    // no podre producir un 6to hasta que no se hayan consumido antes.
    private static final int MAX_SIZE = 5;
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(MAX_SIZE);
    private class  Producer implements Runnable{

        // manejamos si el programa esta terminado
        private boolean done = false;
        private boolean isDone(){
            // flag que se activa cuando se termino de producier los elementos
            // si done == true dejamos de producir elementos
            // si aun hay elementos en queue, quiere decir que aun no ha terminado
            return done && (queue.size() == 0);
        }

        /**
         * queue viene a ser la variable critica. Si manipulamos queue debemos asegurarnos
         * que ningun otro thread este ejecutando codigo donde queue esta involucrado
         * por ello hacemos al metodo cancel synchronized.
         * si invocamos a cancel() nadie podra llamar a get() ni a put() ya que estos
         * metodos manipulan la variable queue
         */
        public synchronized void cancel(){
            done = true;
            queue.clear();
        }
        @Override
        public void run() {
            // definimos para la lista 10 elementos
            for(int n = 0; n < 10; n++){
                try{
                    queue.put(n);
                    System.out.println("Put " + n);
                    // simulamos lo que le tardaria al thread generar la informacion
                    // el thread del producer se tardar medio segundo en producir un elemento
                    // por lo que se produce mucho mas rapido de lo que se consume.
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                }
            }
            done = true;
        }
    }

    private class Consumer implements Runnable{
        private Producer producer;

        public Consumer(Producer producer){
            this.producer = producer;
        }
        @Override
        public void run() {
            // mientras que el Producer no haya terminado, seguimos tomando los elementos de queue
            // y los procesamos
            while(!producer.isDone()){
                // procesamos el datos (fingimos procesamiento con sleep) en 250 milisegundos
                try{
                    // obtenemos dato del producer
                    Integer data = queue.take();
                    if(data == null){
                        System.out.println("null");
                        continue;
                    }
                    // el thread del consumer se tarda 1 segundo en consumir un elemento del producer
                    Thread.sleep(1000);
                    System.out.println("Report: " + data);
                }
                catch (InterruptedException e){
                }
            }
        }
    }
    private void demo(){
        Producer producer = new Producer();
        Consumer consumer = new Consumer(producer);
        // funcionamiento
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new ProducerConsumer7().demo();
    }
}
