/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec13.java2D;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AH_Image extends JPanel{
    private BufferedImage image;
    
    private void readImage(){
        // simulamos la carga de una imagen que tarda 3 segundo
        try{
            Thread.sleep(4000);
        }
        catch(InterruptedException e){
            
        }
        
        try(InputStream in = AH_Image.class.getResourceAsStream("/com/ceva/javaelcamino/sec13/java2D/cross.jpeg")){
            if(in != null){
                image = ImageIO.read(in);
            }
        }
        catch(IOException e){
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            e.printStackTrace();
            image = null;
        }
    }
    
    public AH_Image(JFrame owner){
        super();
        // Cargamos la imagen en modo Asyncrono con Hilos
        // no guardamos referencia a la variable xq solo es un hilo que carga y luego desaparece
        new Thread(new Runnable(){
            @Override
            public void run() {
                readImage();
                // luego de leer la imagen, validamos para pintar la imagen
                if(image != null){
                    // re-dimensionamos el tamanio de la ventana para mostrar toda la imagen
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            Dimension size = new Dimension(image.getWidth(), image.getHeight());
                            setMinimumSize(size);
                            setPreferredSize(size);
                            // volvemos a reorganizar el tamano
                            owner.pack();
                            // nos aseguramos que esta centrado
                            owner.setLocationRelativeTo(null);
                            repaint();
                        } 
                    });
                }
            }
            
        }).start();
        
        Dimension size = new Dimension(400, 300);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, null);
        }else{
            // dibujamos mensaje de cargando...
            String str = "Loading...";
            g.setFont(g.getFont().deriveFont(36f));
            FontMetrics fm = g.getFontMetrics();
            
            g.drawString(str, getWidth()/2 - fm.stringWidth(str)/2 , getHeight()/2 - fm.getHeight()/2 + fm.getAscent());
        }
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                JFrame frame = new JFrame("ImageTest");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new AH_Image(frame));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
            
        });
    }
}
