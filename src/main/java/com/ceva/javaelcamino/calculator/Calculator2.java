/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Calculator2 extends JPanel{
    // arreglo de poligonos para los segmentos del digito
    Polygon segments[];
    private int x;
    private int y;
    private int digitWidth;
    
    /**
     * Parametros para definir el tamanio de ventana
     * @param x
     * @param y
     * @param digitWidth ancho del digito 
     */
    public Calculator2(int x, int y, int digitWidth){
        super();
        this.x = x;
        this.y = y;
        this.digitWidth = digitWidth;
        initSegments();
    }
    
    private void initSegments(){
        // creamos los 7 segmentos para dibujar un digito
        segments = new Polygon[7];
        
        Rectangle r = new Rectangle(x, y, digitWidth, digitWidth * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        Rectangle r = new Rectangle(100, 100, 100, 200);
        // w espacio para el rectangulo interno
        int w = (int)(r.width * 0.15f);
        int gap = (int)(r.width * 0.03f);
        // nos aseguramos que siempre exista por lo menos 1 pixel de distancia
        if(gap == 0){
            gap = 1;
        }
        Point points[] = new Point[]{
            // representamos los puntos para el segmento 0 de nuestro disenio
            new Point(r.x + w * 2, r.y),
            new Point(r.x + r.width - w * 2, r.y),
            new Point(r.x + w, r.y + w),
            new Point(r.x + r.width - w, r.y + w),
            new Point(r.x + w * 2, r.y + w * 2),
            new Point(r.x + r.width - w * 2, r.y + w * 2)
        };
        
        // definicion de polygono
        Polygon p;
        p = new Polygon();
        p.addPoint(points[0].x + gap, points[0].y);
        p.addPoint(points[1].x - gap, points[1].y);
        p.addPoint(points[3].x - gap, points[3].y);
        p.addPoint(points[5].x - gap, points[5].y);
        p.addPoint(points[4].x + gap, points[4].y);
        p.addPoint(points[2].x + gap, points[2].y);
        
        segments[0] = p;
        
        // los segmentos 3 y 6 son similares al segmento 0 por lo que guardamos los puntos del segmento o (p)
        // arreglo de puntos x
        int xPoints[] = new int[p.npoints];
        // arreglo de puntos y
        int yPoints[] = new int[p.npoints];
        // segmento 3
        for(int n = 0; n < xPoints.length; n++){
            xPoints[n] = p.xpoints[n];
            yPoints[n] = p.ypoints[n] + r.height / 2 - w;
        }
        p = new Polygon(xPoints, yPoints, xPoints.length);
        segments[3] = p;
        
        // segmento 6
        for(int n = 0; n < xPoints.length; n++){
            yPoints[n] = p.ypoints[n] + r.height / 2 - w;
        }
        p = new Polygon(xPoints, yPoints, xPoints.length);
        segments[6] = p;
        
        // definicion de segmentos verticales que son 1, 2, 4, 5
        points = new Point[]{
            new Point(r.x + w, r.y + w),
            new Point(r.x, r.y + w * 2),
            new Point(r.x + w * 2, r.y + w * 2),
            new Point(r.x, r.y + r.height/2 - w),
            new Point(r.x + w * 2, r.y + r.height/2 - w),
        };
        p = new Polygon();
        p.addPoint(points[0].x, points[0].y + gap);
        segments[1] = p;
        
        // inicializamos los arreglos xPoints, yPoints para el manejo de los segmentos 2, 4, 5
        for(int n = 0; n < xPoints.length; n++){
            // movemos las coordenadas a la derecha para el segmento 2
            xPoints[n] = p.xpoints[n] + r.width - w * 2;
            yPoints[n] = p.ypoints[n];
        }
        p = new Polygon(xPoints, yPoints, xPoints.length);
        segments[2] = p;
        
        // segmento 4
        p = segments[1];
        for(int n = 0; n < xPoints.length; n++){
            xPoints[n] = p.xpoints[n];
            yPoints[n] = p.ypoints[n] + r.height/2 - w;
        }
        p = new Polygon(xPoints, yPoints, xPoints.length);
        segments[4] = p;
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            Calculator2 mainPanel = new Calculator2(10, 10, 100);
            
            JFrame frame = new JFrame();
            frame.setTitle("Calculator 1");
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
