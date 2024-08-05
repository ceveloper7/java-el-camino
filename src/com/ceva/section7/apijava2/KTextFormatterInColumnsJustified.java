package com.ceva.section7.apijava2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KTextFormatterInColumnsJustified {
    private List<String> lines;
    private int columns;
    private int lineWidth;

    public KTextFormatterInColumnsJustified(String text, int lineWidth, int columns, boolean justify){
        // inicializamos los datos del objeto.
        setFormat(lineWidth, columns);
        setText(text, justify);
    }

    private static String getData()throws IOException {
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

    /**
     * Establecemos el ancho y nro de columnas
     * @param lineWidth - ancho
     * @param columns - nro columnas
     */
    public void setFormat(int lineWidth, int columns) {
        this.lineWidth = lineWidth;
        this.columns = columns;
    }

    /**
     * Aplicamos la justificacion al texto
     * Recorremos cada linea de la coleccion y justificamos el texto
     */
    private void formatLines() {
        StringBuilder sb = new StringBuilder();
        // Algoritmo que distribuye numero de espacios en la linea de texto para justificar el texto
        // 1. Iteramos las lineas de texto de la coleccion.
        for (int nLine=0; nLine<lines.size(); nLine++) {
            // 2. Obtenemos una linea de la coleccion.
            String line = lines.get(nLine);
            // 3. validacion: Si la linea de texto es igual o mayor a la longitud de la columna entonces nos aplicamos el algoritmo
            if (line.length() >= lineWidth)
                continue;

            int sp = 0; // contador de espacios en la linea de texto.
            // 4. Iteramos la linea de texto para contar cuantos espacios hay.
            for (int n=0; n<line.length(); n++) {
                if (line.charAt(n) == ' ')
                    sp++;
            }
            // 5. Validacion: Si no hay espacios, no hay forma de justificar el texto, entonces, pasamos a la siguiente linea.
            if (sp == 0)
                continue;

            // StringBuilder para trabajar con la linea de texto a justificar.
            // 6. en cada iteracion la longitud sera 0
            sb.setLength(0);
            // 7. Agregamos la linea de texto al StringBuilder
            sb.append(line);
            // 8. Obtenemos el nro de espacios necesarios para justificar la linea de texto
            // spaceCount = Longitud de columna - longitud linea texto + nro espacios
            // spaceCount = 35 - 34 + 6
            // Por lo tanto: Para que la segunda linea de texto este justificada es necesario que haya 7 espacios en blanco.
            int spaceCount = lineWidth - line.length() + sp; // total espacios
            // en cada palabra debe haber space1 espacios y completamos la linea con mod espacios para lograrlo agregamos space2 espacios
            // en cada palabra
            int space1 = spaceCount / sp;       // espacios que toca entre cada palabra
            // espacios que sobran en la linea de texto. Se debe repartir mod espacios entre las palabras hasta justificar la linea
            int mod = spaceCount % sp;          // nro de espacios a repartir entre las palabras para justificar la linea.
            // distribucion de espacios
            int space2 = (mod + (sp-1)) / sp;   // espacios extra a repartir en cada palabra,
            // mientras alcance porque esta redondeado.

            int ins = 0;
            for (int n=0; n<sp; n++) {
                // ubicamos en que posiciones de la linea de texto hay un espacio
                ins = sb.indexOf(" ", ins);
                // insertamos espacios adicionales. Debemos repartir space2 espacios hasta completar mod
                int insCount = (mod > space2) ? space2 : mod; // reparto space2 o lo que quede
                // cada vez que inserto espacio adicional, le restamos a mod hasta que mod sea cero
                mod -= insCount;
                insCount += space1 - 1;         // Mas lo que falta para completar space1, o
                // los espacios que debe haber en cada palabra,
                // resto 1 porque asumo que entre cada palabra
                // ya existe un espacio.
                for (int i=0; i<insCount; i++) {
                    sb.insert(ins, ' ');
                }
                ins += 1 + insCount; // buscar siguiente espacio
            }
            lines.set(nLine, sb.toString());
        }
    }

    /**
     * Metodo que aplica formato al texto.
     * @param text
     * @param justify
     */
    public void setText(String text, boolean justify) {
        if (lines == null)
            lines = new ArrayList<>();
        else
            lines.clear();

        int curPos = 0;
        boolean space = false;
        StringTokenizer st = new StringTokenizer(text);
        StringBuilder curLine = new StringBuilder();
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if ((curPos + s.length()) > lineWidth) {
                lines.add(curLine.toString());
                curLine.setLength(0);
                curPos = 0;
                space = false;
            }
            if (space)
                curLine.append(" ");
            curLine.append(s);
            curPos += s.length() + 1;
            space = true;
        }
        lines.add(curLine.toString());

        if (justify)
            formatLines();
    }

    private void printRuler() {
        for (int n=0; n<lineWidth; n++) {
            if (((n+1) % 10) == 0)
                System.out.print(((n/10)+1) % 10);
            else
                System.out.print(" ");
        }
        System.out.println("");
        for (int n=0; n<lineWidth; n++) {
            System.out.print((n+1) % 10);
        }
        System.out.println();
        for (int n=0; n<lineWidth; n++)
            System.out.print("-");
        System.out.println();
    }

    /**
     * Print text
     */
    public void println() {
        printRuler();

        // formato en varias columnas
        StringBuilder curLine = new StringBuilder();
        int rows = (lines.size() + (columns-1)) / columns;
        for (int n=0; n<rows; n++) {
            curLine.setLength(0);

            int col = 0;
            while (col < columns) {
                if ((col*rows + n) < lines.size()) {
                    curLine.append(lines.get(col*rows + n));
                    if (col < (columns-1)) {
                        while (curLine.length() < (lineWidth + 4)*(col+1))
                            curLine.append(" ");
                    }
                }
                col++;
            }
            System.out.println(curLine.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        int columns = 3;
        int lineWidth = 35;

        KTextFormatterInColumnsJustified fmt = new KTextFormatterInColumnsJustified(getData(), lineWidth, columns, true);
        fmt.println();
        System.out.println();
    }
}
