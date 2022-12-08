/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Modo correcto de crear una JFrame En cualquier constructor siempre se debe
 * inicializar las clases bases y al final las clases deribadas
 *
 */
public class AG_CreateJFrame extends JFrame {
    
    private AG_CreateJFrame(){}

    public static AG_CreateJFrame newInstance() {
        AG_CreateJFrame frame = new AG_CreateJFrame();
        frame.setTitle("Create JFrame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AG_CreateJFrame form = AG_CreateJFrame.newInstance();
                form.setVisible(true);
            }
        });
    }
}
