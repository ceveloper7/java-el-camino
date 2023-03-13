/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.watch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class GearTest1 extends JPanel {

    private static boolean animatioEnabled = true;
    private double offsetAngle = 10;
    javax.swing.Timer timer;

    public GearTest1() {
        setBackground(Color.BLACK);
        if (animatioEnabled) {
            // timer a 12 cuadros por segundo
            timer = new javax.swing.Timer(1000 / 12, e -> {
                offsetAngle = (offsetAngle + ((360.0/60) / 3.0)) % 360.0;
                repaint();
            });
            timer.start();
        }
    }

    private void done() {
        if(timer != null){
            timer.stop();
        }
    }
    
    /*
    * x, y: Coordenadas
    * size: diametro del engranaje
    * numTeeth: numero de dientes del engranaje
    * teethSize: tamanio del diente
    */
    private void paintGrear(Graphics2D g2d, int x, int y, int size, int numTeeth, int teethSize, double offsetAngle, Color color){
        int xPoints[] = new int[4 * numTeeth];
        int yPoints[] = new int[xPoints.length];
        double radius = size/2;
        double innerRadius = radius - teethSize;
        // ancho del eje 10% del radio del engrane
        double axeWidth = radius * 0.1;
        int xCenter = x + size/2;
        int yCenter = y + size/2;
        
        // calculamos la distancia en grados entre los espacio de cada cima y baya del engrane
        double gapOuter = (360.0/(numTeeth*2)*0.6);
        double gapInner = (360.0/(numTeeth*2)*1.4);
        
        // dibujamos los dientes
        for(int n = 0; n < numTeeth; n++){
            double curAngle = (270.0 + offsetAngle + n * (360/numTeeth));
            // angulo del primer punto
            double angle = (curAngle - gapInner/2) % 360.0;
            int tx = xCenter + (int)(Math.cos(Math.toRadians(angle)) * innerRadius);
            int ty = yCenter + (int)(Math.sin(Math.toRadians(angle)) * innerRadius);
            xPoints[n*4] = tx;
            yPoints[n*4] = ty;
            
            // angulo del 2do punto
            angle = (curAngle - gapOuter/2)%360.0;
            tx = xCenter + (int)(Math.cos(Math.toRadians(angle)) * radius);
            ty = yCenter + (int)(Math.sin(Math.toRadians(angle)) * radius);
            xPoints[n*4 + 1] = tx;
            yPoints[n*4 + 1] = ty;
            
            // angulo del 3er punto
            angle = (curAngle + gapOuter/2) % 360.0;
            tx = xCenter + (int)(Math.cos(Math.toRadians(angle)) * radius);
            ty = yCenter + (int)(Math.sin(Math.toRadians(angle)) * radius);
            xPoints[n*4 + 2] = tx;
            yPoints[n*4 + 2] = ty;
            
            // angulo del 4to punto
            angle = (curAngle + gapInner/2) % 360.0;
            tx = xCenter + (int)(Math.cos(Math.toRadians(angle)) * innerRadius);
            ty = yCenter + (int)(Math.sin(Math.toRadians(angle)) * innerRadius);
            xPoints[n*4 + 3] = tx;
            yPoints[n*4 + 3] = ty;
        }   
        Polygon p = new Polygon(xPoints, yPoints, xPoints.length);
        g2d.setColor(color);
        g2d.fillPolygon(p);
        
        // hueco en el engrane
        radius = (size/2) - teethSize * 2;
        g2d.setColor(getBackground());
        g2d.fillOval(xCenter - (int)radius, yCenter - (int)radius, (int)radius*2, (int)radius*2);
        
        // circulo para el eje
        g2d.setColor(color);
        g2d.fillOval(xCenter - (int)(radius*0.3), yCenter - (int)(radius*0.3), (int)(radius*0.6), (int)(radius*0.6));
        
        AffineTransform savet = g2d.getTransform();
        AffineTransform t = new AffineTransform();
        t.translate(xCenter, yCenter);
        t.rotate(Math.toRadians(offsetAngle));
        g2d.setTransform(t);
        g2d.fillRect((int)(-axeWidth/2), (int)(-radius*1.05), (int)(axeWidth), (int)(radius*2.1));
        g2d.fillRect((int)(-radius*1.05), (int)(-axeWidth/2), (int)(radius*2.1), (int)(axeWidth));
        g2d.setTransform(savet);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super .paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        int radius = 100;
        int teethSize = 10;
        int centerX = 150;
        int centerY = 125;
        paintGrear(g2d, centerX-radius, centerY-radius, radius*2, 30, teethSize, offsetAngle, Color.yellow);
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final GearTest1 panel = new GearTest1();
            JFrame frame = new JFrame("GearTest");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    panel.done();
                }
            });
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setContentPane(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
