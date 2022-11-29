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
public class AD_RecursiveFindFile {
    
    public static void findFile(File file, String name){
        if(file.getName().toUpperCase().equals(name.toUpperCase())){
            System.out.println(file.getAbsolutePath());
            return;
        }
        if(file.isDirectory()){
            for(File f : file.listFiles()){
                findFile(f, name);
            }
        }
    }
    
    public static void main(String[] args) {
        findFile(new File("D:/Users/PC/Documents/NetBeansProjects/JavaElCamino/src"), "AC_RecursiveDirectory.java");
    }
}
