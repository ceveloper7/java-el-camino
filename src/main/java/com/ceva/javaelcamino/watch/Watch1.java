/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.watch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class Watch1 extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        // forma del reloj: es un circulo responsivo
        Rectangle bounds = getBounds(); // obtenemos Jpanel size
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK); // color de fondo
        g2d.fillRect(0, 0, bounds.width, bounds.height); // pintamos el fondo
        
        /**
         * Conforme se redimensiona la pantalla se forma un ovalo pero el reloj siempre debe ser un circulo
         * para determinar el tamanio el ancho debe ser igual al alto
         */
        int size = Math.min(bounds.width, bounds.height) - 1;// obtenemos el minimo valor entre bounds.width y bounds.height
        
        /**
         * bounds.width/2 - size/2 -> obtenemos la coordenada x centrada
         * bounds.height/2 - size/2 -> obtenemos la coordenada y centrada
         * size -> with
         * size -> height
         */
        Rectangle frame = new Rectangle(bounds.width/2 - size/2, bounds.height/2 - size/2, size, size);
        
        g2d.setColor(new Color(0, 0x80, 0)); // color de linea
        g2d.drawOval(frame.x, frame.y, frame.width, frame.height); // dibujamos el circulo
        // fin forma del reloj
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Watch1 mainPanel = new Watch1();
            JFrame frame = new JFrame();
            frame.setTitle("Watch 1");
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
