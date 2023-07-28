package com.ceva.section18.javabricks.dev;

import java.awt.*;

/*
 * Si el usuario selecciona un bono de velocidad, entonces se aumenta la velocidad del pad
 * y se agrega un DelayLoop que espera durante 5 seg y cuando termina se deshace del efecto
 * del pad rapido
 */
public class DelayLoop implements IAnimationLoops{
    // para remover la animacion
    private IAnimationLoopController loopController;
    // para saber cuantos cuadro llevo
    private int frameCountDelay;
    private Runnable callback;

    public DelayLoop(IAnimationLoopController loopController, int frameCountDelay, Runnable callback){
        this.loopController = loopController;
        this.frameCountDelay = frameCountDelay;
        this.callback = callback;
    }
    @Override
    public void nextFrame(Rectangle screenBounds) {
        frameCountDelay--;
        if(frameCountDelay <= 0){
            callback.run();
            loopController.removeAnimationLoop(this);
        }
    }

    @Override
    public void paint(Graphics2D g2d) {

    }
}
