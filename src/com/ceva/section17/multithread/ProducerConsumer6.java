package com.ceva.section17.multithread;

import java.util.LinkedList;
import java.util.List;

/**
 * Detenemos el esquema Producer-Consumer sin utilizar interrupciones
 */
public class ProducerConsumer6 {
    private class  Producer implements Runnable{
        private List<Integer> queue = new LinkedList<Integer>();
        // manejamos si el programa esta terminado
        private boolean done = false;

        // El producer solo puede producir 5 elementos en la lista. Si ya tengo 5 elementos
        // no podre producir un 6to hasta que no se hayan consumido antes.
        private int maxSize = 5;

        // cada vez que exista un nuevo elemento en la lista queue, notificamos
        // a todos los thread en esta wait que hay informacion disponible
        private synchronized void put(int n){
            // mientras la lista este llena, no se podra agregar mas elementos
            while(queue.size() >= maxSize){
                System.out.println("Queue is currently full");
                try{
                    // ponemos en estado de wait al thread producer porque hay info disponible en queue
                    // el metodo get() es quien lo debe despertar
                    // en caso que el Thread esta en modo wait, despues de 1 segundo va a verificar
                    wait(1000);
                }
                catch (InterruptedException e){
                }
            }
            if(!done)
                queue.add(n);
            // si el consumer esta en wait avisamos que queue ya tiene dato para devolver
            notifyAll();
        }

        private synchronized Integer get() {
            // validamos si tengamos un dato que ofrecer al momento del get()
            while(queue.isEmpty() && !done){
                try{
                    // si queue esta vacio ponemos al thread consumer en modo wait
                    // hasta que producer genere algun dato
                    wait();
                }
                catch (InterruptedException e){}
            }
            if(!done){
                // queue esta llena o tiene dato al menos un dato y lo podemos consumir
                Integer i = queue.get(0);
                queue.remove(0);
                // despertamos al thread producer para que produzca un elemento a la lista
                notifyAll();
                return i;
            }else{
                // ya terminamos, no hay nada que retornar
                return null;
            }
        }

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
            // numero a generar
            int n = 0;
            // definimos para la lista 10 elementos
            while(!done){
                try{
                    // simulamos lo que le tardaria al thread generar la informacion
                    // el thread del producer se tardar medio segundo en producir un elemento
                    // por lo que se produce mucho mas rapido de lo que se consume.
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                }
                if(!done)
                    put(n);
                n++;
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
                // obtenemos dato del producer
                Integer data = producer.get();
                if(data == null){
                    System.out.println("null");
                    continue;
                }
                // procesamos el datos (fingimos procesamiento con sleep) en 250 milisegundos
                try{
                    // el thread del consumer se tarda 1 segundo en consumir un elemento del producer
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                }
                System.out.println("Report: " + data);
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

        // hacemos una pausa de 4 segundos
        try{
            Thread.sleep(4000);
        }
        catch(InterruptedException e){}
        // detenemos el esquema Producer-Consumer
        producer.cancel();
    }

    public static void main(String[] args) {
        new ProducerConsumer6().demo();
    }
}
