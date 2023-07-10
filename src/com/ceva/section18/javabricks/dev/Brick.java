package com.ceva.section18.javabricks.dev;

import java.awt.*;

/**
 * Esta clase no implementa IAnimationLoop xq es utilizada como parte de BrickAnimationLoop
 * que sera la clase principal del juego
 */
public class Brick {
    private Rectangle bounds;
    private boolean visible = true;
    private Color color;
    public int hitSpeed = 1;
    public boolean isPad = false;
    public Runnable hitCallback;
    private int padSpeed = 5;
    private int wideSize;
    private int narrowSize;
    private int normalSize;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param hitSpeed crea ladrillos que cuando toca la pelota, que la pelota sea rapida
     */
    public Brick(int x, int y, int width, int height, Color color, int hitSpeed) {
        bounds = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitSpeed = hitSpeed;

        // calculo de tamanos
        normalSize = bounds.width;
        wideSize = normalSize * 2;
        narrowSize = normalSize / 2;
    }

    /* obtenemos el tamano del rectangulo*/
    Rectangle getBounds() {
        return bounds;
    }

    Color getColor() {
        return color;
    }

    /* aparece y desaparece el brick */
    boolean isVisible() {
        return visible;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    int getPadSpeed() {
        return padSpeed;
    }

    /* establecemos la velocidad del pad */
    void setPadSpeed(int padSpeed) {
        this.padSpeed = padSpeed;
    }

    /* movimiento que realiza el jugador */
    void moveRight(int screenWidth) {
        bounds.x += padSpeed;
        // validamos q si pasamos el limite de pantalla el pad se detenga
        if (bounds.x+bounds.width >= screenWidth)
            bounds.x = screenWidth - bounds.width;
    }

    void moveLeft() {
        bounds.x -= padSpeed;
        if (bounds.x < 0)
            bounds.x = 0;
    }

    void setWide() {
        bounds.width = wideSize;
    }

    void setNarrow() {
        bounds.width = narrowSize;
    }

    void setNormal() {
        bounds.width = normalSize;
    }

    void setX(int x) {
        bounds.x = x;
    }

    void draw(Graphics2D g2d) {
        if (!visible)
            return;
        g2d.setColor(color);
        g2d.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
