package com.ceva.section13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class FFontMetricsDemo extends JPanel{

    private void paint0(Graphics2D g2d) {
        // obtenemos una copia del font de graphics2d
        Font saveFont = g2d.getFont();
        // usamos el tipo de letra por defecto del graphics2d
        Font font = saveFont.deriveFont(92f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);

        g2d.drawString(str, 0, 80);

        // regresamos el font que tenia graphics2d
        g2d.setFont(saveFont);
    }

    // escribimos texto usando font metrics
    private void paint1(Graphics2D g2d) {
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(16f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(str, 0, fm.getAscent());
        g2d.setFont(saveFont);
    }

    // dibujamos un rectangulo para saber el tamanio del texto
    private void paint2(Graphics2D g2d) {
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        // centrado de texto
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, getHeight()/2);
        g2d.setFont(saveFont);
    }

    private void paint3(Graphics2D g2d) {
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        String str = "ajglky";
        g2d.setColor(Color.BLACK);
        int y = getHeight()/2;

        g2d.setColor(Color.BLUE);           // Azul = Linea base
        g2d.drawLine(0, y, getWidth(), y);
        g2d.setColor(Color.RED);            // Rojo = linea Ascent

        g2d.drawLine(0, y-fm.getAscent(), getWidth(), y-fm.getAscent());
        g2d.setColor(new Color(0, 128, 0)); // Verde = Descent
        g2d.drawLine(0, y+fm.getDescent(), getWidth(), y+fm.getDescent());

        g2d.setColor(Color.DARK_GRAY);      // Gris = Height
        g2d.fillRect(getWidth()-10, y-fm.getAscent(), 10, fm.getHeight());

        g2d.setColor(Color.BLACK);
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, y);
        g2d.setFont(saveFont);

        System.out.println("Height:  " + fm.getHeight()); /* height es la suma de ascent+descent+leading222222222222222*/
        System.out.println("Ascent:  " + fm.getAscent());
        System.out.println("Descent: " + fm.getDescent());
        System.out.println("Leading: " + fm.getLeading());
    }

    /* texto centrado */
    private void paint4(Graphics2D g2d) {
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        String str = "ÉajglkyÄÑ";
        g2d.setColor(Color.BLACK);
        int y = getHeight()/2 - fm.getHeight()/2 + fm.getAscent();

        g2d.setColor(Color.ORANGE);         // Amarillo = Centro de JPanel
        g2d.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        g2d.setColor(Color.BLUE);           // Azul = Linea base
        g2d.drawLine(0, y, getWidth(), y);
        g2d.setColor(Color.RED);            // Rojo = Ascent
        g2d.drawLine(0, y-fm.getAscent(), getWidth(), y-fm.getAscent());
        g2d.setColor(new Color(0, 128, 0)); // Verde = Descent
        g2d.drawLine(0, y+fm.getDescent(), getWidth(), y+fm.getDescent());

        g2d.setColor(Color.DARK_GRAY);      // Gris = Height
        g2d.fillRect(getWidth()-10, y-fm.getAscent(), 10, fm.getHeight());

        g2d.setColor(Color.BLACK);
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, y);
        g2d.setFont(saveFont);
    }

    private Rectangle getStringBounds(Graphics2D g2d, String str) {
        FontRenderContext frc = g2d.getFontRenderContext();
        GlyphVector gv = g2d.getFont().createGlyphVector(frc, str);
        return gv.getPixelBounds(null, 0f, 0f);
    }

    /* centrado de texto basado en las metricas del texto y no las de Fontmetrics */
    private void paint5(Graphics2D g2d) {
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        String str = "ÉajglkyÄÑ";
        g2d.setColor(Color.BLACK);

        Rectangle r = getStringBounds(g2d, str);
        int y = getHeight()/2 + r.height/2 - fm.getDescent();

        g2d.setColor(Color.ORANGE);         // Amarillo = Centro de JPanel
        g2d.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        g2d.setColor(Color.BLUE);           // Azul = Linea base
        g2d.drawLine(0, y, getWidth(), y);
        g2d.setColor(Color.RED);            // Rojo = Ascent
        g2d.drawLine(0, y-fm.getAscent(), getWidth(), y-fm.getAscent());
        g2d.setColor(new Color(0, 128, 0)); // Verde = Descent
        g2d.drawLine(0, y+fm.getDescent(), getWidth(), y+fm.getDescent());

        g2d.setColor(Color.DARK_GRAY);      // Gris = Height
        g2d.fillRect(getWidth()-10, y-fm.getAscent(), 10, fm.getHeight());

        g2d.setColor(Color.BLACK);
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, y);
        g2d.setFont(saveFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paint5(g2d);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("FontMetricsDemo - Raul Cosio");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new FFontMetricsDemo());
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}







































































