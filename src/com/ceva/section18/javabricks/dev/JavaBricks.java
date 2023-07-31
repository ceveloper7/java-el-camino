package com.ceva.section18.javabricks.dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JavaBricks extends DrawingPanel{
    // tamanio de la ventana
    private static final Dimension MINIMUMSIZE = new Dimension(570, 480);

    public JavaBricks(){
        super();
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return MINIMUMSIZE;
    }

    private void start(){
        // obtenemos el tamanio de la ventana
        Rectangle bounds = getBounds();
        addAnimationLopp(new Background(bounds));
        // indicamos que un rectangulo cambie de amarillo a naranja en 3 segundos
        // movemos el rectangulo de 100 100 a 285 380 de Magenta a Blanco en 3 seg
//        BrickEffectLoop l1 = new BrickEffectLoop(
//                this,
//                new Rectangle(100, 100, 100, 20),
//                new Rectangle(285, 380, 100, 100),
//                Color.MAGENTA, Color.WHITE, 60*3);
        // cuando se termina la primera animacion, agregamos una nueva animacion
        // movemos el rectangulo de 285 380 a 100 100 de blanco a rojo en 3 seg
//        l1.setOnFinished(()->{
//            addAnimationLopp(new BrickEffectLoop(
//                    this,
//                    new Rectangle(285, 380, 100, 100),
//                    new Rectangle(450, 100, 100, 20),
//                    Color.WHITE, Color.RED, 60*3));
//        });
//        addAnimationLopp(l1);
        new BrickAnimationLoop(this, bounds, getBackground());
        startAnimationThread();
    }

    public static void main(String args[]){
        /* creamos y mostramos el form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JavaBricks panel = new JavaBricks();
                JFrame frame = new JFrame("JavaBricks - Carlos V");
                frame.setBackground(panel.getBackground());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setResizable(false);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowOpened(WindowEvent e) {
                        panel.start();
                    }
                });
                frame.setContentPane(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                panel.requestFocus();
            }
        });
    }
}
