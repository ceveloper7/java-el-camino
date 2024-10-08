package com.ceva.section12.awt_swing.layout;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * CardLayout: se agregan controles los cuales se mostraran como una pila, que muestra solamente el control frontal.
 */
public class CCardLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("CardLayoutDemo");
                CardLayout layout = new CardLayout();
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                for (int n=0; n<4; n++) {
                    JButton button = new JButton("Test " + n);
                    button.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            layout.next(frame.getContentPane());
                        }
                    });
                    button.setAlignmentX(Component.CENTER_ALIGNMENT);
                    frame.add(button);
                }

                frame.setVisible(true);
            }
        });
    }
}
