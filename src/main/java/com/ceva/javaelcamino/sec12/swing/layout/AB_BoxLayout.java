/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * BoxLayout acomoda los componentes uno encima del otro o en un mismo renglon
 */
public class AB_BoxLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("BoxLayout demo");
                BoxLayout layout = new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS);
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                
                for(int n = 0; n < 4; n++){
                    JButton button = new JButton("Test " + n);
                    button.setAlignmentX(Component.CENTER_ALIGNMENT);
                    frame.add(button);
                }
                
                frame.setVisible(true);
            }
        });
    }
}
