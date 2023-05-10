package com.ceva.project.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator7 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("Calculator 7");
            // dimension width 400px y height 60% del width
            Dimension minSize = new Dimension(400, (int)(400*1.6));
            frame.setMinimumSize(minSize);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            LayoutManager layout = new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS);
            frame.setLayout(layout);

            JPanel topPanel = new Calculator4();
            topPanel.setBackground(Color.red);
            // topPanel sera 1/4 del tamano de la ventana
            topPanel.setPreferredSize(new Dimension(minSize.width, minSize.height/4));

            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(Color.blue);
            // bottomPanel sera 3/4 del tamano de la ventana
            bottomPanel.setPreferredSize(new Dimension(minSize.width, minSize.height*3/4));

            // escuchamos cuando se produce un cambio en el tamano del componente
            frame.getContentPane().addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    Rectangle bounds = e.getComponent().getBounds();
                    topPanel.setPreferredSize(new Dimension(bounds.width, bounds.height/4));
                    bottomPanel.setPreferredSize(new Dimension(bounds.width, bounds.height*3/4));
                    // aplicamos un nuevo layout a los componentes
                    e.getComponent().validate();
                }
            });

            frame.add(topPanel);
            frame.add(bottomPanel);

            // Panel para las teclas de la calculadora
            GridBagLayout gl = new GridBagLayout();
            bottomPanel.setLayout(gl);

            CalcLookAndFeel lookAndFeel = new CalcLookAndFeel();
            // agregamos componentes y los posicionamos
            GridBagConstraints constraints = new GridBagConstraints();
            // incluimos 20 teclas
            for(int row = 0; row < 5; row++){
                for(int col = 0; col < 4; col++){
                    if(row == 4 && col ==3){
                        break;
                    }
                    // posicionamos el componente (tecla)
                    constraints.gridx = col;
                    constraints.gridy = row;
                    // distribuimos el espacio en cada row y col
                    constraints.weightx = 1.0;
                    constraints.weighty = 1.0;
                    // indicamos las celdas horizontal y vertical a ocupar
                    constraints.gridwidth = 1;
                    constraints.gridheight = 1;
                    // que el componente ocupe todo el espacio de la celda
                    constraints.fill = GridBagConstraints.BOTH;
                    CalcKey key = new CalcKey(' ', lookAndFeel);
                    if(row == 3 && col == 3){
                        // ocupamos verticalmente 2 celdas que seran para el signo +
                        constraints.gridheight = 2;
                        key.setVerticalKey();
                    }
                    bottomPanel.add(key, constraints);
                }
            }

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    topPanel.firePropertyChange("quit", false, true);
                }
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
