package com.ceva.core1.ch06.innerclass;


import java.awt.*;
import java.awt.event.*;
import java.time.*;

import javax.swing.*;

public class InnerClassTest {
    public static void main(String[] args)
    {
        var clock = new TalkingClock(1000, true);
        clock.start();

        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock
{
    private int interval;
    private boolean beep;

    /**
     * Constructs a talking clock.
     * @param interval the interval between messages (in milliseconds)
     * @param beep true if the clock should beep
     */
    public TalkingClock(int interval, boolean beep)
    {
        this.interval = interval;
        this.beep = beep;
    }

    /**
     * Starts the clock.
     */
    public void start()
    {
        // la clase TimerPrinter esta dentro de la clase TalkingClock
        // cuando TimerPrinter es construido el compilador pasa una referencia a TalkingClock
            var listener = new TimePrinter();
        var timer = new Timer(interval, listener);
        timer.start();
    }

    /*
     * Toda inner class tiene una referencia al objeto de la clase externa que la establece
     * dentro del constructor el compilador por nosotros.
     * Solo las inner class pueden ser tanto public como private
     * Clas TimePrinter no posee campos de instancia aunque bien podria tenerlos.
     * TimerPrinter accede a los atributos de su clase padre.
     */
    private class TimePrinter implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("At the tone, the time is "
                    + Instant.ofEpochMilli(event.getWhen()));
            // la clase anidada TimerPrinter accede al campo de instancia privado beep de TalkingClock
            // por medio de la referencia que toda inner class (TimePrinter) posee hacia su clase externa (TalkingClock)
            // aunque dicha referencia es invisible por lo que bien podemos escribir simplemente beep o
            if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}

