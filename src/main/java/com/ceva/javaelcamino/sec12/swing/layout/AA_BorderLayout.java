/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * El contenedor que usa por defecto java es BorderLayout. Este tipo de contenedor ubica los componentes
 * en cinco regiones de la ventana como North, South, East, West, Center.
 * El border layout necesita que le especifiquemos en que zona se ubicaran los elementos.
 */
public class AA_BorderLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("BorderLayout demo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                // las 5 posiciones del BorderLayout
                frame.add(new JButton("PAGE_START"), BorderLayout.PAGE_START);
                frame.add(new JButton("PAGE_END"), BorderLayout.PAGE_END);
                frame.add(new JButton("LINE_START"), BorderLayout.LINE_START);
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);
                frame.add(new JButton("LINE_END"), BorderLayout.LINE_END);
                
                frame.setVisible(true);
            }
        });
    }
}
