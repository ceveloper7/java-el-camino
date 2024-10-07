package com.ceva.section12.awt_swing;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class DMouseAdapterDemo extends JFrame{
    public DMouseAdapterDemo(String title) {
        super(title);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DMouseAdapterDemo frame = new DMouseAdapterDemo("MouseEventDemo");
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.printf("mouseClicked at (%d,%d)\n", e.getPoint().x, e.getPoint().y);
                    }
                });
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
