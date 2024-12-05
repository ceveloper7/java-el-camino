package com.ceva.core1.ch07.logging;

import java.util.logging.StreamHandler;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;

public class WindowHandler extends StreamHandler {
    private JFrame frame;

    public WindowHandler()
    {
        frame = new JFrame();
        var output = new JTextArea();
        output.setEditable(false);
        frame.setSize(200, 200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream()
        {
            public void write(int b)
            {
            } // not called

            public void write(byte[] b, int off, int len)
            {
                output.append(new String(b, off, len));
            }
        });
    }

    public void publish(LogRecord record)
    {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}
