package com.ceva.project.calculator;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que actuamo como observador (listener) del teclado
 * cada vez que se presiona una tecla se genera el evento keyPressed
 */
public class Calculator8 implements KeyListener{
    private JFrame mainFrame;
    private int numDigits = 9;
    private LCDPanel screen;
    private KeyboardPanel keyPad;
    // almacenamos el valor de la tecla presionada
    private StringBuilder edValue;
    // almacenamos la operacion matematica a realizar (+,-,*,/)
    private char op;

    /**
     * Si se ingresa 5 + 3
     * 5 pasa a edValue
     * + pasa a op
     * 5 pasa a lastValue
     * 3 pasa a edValue
     */
    private StringBuilder lastValue;

    public Calculator8(){
        edValue = new StringBuilder();
        lastValue = new StringBuilder();
    }

    private void start(){
        javax.swing.SwingUtilities.invokeLater(()->{
            mainFrame = new JFrame("Calculator 8");
            Dimension minSize = new Dimension(300, (int)(300*1.6));
            mainFrame.setMinimumSize(minSize);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            CalcLookAndFeel lookAndFeel= new CalcLookAndFeel();
            mainFrame.setBackground(lookAndFeel.getKeyboardBackgroundColor());
            JPanel globalPanel = new JPanel();
            globalPanel.setBackground(lookAndFeel.getKeyboardBackgroundColor());
            LayoutManager layout = new BoxLayout(globalPanel, BoxLayout.PAGE_AXIS);
            globalPanel.setLayout(layout);
            // cremos los componentes para agregarlos
            screen = new LCDPanel(numDigits, lookAndFeel);
            screen.setPreferredSize(new Dimension(minSize.width, minSize.height/5));
            keyPad = new KeyboardPanel(this, lookAndFeel);
            keyPad.setPreferredSize(new Dimension(minSize.width, minSize.height*4/5));

            globalPanel.setBorder(BorderFactory.createEmptyBorder(60,20,20,20));
            globalPanel.add(screen);
            globalPanel.add(keyPad);

            mainFrame.setContentPane(globalPanel);
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            mainFrame.setVisible(true);
        });
    }
    public static void main(String[] args) {
        new Calculator8().start();
    }

    private void calculate(){
        // convertimos los valores ingresados en numeros
        try{
            boolean errorFlag = false;
            double d0 = Double.parseDouble(lastValue.toString());
            double d1 = Double.parseDouble(edValue.toString());
            double res;
            switch (op){
                case '+':
                    res = d0 + d1;
                    break;
                case '-':
                    res = d0 - d1;
                    break;
                case '*':
                    res = d0 * d1;
                    break;
                case '/':
                    res = d0 / d1;
                    if((Double.isNaN(res)) || (Double.isInfinite(res))){
                        errorFlag = true;
                    }
                    // validamos que res no sea menor al valor minimo permitido de un double
                    else if(Math.abs(res) < Double.MIN_NORMAL){
                        res = 0;
                    }
                    break;
                default:
                    res = 0;
            }
            // convertimos el resultado a String para presentarlo en la pantalla
            String strRes;
            // validaos q no haya errores
            if(!errorFlag){
                // si no hay errores, formateamos el resultado (res) hasta 9 decimales
                strRes = String.format("%.9f", res);
            }
            else{
                strRes = "";
            }
            // validamos caso de los ceros a la derecha despues del punto decimal
            int extra = strRes.length()-1; // obtenemos la posicion del ultimo caracter en strRes
            // mientras q el ultimo caracter es cero, decrementamos extra
            while((extra > 0) && (strRes.charAt(extra) == '0')){
                extra--;
            }
            // eliminamos los cero a la derecha despues del punto decimal
            if(extra < strRes.length() - 1){
                strRes = strRes.substring(0, extra+1);
            }

            // validamos el caso del punto decimal
            int inxDot = strRes.indexOf('.');
            if(inxDot == -1)
                inxDot = strRes.indexOf(',');
            int strResLen = strRes.length();
            if(inxDot >= 0){
                // si hay un indice, el punto decimal no lo tomamos para dibujar los digitos
                strResLen--;
            }
            // validamos si no nos excedemos del numero valido de digitos
            if(strResLen > numDigits){
                // la parte fraccionaria se trunca hasta la cantidad de numero disponble
                // si la cadena tiene un punto, tomamos un digito mas
                // tomamos todos los caracteres que se encuentren hasta el num de digitos disponibles
                strRes = strRes.substring(0, numDigits + (inxDot >= 0 ? 1 : 0));
                // 1234567890.123 la cadena es mayor a 9 digitos
                // tomamos los primero nueve es decir, 123456789
                // validamos si inxDot es mayor a la longitud del string que contiene 123456789
                // significa que truncamos el punto decimal
                if(inxDot > strRes.length()){
                    // se trunco el punto decimal
                    errorFlag = true;
                    strRes = "";
                }
            }
            if(errorFlag){
                screen.displayError();
            }
            // limpiamos las variables
            lastValue.setLength(0);
            edValue.setLength(0);
            edValue.append(strRes);
            op = 0;
        }
        catch(NumberFormatException e){
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        }
    }

    /**
     * Metodo que maneja la logica de la calculadora
     * Al presionar una tecla, almacenamos el valor de la tecla en una variable
     * @param code
     */
    @Override
    public void keyPressed(char code) {
        // validamos las operaciones basicas
        if((code == '+') || (code == '-') || (code == '*') || (code == '/')){
            op =  code;
            // limpiamos lastValue
            lastValue.setLength(0);
            lastValue.append(edValue);
            edValue.setLength(0);
        }
        // validamos si se presiona el signo =
        else if(code == '='){
            calculate();
            if(edValue.length() > 0){
                screen.setValue(edValue);
            }
        }
        // casos digitos o puntos decimal
        else{
            // donde se encuentra el punto decimal
            int decimalIndex = edValue.indexOf(".");
            if(decimalIndex == -1)
                decimalIndex = edValue.indexOf(",");
            // longitud de edValue dependiendo si hay o no un punto decimal
            int edValueLen = edValue.length() - (decimalIndex >= 0 ? 1 : 0);
            // validamos que la longitud de edValue sea menos al numero de digitos permitidos
            if(edValueLen < numDigits){
                // validamos el caso donde se coloca dos veces punto
                if((code == '.') || (code == ',')){
                    // validamos si ya existe un punto decimal
                    if(decimalIndex >= 0){
                        return;
                    }
                    // si se presiona primero la tecla . edValue.lenght == 0, se debe anteponer un 0
                    if(edValue.length() == 0){
                        edValue.append("0");
                    }
                }
                edValue.append(code);
                screen.setValue(edValue);
            }
        }
    }
}
