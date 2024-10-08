package com.ceva.section12.awt_swing;

import javax.swing.*;
import java.awt.*;

public class HCreateJFrameDemo extends JFrame {
    
    public static HCreateJFrameDemo newInstance(){
        HCreateJFrameDemo frame = new HCreateJFrameDemo();
        frame.setTitle("CreateJFrameDemo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private HCreateJFrameDemo(){}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HCreateJFrameDemo newFrame = newInstance();
                newFrame.setVisible(true);
            }
        });
    }
}
