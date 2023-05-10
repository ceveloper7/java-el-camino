package com.ceva.project.calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator8 implements KeyListener{
    private JFrame mainFrame;
    private int numDigits = 9;
    private LCDPanel screen;
    private KeyboardPanel keyPad;

    private void start(){
        javax.swing.SwingUtilities.invokeLater(()->{
            mainFrame = new JFrame("Calculator 8");
            Dimension minSize = new Dimension(300, (int)(300*1.6));
            mainFrame.setMinimumSize(minSize);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            CalcLookAndFeel lookAndFeel= new CalcLookAndFeel();
            mainFrame.setBackground(lookAndFeel.getKeyboardBackgroundColor());
            JPanel globalPanel = new JPanel();
            globalPanel.setBackground(lookAndFeel.getKeyboardBackgroundColor());
            LayoutManager layout = new BoxLayout(globalPanel, BoxLayout.PAGE_AXIS);
            globalPanel.setLayout(layout);
            // cremos los componentes para agregarlos
            screen = new LCDPanel(numDigits, lookAndFeel);
            screen.setPreferredSize(new Dimension(minSize.width, minSize.height/5));
            keyPad = new KeyboardPanel(this, lookAndFeel);
            keyPad.setPreferredSize(new Dimension(minSize.width, minSize.height*4/5));

            globalPanel.setBorder(BorderFactory.createEmptyBorder(60,20,20,20));
            globalPanel.add(screen);
            globalPanel.add(keyPad);

            mainFrame.setContentPane(globalPanel);
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            mainFrame.setVisible(true);
        });
    }
    public static void main(String[] args) {
        new Calculator8().start();
    }

    @Override
    public void keyPressed(char code) {

    }
}
