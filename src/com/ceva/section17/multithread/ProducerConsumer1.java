package com.ceva.section17.multithread;

import javax.swing.plaf.TableHeaderUI;
import java.util.LinkedList;
import java.util.List;

/**
 * Pensemos que estamos generando un reporte que se tarda en generar por otro lado la informacion
 * que el reporte necesita proviene de internet y la conexion es muy lenta.
 * El programa tendra que esperar que se obtenga la informacion y luego
 * esperar que el reporte se genere.
 *
 * Para resolver este escenario generamos 2 threads:
 * 1. El primer thread se encargara de obtener la informacion de internet
 * 2. El segundo thread producira los reportes con la informacion obteneido hasta el momento
 */
public class ProducerConsumer1 {
    private List<Integer> queue = new LinkedList<Integer>();

    private class  Producer implements Runnable{

        private void put(int n){
            queue.add(n);
        }

        private Integer get(){
            if(queue.size() == 0){
                return null;
            }
            Integer i = queue.get(0);
            queue.remove(0);
            return i;
        }
        @Override
        public void run() {
            // producimos la informacion
            for(int n = 0; n < 10; n++){
                try{
                    // simulamos lo que le tardaria al thread generar la informacion
                    // cada elemento necesita 1/2 segundo para producirse
                    Thread.sleep(500);
                }
                catch (InterruptedException e){}
                put(n);
            }
        }
    }

    private class Consumer implements Runnable{
        private Producer producer;

        public Consumer(Producer producer){
            this.producer = producer;
        }
        @Override
        public void run() {
            for(int n = 0; n < 10; n++){
                // obtenemos dato del producer
                Integer data = producer.get();
                // procesamos el datos (fingimos procesamiento con sleep) en 250 milisegundos
                try{
                    Thread.sleep(250);
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
        new ProducerConsumer1().demo();
    }
}
