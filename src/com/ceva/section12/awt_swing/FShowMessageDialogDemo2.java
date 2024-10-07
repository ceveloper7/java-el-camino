package com.ceva.section12.awt_swing;

import javax.swing.*;

public class FShowMessageDialogDemo2 {
    public static void showMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    public static void main(String[] args) {
        showMessage("Hola mundo");
    }
}
