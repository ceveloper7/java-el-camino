/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class AA15_CollectionProperties {
    static Properties props = new Properties();
    private static void writeProps(){
        props.put("color", "Red");
        props.put("maxMem", "512");
        props.put("message", "hello world");
        
        System.out.println(props.get("message"));
        
        // para almacenar las propiedades
        try(OutputStream out = new FileOutputStream("c://temp//params.props")){
            props.store(out, "Application properties");
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    private static void readProps(){
        try(InputStream in = new FileInputStream("c://temp/params.props")){
            props.load(in);
            System.out.println(props.get("maxMem"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        writeProps();
        readProps();
    }
}
