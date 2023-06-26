package com.ceva.section18.javabricks.dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JavaBricks5 extends JPanel{
    private static final int PAD_LEFT = 1;
    private  static final int PAD_RIGHT = 2;
    private class Ball{
        private int x;
        private int y;
        private int incX;
        private int incY;
        private int size;
        private Color color;

        public Ball(int x, int y, int size, int incX, int incY, Color color) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.incX = incX;
            this.incY = incY;
            this.color = color;
        }

        public void draw(Graphics2D g2d){
            g2d.setColor(color);
            g2d.fillOval(x, y, size, size);
        }

        private void animate(){
            // obtenemos el tamano de la pantalla
            Rectangle bounds = getBounds();
            // definimos un nuevo rectangulo
            int nextX = x + incX;
            int nextY = y + incY;
            Rectangle rBall = new Rectangle(nextX, nextY, size, size);
            // Iterator nos permite eliminar elementos de la coleccion mientras los recorremos
            for(Iterator<Rectangle> it = bricks.iterator(); it.hasNext();){
                Rectangle brick = it.next();
                // intersects -> informa si un rectangulo se junta con otro
                if(rBall.intersects(brick)){

                    if(((x+size) <= brick.x) || (x >= (brick.x+brick.width))){
                        // cambiamos el signo a la pelota
                        incX = -incX;
                    }

                    if((y+size <= brick.y) || (y >= (brick.y + brick.height))){
                        incY = -incY;
                    }
                    // al cumplirse la colision removemos el brick
                    it.remove();
                }
            }

            // incremento la coordenada de la pelota de acuerdo a la velocidad
            x += incX;
            y += incY;

            // rebote de la pelota
            // preguntamos si se salio de la pantalla
            if((x + size) >= bounds.width){
                // ajustamos la coordenada x
                x = bounds.width - size;
                // cambiamos el sentido de x
                incX *= -1;
            }else if(x <= 0){
                x = 0;
                incX *= -1;
            }

            if((y + size) >= bounds.height){
                y = bounds.height - size;
                incY *= -1;
            }else if(y <= 0){
                y = 0;
                incY *= -1;
            }
        }
    }
    private Ball ball;
    private Timer timer;
    private Set<Rectangle> bricks;
    private Rectangle pad;
    // manejamos la direccion
    private int padDir;
    private int lastDir;
    public JavaBricks5(){
        super();
        setBackground(Color.BLACK);

        ball = new Ball(0,0,20,2,2,Color.YELLOW);
        bricks = new HashSet<>();
        int x = 20;
        for(int n = 0; n < 4; n++){
            bricks.add(new Rectangle(x, 100, 70, 50));
            x += 90;
        }
        pad = new Rectangle(150, 230, 80, 12);
        initKeyBoard_v3();
    }

    private void initKeyBoard_v1(){
        KeyAdapter kbd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // saber codigo tecla presionada
                int keyCode = e.getKeyCode();
                // validamos la flecha izq
                if(keyCode == KeyEvent.VK_LEFT){
                    // pad es la barra que controla el usuario. quitamos coord x 5 pixels
                    pad.x -= 5;
                }else if(keyCode == KeyEvent.VK_RIGHT){
                    pad.x += 5;
                }
            }
        };
        addKeyListener(kbd);
    }

    // encedemos o apagamos los bit dependiendo del tipo de evento
    private void initKeyBoard_v2(){
        KeyAdapter kbd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_LEFT){
                    // encendemos el bit en padDir
                    padDir |= PAD_LEFT;
                }else if(keyCode == KeyEvent.VK_RIGHT){
                    padDir |= PAD_RIGHT;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    // apagamos el bit en padDir
                    padDir = padDir & ~PAD_LEFT;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    padDir = padDir & ~PAD_RIGHT;
                }
            }
        };
        addKeyListener(kbd);
    }


    private void initKeyBoard_v3(){
        KeyAdapter kbd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_LEFT){
                    // encendemos el bit en padDir
                    padDir |= PAD_LEFT;
                    // la ultima tecla es LEFT
                    lastDir = PAD_LEFT;
                }else if(keyCode == KeyEvent.VK_RIGHT){
                    padDir |= PAD_RIGHT;
                    lastDir = PAD_RIGHT;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    // apagamos el bit en padDir
                    padDir = padDir & ~PAD_LEFT;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    padDir = padDir & ~PAD_RIGHT;
                }
            }
        };
        addKeyListener(kbd);
    }
    private void startTimer(){
        // la animacion se va a ejecutar a 60 cuadros x seg
        timer = new Timer(1000 / 120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animate();
            }
        });
        timer.start();
    }

    private void stopTimer(){
        timer.stop();
    }

    private void animate_v1(){
        ball.animate();
        repaint();
    }

    private void animate_v2(){
        ball.animate();
        if(padDir != 0){
            // dependiendo de que bit este encendido, incremetamos o decrementamos la coord x
            if((padDir & PAD_LEFT) != 0){
                pad.x -= 5;
            }else if((padDir & PAD_RIGHT) != 0){
                pad.x += 5;
            }
        }
        repaint();
    }

    private void animate_v3(){
        ball.animate();
        if(padDir != 0){
            /**
             * si la ultima tecla presionada es left, le damos prioridad a la izq
             * al momento de mover el pad
             */
            if(lastDir == PAD_LEFT){
                // la ultima tecla presionada fue la izq
                if(((padDir & PAD_LEFT) != 0)){
                    pad.x -= 5;
                }else if((padDir & PAD_RIGHT) != 0){
                    pad.x += 5;
                }
            }
            /**
             * si la ultima tecla fue derecha, le damos prioridad a la derecha
             * al momento de mover el pad
             */
            else if(lastDir == PAD_RIGHT){
                // evaluamos en el orden contrario
                if((padDir & PAD_RIGHT) != 0){
                    pad.x += 5;
                }else if((padDir & PAD_LEFT) != 0){
                    pad.x -= 5;
                }
            }
        }
        repaint();
    }

    private void animate(){
        animate_v3();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ball.draw(g2d);
        for(Rectangle brick : bricks){
            g2d.drawRect(brick.x, brick.y, brick.width, brick.height);
        }
        g2d.setColor(Color.RED);
        g2d.fillRect(pad.x, pad.y, pad.width, pad.height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("JavaBricks 1");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 300));
            JavaBricks5 panel = new JavaBricks5();
            frame.setContentPane(panel);
            frame.setLocationRelativeTo(null);
            frame.addWindowListener(new WindowAdapter() {
                // evento que se invoca cuando se abre la ventana
                @Override
                public void windowOpened(WindowEvent e) {
                    panel.startTimer();
                }

                // evento que se invoca cuando se cierra la ventana
                @Override
                public void windowClosing(WindowEvent e) {
                    panel.stopTimer();
                }
            });
            frame.setVisible(true);
            // requestFocus() permite que el panel reciba los eventos del teclado
            panel.requestFocus();
        });
    }
}
