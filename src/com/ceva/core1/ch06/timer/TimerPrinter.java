package com.ceva.core1.ch06.timer;

import java.awt.*;
import java.time.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        // getWhen() retorna el tiempo del evento
        System.out.println("At the tone, the time is " + Instant.ofEpochMilli(event.getWhen()));
        Toolkit.getDefaultToolkit().beep();
    }
}
