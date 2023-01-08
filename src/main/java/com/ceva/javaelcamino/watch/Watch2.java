/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.watch;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class Watch2 extends JPanel{
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
         * frame -> frame que describe al circulo
         */
        Rectangle frame = new Rectangle(bounds.width/2 - size/2, bounds.height/2 - size/2, size, size);
        
        /**
         * Dibujo de lineas para horas, minutos y segundos
         */
        // obtenemos el centro del circulo.
        int centerX = frame.x + frame.width / 2;
        int centerY = frame.y + frame.height / 2;
        double radius = frame.width / 2; // radio del circulo
        double innerRadius = radius * 0.95; // definimos un radio interno que es igual al 90% del radio
        
        Stroke simpleStroke = g2d.getStroke();
        Stroke boldStroke = new BasicStroke(2);
        
        g2d.setColor(new Color(0, 0x80, 0)); // color de linea
        for(int n = 0; n < 60; n++){
            // si n es divisible por 5 significa que estoy en la marca de una division de hora
            if((n % 5) == 0){
                innerRadius = radius * 0.9;
                g2d.setStroke(boldStroke);
            }else{
                innerRadius = radius * 0.95;
                g2d.setStroke(simpleStroke);
            }
            
            // angulo que tiene cada segundo 360/60
            double angle = (270 + n * (360/60)) % 360.0; // mod 360 nos permite un rango entre 0 y 360
            double coseno = Math.cos(Math.toRadians(angle));// calculamos el coseno de angulo pero primero lo convertimos a radianes
            double seno = Math.sin(Math.toRadians(angle));// calculamos el seno del a angulo
            
            // dibujamos la linea
            g2d.drawLine((int)(centerX + coseno * innerRadius), (int)(centerY + seno * innerRadius), 
                    (int)(centerX + coseno * radius), (int)(centerY + seno * radius));
        }
        // fin de lineas de reloj
        
        
        g2d.drawOval(frame.x, frame.y, frame.width, frame.height); // dibujamos el circulo
        // fin forma del reloj
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Watch2 mainPanel = new Watch2();
            JFrame frame = new JFrame();
            frame.setTitle("Watch 2");
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
