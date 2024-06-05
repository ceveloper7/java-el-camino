package com.ceva.section7.apijava2;

import java.io.*;

/**
 * Existen dos tipos de Stream:
 * Stream -> lee y escribe en modo binario es decir, byte de 8 bits
 */
public class CStreams{
    private static final File archivoEntrada = new File("/home/ceva/Documents/proyecto.txt");
    private static final File newFile = new File("/home/ceva/Documents/proyecto_copy.txt");

    private void readDataInFile(File inputFile) throws IOException{
        try(InputStream in = new FileInputStream(inputFile);){
            var input = in.read();
            System.out.println("data: " + input);
        }
    }

    private void writeDataIntoFile() throws IOException {

        int numero = 10;
        OutputStream out = new FileOutputStream(archivoEntrada);
        out.write(numero);

        readDataInFile(archivoEntrada);
    }

    private void copyFile(File source, File target)throws IOException{
        try(
                InputStream entrada = new FileInputStream(source);
                OutputStream salida = new FileOutputStream(target)){
            // leemos el primer byte del archivo entrada
            var input = entrada.read();
            while(input!=-1){
                salida.write(input);
                input = entrada.read(); // leemos el sgte byte
            }
            System.out.println("Copia realizada exitosamente");
        }
    }

    private void copyFileVersion2(File source, File target) throws IOException{
        try(
                InputStream entrada = new FileInputStream(source);
                OutputStream salida = new FileOutputStream(target)){
            // creamos un arreglo de bytes
            var buffer = new byte[4096]; // 4k  otra opcion es 8k que equivale a 8192
            // inputData -> nro de bytes leidos, teniendo como limite 4096 byte
            var inputData = entrada.read(buffer); // leemos hasta 4096 bytes del archivo de entrada y se almacena en buffer
            while(inputData != -1){
                salida.write(buffer, 0, inputData);
                inputData = entrada.read(buffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CStreams cStreams = new CStreams();
        cStreams.copyFileVersion2(archivoEntrada, newFile);
        // cStreams.copyFile(archivoEntrada, newFile);
        // cStreams.writeDataIntoFile();
    }
}
