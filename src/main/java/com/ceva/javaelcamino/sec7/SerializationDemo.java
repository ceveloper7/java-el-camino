/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author PC
 */
public class SerializationDemo implements Serializable {
    private static final long serialVersionUID = 1000L;
    private String myString;
    private transient int n; // este campo no sera serializado
    
    private static void serializeDemo() throws IOException{
        try(FileOutputStream fout = new FileOutputStream("c:/temp/object.dat")){
            ObjectOutput s = new ObjectOutputStream(fout);
            s.writeObject("Esta es una prueba");
            s.writeObject(new java.util.Date());
            s.close();
        }
    }
    
    private static void deSerializeDemo()throws IOException{
        try(InputStream in = new FileInputStream("c:/temp/object.dat")){
            ObjectInput s = new ObjectInputStream(in);
            String str = (String)s.readObject();
            java.util.Date d = (java.util.Date)s.readObject();
            System.out.println("mensaje = " + str);
            System.out.println("fecha = " + d);
        }catch(ClassNotFoundException ex){
            System.out.println(ex.getClass().getName() + " generated: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        // serializeDemo();
        deSerializeDemo();
    }
}
