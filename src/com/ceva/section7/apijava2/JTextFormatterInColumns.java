package com.ceva.section7.apijava2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Dado un espacio limitado, pasamos a escribir un texto, una vez completada la columna, pasamos a la sgte columna y seguimos
 * escribiendo el texto. En lugar de imprimir en pantalla cada palabra hasta completar la linea empleamos una coleccion y agregamos
 * en ella las lineas de texto para al final imprimir el texto de la coleccion y formatear la salida.
 */
public class JTextFormatterInColumns {
    private static String getData()throws IOException{
        StringBuilder str = new StringBuilder();

        try(
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(JTextFormatterInColumns.class.getResourceAsStream("resources/magna-carta.txt")))
        ){
            String line = br.readLine();
            while(line != null){
                str.append(line);
                line = br.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return str.toString();
    }

    public static void main(String[] args) throws IOException {
        String str1 = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo " +
                "que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y " +
                "galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, " +
                "duelos y quebrantos los sábados, lantejas los viernes, algún palomino de añadidura " +
                "los domingos, consumían las tres cuartas partes de su hacienda. El resto della " +
                "concluían sayo de velarte, calzas de velludo para las fiestas, con sus pantuflos " +
                "de lo mesmo, y los días de entresemana se honraba con su vellorí de lo más fino. " +
                "Tenía en su casa una ama que pasaba de los cuarenta, y una sobrina que no llegaba " +
                "a los veinte, y un mozo de campo y plaza, que así ensillaba el rocín como tomaba " +
                "la podadera. Frisaba la edad de nuestro hidalgo con los cincuenta años; era de " +
                "complexión recia, seco de carnes, enjuto de rostro, gran madrugador y amigo de " +
                "la caza. Quieren decir que tenía el sobrenombre de Quijada, o Quesada, que en " +
                "esto hay alguna diferencia en los autores que deste caso escriben; aunque, por " +
                "conjeturas verosímiles, se deja entender que se llamaba Quejana. Pero esto importa " +
                "poco a nuestro cuento; basta que en la narración dél no se salga un punto de la " +
                "verdad.";


        int columns = 3; // # columnas a mostrar
        int lineWidth = 35;

// impresion de guia
        for (int n=0; n<lineWidth; n++) {
            // print 9 espacios y a la posicion 10 imprimimos numero
            if (((n+1) % 10) == 0)
                System.out.print(((n/10)+1) % 10);
            else
                System.out.print(" ");
        }
        System.out.println();
        // imprimimos las unidades debajo de las decenas
        for (int n=0; n<lineWidth; n++) {
            System.out.print((n+1) % 10);
        }
        System.out.println();
        // impresion de linea -
        for (int n=0; n<lineWidth; n++)
            System.out.print("-");
        System.out.println();
// fin impresion de guia

        int curPos = 0;
        List<String> lines = new ArrayList<>(); // coleccion de lineas de texto
        StringBuilder curLine = new StringBuilder(); // linea de texto

        boolean space = false;
        StringTokenizer st = new StringTokenizer(getData()); // pasamos una cadena y devuelve los elementos de la cadena separado por espacios
        // mientras haya palabras
        while (st.hasMoreTokens()) {
            // obtenemos la palabra
            String s = st.nextToken();
            // validacion: si la longitud de la palabra + lo acumulado es mayor al tamano de la columna
            if ((curPos + s.length()) > lineWidth) {
                // cuando se excede la longitud maxima de la linea, agregamos la linea a la coleccion de lineas de texto
                lines.add(curLine.toString());
                curLine.setLength(0); // limpiamos la linea para contener el texto de la sgte linea.
                curPos = 0; // reset posicion del cursor
                space = false;
            }
            // validacion: si antes de colocar una palabra debemos agregar un espacio o no.
            if (space)
                curLine.append(" "); // agregamos un espacio para colocar la sgte palabra en la linea actual.
            curLine.append(s); // agregamos la palabra en la linea actual.
            curPos += s.length() + 1; // actualizamos la posicion actual (el tamano de la linea que llevamos escrito)
            space = true;
        }
        // validacion: si la longitud de la linea de texto es mayor a 0, agregamos lo ultimo que se acumulo a la coleccion.
        if (curLine.length() > 0)
            lines.add(curLine.toString());

// formateo en columnas
        /**
         * formula para calcular las filas necesarias.
         * Esta formula retorna la division de dos numeros redondeada al siguiente numero entero, lo redondeamos al sgte entero para
         * aseguarnos que alcance el espacio.
         * Ejemplo: # elementos 10 / 3 # columns -> el resultado es 3.33, si usamos int el resultado es truncado a 3 pero con la formula el resultado es redondeado a 4
         * La formula seria: (10 + (3-1)) / 3
         * 10 -> # filas
         * 3 -> # columnas
         */
        // rows -> # de renglones que se imprimira al final
        int rows = (lines.size() + (columns-1)) / columns;

        /**
         * Cada linea de la coleccion de texto estara compuesta por 3 elementos de la lista
         * 1er renglon -> primer elemento de la lista + el elemento rows * 1 + el elemento rows * 2
         * Los primeros rows renglones forman la primer columna de texto.
         */
// impresion rows en renglones
        for (int n=0; n<rows; n++) {
            curLine.setLength(0);
            // Process
            int col = 0; // # columna
            // mientras el #columnas sea menor al # de columnas a imprimir
            while (col < columns) {
                // validamos que no se exceda el nro de lineas
                if ((rows*col + n) < lines.size()) {
                    // 1 iteracion -> row*0+0
                    // 2 iteracion -> row*1+0
                    // 3 iteracion -> row*2+0
                    curLine.append(lines.get(rows*col + n));
                    // validacion para la primera y segunda columna
                    if (col < (columns-1)) {
                        // una vez agregada la linea completamos con espacios para hasta alcanzar a la siguiente linea
                        while (curLine.length() < (lineWidth + 4)*(col+1))
                            curLine.append(" ");
                    }
                }
                col++; // incremento columna
            }
            // Process end
            System.out.println(curLine.toString());
        }
// fin impresion rows
        System.out.println();
    }
}
