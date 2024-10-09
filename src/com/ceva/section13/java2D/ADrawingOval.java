package com.ceva.section13.java2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * Graphics y Graphics2D
 * Estos objetos representan el contexto sobre el cual podemos comenzar a dibujar. Graphics2D se deriba de Graphics
 */
public class ADrawingOval extends JPanel{
    public ADrawingOval() {
        super();
        setBackground(Color.BLACK);
    }

    // dibujamos la forma. el metodo paintComponent es invocado por java cuando cree que es necesario. por ejemplo cuando redimensionamos la venta
    // si necesitamos nosotros invocarlo, hay formas de hacerlo.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        // getWidth() y getHeight() son metodos del JPanel que retorna los pixels de ancho (right) y alto(bottom) respectivamente.
        g2d.drawOval(0, 0, getWidth()-32, getHeight()-32);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Drawing Oval");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                // Los dibujos se realizan dentro del JPanel, por ello pasamos el JPanel DrawingOval al frame.setContentPane()
                frame.setContentPane(new ADrawingOval());
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
