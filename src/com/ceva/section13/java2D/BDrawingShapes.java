package com.ceva.section13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BDrawingShapes extends JPanel{
    public BDrawingShapes() {
        super();
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.yellow);
        g2d.drawOval(0, 0, 100, 100); // circulo

        g2d.setColor(Color.red);
        g2d.drawRect(120, 120, 100, 100); // rectangulo

        g2d.setColor(Color.green);
        g2d.drawRect(10, 100, 100, 100);
        g2d.fillArc(10, 100, 100, 100, 0, 60); // arco con relleno

        g2d.setColor(Color.cyan);
        g2d.fillRoundRect(200, 10, 150, 100, 40, 40); // rectangulo relleno con bordes redondeados

        g2d.setColor(Color.orange);
        // primer punto en la coordenada (300,200)
        // segundo punto en la coordenada (350,300)
        // tercer punto en la coordenada (250,300)
        int xPoints[] = {300, 350, 250};
        int yPoints[] = {200, 300, 300};
        // pasamos coordena x, coordenada y, mas el numero de puntos 3
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Drawing Shaped");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.setContentPane(new BDrawingShapes());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
