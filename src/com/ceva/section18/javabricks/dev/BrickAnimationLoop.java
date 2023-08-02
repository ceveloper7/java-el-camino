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
    private int score = 0;
    private List<Brick> bricks = new LinkedList<Brick>();
    private IAnimationLoopController mainLoopController;
    private LivesIndicator livesIndicator;
    private Rectangle screenSizeBounds;
    public BrickAnimationLoop(IAnimationLoopController mainLoopController, Rectangle screenSizeBounds, Color backgroundColor){
        this.mainLoopController = mainLoopController;
        this.backgroundColor = backgroundColor;
        this.screenSizeBounds = screenSizeBounds;

        keyListener = initKeyListener();
        livesIndicator = new LivesIndicator(screenSizeBounds, Config.STARTLIVES, pad.getColor());

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

        initBricks();

        // BrickAnimationLoop se agrega a si mismo en la lista de animaciones
        mainLoopController.addAnimationLopp(this);
    }

    /*
     * Metodo que crea el escenario con la coleccion de bricks
     */
    private void initBricks() {
        // limpiamos la coleccion
        bricks.clear();
        // definimos el escenario
        int rows = 9;
        int cols = 9;

        Rectangle r = new Rectangle(12, 50, 50, 12);
        for(int j = 0; j < rows; j++){
            /*
             * la velocidad del brick dependent de si es un brick fast
             * o un brick normal
             */
            int hitSpeed = j < 3 ? 6 : 3;

            /*
             * determinamos el color dependiendo del renglon donde estamos
             * los primeros renglones de la coleccion de brick van a ser bolas rapidas
             * j < 3 ? preguntamos si estamos en el renglon 0, 1, 2
             */
            Color c = j < 3 ? Config.brickFastColor : Config.brickColor;

            for(int i = 0; i < cols; i++){
                Brick b = new Brick(r.x, r.y, r.width, r.height, c, hitSpeed);
                bricks.add(b);

                // modificamos el rectangulo del sgte brick
                r.x += r.width + 12; //add margen 12px
            }
            r.y += r.height + 10; // add 10px hacia abajo
            // inicializamos el rectangulo en la coordenada 12 (sgte renglon)
            r.x = 12;
        }
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

    /*
     * al momento de tocar con la pelota un brick aplicamos un efecto al borrado del brick
     * del escenario. El efecto que se aplica es una reduccion paulatina del brick
     */
    private void dismissBrickEffect(Brick b){
        // obtenemos el tamano del brick
        Rectangle r = b.getBounds();

        // calculamos la nueva dimension o dimension final del brick
        int width = (int)(r.width * 0.6); // terminal al 60% de su tamano inicial
        int height = (int)(r.height * 0.6);
        // nueva coordenada x, y centrada
        int x = r.x + r.width/2 - width/2;
        int y = r.y + r.height/2 - height/2;

        // aplicamos nuevos valores al brick redefinido
        r = new Rectangle(x, y, width, height);

        BrickEffectLoop el = new BrickEffectLoop(
                mainLoopController, b.getBounds(), r, b.getColor(), backgroundColor, 60);
        mainLoopController.addAnimationLopp(el);
    }

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
        // validamos que existan bricks en el scenario
        if(bricks.size() > 0){
            // Iteramos los bricks de la pantalla
            for(Iterator<Brick> it = bricks.iterator(); it.hasNext();){
                Brick b = it.next();
                // validamos si se produce colision
                if(ball.testForHit(b)){
                    // efecto de desvanecimiento del brick de la escena
                    dismissBrickEffect(b);
                    // quitamos el brick del escenario
                    it.remove();

                    // manejamos la puntuacion
                    if(b.hitSpeed > 3){
                        score += 20;
                    }else{
                        score += 10;
                    }
                }
            }
        }

        ball.checkForScreenSize();
        ball.nextFrame();
    }

    @Override
    public void paint(Graphics2D g2d) {
        // dibujamos el score
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format(Config.scoreMessage, score), 20, 20);
        livesIndicator.paint(g2d);

        // dibujamos la coleccion de bricks
        for(Brick b : bricks){
            b.draw(g2d);
        }

        ball.draw(g2d);
        pad.draw(g2d);
    }
}
