/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda.ch03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author PC
 */
public class FileTask {
    private void processFile(BufferedReaderProcessor p)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("quijote.txt", Charset.forName("UTF-8")))){
            p.process(br);
        }
    }
    
    public static void main(String[] args)throws IOException {
        FileTask ft = new FileTask();
        ft.processFile((BufferedReader br) -> {
            String line = br.readLine();
            while(line != null){
                System.out.println(line);
                line = br.readLine();
            }
        });
    }
}
