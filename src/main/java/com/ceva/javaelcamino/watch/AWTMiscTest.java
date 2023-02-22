/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.watch;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AWTMiscTest extends JPanel{
    public AWTMiscTest(){
        setBackground(Color.BLACK);
    }
    
    private void test1(Graphics2D g2d){
        g2d.setColor(Color.YELLOW);
        Arc2D arc = new Arc2D.Float(50, 50, 200, 200, 0, 180, Arc2D.CHORD);
        g2d.fill(arc);
    }
    
    private void test2(Graphics2D g2d){
        int radius = 100;
        int centerX = 150;
        int centerY = 150;
        
        g2d.setColor(Color.YELLOW);
        Arc2D arc = new Arc2D.Float(centerX-radius, centerY-radius, radius*2, radius*2, 10, 180, Arc2D.CHORD);
        g2d.fill(arc);
        
        g2d.setColor(new Color(255, 0, 0, 128));
        arc = new Arc2D.Float(centerX-radius*1.1f, centerY-radius*1.1f, radius*2.2f, radius*2.2f, 170, 180, Arc2D.CHORD);
        g2d.fill(arc);
    }
    
    private void test3(Graphics2D g2d){
        int radius = 100;
        int centerX = 150;
        int centerY = 150;
        
        Arc2D arc = new Arc2D.Float(centerX-radius, centerY-radius, radius*2, radius*2, 10, 180, Arc2D.CHORD);
        // creamos un area a partir del arco arc
        Area a = new Area(arc);
        
        arc = new Arc2D.Float(centerX-radius*1.1f, centerY-radius*1.1f, radius*2.2f, radius*2.2f, 170, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        g2d.setColor(Color.YELLOW);
        g2d.fill(a);
    }
    
    private void test4(Graphics2D g2d){
        int radius = 100;
        int centerX = 150;
        int centerY = 150;
        // 1er arco
        Arc2D arc = new Arc2D.Float(centerX-radius, centerY-radius, radius*2, radius*2, 10, 180, Arc2D.CHORD);
        // creamos un area a partir del arco arc
        Area a = new Area(arc);
        
        // 2do arco
        arc = new Arc2D.Float(centerX-radius*1.1f, centerY-radius*1.1f, radius*2.2f, radius*2.2f, 170, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // 3er arco
        arc = new Arc2D.Float(centerX-radius*0.2f, centerY-radius*0.2f, radius*0.4f, radius*0.4f, 0, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        g2d.setColor(Color.YELLOW);
        g2d.fill(a);
    }
    
    private void test5(Graphics2D g2d){
        GeneralPath gp = new GeneralPath();
        gp.moveTo(100, 200);
        gp.lineTo(200, 200);
        gp.lineTo(200, 100);
        //gp.closePath(); opcional, si no indicamos closePath() automaticamente cierra los puntos
        
        g2d.setColor(Color.YELLOW);
        g2d.fill(gp);
    }
    
    private void test6(Graphics2D g2d){
        GeneralPath gp = new GeneralPath();
        gp.moveTo(100, 200);
        gp.lineTo(200, 200);
        gp.lineTo(200, 100);
        gp.quadTo(200, 200, 100, 200);
        gp.closePath();
        g2d.setColor(Color.YELLOW);
        g2d.fill(gp);
    }
    
    private void test7(Graphics2D g2d){
        int radius = 100;
        int centerX = 150;
        int centerY = 150;
        float smoothLen = 0.5f;
        
        // 1er arco
        Arc2D arc = new Arc2D.Float(centerX-radius, centerY-radius, radius*2, radius*2, 10, 180, Arc2D.CHORD);
        // creamos un area a partir del arco arc
        Area a = new Area(arc);
        
        // 2do arco
        arc = new Arc2D.Float(centerX-radius*1.1f, centerY-radius*1.1f, radius*2.2f, radius*2.2f, 170, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // 3er arco
        arc = new Arc2D.Float(centerX-radius*0.2f, centerY-radius*0.2f, radius*0.4f, radius*0.4f, 0, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // dibujamos una region
        int x1 = (int)(centerX + radius * (1 - smoothLen) * Math.cos(Math.toRadians(10)));
        int y1 = (int)(centerY - radius * (1 - smoothLen) * Math.sin(Math.toRadians(10)));
        
        int x2 = (int)(centerX + radius * Math.cos(Math.toRadians(10)));
        int y2 = (int)(centerY - radius * Math.sin(Math.toRadians(10)));
        
        int x3 = (int)(centerX + radius * Math.cos(Math.toRadians(10) + smoothLen));
        int y3 = (int)(centerX - radius * Math.sin(Math.toRadians(10) + smoothLen));
        
        g2d.setColor(Color.YELLOW);
        g2d.fill(a);
        
        // Agregamos la nueva cuerva
        GeneralPath gp = new GeneralPath();
        // movemos al punto 1
        gp.moveTo(x1, y1);
        // dibujamos la curva
        gp.quadTo(x2, y2, x3, y3);
        gp.lineTo(x2, y2);
        // cerramos la curva
        gp.closePath();
        // la pintamos con color rojo
        g2d.setColor(Color.RED);
        g2d.fill(gp);
    }
    
    private void test8(Graphics2D g2d){
        int radius = 100;
        int centerX = 150;
        int centerY = 150;
        float smoothLen = 0.5f;
        
        // 1er arco
        Arc2D arc = new Arc2D.Float(centerX-radius, centerY-radius, radius*2, radius*2, 10, 180, Arc2D.CHORD);
        // creamos un area a partir del arco arc
        Area a = new Area(arc);
        
        // 2do arco
        arc = new Arc2D.Float(centerX-radius*1.1f, centerY-radius*1.1f, radius*2.2f, radius*2.2f, 170, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // 3er arco
        arc = new Arc2D.Float(centerX-radius*0.2f, centerY-radius*0.2f, radius*0.4f, radius*0.4f, 0, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // dibujamos una region
        int x1 = (int)(centerX + radius * (1 - smoothLen) * Math.cos(Math.toRadians(10)));
        int y1 = (int)(centerY - radius * (1 - smoothLen) * Math.sin(Math.toRadians(10)));
        
        int x2 = (int)(centerX + radius * Math.cos(Math.toRadians(10)));
        int y2 = (int)(centerY - radius * Math.sin(Math.toRadians(10)));
        
        int x3 = (int)(centerX + radius * Math.cos(Math.toRadians(10) + smoothLen));
        int y3 = (int)(centerX - radius * Math.sin(Math.toRadians(10) + smoothLen));
        
        int x4 = (int)(centerX + radius * 1.05 * Math.cos(Math.toRadians(10) + smoothLen));
        int y4 = (int)(centerX - radius * 1.05 * Math.sin(Math.toRadians(10) + smoothLen));
        
        g2d.setColor(Color.YELLOW);
        g2d.fill(a);
        
        // Agregamos la nueva cuerva
        GeneralPath gp = new GeneralPath();
        // movemos al punto 1
        gp.moveTo(x1, y1);
        // dibujamos la curva
        gp.quadTo(x2, y2, x3, y3);
        gp.lineTo(x4, y4);
        x2 = (int)(centerX + radius * 1.05 * Math.cos(Math.toRadians(10-1)));
        y2 = (int)(centerY - radius * 1.05 * Math.sin(Math.toRadians(10-1)));
        gp.lineTo(x2, y2);
        // cerramos la curva
        gp.closePath();
        // la pintamos con color rojo
        g2d.setColor(Color.RED);
        g2d.fill(gp);
    }
    
    private void test9(Graphics2D g2d){
        int radius = 100;
        int centerX = 150;
        int centerY = 150;
        float smoothLen = 0.15f;
        
        // 1er arco
        Arc2D arc = new Arc2D.Float(centerX-radius, centerY-radius, radius*2, radius*2, 10, 180, Arc2D.CHORD);
        // creamos un area a partir del arco arc
        Area a = new Area(arc);
        
        // 2do arco
        arc = new Arc2D.Float(centerX-radius*1.1f, centerY-radius*1.1f, radius*2.2f, radius*2.2f, 170, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // 3er arco
        arc = new Arc2D.Float(centerX-radius*0.2f, centerY-radius*0.2f, radius*0.4f, radius*0.4f, 0, 180, Arc2D.CHORD);
        a.subtract(new Area(arc));
        
        // dibujamos una region
        int x1 = (int)(centerX + radius * (1 - smoothLen) * Math.cos(Math.toRadians(10)));
        int y1 = (int)(centerY - radius * (1 - smoothLen) * Math.sin(Math.toRadians(10)));
        
        int x2 = (int)(centerX + radius * Math.cos(Math.toRadians(10)));
        int y2 = (int)(centerY - radius * Math.sin(Math.toRadians(10)));
        
        int x3 = (int)(centerX + radius * Math.cos(Math.toRadians(10) + smoothLen));
        int y3 = (int)(centerX - radius * Math.sin(Math.toRadians(10) + smoothLen));
        
        int x4 = (int)(centerX + radius * 1.05 * Math.cos(Math.toRadians(10) + smoothLen));
        int y4 = (int)(centerX - radius * 1.05 * Math.sin(Math.toRadians(10) + smoothLen));
        
        // Agregamos la nueva cuerva
        GeneralPath gp = new GeneralPath();
        // movemos al punto 1
        gp.moveTo(x1, y1);
        // dibujamos la curva
        gp.quadTo(x2, y2, x3, y3);
        gp.lineTo(x4, y4);
        x2 = (int)(centerX + radius * 1.05 * Math.cos(Math.toRadians(10-1)));
        y2 = (int)(centerY - radius * 1.05 * Math.sin(Math.toRadians(10-1)));
        gp.lineTo(x2, y2);
        // cerramos la curva
        gp.closePath();
        
        a.subtract(new Area(gp));
        // la pintamos con color rojo
        g2d.setColor(Color.YELLOW);
        g2d.fill(a);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        test9(g2d);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            AWTMiscTest panel = new AWTMiscTest();
            JFrame frame = new JFrame("AWTMiscTest");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setMaximumSize(new Dimension(400, 300));
            frame.setLocationRelativeTo(null);
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
