/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec10.recursividad;

import java.io.File;

/**
 *
 * @author PC
 */
public class AC_RecursiveDirectory {
    private int countFiles = 0;
    public void printDir(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                printDir(f);
            }
        }// si no es un directorio, entonces es un archivo
        else {
            // getAboslutePath retorna un string con el nombre y ruta del archivo
            // validamos imprimir unicamente archivo .java
                if (file.getName().endsWith(".java")) {
                    countFiles++;
                    System.out.println(file.getAbsolutePath());
                } 
        }
    }
    
    public int getCountFiles(){
        return countFiles;
    }

    public static void main(String[] args) {
        AC_RecursiveDirectory dir = new AC_RecursiveDirectory();
        dir.printDir(new File("d:/Users/PC/Documents/NetBeansProjects/JavaElCamino"));
        System.out.printf("Total: %d archivos(s). \n", dir.getCountFiles());
    }
}
