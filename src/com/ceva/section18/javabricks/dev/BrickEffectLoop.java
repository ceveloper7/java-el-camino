package com.ceva.section18.javabricks.dev;

import java.awt.*;

public class BrickEffectLoop implements IAnimationLoops{
    private final IAnimationLoopController animationLoopController;
    private final Rectangle startBounds;
    // rectangulo que actualizamos en cada cuadro
    private Rectangle curBounds;
    private final Color startColor;
    // color actual que vamos a actualizar
    private Color curColor;
    private final int numberOfFrames;
    // cuenta el nro de cuadros actual
    private int curFrame;
    // callback -> codigo que se ejecuta cuando se presenta un evento
    // onFinishedCallback se ejecuta cuando se termine la animacion
    private Runnable onFinishedCallback = null;

    private final double redDelta;
    private final double greenDelta;
    private final double blueDelta;

    // componentes de un rectangulo
    private final double xDelta;
    private final double yDelta;
    private final double widthDelta;
    private final double heightDelta;


    /**
     * Operaciones para calcular las interpolaciones
     * @param animationLoopController -> permite removerse a si mismo de la lista de animaciones
     * @param startBounds
     * @param endBounds -> Rectangulo inicial y final al que se hace animacion por interpolacion
     * @param startColor
     * @param endColor -> Color inicial y final
     * @param numberOfFrames -> Numero s de cuadros donde se realiza la animacion
     */
    public BrickEffectLoop(IAnimationLoopController animationLoopController, Rectangle startBounds, Rectangle endBounds, Color startColor,Color endColor, int numberOfFrames){
        this.animationLoopController = animationLoopController;
        this.startBounds = startBounds;
        this.startColor = startColor;
        this.numberOfFrames = numberOfFrames;
        curFrame = 0;

        /*
         * Para calcular una interpolacion por ejemplo la interpolacion de 1 a 10 entonces dividimos
         * el rango que es 10 entre el numero de pasos y nos da el incremento en cada paso a eso
         * lo llamamos una delta
         */

        /*
         * Tenemos un color inicial startColor y un color final endColor pero como hacemos para
         * llegar del color inicial al color final.
         * Dividimos el problema en los tres componentes del color (rojo, verde, azul)
         * si restamos el color final - color inicial, obtenemos el rango de colores
         */

        // rango / # de incremento
        redDelta = (double) (endColor.getRed() - startColor.getRed()) / numberOfFrames;
        greenDelta = (double) (endColor.getGreen() - startColor.getGreen()) / numberOfFrames;
        blueDelta = (double) (endColor.getBlue() - startColor.getBlue()) / numberOfFrames;

        // color inicial
        curColor = startColor;

        // interpolacion para las dimensiones del rectangulo
        xDelta = (double) (endBounds.getX() - startBounds.getX()) / numberOfFrames;
        yDelta = (double)(endBounds.getY() - startBounds.getY()) / numberOfFrames;
        widthDelta = (double) (endBounds.getWidth() - startBounds.getWidth()) / numberOfFrames;
        heightDelta = (double) (endBounds.getHeight() - startBounds.getHeight()) / numberOfFrames;
    }

    // interpolamos de un color a otro color
    private void calcNextColor(){
        int red = (int)(startColor.getRed() + redDelta * curFrame);
        int green = (int)(startColor.getGreen() + greenDelta * curFrame);
        int blue = (int)(startColor.getBlue() + blueDelta * curFrame);

        curColor = new Color(red, green, blue);
    }

    private void calcNextBounds(){
        int x = (int)(startBounds.x + xDelta * curFrame);
        int y = (int)(startBounds.y + yDelta * curFrame);
        int width = (int)(startBounds.width + widthDelta * curFrame);
        int height = (int)(startBounds.height + heightDelta * curFrame);

        curBounds = new Rectangle(x,y, width, height);
    }

    public void setOnFinished(Runnable callback){
        onFinishedCallback = callback;
    }

    // actualizamos el color y el rectangulo de acuerdo al numero de frame
    @Override
    public void nextFrame(Rectangle screenBounds) {
        calcNextColor();
        calcNextBounds();

        curFrame++;
        // validamos si llegamos al final de la animacion
        if(curFrame >= numberOfFrames){
            animationLoopController.removeAnimationLoop(this);
            if(onFinishedCallback != null){
                onFinishedCallback.run();
            }
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(curColor);
        // pintamos el rectangulo actual
        g2d.fillRect(curBounds.x, curBounds.y, curBounds.width, curBounds.height);
    }
}
