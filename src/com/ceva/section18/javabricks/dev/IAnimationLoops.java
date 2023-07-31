package com.ceva.section18.javabricks.dev;

import java.awt.*;
import java.awt.event.KeyListener;

/**
 * La animacion de una pelota consiste en actualizar su posicion y redibujar
 * por lo que si cambiamos la animacion a la interface IAnimationLoop entonces
 * nextFrame() tendria el codigo para actualizar la posicion y paint() para
 * redibujar la pelota
 */
public interface IAnimationLoops {
    // metodo que actualiza el estado del objeto
    public void nextFrame(Rectangle screenBounds);
    // redibuja
    public void paint(Graphics2D g2d);
    /*
    Importante: Las clases que implementen getKeyListener() deben devolver SIEMPRE
    la misma instancia.
    */
    public default KeyListener getKeyListener() {
        return null;
    }
}
