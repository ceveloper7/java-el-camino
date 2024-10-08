package com.ceva.section12.awt_swing.layout;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * GridLayout especificamos un espacio de filas y columnas y el GridLayout va utilizar todo el espacio disponible para
 * ubicar los elementos.
 */
public class FGridLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("GridLayoutDemo");
                GridLayout layout = new GridLayout(3, 3);
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                for (int n=0; n<8; n++) {
                    JButton button = new JButton("Test " + n);
                    frame.add(button);
                }

                frame.setVisible(true);
            }
        });
    }
}
