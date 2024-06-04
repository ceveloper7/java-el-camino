package com.ceva.section7.apijava2;

import java.io.*;

/**
 * Tipo de Stream que lee y escribe en modo texto es decir, caracteres
 */
public class DReader {
    private static final String resource = "/home/ceva/local-projects/intellij/java-basics/java-el-camino/src/log4j2.xml";

    private void readFile() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(resource)));
        // Leemos lineas
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }

    private void readFilesAsResourceStream()throws IOException{
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        DReader.class.getResourceAsStream("resources/magna-carta.txt")));
        // Leemos lineas
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }

    public static void main(String[] args)throws IOException {
        DReader reader = new DReader();
        reader.readFilesAsResourceStream();
        // reader.readFile();
    }
}
