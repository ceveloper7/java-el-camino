package com.ceva.section17.multithread;

public class SynchronizedStart {
    /**
     * Ejemplo 1: El Thread inicia sin sincronizacion
     */
    private static void test1(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // inicia proceso del thread ha comenzado a trabajar
                System.out.println("El Thread ha comenzado...");
            }
        });
        t.start();
        // linea que representa el momento que monitoreamos el Thread
        // el thread principal finaliza antes que el thread haya comenzadox
        System.out.println("Main ha finalizado...");
    }

    /**
     * Ejemplp 2: El Thread inicia con sincronizacion
     */
    private static void test2(){
        // 1. objeto lock que sera elemento de sincronizacion
        Object lock = new Object();
        // 2. Creamos el Thread
        // Cuando inicie el Thread secundario se toma contro del lock y llama a lock.notify()
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread ha comenzado...");
                // tomamos control del lock
                // si lock esta ocupado entonces el tread secundario esperara hasta que se desocupe
                // el bloque synchronized no pone al thread en una lista de espera sino que lo pone en
                // espera a que el lock se desocupe. No es lo mismo esperar por un thread que estar
                // en la lista de espera. Un thread que esta esperando continuara cuando lock se
                // desocupe mientras que un thread en la lista de espera o en estado wait no
                // funcionara aunque el lock este desocupado
                // la unica forma de hacer funcionar el thread es enviando un notify()
                synchronized (lock){
                    // lock.notify() hace que el thread secundario tome el control del lock
                    // e informa a un thread en lista de espera que ya puede comenzar a esperar
                    // por su lock. Luego el bloque synchronized termina. Por lo tanto el codigo
                    // thread da inicio
                    lock.notify();
                }
            }
        });
        // 3. bloque synchronized. lock es el metodo de sincronizacion
        // todo lo que esta dentro del bloque sera ejecutado por el tread principal
        // el thread principal tiene el lock
        synchronized (lock){
            // 4. El thread principal inicia el thread secundario
            t.start();
            try{
                // 5. wait() detiene el tread principal y lo deja guardado en una lista de espera
                // el thread no funcionara hasta que algien lo saque del estado wait
                lock.wait();
            }
            catch (InterruptedException e){}
        }
        System.out.println("Main ha finalizado");
    }

    /**
     * Ejemplo 3: el thread inicia con retardo y usando al mismo thread como lock
     * cualquier objeto en java puede ser utilizado como elemento de sincronizacion
     */
    public static void test3(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // retrasamos run
                try{
                    Thread.sleep(3000);
                }
                catch (InterruptedException e){}
                System.out.println("El Threa ha comenzado...");
                // retornamos el thread bajo el cual se esta ejecutando el metodo run()
                Thread currentThread = Thread.currentThread();
                // usamos al thread t como elemento de sincronizacion
                synchronized (currentThread){
                    currentThread.notify();
                }
            }
        });
        synchronized (t){
            t.start();
            try{
                t.wait();
            }
            catch (InterruptedException e){}
        }
        System.out.println("Main ha finalizado");
    }

    /**
     * Pasamos un Runnable y retornamos un Thread pero ya sincronizado
     */
    private static Thread synchronizedStartThread(Runnable r){
        Thread aux = new Thread(()->{
            Thread currentAux = Thread.currentThread();
            synchronized (currentAux){
                currentAux.notify();
            }
            r.run();
        });
        synchronized (aux){
            aux.start();
            try{
                aux.wait();
            }
            catch(InterruptedException e){}
        }
        return aux;
    }

    /**
     * Ejemplo 4: Encapsulamos la funcionalidad para que se pueda utilizar en el futuro
     */
    private static void test4(){
        synchronizedStartThread(()->{
            System.out.println("Thread ha comendado...");
        });
        System.out.println("Main ha finalziado...");
    }
    public static void main(String[] args) {
        System.out.println("Main ha iniciado...");
        test4();
    }
}
