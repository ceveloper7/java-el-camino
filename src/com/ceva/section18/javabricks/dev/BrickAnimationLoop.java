package com.ceva.section18.javabricks.dev;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class BrickAnimationLoop implements IAnimationLoops{
    private static final int PAD_RIGHT = 1;
    private static final int PAD_LEFT = 2;

    private final KeyListener keyListener;
    private Color backgroundColor;
    private Ball ball;
    private Brick pad;
    private int lastPadDir = 0;
    private int padDir = 0;
    private List<Brick> bricks = new LinkedList<Brick>();
    private IAnimationLoopController mainLoopController;
    private Rectangle screenSizeBounds;
    public BrickAnimationLoop(IAnimationLoopController mainLoopController, Rectangle screenSizeBounds, Color backgroundColor){
        this.mainLoopController = mainLoopController;
        this.backgroundColor = backgroundColor;
        this.screenSizeBounds = screenSizeBounds;

        keyListener = initKeyListener();

        // inicio del pad
        int padWidth = 75;
        int padHeight = 12;
        // rectangulo que define el pad
        Rectangle r = new Rectangle(
                screenSizeBounds.width/2 - padWidth/2,
                screenSizeBounds.height - padHeight * 3,
                padWidth,
                padHeight);
        pad = new Brick(r.x, r.y, r.width, r.height,Config.padColor, 0);
        pad.isPad = true;
        ball = new Ball(r.x + r.width/2 - 5, r.y - 10, 1, -1);
        ball.setScreenSize(screenSizeBounds);

        // BrickAnimationLoop se agrega a si mismo en la lista de animaciones
        mainLoopController.addAnimationLopp(this);
    }

    // metodo para manejar el teclado
    private KeyListener initKeyListener() {
        KeyListener kbd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    padDir |= PAD_LEFT;
                    lastPadDir = PAD_LEFT;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    padDir |= PAD_RIGHT;
                    lastPadDir = PAD_RIGHT;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT)
                    padDir = padDir & ~PAD_LEFT;
                else if (keyCode == KeyEvent.VK_RIGHT)
                    padDir = padDir & ~PAD_RIGHT;
            }
//            @Override
//            public void keyTyped(KeyEvent e) {
//                if ((e.getKeyChar() == '\n') && gameOver) {
//                    restartGame(true);
//                }
//            }
        };
        return kbd;
    }

    @Override
    public KeyListener getKeyListener() {
        return keyListener;
    }

    public void pause(){}
    public void unPause(){}

    private void movePad() {
        if (padDir != 0) {
            if (lastPadDir == PAD_LEFT) {
                if ((padDir & PAD_LEFT) != 0)
                    pad.moveLeft();
                else if ((padDir & PAD_RIGHT) != 0)
                    pad.moveRight(screenSizeBounds.width);
            } else {
                if ((padDir & PAD_RIGHT) != 0)
                    pad.moveRight(screenSizeBounds.width);
                else if ((padDir & PAD_LEFT) != 0)
                    pad.moveLeft();
            }
        }
    }
    @Override
    public void nextFrame(Rectangle screenBounds) {
        movePad();
        ball.checkForScreenSize();
        ball.nextFrame();
    }

    @Override
    public void paint(Graphics2D g2d) {
        ball.draw(g2d);
        pad.draw(g2d);
    }
}
