/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AD_Drawing extends JPanel{
    public AD_Drawing(){
        super();
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2d = (Graphics2D)g;
        // Obtenemos el ancho de la ventana
        int width = getWidth();
        int height = getHeight();
        
        // coordenada inicial x
        int xPos = 0;
        int yPos = 0;
        
        // ancho y alto actual
        int currentWidth = width;
        int currentHeight = height;
        
        for(int n=0; n < 16; n++){
            g2d.setColor(new Color(0, (256/16)*n, 0));
            g2d.fillRect(xPos, yPos, width, height);
            // ajustamos los valores para la siguiente iteracion
            xPos = xPos + (width/32);
            yPos = yPos + (height/32);
            currentWidth = currentWidth - (width/16);
            currentHeight = currentHeight - (height/16);
        }
    }
    
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Java 2D Drawing demo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.setContentPane(new AD_Drawing());
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
