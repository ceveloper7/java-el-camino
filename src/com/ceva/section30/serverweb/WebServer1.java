package com.ceva.section30.serverweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor que recibe una peticio GET del cliente
 */
public class WebServer1 {
    private int serverPort;
    private int serverMaxConnections;
    private ServerSocket serverSocket;
    // variable que nos ayuda a saber cuando terminar el servidor
    private boolean finished = true;
    /**
     * un Hook es un gancho que se registra en la JVM y se ejecuta al momento que la app
     * va a terminar.
     */
    private Thread shutdownHook;

    public WebServer1() {
        serverPort = 2000; // puerto al que escucha el servidor
        serverMaxConnections = 128; // nro conexiones simultaneas que escuchara el server
    }

    private void reportException(Exception e) {
        System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        e.printStackTrace();
    }

    private void start() {
        if (shutdownHook == null) {
            shutdownHook = new Thread(() -> stop() );
            // registramos el Hook
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        }
        // el server funciona en un Thread separad
        Thread t = new Thread(() -> run() );
        t.start();
    }

    private boolean openServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort, serverMaxConnections);
        } catch (IOException e) {
            reportException(e);
            serverSocket = null;
            return false;
        }
        return true;
    }

    // programa principal del servidor
    private void run() {
        if (!finished) {
            System.out.println("Error: Ya se encuentra funcionando.");
            return;
        }
        if (!openServerSocket()) {
            System.out.println("Error: No se puede establecer la conexión.");
            stop();
            return;
        }
        // conexion abierta
        System.out.printf("\nServidor iniciado en puerto %d.\n\n", serverPort);
        finished = false;
        // mientras no haya terminado el servidor
        while (!finished) {
            // socket que representa la conexion con el cliente
            Socket client;
            try {
                // el Thread se detiene esperando por una conexion, cuando llegue la conexion
                // el programa continua su funcionamiento
                client = serverSocket.accept();
            } catch (IOException e) {
                // cuando se cierra el socket del servidor se producira una excepcion
                // si finished es true entonces temrinamos el while
                if (finished)
                    break;
                System.out.println("Error: No se puede aceptar la conexion.");
                continue;
            }
            // llega una conexion del cliente y la procesamos.
            processRequest(client);
        }
        stop();
    }

    /**
     * Antes de finalizar la app cerramos la conexion con el server
     */
    private void stop() {
        finished = true;
        if (serverSocket != null) {
            System.out.println();
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error: No se pudo cerrar el socket del servidor.");
            }
            serverSocket = null;
            System.out.println("Servidor finalizado.");
        }
    }

    /**
     * processRequest -> imprime en pantalla lo que recibe del cliente
     * @param client
     */
    private void processRequest(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
            String line = in.readLine();
            while ((line != null) && (line.length() > 0)) {
                System.out.println(line); // impresion de la linea
                line = in.readLine(); // leemos la sgte linea
            }
            //in.close();
        } catch (IOException e) {
            // manejamos cualquier problema que se presente al atender al client
            reportException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("WebServer v0.1 - Escrito por Carlos V.");

        WebServer1 server = new WebServer1();
        server.start();
    }
}
