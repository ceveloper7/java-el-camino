package com.ceva.section13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CWorkingColors extends JPanel{
    public CWorkingColors() {
        super();
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // obtenemos ancho y alto del JPanel
        int width = getWidth();
        int height = getHeight();

        for (int n=0; n<16; n++) {
            g2d.setColor(new Color(0, (256/16)*n, 0));
            g2d.fillRect(n*width/32, n*height/32, width - n*width/16, height - n*height/16);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("DrawingDemo3 - Raul Cosio");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.add(new CWorkingColors());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
