package com.ceva.section12.awt_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CMouseEventDemo extends JFrame implements MouseListener {

    public CMouseEventDemo(String title) {
        super(title);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("mouseClicked at (%d,%d)\n", e.getPoint().x, e.getPoint().y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CMouseEventDemo frame = new CMouseEventDemo("MouseEventDemo");
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                // Registramos el listener para controlar los eventos del mouse.
                frame.addMouseListener(frame);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
