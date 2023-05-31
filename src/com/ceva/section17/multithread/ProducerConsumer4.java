package com.ceva.section17.multithread;

import java.util.LinkedList;
import java.util.List;

/**
 * Pensemos que estamos generando un reporte que se tarda en generar por otro lado la informacion
 * que el reporte necesita proviene de internet y la conexion es muy lenta.
 * El programa tendra que esperar que se obtenga la informacion y luego
 * esperar que el reporte se genere.
 *
 * Para resolver este escenario generamos 2 threads sincronizados (consumer y producer)
 * 1. El primer thread se encargara de obtener la informacion de internet
 * 2. El segundo thread producira los reportes con la informacion obteneido hasta el momento
 */
public class ProducerConsumer4 {
    private class  Producer implements Runnable{
        private List<Integer> queue = new LinkedList<Integer>();
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
                    wait();
                }
                catch (InterruptedException e){}
            }

            queue.add(n);
            // si el consumer esta en wait avisamos que queue ya tiene dato para devolver
            notifyAll();
        }

        private synchronized Integer get(){
            // validamos si tengamos un dato que ofrecer al momento del get()
            while(queue.isEmpty()){
                try{
                    // si queue esta vacio ponemos al thread consumer en modo wait
                    // hasta que producer genere algun dato
                    wait();
                }
                catch (InterruptedException e){}
            }
            // queue esta llena o tiene dato al menos un dato y lo podemos consumir
            Integer i = queue.get(0);
            queue.remove(0);
            // despertamos al thread producer para que produzca un elemento a la lista
            notifyAll();
            return i;
        }

        private boolean isDone(){
            // flag que se activa cuando se termino de producier los elementos
            // si done == true dejamos de producir elementos
            // si aun hay elementos en queue, quiere decir que aun no ha terminado
            return done && (queue.size() == 0);
        }
        @Override
        public void run() {
            // definimos para la lista 10 elementos
            for(int n = 0; n < 10; n++){
                try{
                    // simulamos lo que le tardaria al thread generar la informacion
                    // el thread del producer se tarda 10 miliseg en producir un elemento
                    // por lo que se produce mucho mas rapido de lo que se consume.
                    Thread.sleep(100);
                }
                catch (InterruptedException e){}
                put(n);
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
                // procesamos el datos (fingimos procesamiento con sleep) en 250 milisegundos
                try{
                    // el thread del consumer se tarda 1 segundo en consumir un elemento del producer
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){}
                if(data == null){
                    System.out.println("null");
                    continue;
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
    }

    public static void main(String[] args) {
        new ProducerConsumer4().demo();
    }
}
