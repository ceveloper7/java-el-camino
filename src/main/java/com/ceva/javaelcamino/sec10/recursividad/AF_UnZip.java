/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec10.recursividad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author PC
 */
public class AF_UnZip {

    // listar el contenido de un archivo .zip
    public static void listZip(ZipInputStream zin) throws IOException {
        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            System.out.println(entry);
            entry = zin.getNextEntry();
        }
    }

    public static void unZip(ZipInputStream zin, File destDirectory) throws IOException {
        // validamos que exista el directorio destino
        if (!destDirectory.exists()) {
            throw new IOException("No existe el directorio " + destDirectory.getAbsolutePath());
        }

        // validamos si es un directorio
        if (!destDirectory.isDirectory()) {
            throw new IOException("El destino " + destDirectory.getAbsolutePath() + " no es un directorio");
        }

        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String fileName = entry.getName();
            // validamos si el entry en directorio, entonces lo creamos
            if (entry.isDirectory()) {
                File dest = new File(destDirectory, fileName);
                if (!dest.mkdirs()) {
                    throw new IOException("No se puede crear el directorio" + dest.getParentFile());
                }
            } else {
                // se trata de un archivo, y lo creamos
                File dest = new File(destDirectory, fileName);
                // validamos si existe el directorio donde esta el fileName
                if (!dest.getParentFile().exists()) {
                    if (!dest.getParentFile().mkdirs()) {
                        throw new IOException("No se puede crear el directorio " + dest.getParentFile().getAbsolutePath());
                    }
                }
                // teniendo el directorio destino, solo creamos la informacion
                try ( FileOutputStream zout = new FileOutputStream(dest)) {
                    byte buffer[] = new byte[4096];
                    int n = zin.read(buffer);
                    // leemos hasta que n retorne un -1, pero eso no significa que el archivo termino
                    while (n != -1) {
                        // escribimos el contenido en el outputstream
                        zout.write(buffer, 0, n);
                        // leemos el sgte bloque
                        n = zin.read(buffer);
                    }
                }
                // imprimimos lo que acaba de generar
                System.out.println(dest.getAbsolutePath());
            }
            entry = zin.getNextEntry();
        }
    }

    public static void main(String[] args) throws IOException {
        try ( ZipInputStream zin = new ZipInputStream(new FileInputStream("D:/Users/PC/Documents/src.zip"))) {
            // listZip(zin);
            unZip(zin, new File("D:/Users/PC/Documents/learnJava"));
        }
    }
}
