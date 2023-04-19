package com.ceva.project.calculator;

import java.awt.*;

public class NDigitDisplay {
    // ancho y alto de la pantalla
    private int width;
    private int height;
    // cantidad de digitos
    private int numDigits;
    // arreglo de objetos LCD7Seg
    private LCD7Seg digits[];

    public NDigitDisplay(int width, int height, int numDigits) {
        this.numDigits = numDigits;
        setSize(width, height);
    }

    /**
     * Metodo que modifica el tamanio de la pantalla.
     * Ajusta cada digit dependiendo del tamanio de la ventana.
     */
    public void setSize(int width, int height){
        // inicializamos el tamanio
        this.width = width;
        this.height = height;

        if(digits == null){
            // inicializamos digits array
            digits = new LCD7Seg[numDigits];
            for(int n = 0; n < digits.length; n++){
                digits[n] = new LCD7Seg(0, 0, 0);
            }
        }

        /*
        * determinamos cual sera el tamanio de cada digit: ancho totald de la pantalla entre numero de digitos +
        * el fgap veces el numero de digitos + 1
        * width: ancho disponible para cada digito
        * */
        float fgap = 0.26f;
        int digitWidth = width / (int)((numDigits + (fgap * (numDigits+1))) + 0.9f);
        // el height de un digit es width*2
        if((digitWidth*2 + 20) > height){
            digitWidth = (height - 20)/2;
        }

        int margin = (int)(digitWidth*fgap);
        // colocamos a x casi a la izquierda de la pantalla
        int x = width - digitWidth * numDigits - margin * (numDigits + 1);
        // coordenada y tendra 10px de margen
        int y = 10;
        // asignamos a los digits el ancho. n va a de 0 a 4 y index va de 4 a 0
        for(int n = 0; n < numDigits; n++){
            int index = numDigits - n - 1;
            // establecemos la posicion de los digitos en la pantalla
            digits[index].setPosition(x, y, digitWidth);
            x += digitWidth + margin;
        }
    }

    public void setValue(long value){
        int n = 0;
        // minus is true si value es menor a 0
        boolean minus = value < 0;
        if(minus)
            value = -value;
        if(digits == null)
            return;
        while(n < digits.length) {
            if((value == 0) && (n > 0)){
                if(minus){
                    digits[n].setSegments(LCD7Seg.SEGMENT_3);
                    minus = false;
                }else{
                    // limpiamos el digito
                    digits[n].setValue(-1); // -1 limpia todos los segments del digit
                }
            }else{
                // colocamos al digit indice n el valor del modulo 10
                digits[n].setValue((byte) (value % 10));
            }
            value = value / 10;
            n++;
        }

        if((value > 0 || minus)){
            clear();
            // validamos tener el espacio para la palabra error
            if(digits.length >= 5) {
                // dibujamos la palabra error en la pantalla
                digits[4].setSegments(LCD7Seg.SEGMENT_0 | LCD7Seg.SEGMENT_1 | LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4 | LCD7Seg.SEGMENT_6);
                digits[3].setSegments(LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4);
                digits[2].setSegments(LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4);
                digits[1].setSegments(LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4 | LCD7Seg.SEGMENT_5 | LCD7Seg.SEGMENT_6);
                digits[0].setSegments(LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4);
            }else if(digits.length >= 3){
                digits[2].setSegments(LCD7Seg.SEGMENT_0 | LCD7Seg.SEGMENT_1 | LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4 | LCD7Seg.SEGMENT_6);
                digits[1].setSegments(LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4);
                digits[0].setSegments(LCD7Seg.SEGMENT_3 | LCD7Seg.SEGMENT_4);
            }else{
                // solo dibujamos una linea
                digits[0].setSegments(LCD7Seg.SEGMENT_3);
            }
        }
    }

    public void clear(){
        if(digits == null){
            return;
        }
        for(LCD7Seg d : digits){
            d.setValue(-1);
        }
    }

    public void paintComponent(Graphics2D g2d){
        if(digits == null){
            return;
        }
        for(LCD7Seg d : digits){
            d.paintComponent(g2d);
        }
    }
}
