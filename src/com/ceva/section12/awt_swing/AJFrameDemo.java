package com.ceva.section12.awt_swing;

import javax.swing.*;
import java.awt.*;

public class AJFrameDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFrameDemo");
        /*
         * NUNCA USAR EXIT_ON_CLOSE esta opcion hace que se ejecute System.exit el cual hace que la app termine de golpe
         * cerrandose tambien cualquier otra ventana abierta, hilos y app en si.
         */
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
