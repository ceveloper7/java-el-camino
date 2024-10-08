package com.ceva.section12.awt_swing.layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * EL componente BorderLayout ubica los componentes en 5 regiones de la pantalla que son NORTH, SOUTH, EAST, WEST, CENTER
 */
public class ABorderLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("BorderLayoutDemo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                // Especificamos las zonas donde se ubicaran los componentes JButton
                frame.add(new JButton("PAGE_START"), BorderLayout.PAGE_START);
                frame.add(new JButton("PAGE_END"), BorderLayout.PAGE_END);
                frame.add(new JButton("LINE_START"), BorderLayout.LINE_START);
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);
                frame.add(new JButton("LINE_END"), BorderLayout.LINE_END);

                frame.setVisible(true);
            }
        });
    }
}
