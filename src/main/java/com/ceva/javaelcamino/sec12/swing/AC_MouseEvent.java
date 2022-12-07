/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec12.swing;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AC_MouseEvent extends JFrame implements MouseListener{
    
    public AC_MouseEvent(String title){
        super(title);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("mouse clicked at (%d, %d)\n", e.getPoint().x, e.getPoint().y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse relesed");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AC_MouseEvent frame = new AC_MouseEvent("Mouse event demo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                
                frame.addMouseListener(frame);
                
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
}
