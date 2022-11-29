/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec10.recursividad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author PC
 */
public class AE_ZipCompress {
    
    private static void addFile(File file, String fileName, ZipOutputStream zout) throws IOException{
        // ZipEntry -> permite agregar un archivo al zip
        ZipEntry entry = new ZipEntry(fileName);
        // agregamos el archivo al ZipOutputStream
        zout.putNextEntry(entry);
        // guardamos la info en ZipOutputStream
        byte buffer[] = new byte[4096];
        // escribimos el contenido del archivo a agregar
        try(InputStream in = new FileInputStream(file)){
            // leemos hasta 4096 bytes y lo guardamos en el array buffer
            // n -> contiene el numero de bytes que leyo o -1 si ya termino
            int n = in.read(buffer);
            while(n != -1){
                // escribimos en zout el buffer, desde la posicion 0 hasta n bytes
                zout.write(buffer, 0, n);
                n = in.read(buffer);
            }
        }
        zout.closeEntry();
    }
    
    public static void recursiveAddFile(File source, String path, ZipOutputStream zout) throws IOException{
        path = path.length() > 0 ? path + "/" + source.getName() : source.getName();
        // si source se trata de un archivo
        if(source.isFile()){
            // lo agregamos al ZipOutputStream
            addFile(source, path, zout);
            System.out.println(path);
        }// si source es un directorio
        else{
            // aplicamos recursividad
            // listamos todos los archivos
            File list[] = source.listFiles();
            for(File f : list){
                recursiveAddFile(f, path, zout);
            }
        }
    }
    
    public static void compress(File source, File destino)throws IOException{
        if(destino.exists()){
            throw new IOException("El archivo " + destino.getAbsolutePath() + " ya existe");
        }
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(destino))){
            recursiveAddFile(source, "", zout);
        }
    }
    
    public static void main(String[] args)throws IOException {
        File source = new File("D:/Users/PC/Documents/NetBeansProjects/JavaElCamino/src");
        File destino = new File("D:/Users/PC/Documents/src.zip");
        compress(source, destino);
    }
}
