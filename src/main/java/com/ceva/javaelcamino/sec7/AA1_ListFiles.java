/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.io.File;

/**
 *
 * @author PC
 */
public class AA1_ListFiles {
    private static StringBuilder temp = new StringBuilder();
    
    /**
     * 
     * @param str -> cadena a formatear
     * @param len -> logitud determina de la cadena
     * @return una cadena formateada y completada con espacios si se necesita para alcanzar el len especificado
     */
    private static String formatString(String str, int len){
        // 1. Inicializamos el StringBuilder
        temp.setLength(0);
        temp.append(str);
        while(temp.length() < len){
            temp.append(' ');
        }
        return temp.toString();
    }
    
    public static void main(String[] args) {
        File base = new File("c:\\elcaminojava\\learnJava\\build\\com\\ceva");
        File[] list = base.listFiles();
        int longitudNombreArchivo = 0;
        for(File f : list){
            longitudNombreArchivo = Math.max(longitudNombreArchivo, f.getName().length());
        }
        longitudNombreArchivo += 3;
        
        // cabecera
        System.out.print(formatString("Nombre", longitudNombreArchivo));
        System.out.print("Tipo         ");
        System.out.println("Tamanio");
        // fin cabecera
        
        // subrayado
        StringBuilder sb = new StringBuilder();
        while(sb.length() < longitudNombreArchivo){
            sb.append("*");
        }
        sb.append("-----------");
        
        for(int n = 0; n < 12; n++){
            sb.append("=");
        }
        System.out.println(sb.toString());
        // fin subrayados
        
        // print files & directories
        for(File f : list){
            sb.setLength(0);
            sb.append(formatString(f.getName(), longitudNombreArchivo));
            sb.append(f.isFile()? "archivo         " : "directorio    ");
            sb.append(f.length());
            System.out.println(sb.toString());
        }
        // end print files & directories
    }
}
