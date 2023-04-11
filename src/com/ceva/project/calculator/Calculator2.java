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

public class Calculator2 extends JPanel{
    // definimos el bit que corresponde a cada segmento
    private static final int SEGMENT_0 = 0x1; // bit 1 - binario    1
    private static final int SEGMENT_1 = 0x2; // bit 2 - binario   10
    private static final int SEGMENT_2 = 0x4; // bit 4 - binario  100
    private static final int SEGMENT_3 = 0x8; // bit 8 - binario 1000
    private static final int SEGMENT_4 = 0x10; // bit 16
    private static final int SEGMENT_5 = 0x20; // bit 32
    private static final int SEGMENT_6 = 0x40; // bit 64

    private Color backgroundColor = new Color(0xc3cdc8); // color pantalla
    private Color segmentColor = new Color(0x000000);
    private Color disableSegmentColor = new Color(0,0,0,16);
    private Color shadowColor = new Color(0,0,0,64);

    // arreglo de poligonos para los segmentos del digito
    Polygon segments[];
    Polygon segmentsShadows[];
    // guardamos en cada bit del byte la representacion de uno de los segmentos, es decir, al tener 7 segmentos
    // usaremos 7 bits del byte para saber cual esta encendido y cual no,
    private byte segmentState;
    private int x;
    private int y;
    private int digitWidth;

    /*
              s0
        +------------+
        |            |
        |            |
     s1 |            | s2
        |            |
        |      s3    |
        +------------+
        |            |
        |            |
     s4 |            | s5
        |            |
        |      s6    |
        +------------+
     */

    /**
     * Parametros para definir el tamanio de ventana
     * @param x
     * @param y
     * @param digitWidth ancho del digito
     */
    public Calculator2(int x, int y, int digitWidth){
        super();
        setBackground(backgroundColor);
        // definimos los bits que vamos a encender
        this.segmentState = SEGMENT_1 | SEGMENT_2 | SEGMENT_3 | SEGMENT_5;
        this.x = x;
        this.y = y;
        this.digitWidth = digitWidth;
        initSegments();
    }

    private void initSegments(){
        // creamos los 7 segmentos para dibujar un digito
        segments = new Polygon[7];

        Rectangle r = new Rectangle(x, y, digitWidth, digitWidth * 2);
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
                new Point(r.x, r.y + w*2),
                new Point(r.x + w*2, r.y + w*2),
                new Point(r.x, r.y + r.height/2 - w),
                new Point(r.x + w*2, r.y + r.height/2 - w),
                new Point(r.x + w, r.y + r.height/2)
        };
        p = new Polygon();
        p.addPoint(points[0].x, points[0].y + gap);
        p.addPoint(points[2].x, points[2].y+gap);
        p.addPoint(points[4].x, points[4].y-gap);
        p.addPoint(points[5].x, points[5].y-gap);
        p.addPoint(points[3].x, points[3].y-gap);
        p.addPoint(points[1].x, points[1].y+gap);
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

        // segmento 5 (tomamos el segmento 4 y lo recorremos a la derecha)
        p = segments[2];
        for (int n = 0; n < xPoints.length; n++){
            xPoints[n] = p.xpoints[n];
            yPoints[n] = p.ypoints[n] + r.height/2 - w;
        }
        p = new Polygon(xPoints, yPoints, xPoints.length);
        segments[5] = p;

        // definimos los segmentos para sombras
        if(digitWidth >= 32){
            // aplicamos sombras
            int xOffset = (digitWidth/32) * 2; // un 32 avo del ancho del digito
            int yOffset = xOffset;
            segmentsShadows = new Polygon[segments.length];
            for(int nPoly = 0; nPoly < segmentsShadows.length; nPoly++){
                for(int n = 0; n < xPoints.length; n++){
                    xPoints[n] = segments[nPoly].xpoints[n] + xOffset;
                    yPoints[n] = segments[nPoly].ypoints[n] + yOffset;
                }
                p = new Polygon(xPoints, yPoints, xPoints.length);
                segmentsShadows[nPoly] = p;
            }
        }
    }

//    private void paintDigit(Graphics2D g2d){
//        byte mask = 1;
//        for(int n = 0; n < segments.length; n++){
//            // preguntamos si el primer bit esta encendido
//            if((segmentState & mask) != 0){
//                g2d.setColor(segmentColor);
//                g2d.fillPolygon(segments[n]);
//            }
//            mask *= 2;
//        }
//    }

    // manejamos deshabilitar el color cuando el segmento esta apagado
//    private void paintDigit(Graphics2D g2d){
//        byte mask = 1;
//        for(int n = 0; n < segments.length; n++){
//            // preguntamos si el primer bit esta encendido
//            if((segmentState & mask) != 0){
//                g2d.setColor(segmentColor);
//            }else{
//                g2d.setColor(disableSegmentColor);
//            }
//            g2d.fillPolygon(segments[n]);
//            mask *= 2;
//        }
//    }
    // manejamos una pequenia sobra en los segmentos
    private void paintDigit(Graphics2D g2d){
        byte mask = 1;
        for(int n = 0; n < segments.length; n++){
            // preguntamos si el primer bit esta encendido
            if((segmentState & mask) != 0){
                // dibujamos las sombras
                if(segmentsShadows != null){
                    g2d.setColor(shadowColor);
                    g2d.fillPolygon(segmentsShadows[n]);
                }
                g2d.setColor(segmentColor);
            }else{
                g2d.setColor(disableSegmentColor);
            }
            g2d.fillPolygon(segments[n]);
            mask *= 2;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        Rectangle r = new Rectangle(100, 100, 100, 200);
        paintDigit(g2d);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            Calculator2 mainPanel = new Calculator2(10, 10, 150);

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

