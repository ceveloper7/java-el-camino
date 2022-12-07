/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
public class AA_JFrameDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFrameDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 300));
        // centramos la ventana
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
