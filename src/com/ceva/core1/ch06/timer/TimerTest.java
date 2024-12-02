package com.ceva.core1.ch06.timer;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

public class TimerTest {
    public static void main(String[] args)
    {
        var listener = new TimerPrinter();

        // construct a timer that calls the listener once every second
        // delay tiempo que debe pasar entre cada notificacion (1 segundo)
        // timer llama a actionPerformed() del objeto listener cada vez que el tiempo delay se cumpla
        var timer = new Timer(1000, listener);
        timer.start();

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
