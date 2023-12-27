package com.ceva.section30.serverweb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * El servidor envia un respuesta al cliente
 */
public class WebServer2 {
    private static final String CRLF = "\r\n";
    private boolean finished = true;
    private int serverPort;
    private int serverMaxConnections;
    private ServerSocket serverSocket;
    private Thread shutdownHook;

    public WebServer2() {
        serverPort = 2000;
        serverMaxConnections = 128;
    }

    private void reportException(Exception e) {
        System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        e.printStackTrace();
    }

    private void start() {
        if (shutdownHook == null) {
            shutdownHook = new Thread(() -> stop() );
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        }
        Thread t = new Thread(() -> run() );
        t.start();

        System.out.println("Servidor iniciado.");
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

    private void run() {
        if (!finished) {
            System.out.println("Error: Ya se encuentra funcionando.");
            return;
        }
        if (!openServerSocket()) {
            stop();
        }
        System.out.printf("\nServidor iniciado en puerto %d.\n\n", serverPort);
        finished = false;
        while (!finished) {
            Socket client;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                if (finished)
                    break;
                log("Error: No se puede aceptar la conexion.");
                continue;
            }
            processRequest(client);
        }
        stop();
    }

    private void stop() {
        finished = true;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error: No se pudo cerrar el socket del servidor.");
            }
            serverSocket = null;
            System.out.println("Servidor finalizado.");
        }
    }

    private void log(String s) {
        System.out.println(s);
    }

    // impresion de todos los headers de una respuesta
    private static void printHeaders(OutputStream out, Map<String,String> headers) throws IOException {
        // recorremos el Map con los headers
        for (Map.Entry<String,String> entry : headers.entrySet()) {
            String s = String.format("%s: %s\r\n", entry.getKey(), entry.getValue());
            out.write(s.getBytes());
        }
    }

    /**
     * Procesamos una respuesta para el client
     * @param client
     */
    private void processRequest(Socket client) {
        try {
            String method = null;
            String path = null;
            String protocol = null;

            // Entrada
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = in.readLine();
            int nLine = 0; // controla el nro de linea que se lee
            while ((line != null) && (line.length() > 0)) {
                System.out.println(line);

                // leemos la primera linea que contiene el texto: GET / HTTP/1.1
                if (nLine == 0) {
                    StringTokenizer st = new StringTokenizer(line, " " );
                    method = st.nextToken(); // verbo GET
                    path = st.nextToken();
                    protocol = st.nextToken();
                }

                line = in.readLine();
                nLine++;
            }

            // ruta donde van a estar todos los documentos
            String root = "/com/ceva/section30/serverweb/";
            InputStream resource; // recurso a devolver al cliente
            if (path.equals("/")) {
                // cuando hay una / el servidor retorna el recurso index.html
                resource = new BufferedInputStream(getClass().getResourceAsStream(root + "index.html"));
            } else {
                // si no es / quiere decir que el cliente esta indicando el recurso especifico que
                // necesita como por ejemplo /test1
                if (path.startsWith("/"))
                    path = path.substring(1);
                resource = getClass().getResourceAsStream(root + path);
                if (resource != null)
                    resource = new BufferedInputStream(resource);
            }

            // Salida
            OutputStream out = new BufferedOutputStream(client.getOutputStream());
            String strResponse; // variable donde se contruye la cadena de respuesta
            // validamos si se encontro el recurso
            if (resource != null) {
                // construimos la respuesta al cliente
                strResponse = "HTTP/1.1 200 OK" + CRLF;
                out.write(strResponse.getBytes());
                // construimos los headers de la respuesta
                // cuando se recorre el LinkedHashMap, se recorren en el mismo orden en el que se
                // escribieron los valores
                Map<String,String> headers = new LinkedHashMap<>();
                // validamos si la extension del recurso solicitado es un html
                if (path.toLowerCase().endsWith(".html"))
                    headers.put("Content-Type", "text/html");
                // validamos si la extension el recurso solicitado es una imagen
                else if (path.toLowerCase().endsWith("png"))
                    headers.put("Content-Type", "image/png");
                // especificamos que se va a escribir la informacion por pedazos (chunk)
                headers.put("Transfer-Encoding", "chunked");
                printHeaders(out, headers);
                // imprimimos una linea vacia luego de los headers
                out.write(CRLF.getBytes());

                // buffer para leer la informacion
                byte buffer[] = new byte[0x2000]; // arreglo de de 8K bytes
                // leemos la informacion en bloques
                int nRead = resource.read(buffer);
                // mientras nRead contenga datos
                while (nRead != -1) {
                    // escribimos el tamano del bloque y un cambio de linea
                    // el nro de bytes leido lo convertimos al hexadecimal y le sumamos un cambio de linea
                    out.write((Integer.toHexString(nRead) + CRLF).getBytes());
                    // escribimos el bloque
                    out.write(buffer, 0, nRead);
                    // imprimimos un cambio de linea

                    out.write(CRLF.getBytes());
                    nRead = resource.read(buffer);
                }
                resource.close(); // cerramos el recurso
                // escribimos bloque vacio
                out.write((("0" + CRLF).getBytes()));
                // escribimos la ultima linea donde indicamos el final de la transmision
                out.write(CRLF.getBytes());
            } else {
                // no se encontro el recurso, retornamos mensaje de error
                strResponse = "HTTP/1.1 404 Not Found" + CRLF + CRLF;
                out.write(strResponse.getBytes());
            }
            System.out.println(strResponse);

            out.close();
            in.close();
        } catch (IOException e) {
            reportException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("WebServer v0.2 - Escrito por Carlos V.");

        WebServer2 server = new WebServer2();
        server.start();
    }
}
