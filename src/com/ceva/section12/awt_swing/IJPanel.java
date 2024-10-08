package com.ceva.section12.awt_swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * El arbol de componentes que se construye en este ejemplo es:
 * JFrame
 *  ContentPane -> representa toda la zona azul de formulario (vendria a ser una especie de viewport)
 *      JPanel -> ContentPane contiene al elemento JPanel
 */
public class IJPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("JFrameDemo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                JPanel panel = new JPanel();
                panel.setBackground(Color.blue);
                frame.add(panel);
                // frame.setContentPane(panel);

                frame.setVisible(true);
            }
        });
    }
}
