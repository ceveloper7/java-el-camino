package com.ceva.section12.awt_swing;

import javax.swing.*;
import java.awt.*;

/*
 * AWT Y SWING no son bibliotecas multithread
 * EDT (event dispatch thread) se encarga de varias tareas de swing como: manejo de eventos y re-dibujado de pantallas.
 * Una aplicacion de Swing crea dos thread:
 * Thread 1: se encarga de capturar los eventos del sistema
 * Thread 2: EDT se encarga de manejar los eventos dibujar en la pantalla y lo que tiene que ver con swing y tambien es responsable
 *           ejecutar cualquier metodo que modifique el estado de un componente
 *
 * SwingUtitlities.isEventDispatchThread: retorna true si el thread en ejecucion es el EDT.
 */
public class BJFrameDemo {
    public static void main(String[] args) {
        // agenda el codigo a ser ejecutado a la primera oportunidad dentro del EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("JFrameDemo");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 300));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
