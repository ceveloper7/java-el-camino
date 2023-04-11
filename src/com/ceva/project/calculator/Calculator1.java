package com.ceva.project.calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Calculator1 extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        Rectangle r = new Rectangle(100, 100, 100, 200);
        // dibujamos 2 rectangulos
        g2d.drawRect(100, 100, 100, 100);
        g2d.drawRect(100, 200, 100, 100);

        // w -> define el padding (15% del ancho del rectangulo) con respecto al rectangulo interno
        int w = (int)(r.width * 0.15f);
        // gap -> distancia entre cada segemento
        int gap = (int)(r.width * 0.03f);

        Point[] points = new Point[]{
                // establecemos los puntos requeridos para el primer segmento externo
                new Point(r.x, r.y),
                new Point(r.x + r.width, r.y),
                new Point(r.x + r.width, r.y + r.height/2),
                new Point(r.x, r.y + r.height / 2),

                // establecemos los puntos requeridos para el prier segmento interno
                new Point(r.x + w, r.y + w),
                new Point(r.x + r.width - w, r.y + w),
                new Point(r.x + r.width - w, r.y + r.height/2 - w),
                new Point(r.x + w, r.y + r.height / 2 - w),

                new Point(r.x + r.width, r.y + r.height),
                new Point(r.x, r.y + r.height),
                new Point(r.x + w, r.y + r.height/2 + w),
                new Point(r.x + r.width - w, r.y + r.height/2 + w),
                new Point(r.x + r.width - w, r.y + r.height - w),
                new Point(r.x + w, r.y + r.height - w)
        };

        // Creacion de los poligonos
        Polygon segments[] = new Polygon[7];
        Polygon p;
        p = new Polygon();
        p.addPoint(points[0].x + gap, points[0].y);
        p.addPoint(points[1].x - gap, points[1].y);
        p.addPoint(points[5].x - gap, points[5].y);
        p.addPoint(points[4].x + gap, points[4].y);
        segments[0] = p;

        p = new Polygon();
        p.addPoint(points[0].x, points[0].y + gap);
        p.addPoint(points[4].x, points[4].y + gap);
        p.addPoint(points[7].x, points[7].y - gap);
        p.addPoint(points[3].x, points[3].y - gap);
        segments[1] = p;

        p = new Polygon();
        p.addPoint(points[1].x, points[1].y + gap);
        p.addPoint(points[5].x, points[5].y + gap);
        p.addPoint(points[6].x, points[6].y - gap);
        p.addPoint(points[2].x, points[2].y - gap);
        segments[2] = p;

        p = new Polygon();
        p.addPoint(points[6].x - gap, points[6].y);
        p.addPoint(points[7].x + gap, points[7].y);
        p.addPoint(points[3].x + gap, points[3].y);
        p.addPoint(points[10].x + gap, points[10].y);
        p.addPoint(points[11].x - gap, points[11].y);
        p.addPoint(points[2].x - gap, points[2].y);
        segments[3] = p;

        p = new Polygon();
        p.addPoint(points[3].x, points[3].y + gap);
        p.addPoint(points[10].x, points[10].y + gap);
        p.addPoint(points[13].x, points[13].y - gap);
        p.addPoint(points[9].x, points[9].y - gap);
        segments[4] = p;

        p = new Polygon();
        p.addPoint(points[2].x, points[2].y + gap);
        p.addPoint(points[11].x, points[11].y + gap);
        p.addPoint(points[12].x, points[12].y - gap);
        p.addPoint(points[8].x, points[8].y - gap);
        segments[5] = p;

        p = new Polygon();
        p.addPoint(points[12].x - gap, points[12].y);
        p.addPoint(points[13].x + gap, points[13].y);
        p.addPoint(points[9].x + gap, points[9].y);
        p.addPoint(points[8].x - gap, points[8].y);
        segments[6] = p;

        g2d.setColor(Color.RED);
        g2d.fillPolygon(segments[0]);

        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(segments[1]);

        g2d.setColor(Color.BLUE);
        g2d.fillPolygon(segments[2]);

        g2d.setColor(Color.MAGENTA);
        g2d.fillPolygon(segments[3]);

        g2d.setColor(Color.RED);
        g2d.fillPolygon(segments[4]);

        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(segments[5]);

        g2d.setColor(Color.BLUE);
        g2d.fillPolygon(segments[6]);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            Calculator1 mainPanel = new Calculator1();

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

