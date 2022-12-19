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
public class AC_Drawing extends JPanel{
    public AC_Drawing(){
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
        
        /**
         * Formula basada en recorrer un rango de numero de pasos. por ejemplo>
         * Si quiero recorrer un rango de 100 unidades en 10 pasos entonces a cada paso le corresponderia:
         * 100/10 unidades.
         * Si n es el numero de pasos entonces: (100/10)*n es el numero de unidades que corresponde a cada valor de n
         * El programa lo que hace es dibujar rectangulos centrados en diferentes intensidades de color.
         * 
         * Existen 256 tono de cada componente basico de color
         */
        // dibujamos 16 rectangulos
        for(int n=0; n < 16; n++){
            // elegimos el color: diviendo en numero de tono de color disponibles x el numero de rectangulos
            // multiplicado por n, retorna el tono de color que necesitamos
            g2d.setColor(new Color(0, (256/16)*n, 0));
            g2d.fillRect(n*width/32, n*height/32, width-n*width/16, height-n*height/16);
        }
    }
    
    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Java 2D Drawing demo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.setContentPane(new AC_Drawing());
                // centramos la ventana
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
