package com.ceva.section17.multithread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Internamente un Timer esta relacionado con un Thread que se encarga de ejecutar las tareas que tiene programadas
 *
 */
public class GTimerTest1 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // pasamos al timer una tarea, una vez que pasa 1 segundo se ejecuta la tarea por primera vez y luego
        // la tarea se ejecuta cada segundo por eso los valores 1000, 1000 (mil milisegundo es 1 segundo)
        timer.schedule(new TimerTask() {
            private int n = 0;
            @Override
            public void run() {
                System.out.println("n = ");
                n++;
                // si supera 10 finalizamos el timer
                if (n > 10) {
                    timer.cancel(); // nos aseguramos que el thread del timer termine, por eso llamamos al metodo cancel del timer y no del TimerTask
                }
            }
        }, 1000, 1000);
    }
}
