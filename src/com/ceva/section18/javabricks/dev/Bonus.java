package com.ceva.section18.javabricks.dev;

import java.awt.*;

public class Bonus implements IAnimationLoops{
    Rectangle bounds;
    IAnimationLoopController loopController;
    // para trabajar con el pad del usuario
    Brick targetPad;
    Color color;
    // callback para que se ejecute cuando el brick toca el pad
    private Runnable callback;

    public Bonus(int x, int y, Brick targetPad, Color color, IAnimationLoopController loopController, Runnable callback){
        this.bounds = new Rectangle(x, y, 15, 15); // tamano fijo
        this.loopController = loopController;
        this.targetPad = targetPad;
        this.color = color;
        this.callback = callback;
    }

    /*
    * Implementamos la coordenada Y
    * Si el el rectangulo del Bono hace interseccion con el pad del jugador entonces se elimna
    * de la lista de animaciones y ejecuta su callback para que se realice la accion que se
    * le haya programado.
    * Si la coordenada Y del bono es mayor al tamano de la pantalla entonces se elimina de la lista
    * de animaciones xq ya salio del juego
     */
    @Override
    public void nextFrame(Rectangle screenBounds) {
        // vamos hacia abajo a la velocidad de 1px por cuadro
        bounds.y++;
        // validamos si hay colision
        if(bounds.intersects(targetPad.getBounds())){
            callback.run();
            loopController.removeAnimationLoop(this);
            return;
        }
        // validamos si llegamos al final de la pantalla
        if(bounds.y >= screenBounds.height){
            loopController.removeAnimationLoop(this);
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
