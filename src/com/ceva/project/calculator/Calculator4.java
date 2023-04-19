package com.ceva.project.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator4 extends JPanel{
    //private Color backgroundColor = new Color(0xc3cdc8); // color pantalla
    private Color backgroundColor = new Color(0x03f705); // color pantalla

//    private LCD7Seg digit;
    private Timer timer;
    private int digitValue;
    private NDigitDisplay display;
    /**
     * Parametros para definir el tamanio de ventana
     */
    public Calculator4(){
        super();
        setBackground(backgroundColor);
        display = new NDigitDisplay(getWidth(), getHeight(), 9);
        //display.setValue(1234567890);
        // cuando se produzca un cambio de propiedad damos aviso con el listener
        addPropertyChangeListener((evt -> {
            if("quit".equals(evt.getPropertyName()) && (Boolean)evt.getNewValue()){
                System.out.println("Stopping timer");
                // si el nuevo valor de la propiedad quit es true, terminamos el times
                timer.stop();
            }
        }));

        // detectamos cuando se produce un cambio en el tamanio de la pantalla
        addComponentListener(new ComponentAdapter() {
            // este metodo se llama cuando el componente cambio de tamanio y sucede cuando se muestra la pantalla
            @Override
            public void componentResized(ComponentEvent e) {
                // cuando se muestre la pantalla se ajusta el tamanio
                display.setSize(getWidth(), getHeight());
            }
        });

        digitValue = -2;
        // probamos que se dibujen todos los numeros
        timer = new Timer(1000, (e -> {
            updateNextValue();
            repaint();
        }));
        timer.start();
    }

    private void updateNextValue(){
        // efecto reset de la pantalla
        if((digitValue < 0) && (digitValue >= -2)){
            digitValue++;
            display.clear();
        }else{
            digitValue *= 2;
            display.setValue(digitValue);
            // no aseguramos que digitValue no se quede en cero
            if(digitValue == 0){
                digitValue = 1;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        display.paintComponent(g2d);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Calculator4 mainPanel = new Calculator4();

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

            frame.add(mainPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

