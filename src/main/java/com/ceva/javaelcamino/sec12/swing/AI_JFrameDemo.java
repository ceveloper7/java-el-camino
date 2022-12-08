/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Cerrar una ventana con previa confirmacion
 */
public class AI_JFrameDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("JFrameDemo");
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                
                // detectamos cuando se esta a punto de ser cerrada
                frame.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e) {
                        if(JOptionPane.showConfirmDialog(frame, "Esta seguro que desea cerrar la ventana?", 
                                "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ){
                            frame.dispose();
                        }
                    }
                    
                });
                
                frame.setMinimumSize(new Dimension(400, 300));
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
