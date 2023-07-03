package com.ceva.section18.javabricks.dev;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Esta clase se basa en una imagen en memoria donde se dibuja
 */
public class Background implements IAnimationLoops{
    BufferedImage img;

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

        private void nextFrame(){
            y += speed;
            if(y >= img.getHeight()){
                y = 0;
            }
        }

        void update(){}
    }

    // Pasamos un rectangulos al constructor
    public Background(Rectangle bounds){
        // creamos la imagen del rectangulo
        img = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
        init(bounds);
    }

    private void init(Rectangle bounds){

    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, 0, 0, null);
    }
}
