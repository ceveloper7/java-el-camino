package com.ceva.section12.awt_swing.layout;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BBoxLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("BoxLayoutDemo");
                BoxLayout layout = new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS);
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                for (int n=0; n<4; n++) {
                    JButton button = new JButton("Test " + n);
                    button.setAlignmentX(Component.LEFT_ALIGNMENT);
                    frame.add(button);
                }

                frame.setVisible(true);
            }
        });
    }
}
