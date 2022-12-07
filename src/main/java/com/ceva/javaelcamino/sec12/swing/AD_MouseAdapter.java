/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Los adapters: Nos permite agregar la funcionalidad que queremos a algunos eventos especificos, sin tenr
 * la necesidad de implementar todos los eventos de la interface
 */
public class AD_MouseAdapter extends JFrame{
    public AD_MouseAdapter(String title){
        super(title);
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AD_MouseAdapter frame = new AD_MouseAdapter("Mouse Adapter demo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                
                frame.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.printf("mouse clicked at (%d, %d)\n", e.getPoint().x, e.getPoint().y);
                    }
                    
                });
                
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
}
