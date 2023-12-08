package com.ceva.section20.sockets;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
public class NasdaqReader2 {
    private static final String NASDAQLISTFILE = "C:/temp/nasdaqListFile.txt";

    // retornamos un InputStream con la conexion al servidor de Nasdaq
    private static InputStream getServerInputStream() throws IOException {
        URL nasdaq = new URL("ftp://ftp.nasdaqtrader.com/SymbolDirectory/nasdaqlisted.txt");
        return nasdaq.openStream();
    }

    /**
     * Guardamos la informacion que proviene de la direccion FTP de Nasdaq
     * @param destFile - destino del archivo que se va a crear
     * @throws IOException
     */
    private static void createNasdaqListFile(File destFile) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(getServerInputStream()));
             PrintWriter pr = new PrintWriter(destFile)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                pr.println(inputLine);
        }
    }

    // retornamos un Stream con la info del archivo en disco generado
    public static InputStream getInputStream() throws IOException {
        // creamos un archivo basado en C:/temp/nasdaqListFile.txt
        File f = new File(NASDAQLISTFILE);
        // validamos si existe el archivo
        if (!f.exists()) {
            // si no existe, lo crea. La segunda vez que se ejecute el programa, ya el archivo
            // va a existir por lo que no se volvera a crear el archivo.
            createNasdaqListFile(f);
        }
        // en esta linea, nos aseguramos que siempre exista el archivo.
        URL url = new URL("file:///" + NASDAQLISTFILE);
        return url.openStream();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
