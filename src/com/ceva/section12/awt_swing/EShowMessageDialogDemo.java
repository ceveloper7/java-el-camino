package com.ceva.section12.awt_swing;

import javax.swing.*;

public class EShowMessageDialogDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "Hola mundo");
            }
        });
    }
}
