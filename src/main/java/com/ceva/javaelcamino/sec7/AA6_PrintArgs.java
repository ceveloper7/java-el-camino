/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

/**
 *
 * @author PC
 */
public class AA6_PrintArgs {
    public static void main(String[] args) {
        if(args.length > 0){
            System.out.println("Los argumentos recibidos son...");
            for(String arg : args){
                System.out.println(arg);
            }
        }else{
            System.out.println("No hay argumentos");
        }
    }
}
