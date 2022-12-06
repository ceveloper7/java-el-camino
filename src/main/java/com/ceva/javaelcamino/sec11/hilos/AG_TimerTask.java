/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author PC
 */
public class AG_TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        // definimos el timer taks que es la tarea a ejecutar
        timer.schedule(new TimerTask() {
            private int n;
            @Override
            public void run() {
                System.out.println("n = " + n);
                n++;
                if(n > 10){
                    // exite un metodo cancel() que permite cancelar la tarea agendada pero el timer se mantiene activo
                    // cancel();
                    // detenemos el Thread del timer
                    timer.cancel();
                }
            }
            // esperamos 1 segundo antes de la primera ejecucion
            // el timer stask se va a ejecutar cada segundo
        }, 1000, 1000);
    }
}
