/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec13.java2D;

import java.awt.GraphicsEnvironment;

/**
 *
 * @author PC
 */
public class AE_ListFontsInstalled {
    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String familyNames[] = ge.getAvailableFontFamilyNames();
        
        for(String name : familyNames){
            System.out.println(name);
        }
    }
}
