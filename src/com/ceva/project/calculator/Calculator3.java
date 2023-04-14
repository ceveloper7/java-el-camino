package com.ceva.project.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator3 extends JPanel{
    //private Color backgroundColor = new Color(0xc3cdc8); // color pantalla
    private Color backgroundColor = new Color(0x03f705); // color pantalla

    private  LCD7seg digit;
    private javax.swing.Timer timer;
    private int digitValue;
    /**
     * Parametros para definir el tamanio de ventana
     * @param x
     * @param y
     * @param digitWidth ancho del digito
     */
    public Calculator3(int x, int y, int digitWidth){
        super();
        setBackground(backgroundColor);
        digit = new LCD7seg(x, y, digitWidth);
        // cuando se produzca un cambio de propiedad damos aviso con el listener
        addPropertyChangeListener((evt -> {
            if("quit".equals(evt.getPropertyName()) && (Boolean)evt.getNewValue()){
                System.out.println("Stopping timer");
                // si el nuevo valor de la propiedad quit es true, terminamos el times
                timer.stop();
            }
        }));

        // probamos que se dibujen todos los numeros
        timer = new javax.swing.Timer(1000, (e -> {
            updateNextValue();
            repaint();
        }));
        timer.start();
    }

    private void updateNextValue(){
        digitValue = (digitValue+1) % 17;
        digit.setValue(digitValue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        digit.paintComponent(g2d);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            Calculator3 mainPanel = new Calculator3(10, 10, 150);

            JFrame frame = new JFrame();
            frame.setTitle("Calculator 3");
            frame.setMinimumSize(new Dimension(400, 400));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // apagamos el timer al cerrar la app
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    // este metodo se invoca luego que se cerro la ventana
                    // firePropertyChange lanza un mensaje a mainPanel indicando que cambio una propiedad
                    // la propiedad quit antes era false y ahora es true
                    mainPanel.firePropertyChange("quit", false, true);
                }
            });
            frame.setContentPane(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

