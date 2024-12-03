package com.ceva.core1.ch06.localinnerclass;

import java.awt.*;
import java.awt.event.*;
import java.time.*;

import javax.swing.*;

public class LocalInnerClassTest {
    public static void main(String... args)
    {
        var clock = new TalkingClock();
        clock.start(1000, true);

        // keep program running until the user selects "Ok"
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
        // definimos la clase local TimerPrinter. Una clase local no lleva modificador de acceso
        // la clase local TimerPrinter esta completamente excluida del mundo exterior. esta clase
        // copia el parametro beep como variable local.
        class TimePrinter implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("At the tone, the time is "
                        + Instant.ofEpochMilli(event.getWhen()));
                /*
                 * las clases locales no solo pueden acceder a campos de su clase externa, ellas pueden
                 * acceder a variables locales, sin emabrgo estas variables deben ser final.
                 */
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }
        // inicializamos la variable listener, llamando al constructor de la clase interna TimePrinter()
        var listener = new TimePrinter();
        // pasamos la referencia al listener a la clase Timer
        var timer = new Timer(interval, listener);
        // se inicia el timer
        timer.start();
    }
}
