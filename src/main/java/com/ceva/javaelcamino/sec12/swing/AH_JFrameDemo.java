/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AH_JFrameDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("JFrameDemo - JPanel");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                
                JPanel panel = new JPanel();
                panel.setBackground(Color.BLUE);
                
                frame.add(panel);
                //frame.setContentPane(panel);
                System.out.println("panel == contentPane? " + (frame.getContentPane() == panel));
                frame.setVisible(true);
            }
        });
    }
}
