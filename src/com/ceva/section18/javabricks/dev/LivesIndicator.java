package com.ceva.section18.javabricks.dev;

import org.w3c.dom.css.Rect;

import java.awt.*;

/**
 * Manejamos en el objeto LiveIndicator una forma de representar las vidas del user
 */
public class LivesIndicator {

    private int numerOfLives;
    private Rectangle bounds;
    private Color color;
    public LivesIndicator(Rectangle bounds, int numberOfLives, Color color){
        this.bounds = bounds;
        this.numerOfLives = numberOfLives;
        this.color = color;
    }

    /*
     * dibujamos en la parte superior derecha de la pantalla numero de vida
     */
    public void paint(Graphics2D g2d){
        /*
         * dibujamos el numero de vidas en pantalla
         * cada vida es un pad de 16px ancho 4 px alto
         * coordenada x es igual al tamano de la ventana - 16px
         * coordenada y es igual al tamano de la ventana + 10x
         */
        Rectangle liveRect = new Rectangle(bounds.width-16, bounds.y+10, 16, 4);
        g2d.setColor(color);
        for(int n = 0; n < numerOfLives; n++){
            // aplicamos un margin de 6px
            liveRect.x -= 6;
            // escribimos la vida en pantalla
            g2d.fillRect(liveRect.x, liveRect.y, liveRect.width, liveRect.height);
            liveRect.x -= liveRect.width;
        }
    }
}
