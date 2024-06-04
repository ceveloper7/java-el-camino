package com.ceva.section7.apijava2;

import java.io.File;

/**
 * Programa que muestra el contenido del directorio /home/ceva/Documents
 * La informacion se alinea dependiendo del tamano del nombre de archivo mas grande que encuentre
 */
public class BFileDemo {
    private static StringBuilder temp =  new StringBuilder();

    // metodo que recibe una cadena y rellena con espacios hasta alcanzar la longitud indicada
    private static String formatString(String str, int len){
        temp.setLength(0);
        temp.append(str);
        while(temp.length() < len){
            temp.append(" ");
        }
        return temp.toString();
    }

    public static void main(String[] args) {
        File base = new File("/home/ceva/Documents");
        File[] files = base.listFiles();
        int nameLen = 0;
        for(File file : files){
            nameLen = Math.max(nameLen, file.getName().length());
        }
        nameLen += 3;

        // encabezado
        System.out.print(formatString("Name", nameLen));
        System.out.print("Type             ");
        System.out.println("Size");

        StringBuilder sb = new StringBuilder();
        while (sb.length() < nameLen) {
            sb.append("-");
        }
        sb.append("-----------------");

        for(int n = 0; n < 12; n++){
            sb.append("-");
        }
        System.out.println(sb.toString());

        // contenido
        for(File file : files){
            sb.setLength(0);
            sb.append(formatString(file.getName(), nameLen));
            // validamos si es un archivo o un directorio
            sb.append(file.isFile() ? "File             " : "Folder           ");
            sb.append(file.length());
            System.out.println(sb.toString());
        }
    }
}
