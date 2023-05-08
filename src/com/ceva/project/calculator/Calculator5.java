package com.ceva.project.calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            CalcKey panel = new CalcKey('+', new KeyListener() {
                @Override
                public void keyPressed(char code) {
                    System.out.println("Tecla "+ code + " presionada");
                }
            }, new CalcLookAndFeel());
            panel.setVerticalKey();

            JFrame frame = new JFrame("Calculator 5 - C.V.");
            frame.setMinimumSize(new Dimension(300, 300));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
