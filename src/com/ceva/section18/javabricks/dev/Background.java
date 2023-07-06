package com.ceva.section18.javabricks.dev;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Esta clase se basa en una imagen en memoria donde se dibuja estrellas de fondo con animacion
 * Para usar esta clase en otros proyectos debemos agregar un objeto background al motor
 * de animaciones.
 */
public class Background implements IAnimationLoops{
    // imagen en memoria
    BufferedImage img;
    Star[] stars = new Star[500];

    private class Star{
        double x;
        double y;
        int color;
        double speed;

        public Star(double x, double y, int color, double speed) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.speed = speed;
        }

        // actualiza el estado del objeto star
        private void nextFrame(){
            y += speed;
            if(y >= img.getHeight()){
                y = 0;
            }
        }

        void update(){
            // setRGB pone un pixel en la coord x,y con el color indicado como un int
            // borramos la posicion inicial de la estrella
            img.setRGB((int)x, (int)y, 0);
            // recalculamos su posicion
            nextFrame();
            // volvemos a dibujarla con una nueva posicion y nuevo color
            img.setRGB((int)x, (int)y, color);
        }
    }

    // Pasamos un rectangulos al constructor
    public Background(Rectangle bounds){
        // creamos la imagen del rectangulo
        img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
        init(bounds);
    }

    private void init(Rectangle bounds){
        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, img.getWidth(), img.getHeight());
        g2d.dispose();
        // ciclo de inicializaciones
        for(int i = 0; i < stars.length; i++){
            // generamos numeros aleatorios de acuerdo al # de la imagen
            int x = (int)(Math.random() * bounds.width);
            int y = (int)(Math.random() * bounds.height);
            double speed = Math.random() * 0.3;
            int color = (int)(Math.random() * 256);
            // componentes del color rojo, verde , azul
            color = (color<<16) | (color<<8) | color;
            // inicializamos el elemento i de array stars con una nueva estrella
            stars[i] = new Star(x,y, color, speed);
        }
    }

    private void moveStars(){
        for(Star s : stars){
            // animacion de cada estrella es responsabilidad del metodo update de cada object star
            s.update();
        }
    }

    @Override
    public void nextFrame(Rectangle screenBounds) {
        // calculamos el sgte cuadro o frame
        moveStars();
    }

    // dibujamos el objeto
    @Override
    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, 0, 0, null);
    }
}
