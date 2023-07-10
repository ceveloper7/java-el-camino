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
        addAnimationLopp(new BlinkMessageLoop(
                bounds, "Biblia de las Americas", "Subtitulo ",
                this, new BrickAnimationLoop(), Color.BLUE, Color.YELLOW, 8
        ));
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
