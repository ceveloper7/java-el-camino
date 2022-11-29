/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec7;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author PC
 */
public class PrintTextInColumns {
    public static void main(String[] args) {
        System.out.println();
        String str = "esternocleidomastoide1 esternocleidomastoide2 esternocleidomastoide3 " +
"esternocleidomastoide4 esternocleidomastoide5 esternocleidomastoide6\n";

// start print rule
        // usamos modulo % 10 para que siempre sea un dígito
        int columnWidth = 25;
        // numer de columnas
        int numberOfColumns = 3;
        // start print decenas ex:    1   2   3 ...
        System.out.println();
        for (int j = 0; j < columnWidth; j++) {
            if (((j + 1) % 10) == 0) {
                System.out.print(((j / 10) + 1) % 10);
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
        // end print decenas
        
        // start print units ex: 1234567890
        for (int n = 0; n < columnWidth; n++) {
            System.out.print((n + 1) % 10);
        }
        // start print underline ---------------
        System.out.println();
        for (int i = 0; i < columnWidth; i++) {
            System.out.print("-");
        }
        System.out.println();
// end print rule

// begin program
// start formatter text
        int currentPosition = 0;
        // lista con cadenas de texto donde la logitud de cada cadena no excede el limite establecido en columwidth
        List textLines = new ArrayList();
        // objeto que contiene la linea actual de texto
        StringBuilder currentTextLine = new StringBuilder();
        
        // al tomar las palabras separadas por espacio para mostrarlo en pantalla tengo que volver a escribir el espacio que con StringTokenizer eliminamos
        // pero a la primera palabra que escribimos no le debemos agregar un espacio 
        boolean space = false;
        // Tokenizer por defecto usa como token el space en un texto dado (str)
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            // leemos una palabra
            String word = st.nextToken();
            // validamos si la posicion actual + la longitud de la palabra en str supera el lineWidth (no debemos excedernos el limite)
            // si excedemos la longitud maxima del columnWidth
            if ((currentPosition + word.length()) > columnWidth) {
                textLines.add(currentTextLine.toString());
                // limpiamos la linea de texto actual
                currentTextLine.setLength(0);
                // reseteamos la posicion a 0
                currentPosition = 0;
                space = false;
            }
            // space solo será false la primera vez que empezamos a escribir en console. La segunda iteracion space = true
            if (space) {
                // agregamos a la linea actual un espacio
                currentTextLine.append(" ");
            }
            // agregamos a la linea actual la palabra
            currentTextLine.append(word);
            // actualizamos la posicion, + 1 representa al espacio que incluimos
            currentPosition += word.length() + 1;
            space = true;
        }
        // descargamos a textLines la ultima linea que se pudo haber acumulado en el ultimo ciclo del while
        if(currentTextLine.length() >  0){
            textLines.add(currentTextLine.toString());
        }
// end formatter text
        
        /**
         * Numero de renglones redondeado al siguiente digito
         * Dada la division 10 / 3 es igual a 3.3333333 pero queremos que de 4
         * con la formula seria:
         * rows = (10 + (3-1)) / 3
         * rows -> numero de renglones que se imprimiran
         */
        int rows = (textLines.size() + (numberOfColumns - 1)) / numberOfColumns;
        
        /**
         * ciclo for donde componemos cada linea contiene 3 elementos de la lista
         * el primer renglon estara conformado por el primer elemento de la lista + rows * 1 + rows * 2
         * los primeros rows renglones forman la primer columna de texto, lo siguientes rows renglones
         * forman la sgte columna y los sgtes rows forman la tercera columna hasta donde alcance
         */
        for(int n = 0; n < rows; n++){
            currentTextLine.setLength(0);
            // process
            int columnNro = 0;
            // mientras que el numero de columna sea menor al numero de columnas a imprimir
            while(columnNro < numberOfColumns){
                if((rows * columnNro + n) < textLines.size()){
                    /**
                     * 1era iteracion: n = a 0 por lo que rows * columnNro + n =
                     * rows * 0 + 0 todo eso es igual a 0 2da iteracion: rows *
                     * 1 + 0 3era iteracion: rows * 2 + 0
                     */
                    currentTextLine.append(textLines.get(rows * columnNro + n));

                    // solo completamos espacion para las columnas 0 y 1 pero no es necesario para la columna 2
                    if (columnNro < (numberOfColumns - 1)) {
                        // completamos con espacios en blanco hasta llegar a la posicion para pasar a la sgte columna
                        // mientras que la linea actual o lo que llevo armado sea menor a...
                        /**
                         * columnWidth = 35 mientras que la longitud de
                         * currentTextLine sea menor a 35. a columnWidth le
                         * damos un margen de 4 espacios y luego se imprime la
                         * segunda linea currentTextLine contiene todas las
                         * columnas de la linea
                         */
                        while (currentTextLine.length() < (columnWidth + 4) * (columnNro + 1)) {
                            currentTextLine.append(" ");
                        }
                    }
                columnNro++;
                }
            }
            System.out.println(currentTextLine.toString());
        }
    }
}
