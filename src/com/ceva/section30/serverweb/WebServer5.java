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
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Servidor Web que procesa metodo POST
 */
public class WebServer5 {
    private static final String CRLF = "\r\n";
    private boolean finished = true;
    private int serverPort;
    private int serverMaxConnections;
    private File rootFolder;
    private ServerSocket serverSocket;
    private Thread shutdownHook;

    public WebServer5() {
        loadParams();
    }

    private void setDefaultParams() {
        serverPort = 2000;
        serverMaxConnections = 128;
        rootFolder = null;
    }

    private void loadParams() {
        File fileParams = new File(System.getProperty("user.home"), "WebServer.props");
        if (fileParams.exists()) {
            Properties props = new Properties();
            try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileParams))) {
                props.loadFromXML(bin);

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
                reportException(e);
                setDefaultParams();
            }
        } else {
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

    private Map<String,String> parseQueryString(String queryString, Map<String,String> queryStringMap) {
        Map<String,String> res;
        if (queryStringMap == null)
            res = new HashMap<>(); // creamos un nuevo objeto
        else
            res = queryStringMap; // utilizamos el objeto que nos pasan
        StringTokenizer st = new StringTokenizer(queryString, "&");
        while (st.hasMoreTokens()) {
            String nameValue = st.nextToken();
            int idx = nameValue.indexOf('=');
            String name = nameValue.substring(0, idx);
            String value = nameValue.substring(idx+1);
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                reportException(e);
            }
            res.put(name, value);
        }
        return res;
    }

    private void processRequest(Socket client) {
        try {
            String method = null;
            String path = null;
            String queryString = null;
            String protocol = null;
            Map<String,String> queryStringMap = null;

            // Entrada
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            // Leer solicitud
            String line = in.readLine();
            if ((line == null) || (line.length() == 0))
                return;
            System.out.println(line);
            StringTokenizer st = new StringTokenizer(line, " " );
            method = st.nextToken().toUpperCase();

            path = st.nextToken();
            int idx = path.indexOf("?");
            if (idx > 0) {
                queryString = path.substring(idx+1);
                path = path.substring(0, idx);

                if (queryString.length() > 0)
                    queryStringMap = parseQueryString(queryString, null);
            }
            protocol = st.nextToken();

            // Leer headers
            int contentLength = 0;
            line = in.readLine();
            while ((line != null) && (line.length() > 0)) {
                System.out.println(line);
                int index = line.indexOf(':');
                // obtenemos name y value de cada header
                String name = line.substring(0, index).trim();
                String value = line.substring(index+1).trim();
                // validamos el Content-Length
                if ("Content-Length".equalsIgnoreCase(name)) {
                    contentLength = Integer.parseInt(value);
                }
                line = in.readLine();
            }
            if ("POST".equalsIgnoreCase(method)) {
                if (contentLength > 0) {
                    // Leer el contenido que se envia por metodo POST
                    char buffer[] = new char[contentLength];
                    in.read(buffer, 0, contentLength);
                    String content = new String(buffer);
                    System.out.println(content);

                    // convertimos los valores pasados por POST en un mapa
                    parseQueryString(new String(buffer), queryStringMap);
                }
            }

            File resourceFile = null;
            contentLength = 0;
            boolean chunked = false;
            InputStream resource = null;
            if (rootFolder != null) {
                if (path.equals("/")) {
                    resourceFile = new File(rootFolder, "index.html");
                } else {
                    if (path.startsWith("/"))
                        path = path.substring(1);
                    resourceFile = new File(rootFolder, path);
                }
                if ((resourceFile != null) && resourceFile.exists()) {
                    contentLength = (int) resourceFile.length();
                    resource = new BufferedInputStream(new FileInputStream(resourceFile));
                    if (contentLength > 0x2000)
                        chunked = true;
                }
            } else {
                resource = new BufferedInputStream(getClass().getResourceAsStream("/com/ceva/section30/serverweb/help.html"));
                chunked = true;
            }

            // Salida
            OutputStream out = new BufferedOutputStream(client.getOutputStream());
            String strResponse;
            if (resource != null) {
                strResponse = "HTTP/1.1 200 OK" + CRLF;
                out.write(strResponse.getBytes());
                Map<String,String> headers = new LinkedHashMap<>();
                if (path.toLowerCase().endsWith(".html") || path.equals("/"))
                    headers.put("Content-Type", "text/html");
                else if (path.toLowerCase().endsWith(".png"))
                    headers.put("Content-Type", "image/png");
                if (chunked) {
                    headers.put("Transfer-Encoding", "chunked");
                } else {
                    headers.put("Content-Length", String.valueOf(contentLength));
                }
                printHeaders(out, headers);
                out.write(CRLF.getBytes());

                if ("GET".equals(method) || "POST".equals(method)) {
                    byte buffer[] = new byte[0x2000];
                    if (chunked) {
                        int nRead = resource.read(buffer);
                        while (nRead != -1) {
                            out.write((Integer.toHexString(nRead) + CRLF).getBytes());
                            out.write(buffer, 0, nRead);
                            out.write(CRLF.getBytes());
                            nRead = resource.read(buffer);
                        }
                        out.write((("0" + CRLF).getBytes()));
                        out.write(CRLF.getBytes());
                    } else {
                        int nRead = resource.read(buffer);
                        while (nRead != -1) {
                            out.write(buffer, 0, nRead);
                            nRead = resource.read(buffer);
                        }
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
        System.out.println("WebServer v0.5 - Escrito por Carlos V.");
        System.out.println("user.home   = " + System.getProperty("user.home"));

        WebServer5 server = new WebServer5();
        System.out.println("root folder = " + server.rootFolder);
        server.start();
    }
}
