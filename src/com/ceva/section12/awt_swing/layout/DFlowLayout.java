package com.ceva.section12.awt_swing.layout;


import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * FlowLayout acomoda los componentes horizontalmente en una fila. Ajusta el numero de filas si los elementos superan el ancho de la fila
 */
public class DFlowLayout {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("FlowLayoutDemo");
                FlowLayout layout = new FlowLayout();
                frame.getContentPane().setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);

                for (int n=0; n<15; n++) {
                    JButton button = new JButton("Test " + n);
                    frame.add(button);
                }

                frame.setVisible(true);
            }
        });
    }
}
