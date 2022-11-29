/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Para leer un archivo de texto debemos utilizar un objeto Reader pero este objeto es limitado por solo lee caracteres
 * conviene mas trabajar con el objeto BufferedReader
 */
public class AA3_ReadTextFile {
    public static void main(String[] args) throws IOException {
        // lee la info en bloquedes y luego las entrega. Esta es una manera mas eficiente de leer la informacion
        /**
         * 1. Creamos un FilenputStream para el archivo txt que vamos a leer
         * 2. El constructor del InputStreamReader recibe el FileInputStreamReader el cual es envuelto para
         *    "convertirlo" en un InputStreamReader
         * 3. El objeto ya convertido en InputStreamReader lo enviamos al constructor del BufferedReader por lo
         *    al final envolvemos el InputStreamReader en un objeto BufferedReader
         */
        BufferedReader br = new BufferedReader(
                // BufferedReader necesita un reader, por lo que pasamos la clase InputStreamReader que implementa
                // a la clase Reader
                new InputStreamReader(
                        // InputStreaReader requiere StreamReader por lo que pasamos la clase FileInputStreanReader
                        // que implementa la clase InputStreamReader
                        new FileInputStream("C:\\temp\\quijote.txt"), "UTF-8"
                ));
        
        // leemos una linea de txto
        String line = br.readLine();
        // Si no hay mas por leer, retorna null
        while(line != null){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }
}
