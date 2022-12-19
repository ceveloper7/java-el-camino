/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AB_Drawing extends JPanel{
    public AB_Drawing(){
        super();
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2d = (Graphics2D)g;
        // Dibujamos ovalo
        g2d.setColor(Color.yellow);
        g2d.drawOval(0, 0, 100, 100);
        
        // dibujamos rectangulo
        g2d.setColor(Color.RED);
        g2d.drawRect(120, 120, 100, 100);
        
        // dibujamos un arco
        g2d.setColor(Color.GREEN);
        g2d.drawRect(10, 100, 100, 100);
        g2d.fillArc(10, 100, 100, 100, 0, 60);
        
        // dibujamos un rectangulo relleno con ezquinas redondeadas
        g2d.setColor(Color.CYAN);
        g2d.fillRoundRect(200, 10, 150, 100, 40, 40);
        
        /**
         * Dibujar un Triangulo: Por medio de la clase que dibuja un poligono, podemos crear un triangulo
         * Para crear un poligono debemos establecer puntos los cuales luego son unidos y asi obtenemos
         * la forma deseada. Para dibujar un triangulo necesito 3 puntos:
         * xPoints[] -> corresponde a las coordenadas x de los 3 puntos
         * yPoints[] -> corresponde a las coordenadas y de los 3 puntos
         * Las coordenadas son: x=300 y=300, x=350 y=300 x=250 y=300
         */
        g2d.setColor(Color.ORANGE);
        int xPoints[] = {300,350,250};
        int yPoints[] = {200,300,300};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
    
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Java 2D Drawing demo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.setContentPane(new AB_Drawing());
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
