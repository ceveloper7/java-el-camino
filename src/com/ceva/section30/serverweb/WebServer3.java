package com.ceva.section30.serverweb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Properties;
import java.util.StringTokenizer;
public class WebServer3 {
    private static final String CRLF = "\r\n";
    private int serverPort;
    private int serverMaxConnections;
    private File rootFolder; // directorio a partir de donde se lee todos los archivos
    private ServerSocket serverSocket;
    private boolean finished = true;
    private Thread shutdownHook;

    public WebServer3() {
        loadParams();
    }

    private void setDefaultParams() {
        serverPort = 2000;
        serverMaxConnections = 128;
        rootFolder = null;
    }

    private void loadParams() {
        // obtenemos el archivo de propiedades
        File fileParams = new File(System.getProperty("user.home"), "WebServer.props");
        // si existe lo procesamos
        if (fileParams.exists()) {
            // cargamos la configuracion del archivo
            Properties props = new Properties();
            try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileParams))) {
                props.loadFromXML(bin);
                // leemos las propiedades del archivo
                String s = props.getProperty("port");
                serverPort = Integer.parseInt(s);
                s = props.getProperty("maxConnections");
                serverMaxConnections = Integer.parseInt(s);
                s = props.getProperty("rootFolder");
                if (s != null)
                    rootFolder = new File(s);
                else
                    rootFolder = null;
            } catch (IOException e) {
                // si se produce un error lo reportamos y colocamos los valores por defecto al archivo
                reportException(e);
                setDefaultParams();
            }
        } else {
            // si no existe, creamos uno
            setDefaultParams();

            Properties props = new Properties();
            props.put("port", "2000");
            props.put("maxConnections", "128");
            try (BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(fileParams))) {
                props.storeToXML(bout, "Archivo de configuracion WebServer");
            } catch (IOException e) {
                reportException(e);
            }
        }
    }

    private void start() {
        if (shutdownHook == null) {
            shutdownHook = new Thread(() -> stop() );
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        }
        Thread t = new Thread(() -> {
            run();
            finished = false;
        });
        t.start();

        System.out.println("Servidor iniciado.");
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

    private void reportException(Exception e) {
        System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        e.printStackTrace();
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

    private void printHeaders(OutputStream out, Map<String,String> headers) throws IOException {
        for (Map.Entry<String,String> entry : headers.entrySet()) {
            String s = String.format("%s: %s\r\n", entry.getKey(), entry.getValue());
            out.write(s.getBytes());
        }
    }

    private void processRequest(Socket client) {
        try {
            String method = null;
            String path = null;
            String protocol = null;

            // Entrada
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            // Leer solicitud
            String line = in.readLine();
            // si no hay la primera linea, se cancela
            if ((line == null) || (line.length() == 0))
                return;
            System.out.println(line);
            // como hay primera linea, la interpretamos
            StringTokenizer st = new StringTokenizer(line, " " );
            // interpretamos la primera linea
            method = st.nextToken();
            path = st.nextToken();
            protocol = st.nextToken();

            // Leer headers
            line = in.readLine();
            while ((line != null) && (line.length() > 0)) {
                System.out.println(line);
                int index = line.indexOf(':');
                String name = line.substring(0, index).trim();
                String value = line.substring(index+1).trim();
                // ... Guardar name, value en algun lugar para su posterior consulta
                line = in.readLine(); // obtenemos la sgte linea
            }

            // obtenemo el recurso a leer del servidor
            File resourceFile = null;
            long contentLength = 0;
            // la informacion del server se enviara por chunks o content lenght
            boolean chunked = false;
            InputStream resource = null;
            if (rootFolder != null) {
                if (path.equals("/")) {
                    // leemos el recurso index.html
                    resourceFile = new File(rootFolder, "index.html");
                } else {
                    if (path.startsWith("/"))
                        path = path.substring(1);
                    resourceFile = new File(rootFolder, path);
                }
                if ((resourceFile != null) && resourceFile.exists()) {
                    contentLength = resourceFile.length(); // obtenemos la longitud
                    resource = new BufferedInputStream(new FileInputStream(resourceFile));
                    // validamos si el archivo supera los 8kb
                    if (contentLength > 0x2000)
                        chunked = true;
                }
            } else {
                // si no se encuentra el recurso, informamos al usuario
                resource = new BufferedInputStream(getClass().getResourceAsStream("/com/ceva/section30/serverweb/help.html"));
                chunked = true;
            }

            // Salida
            OutputStream out = new BufferedOutputStream(client.getOutputStream());
            String strResponse;
            if (resource != null) {
                // armamos la respuesta al client
                strResponse = "HTTP/1.1 200 OK" + CRLF;
                out.write(strResponse.getBytes());
                Map<String,String> headers = new LinkedHashMap<>();
                if (path.toLowerCase().endsWith(".html") || path.equals("/"))
                    headers.put("Content-Type", "text/html");
                else if (path.toLowerCase().endsWith(".png"))
                    headers.put("Content-Type", "image/png");
                // establecemos el Transfer-Encoding
                if (chunked) {
                    headers.put("Transfer-Encoding", "chunked");
                } else {
                    headers.put("Content-Length", String.valueOf(contentLength));
                }
                printHeaders(out, headers);
                out.write(CRLF.getBytes());

                byte buffer[] = new byte[0x2000];
                // si el Tranfer-Encoding es chunked
                if (chunked) {
                    int nRead = resource.read(buffer); // creamos el buffer
                    while (nRead != -1) {
                        // leermos el bufer
                        // escribimos el tamano del chunk
                        out.write((Integer.toHexString(nRead) + CRLF).getBytes());
                        // escribimos los datos
                        out.write(buffer, 0, nRead);
                        // cambiamos de linea
                        out.write(CRLF.getBytes());
                        // leemos el sgte bloque
                        nRead = resource.read(buffer);
                    }
                    // escribimos el ultimo bloque de 0 byte
                    out.write((("0" + CRLF).getBytes()));
                    // ultimo cambio de linea
                    out.write(CRLF.getBytes());
                }
                // cuando Tranfer-Econding no es chunked
                else {
                    int nRead = resource.read(buffer);
                    while (nRead != -1) {
                        out.write(buffer, 0, nRead);
                        nRead = resource.read(buffer);
                    }
                }
                resource.close();
            } else {
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
                System.out.println("Error: No se puede aceptar la conexion.");
                continue;
            }
            processRequest(client);
        }
        stop();
    }

    public static void main(String[] args) {
        System.out.println("WebServer v0.3 - Escrito por Carlos V.");
        System.out.println("user.home = " + System.getProperty("user.home"));

        WebServer3 server = new WebServer3();
        server.start();
    }
}
