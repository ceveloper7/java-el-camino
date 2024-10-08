package com.ceva.section12.awt_swing.layout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * GridBagLayout agrega componentes alineados en una cuadricula. GridBagLayout determina su tamano dependiendo de las preferencias
 * de tamano de cada elemento. Los elementos se agregan por medio de crear restricciones a traves del objeto GridBagConstraints
 *
 */
public class EGridBagLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("GridBagLayoutDemo");
                GridBagLayout layout = new GridBagLayout();
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                for (int n=0; n<8; n++) {
                    JButton button = new JButton("Test " + n);
                    GridBagConstraints c = new GridBagConstraints();
                    c.gridx = n % 3;
                    c.gridy = n / 3;
                    c.insets = new Insets(32, 32, 32, 32);
                    frame.add(button, c);
                }

                frame.setVisible(true);
            }
        });
    }
}
