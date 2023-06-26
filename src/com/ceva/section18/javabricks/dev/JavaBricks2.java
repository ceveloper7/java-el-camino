package com.ceva.section18.javabricks.dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JavaBricks2 extends JPanel{
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
            // intersects -> informa si un rectangulo se junta con otro
            if(rBall.intersects(brick)){
                // en el siguiente cuadro se va a encimar la pelota con el rectangulo
                // se produce una colisiones. por lo que cambiamos el signo de x y de y
                /**
                 * 1 condicion: x + size (que representa la coordenada x actual) es menor
                 *   a la coordenada x del brick, significa que antes de haber una colision
                 *   nos encontrabamos del lado izquierdo, por lo tanto debemos regresar
                 *   hay un rebote
                 */
                /**
                 * 2 condicion:si el x actual es mayor o igual que la coordenada x del brick + el ancho
                 *   entonces la pelota viene por la izquierda
                 */
                if(((x+size) <= brick.x) || (x >= (brick.x+brick.width))){
                    // cambiamos el signo a la pelota
                    incX = -incX;
                }
                // condicion para la coordenada Y
                /**
                 * determinamos si antes de la interseccion nos encontramos arriba o abajo
                 * del brick
                 */
                if((y+size <= brick.y) || (y >= (brick.y + brick.height))){
                    incY = -incY;
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
    private Rectangle brick;
    public JavaBricks2(){
        super();
        setBackground(Color.BLACK);
        /**
         * 1 y 2 param - coordenadas x,y
         * 3er param - tamano
         * 4to y 5to - incremento en pixeles en eje x, y
         * 6to para - color
         */
        ball = new Ball(0,0,20,2,2,Color.YELLOW);
        brick = new Rectangle(130, 100, 120, 50);
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

    private void animate(){
        ball.animate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        ball.draw(g2d);
        g2d.drawRect(brick.x, brick.y, brick.width, brick.height);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("JavaBricks 1");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 300));
            JavaBricks2 panel = new JavaBricks2();
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
        });
    }
}
