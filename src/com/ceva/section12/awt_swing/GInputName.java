package com.ceva.section12.awt_swing;

import javax.swing.*;

public class GInputName {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String name = JOptionPane.showInputDialog(null, "¿Cual es tu nombre?");
                if (name != null)
                    JOptionPane.showMessageDialog(null, "¡Hola " + name + "!");
            }
        });
    }
}
