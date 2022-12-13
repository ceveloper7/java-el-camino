/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing.layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * CardLayout: Este disenio consiste en agregar controles que se mstraran como una pila que muestra
 * unicamente el control frontal
 */
public class AC_CardLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("CardLayout demo");
                CardLayout layout = new CardLayout();
                // asignamos el nuevo layout
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                
                for(int n = 0; n < 4; n++){
                    JButton button = new JButton("Test " + n);
                    button.addActionListener(new AbstractAction(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            layout.next(frame.getContentPane());
                        }
                        
                    });
                    
                    button.setAlignmentX(Component.CENTER_ALIGNMENT);
                    frame.add(button);
                }
                
                frame.setVisible(true);
            }
        });
    }
}
