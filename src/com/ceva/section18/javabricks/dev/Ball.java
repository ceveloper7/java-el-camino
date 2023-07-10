package com.ceva.section18.javabricks.dev;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Esta clase tampoco implementa IAnimationLoop porque va a ser controlada desde
 * BrickAnimationLoop que es el juego principal.
 * metodo nextFrame() -> permite la animacion de la pelota
 */
public class Ball {
    private double x;
    private double y;
    private double incX;
    private double incY;
    private int size;
    private int speed;
    private Rectangle screenSize;
    // saber si perdio el jugador que ocurre cuando la pelota llega al fondo
    private boolean playerFailed = false;
    private Color color;
    private BufferedImage ballImg;

    Ball(int x, int y, int incX, int incY) {
        this.x = x;
        this.y = y;
        this.incX = incX;
        this.incY = incY;
        size = 10;
        speed = 3;
        color = Config.ballColor;
        initBallImg();
    }

    int getSize() {
        return size;
    }

    void initBallImg() {
        ballImg = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = ballImg.createGraphics();
        g2d.setColor(color);
        g2d.fillOval(0, 0, size, size);
        // borramos el graphics
        g2d.dispose();
    }

    /* saber el tamano de la pantalla */
    void setScreenSize(Rectangle screenSize) {
        this.screenSize = screenSize;
    }

    // nos aseguramos que la coordenada no se pase de la pantalla
    void checkForScreenSize() {
        if (x+size > screenSize.width)
            x = screenSize.width-size;
        else if (x < 0)
            x = 0;
        if (y+size > screenSize.height)
            y = screenSize.height-size;
        else if (y < 0)
            y = 0;
    }

    /* sabemos si la pelota toco un brick del escenario o el pad del jugador */
    boolean testForHit(Brick brick) {
        // calculamos la sgte coordenada que va a tener
        int nextX = (int) (x + (incX*speed));
        int nextY = (int) (y + (incY*speed));

        // determinamos el nuevo rectangulo de la pelota
        Rectangle r = new Rectangle(nextX, nextY, size, size);
        Rectangle brickBounds = brick.getBounds();
        // validamos la interseccion del rectangulo para saber si lo toco
        if (r.intersects(brickBounds)) {
            /* La bola viene por la izquierda (cambiamos signo a x) si la posición actual < x1
            y por la derecha si la posicion actual > x2. */
            if ((x+size <= brickBounds.x) || (x >= (brickBounds.x + brickBounds.width)))
                incX = -incX;

            // Moving down
            if (y+size <= brickBounds.y) {
                // top wall
                incY = -incY;

                // validamos si el brick es el pad del usuario
                if (brick.isPad) {
                    // calculamos el 15% del ancho del rectangulo del brick
                    double n0 = (double)brickBounds.width * 0.15;

                    // calculamos el punto donde tocamos el rectangulo
                    int pt = (int) (x + size/2) - brickBounds.x;
                    // valido si estoy dentro del rango (la pelota pega en la orilla del pad)
                    if ((pt <= n0) || (pt >= (brickBounds.width - n0)))
                        // incremento en x igual al incremento en x multiplicado x 2
                        incX = 2 * Math.signum(incX);
                    // valido si la pelota golpeo en el medio del pad
                    else if ((pt >= (brickBounds.width/2 - n0/2)) && (pt <= (brickBounds.width/2 + n0/2)))
                        incX = 0.5 * Math.signum(incX);
                    else
                        incX = Math.signum(incX);
                }
            } else if (y >= (brickBounds.y + brickBounds.height)) {
                // bottom wall
                incY = -incY;
            }

            if ((brick.hitSpeed != 0) && (brick.hitSpeed != speed))
                // cambiamos la volocidad de la pelota a la velocidad del brick
                speed = brick.hitSpeed;

            if (brick.hitCallback != null)
                // se ejecuta un codigo cuando toque la pelota un brick (como brick sorpresa)
                brick.hitCallback.run();

            return true;
        }
        return false;
    }

    boolean playerFailed() {
        return playerFailed;
    }

    void setPlayerFailed(boolean playerFailed) {
        this.playerFailed = playerFailed;
    }

    // colocamos la posicion manualmente, que lo hacemos cuando iniciamos una nueva vida
    void setPosition(int x, int y, int incX, int incY) {
        this.speed = 3;
        this.x = x;
        this.y = y;
        this.incX = incX;
        this.incY = incY;
    }

    void testForScreen() {
        if ((x+incX+size > screenSize.width) || (x+incX <= 0))
            // rebota incremento en X (cambiamos el signo)
            incX = -incX;
        if ((y+incY+size > screenSize.height) || (y+incY <= 0)) {
            // rebota incremento en Y (cambiamos el signo)
            incY = -incY;

            /**
             * Cuando la pelota sube significa que el incremento es negativo y cuando baja es porque
             * el incremento es positivo, entonces si le cambiamos el signo al incremento es o porque
             * toque la parte de arriba o porque toque la parte de abajo
             * Si tocamos la parte de abajo el incremento sera negativo por lo tanto ya perdimos
             * Cuando la pelota toque el pad, entonces la pelota le cambiara el signo a negativo y no
             * el metodo testForScreen
             */
            if (incY < 0) {
                // Fin de la pantalla, ya perdi.
                playerFailed = true;
            }
        }
    }

    /* invocamos el metodo desde el juego principal cada vez que queremos que se anime la pelota */
    void nextFrame() {
        testForScreen();

        x += (incX * speed);
        y += (incY * speed);
    }

    /* dibujamos la pelota*/
    void draw(Graphics2D g2d) {
        g2d.drawImage(ballImg, (int)x, (int)y, null);
    }
}
