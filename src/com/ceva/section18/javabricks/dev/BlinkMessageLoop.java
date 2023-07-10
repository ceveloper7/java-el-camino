package com.ceva.section18.javabricks.dev;

import java.awt.*;

public class BlinkMessageLoop implements IAnimationLoops{
    private IAnimationLoopController animationLoopController;
    private Rectangle bounds;
    private int maxIterations = 8; // 0 == infinito
    private int iteration = 0;
    private Font font = null;
    private BrickAnimationLoop brickAnimationLoop;
    private String message;
    private String subMessage;
    private Color backgroundColor;
    private Color textColor;
    private long delayLimit;
    /**
     *
     * @param bounds tamano de la pantalla
     * @param message mensaje principal
     * @param subMessaje mensaje secundario
     * @param animationLoopController controlador de animaciones
     * @param brickAnimationLoop clase que representa el juego principal (hacemos y quitamos pausa)
     * @param backgroundColor color de fondo
     * @param textColor color de texto
     * @param maxIterations numero de iteraciones, valor 0 significa iteraciones infinitas
     */
    public BlinkMessageLoop(
            Rectangle bounds,
            String message,
            String subMessaje,
            IAnimationLoopController animationLoopController,
            BrickAnimationLoop brickAnimationLoop,
            Color backgroundColor,
            Color textColor,
            int maxIterations){
        this.bounds = bounds;
        this.message = message;
        this.subMessage = subMessaje;
        this.animationLoopController = animationLoopController;
        this.brickAnimationLoop = brickAnimationLoop;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.maxIterations = maxIterations;
        // mientras el mensaje este en pantalla, ponemos en pausa al juego
        brickAnimationLoop.pause();
    }

    @Override
    public void nextFrame(Rectangle screenBounds) {
        // obtenemos la hora actual
        long curTime = System.currentTimeMillis();
        if(curTime < delayLimit)
            return;
        /* cuando hacemos una pausa, nextFrame no hara nada hasta q se cumpla tiempo limite */
        bounds = screenBounds;
        /* detenemos el proceso 1/2 segundo */
        delay(500);
        /* validamos si se cumplieron el numero de iteraciones */
        if((maxIterations > 0 ) && (iteration >= maxIterations)){
            brickAnimationLoop.unPause();
            // nos borramos de la lista de animaciones, que significa desaparecer el msg
            animationLoopController.removeAnimationLoop(this);
        }
        iteration++;
    }

    private void delay(long millis){
        delayLimit = System.currentTimeMillis() + millis;
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(backgroundColor);
        if(font == null){
            font = g2d.getFont().deriveFont(40f);
        }
        // dejamos todo como estaba luego de dibujar
        Font oldFont = g2d.getFont();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        // tamano que va ocupar el msg
        Rectangle msgBounds = fm.getStringBounds(message, g2d).getBounds();
        // centramos el msg
        // establecemos un ancho y altoun poco superior para que se vea bien el msg
        int width = msgBounds.width + 32;
        int height = msgBounds.height;
        int x = bounds.width/2 - width/2;
        int y = bounds.height/2 - height/2;
        g2d.fillRect(x, y, width, height);

        // parpadeo, en las iteraciones impares mostramos el texto y en las pares no mostramos
        // pintamos el msg
        if((iteration % 2) == 1){
            g2d.setColor(textColor);
            g2d.drawString(message, (int)((x + width/2) - msgBounds.width/2), (int)((y + height/2) + fm.getDescent()));
        }
        g2d.setFont(oldFont);
        if(subMessage != null){
            g2d.setColor(textColor);
            // obtenemos el nuevo font metrics del nuevo tipo de letra
            fm = g2d.getFontMetrics();
            msgBounds = fm.getStringBounds(subMessage, g2d).getBounds();
            // dibujamos
            g2d.drawString(subMessage, (int)(x+width/2-msgBounds.width/2), (int)(y+height-msgBounds.height + fm.getAscent()));
        }
    }
}
