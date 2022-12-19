/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Ejemplo de texto perfectamente centrado
 * @author PC
 */
public class AF_FontMetrics extends JPanel {
    private void paint0(Graphics2D g2d){
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);
        
        g2d.drawString(str, 0, 80);
        g2d.setFont(saveFont);
    }
    
    private void paint1(Graphics2D g2d){
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);
        
        FontMetrics fm = g2d.getFontMetrics();
        // texto centrado
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, fm.getAscent());
        g2d.setFont(saveFont);
    }
    
    private void paint2(Graphics2D g2d){
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        
        int y = getHeight()/2;
        
        // linea  base
        g2d.setColor(Color.blue);
        g2d.drawLine(0, y, getWidth(), y);
        
        // linea roja arriba (Ascent)
        g2d.setColor(Color.RED);
        g2d.drawLine(0, y-fm.getAscent(), getWidth(), y-fm.getAscent());
        
        // linea roja (Descent)
        g2d.setColor(new Color(0,128,0));
        g2d.drawLine(0, y+fm.getDescent(), getWidth(), y+fm.getDescent());
        
        g2d.setColor(Color.DARK_GRAY);
        
        g2d.fillRect(getWidth()-10, y-fm.getAscent(), 10, fm.getHeight());
        
        // texto centrado, el texto lo dibujamos en la coordenada 
        g2d.setColor(Color.BLACK);
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, y);
        
        // coordenadas
        System.out.println("Height: " + fm.getHeight()); // height = ascent + descent + leading
        System.out.println("Ascent: " + fm.getAscent());
        System.out.println("Descent: " + fm.getDescent());
        System.out.println("Leading: " + fm.getLeading());
        
        g2d.setFont(saveFont);
        
    }
    
    private void paint3(Graphics2D g2d){
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        
        // getHeight()/2; // obtenemos el punto medio de la pantalla
        int y = getHeight()/2 - fm.getHeight()/2 + fm.getAscent();
        // linea  base
        g2d.setColor(Color.blue);
        g2d.drawLine(0, y, getWidth(), y);
        
        // linea roja arriba (Ascent)
        g2d.setColor(Color.RED);
        g2d.drawLine(0, y-fm.getAscent(), getWidth(), y-fm.getAscent());
        
        // linea roja (Descent)
        g2d.setColor(new Color(0,128,0));
        g2d.drawLine(0, y+fm.getDescent(), getWidth(), y+fm.getDescent());
        
        g2d.setColor(Color.DARK_GRAY);
        
        g2d.fillRect(getWidth()-10, y-fm.getAscent(), 10, fm.getHeight());
        
        // texto centrado, el texto lo dibujamos en la coordenada 
        g2d.setColor(Color.BLACK);
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, y);
        
        // coordenadas
        System.out.println("Height: " + fm.getHeight()); // height = ascent + descent + leading
        System.out.println("Ascent: " + fm.getAscent());
        System.out.println("Descent: " + fm.getDescent());
        System.out.println("Leading: " + fm.getLeading());
        
        g2d.setFont(saveFont);
    }
    
    private Rectangle getStringBounds(Graphics2D g2d, String str){
        FontRenderContext frc = g2d.getFontRenderContext();
        GlyphVector gv = g2d.getFont().createGlyphVector(frc, str);
        return gv.getPixelBounds(null, 0f, 0f);
    }
    
    private void paint4(Graphics2D g2d){
        Font saveFont = g2d.getFont();
        Font font = saveFont.deriveFont(96f);
        g2d.setFont(font);
        String str = "ajglky";
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        
        Rectangle r = getStringBounds(g2d, str);
        
        // getHeight()/2; // obtenemos el punto medio de la pantalla
        int y = getHeight()/2 + r.height/2 - fm.getAscent();
        // linea  base
        g2d.setColor(Color.blue);
        g2d.drawLine(0, y, getWidth(), y);
        
        // linea roja arriba (Ascent)
        g2d.setColor(Color.RED);
        g2d.drawLine(0, y-fm.getAscent(), getWidth(), y-fm.getAscent());
        
        // linea roja (Descent)
        g2d.setColor(new Color(0,128,0));
        g2d.drawLine(0, y+fm.getDescent(), getWidth(), y+fm.getDescent());
        
        g2d.setColor(Color.DARK_GRAY);
        
        g2d.fillRect(getWidth()-10, y-fm.getAscent(), 10, fm.getHeight());
        
        // texto centrado, el texto lo dibujamos en la coordenada 
        g2d.setColor(Color.BLACK);
        g2d.drawString(str, getWidth()/2 - fm.stringWidth(str)/2, y);
        
        // coordenadas
        System.out.println("Height: " + fm.getHeight()); // height = ascent + descent + leading
        System.out.println("Ascent: " + fm.getAscent());
        System.out.println("Descent: " + fm.getDescent());
        System.out.println("Leading: " + fm.getLeading());
        
        g2d.setFont(saveFont);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        paint4(g2d);
        //paint3(g2d);
        //paint2(g2d);
        //paint1(g2d);
        //paint0(g2d);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("FontMetricsDemo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new AF_FontMetrics());
                frame.setMinimumSize(new Dimension(400,300));
                frame.setContentPane(new AF_FontMetrics());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
