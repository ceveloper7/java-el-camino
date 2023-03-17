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
 * @author rcosio
 * @date 9/nov/2019 11:23 pm
 */
public class GearTest3 extends JPanel {
    private static final boolean animationEnabled = true;
    private double offsetAngle = 0;
    private javax.swing.Timer timer;

    public GearTest3() {
        setBackground(Color.BLACK);
        if (animationEnabled) {
            timer = new javax.swing.Timer(1000/12, e -> {
                offsetAngle = (offsetAngle + ((360.0/60) / 3.0)) % 360.0;
                repaint();
            });
            timer.start();
        }
    }
    
    private void paintGear(Graphics2D g2d, int x, int y, int size, int numTeeth, int teethSize, double offsetAngle, Color color) {
        int xPoints[] = new int[4*numTeeth];
        int yPoints[] = new int[xPoints.length];
        double radius = size/2;
        double innerRadius = radius - teethSize;
        double axeWidth = radius * 0.1;
        int xCenter = x + size/2;
        int yCenter = y + size/2;
        double gapOuter = (360.0/(numTeeth*2)*0.6);
        double gapInner = (360.0/(numTeeth*2))*1.4;
        for (int n=0; n<numTeeth; n++) {
            double curAngle = (270.0 + offsetAngle + n*(360/numTeeth));
            double angle = (curAngle - gapInner/2) % 360.0;
            int tx = xCenter + (int) (Math.cos(Math.toRadians(angle)) * innerRadius);
            int ty = yCenter + (int) (Math.sin(Math.toRadians(angle)) * innerRadius);
            xPoints[n*4] = tx;
            yPoints[n*4] = ty;
            angle = (curAngle - gapOuter/2) % 360.0;
            tx = xCenter + (int) (Math.cos(Math.toRadians(angle)) * radius);
            ty = yCenter + (int) (Math.sin(Math.toRadians(angle)) * radius);
            xPoints[n*4 + 1] = tx;
            yPoints[n*4 + 1] = ty;
            angle = (curAngle + gapOuter/2) % 360.0;
            tx = xCenter + (int) (Math.cos(Math.toRadians(angle)) * radius);
            ty = yCenter + (int) (Math.sin(Math.toRadians(angle)) * radius);
            xPoints[n*4 + 2] = tx;
            yPoints[n*4 + 2] = ty;
            angle = (curAngle + gapInner/2) % 360.0;
            tx = xCenter + (int) (Math.cos(Math.toRadians(angle)) * innerRadius);
            ty = yCenter + (int) (Math.sin(Math.toRadians(angle)) * innerRadius);
            xPoints[n*4 + 3] = tx;
            yPoints[n*4 + 3] = ty;
        }
        Polygon p = new Polygon(xPoints, yPoints, xPoints.length);
        g2d.setColor(color);
        g2d.fillPolygon(p);
        
        radius = (size/2) - teethSize*2;
        g2d.setColor(getBackground());
        g2d.fillOval(xCenter - (int)(radius), yCenter - (int)(radius), (int)radius*2, (int)radius*2);
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
    
    private void done() {
        if (timer != null)
            timer.stop();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int radius = 100;
        int teethSize = 10;
        int centerX = 150;
        int centerY = 125;
        paintGear(g2d, (int)(centerX-radius), (int)(centerY-radius), (int)(radius*2), 30, teethSize, offsetAngle, Color.PINK);
        
        int x = centerX + radius - teethSize;
        int y = centerY - radius;
        paintGear(g2d, x, y, radius*2, 30, teethSize, -offsetAngle+6, Color.RED);
        
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x-5, y-5, 10, 10);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final GearTest3 panel = new GearTest3();
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
