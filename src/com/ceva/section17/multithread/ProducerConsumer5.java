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
public class ProducerConsumer5 {
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
                catch (InterruptedException e){
                    System.out.println("Producer interrupted in put method");
                    done = true;
                    queue.clear();
                    return;
                }
            }

            queue.add(n);
            // si el consumer esta en wait avisamos que queue ya tiene dato para devolver
            notifyAll();
        }

        private synchronized Integer get() throws InterruptedException{
            // validamos si tengamos un dato que ofrecer al momento del get()
            while(queue.isEmpty()){
                // si queue esta vacio ponemos al thread consumer en modo wait
                // hasta que producer genere algun dato
                wait();
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
                    System.out.println("Producer interrupted sleep time");
                    // limpiamos la lista para que no se siga trabajando con los elementos
                    queue.clear();
                    break;
                }
                // validamos que se haya cambiado a interrupted el Thread
                if(Thread.currentThread().isInterrupted()){
                    // si el Thread esta interrumpido, ya no necesitamos lo que esta en la lista
                    queue.clear();
                    // detenemos el programa
                    break;
                }
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
                // procesamos el datos (fingimos procesamiento con sleep) en 250 milisegundos
                try{
                    // obtenemos dato del producer
                    Integer data = producer.get();
                    if(data == null){
                        System.out.println("null");
                        continue;
                    }
                    // el thread del consumer se tarda 1 segundo en consumir un elemento del producer
                    Thread.sleep(1000);
                    System.out.println("Report: " + data);
                }
                catch (InterruptedException e){
                    System.out.println("Consumer interrupted");
                    break;
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

        // hacemos una pausa de 4 segundos
        try{
            Thread.sleep(4000);
        }
        catch(InterruptedException e){}
        // detenemos el proceso. cuando el producer se interrupe, tambien interrumpimo el consumer
        t1.interrupt();
    }

    public static void main(String[] args) {
        new ProducerConsumer5().demo();
    }
}
