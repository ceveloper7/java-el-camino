/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Using try - catch with resources
 */
public class AA8_CopyFile {
    // Aunque este metodo funcion y copia un archivo, es extremadamente ineficiente.
    public static void copyFile(File source, File dest) throws IOException{
        // Stream primarios basicos
        try(InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(dest)){
            // obtenemos un entero con el siguiente byte leido del stream in
            // por lo que el numero obtenido es entre 0-255, si se produce un error retorna -1
            int b = in.read();
            while(b != -1){
                // escribimos el byte
                out.write(b);
                // leemos el sgte byte
                b = in.read();
            }
        }
    }
    
    public static void copyFileImproved(File source, File dest) throws IOException{
        try(InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(dest)){
            // preparamos un array de 4096 bytes
            byte[] buffer= new byte[4096];
            // leemos del inputstream hasta 4096 bytes
            int n = in.read(buffer);

            while(n != -1){
                /**
                 * Escribimos el contenido del buffer
                 * offset: iniciando desde la posicion 0
                 * longitud: la longitud es decir, los bytes a escribir y q se han leido estan en la variable n
                 */
                out.write(buffer, 0, n);
                n = in.read(buffer);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        copyFileImproved(new File("D:\\Users\\PC\\Pictures\\samaritano.jpg"), 
                         new File("D:\\Users\\PC\\Pictures\\samari44.jpg"));
    }
}
