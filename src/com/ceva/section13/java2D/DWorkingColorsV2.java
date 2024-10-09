package com.ceva.section13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DWorkingColorsV2 extends JPanel{
    public DWorkingColorsV2() {
        super();
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        int xPos = 0;
        int yPos = 0;
        int curWidth = width;
        int curHeight = height;
        for (int n=0; n<16; n++) {
            g2d.setColor(new Color(0, (256/16)*n, 0));
            g2d.fillRect(xPos, yPos, curWidth, curHeight);

            // Ajustamos los valores para la siguiente iteracion
            xPos += (width/32);
            yPos += (height/32);
            curWidth -= (width/16);
            curHeight -= (height/16);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Drawing color V2");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.setContentPane(new DWorkingColorsV2());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
