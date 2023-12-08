package com.ceva.section20.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Este es un programa simple ya que no es multithread.
 * Cuando un cliente se conecte sera atendido y despues volvera a esperar la sgte conexion.
 * Un verdadero servidor debe ser capaz de atender multiples conexiones simultaneamente y esto
 * se logra utillizando Thread en lugar de atender la conexion directamente. Por cada peticion
 * se deberia crear un Thread
 */
public class ServerDemo {
    private ServerSocket soServer;
    private int nCount;

    public ServerDemo() {
        /**
         * ShutdownHook es un Thread que se registra en el JVM que es invocado cuando el
         * user presiona CTRL + C entonces el JVM despierta el Thread pasado como lambda
         * y llamamos al metodo stop() para detener la ejecucion del server
         */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            stop(); // finalizamos el server listener
        }));
    }

    /**
     * Metodo que atiende la peticion del cliente: enviamos un mensaje de texto y cerramos la conexion
     * @param client
     */
    private void dispatchClient(Socket client) {
        // Cada peticion la procesamos en un Thread
        Thread t = new Thread(() -> {
            try {
                // getOutputStream - stream de salida, todo lo que escribimos en ella
                // se va para la otra computadora.
                PrintWriter pr = new PrintWriter(client.getOutputStream());
                pr.printf("Hola mundo\nEres el cliente #%d.\n", ++nCount);
                pr.close(); // cerramos la conexion
                System.out.println("Conexion atendida.");
            } catch (IOException e) {
                System.out.println("Error al enviar informacion al cliente: " + e.getMessage());
            }
        });
        t.start();
    }

    private void run() {
        /*
         * 1era Parte: Inicializamos el server en modo listener
         */
        try {
            soServer = new ServerSocket(2000); // socket en modo listener, puerto 2000
        } catch (IOException e) {
            System.out.println("No se puede crear el servidor: " + e.getMessage());
            return;
        }
        System.out.printf("El servidor ha iniciado en el puerto %d.\n", soServer.getLocalPort());
        // 2da parte: Ciclo infinito que acepta conexiones y las proceso conforme llegan
        while (true) {
            try {
                // obtenemos el socket cuando el client se conecta por el puerto 2000
                // accept() se queda en pausa hasta que un cliente se conecte
                Socket client = soServer.accept();
                dispatchClient(client); // procesamos la peticion del client
            } catch (IOException e) {
                System.out.println("Error al establecer conexion con cliente: " + e.getMessage());
            }
        }
    }

    /*
     * 3era parte: Finalizacion del servidor
     */
    private void stop() {
        try {
            soServer.close();
        } catch (IOException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("El servidor ha finalizado.");
    }

    public static void main(String[] args) {
        new ServerDemo().run();
    }
}
