package com.ceva.core1.ch06.anonymousInnerClass;

import java.awt.*;
import java.awt.event.*;
import java.time.*;

import javax.swing.*;

public class AnonymousInnerClassTest {
    public static void main(String[] args)
    {
        var clock = new TalkingClock();
        clock.start(1000, true);

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
    /**
     * Starts the clock.
     * @param interval the interval between messages (in milliseconds)
     * @param beep true if the clock should beep
     */
    public void start(int interval, boolean beep)
    {
        /*
         * Cuando necesitamos solo un objeto de esta clase, no necesitamos darle nombre a la clase
         * por ello se les llama clase interna anonima.
         * Creamos un nuevo objeto de la clase que implementa la interface ActionListener. La sintaxis es
         * new SuperType(construction arguments)
            {
                inner class methods and data
            }
         * SuperType puede ser una interface como ActionListener, SuperType puede ser una clase, luego
         * la inner class extediende esa clase.
         */
        var listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("At the tone, the time is "
                        + Instant.ofEpochMilli(event.getWhen()));
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        };
        var timer = new Timer(interval, listener);
        timer.start();
    }
}
