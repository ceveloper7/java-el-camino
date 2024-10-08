package com.ceva.section12.awt_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * Terminar o cerrar una aplicacion con una ventana de confirmacion.
 */
public class JCreate_CloseWIndow extends JFrame {

    public static JCreate_CloseWIndow newInstance(){
        JCreate_CloseWIndow frame = new JCreate_CloseWIndow();
        frame.setTitle("CreateJFrameDemo");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // detectamos cuando el evento close window se presente.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(frame, "Desea cerrar la aplicacion?", "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    frame.dispose();
                }
            }
        });

        frame.setMinimumSize(new Dimension(400,300));
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JCreate_CloseWIndow(){}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JCreate_CloseWIndow newFrame = newInstance();
                newFrame.setVisible(true);
            }
        });
    }
}
