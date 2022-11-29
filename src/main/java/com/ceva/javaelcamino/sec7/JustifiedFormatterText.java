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
public class JustifiedFormatterText {
    private List textLines;
    private int numberOfColumns;
    private int columnWidth;
    
    public JustifiedFormatterText(String str, int columnWidth, int numberOfColumns, boolean justified){
        // establecemos ancho y numero d columnas
        setFormat(columnWidth, numberOfColumns);
        setText(str, justified);
    }
    
    public void setFormat(int columnWidth, int numberOfColumns){
        this.columnWidth =  columnWidth;
        this.numberOfColumns = numberOfColumns;
    }
    
    public void setText(String str, boolean justified){
        if(textLines == null){
            textLines = new ArrayList();
        }else{
            textLines.clear();
        }
        
        int currentPosition = 0;
        boolean space = false;
        StringTokenizer st = new StringTokenizer(str);
        StringBuilder currentTextLine = new StringBuilder();
        while(st.hasMoreTokens()){
            String word = st.nextToken();
            if ((currentPosition + word.length()) > columnWidth) {
                textLines.add(currentTextLine.toString());
                currentTextLine.setLength(0);
                currentPosition = 0;
                space = false;
            }
            if (space) {
                currentTextLine.append(" ");
            }
            currentTextLine.append(word);
            currentPosition += word.length() + 1;
            space = true;
        }
        if(currentTextLine.length() >  0){
            textLines.add(currentTextLine.toString());
        }
        
        if(justified){
            applyJustifiedFormat();
        }
    }
    
/**
 *       1         2         3     
12345678901234567890123456789012345
-----------------------------------
En un lugar de la Mancha, de cuyo  
nombre no quiero acordarme, no ha  
mucho tiempo que vivía un hidalgo   
de los de lanza en astillero,      
adarga antigua, rocín flaco y galgo
 */
    private void applyJustifiedFormat(){
        StringBuilder temporalLineText = new StringBuilder();
        // debemos crear un algoritmo que distribuya los espacios entre las palabras para justificar el texto
        for(int nLines = 0; nLines < this.textLines.size(); nLines++){
            // tomamos cada linea y la justificamos
            String line = (String)this.textLines.get(nLines);
            // validacion
            if(line.length() >= this.columnWidth){
                continue; // no aplicamos algoritmo
            }
            // determinamos el nro de espacios que existe en la linea
            int numberOfSpacesInText = 0;
            for(int n = 0; n < line.length(); n++){
                if(line.charAt(n) == ' '){
                    numberOfSpacesInText++;
                }
                if(numberOfSpacesInText == 0) 
                    continue;
                
                temporalLineText.setLength(0);
                temporalLineText.append(line);
                
                int spacesNeededToJustifyLineText =  this.columnWidth - line.length() + numberOfSpacesInText;
                
                // En cada palabra debe haber numberOfSpacesNeededBetweenWord espacios y debo completar con remainingSpaces espacios
                // en toda la linea y en cada palabra voy a agregar extraSpaces espacios hasta que se
                // complete los remainingSpaces espacios
                
                int numberOfSpacesNeededBetweenWord = spacesNeededToJustifyLineText  / numberOfSpacesInText;
                // remainingSpaces corresponde al numero de espacios que se deben repartir adicionales para completar
                // el ancho total de la columna
                int remainingSpaces = spacesNeededToJustifyLineText % numberOfSpacesInText;
                // representa el nro de espacios adicionales a completar en cada palabra hasta que se complete
                // los remainingSpaces espacios
                int extraSpaces = (remainingSpaces + (numberOfSpacesInText + 1) / numberOfSpacesInText);
                
                int spacePosition = 0;
                for(int i = 0; i < numberOfSpacesInText; i++){
                    // ubicamos la posicion donde hay un espacio
                    spacePosition = temporalLineText.indexOf(" ", spacePosition);
                    // Ademas de que debe haber numberOfSpacesNeededBetweenWord espacios debemos repartir extraSpaces hasta completar remainingSpaces
                    // si remainingSpaces es mayor a extraSpaces entonces insertamos extraSpaces espacios si ya no alcanza
                    // entonces repartimos remainingSpaces espacios
                    int insCount = (remainingSpaces > extraSpaces) ? extraSpaces : remainingSpaces;
                    // cada vez que agregamos espacio adicional para insertar se lo restamos a remainingSpaces hasta
                    // que remainingSpaces sea 0
                    remainingSpaces -= insCount;
                    insCount += numberOfSpacesNeededBetweenWord -1;
                    // el nro de espacios a insertar es igual a (al numero que debe existir entre cada palabra) - 1
                    for(int j = 0; j < insCount; j++){
                        temporalLineText.insert(spacePosition, ' ');
                    }
                    // actualizamos la posicion para buscar el sgte espacio
                    spacePosition += 1 + insCount; // 1 + espacios ya insertados
                }
                this.textLines.set(nLines, temporalLineText.toString());
            }
        }
    }
    
    private void printRuler(){
        // start print decenas ex:    1   2   3 ...
        System.out.println();
        for (int j = 0; j < this.columnWidth; j++) {
            if (((j + 1) % 10) == 0) {
                System.out.print(((j / 10) + 1) % 10);
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
        // end print decenas
        
        // start print units ex: 1234567890
        for (int n = 0; n < this.columnWidth; n++) {
            System.out.print((n + 1) % 10);
        }
        // start print underline ---------------
        System.out.println();
        for (int i = 0; i < this.columnWidth; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    public void printText(){
        printRuler();
        
        StringBuilder currentTextLine = new StringBuilder();
        int rows = (this.textLines.size() + (this.numberOfColumns - 1)) / this.numberOfColumns;
        for(int n = 0; n < rows; n++){
            currentTextLine.setLength(0);
            // process
            int columnNro = 0;
            // mientras que el numero de columna sea menor al numero de columnas a imprimir
            while(columnNro < this.numberOfColumns){
                if((rows * columnNro + n) < this.textLines.size()){
                    currentTextLine.append(this.textLines.get(rows * columnNro + n));

                    if (columnNro < (this.numberOfColumns - 1)) {
                        while (currentTextLine.length() < (this.columnWidth + 4) * (columnNro + 1)) {
                            currentTextLine.append(" ");
                        }
                    }
                columnNro++;
                }
            }
            System.out.println(currentTextLine.toString());
        }
    }
    
    public static void main(String[] args) {
        String simpleText = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo\n"
                + "que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y\n"
                + "galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches,\n"
                + "duelos y quebrantos los sábados, lantejas los viernes, algún palomino de añadidura\n"
                + "los domingos, consumían las tres cuartas partes de su hacienda. El resto della\n"
                + "concluían sayo de velarte, calzas de velludo para las fiestas, con sus pantuflos\n"
                + "de lo mesmo, y los días de entresemana se honraba con su vellorí de lo más fino.\n"
                + "Tenía en su casa una ama que pasaba de los cuarenta, y una sobrina que no llegaba\n"
                + "a los veinte, y un mozo de campo y plaza, que así ensillaba el rocín como tomaba\n"
                + "la podadera. Frisaba la edad de nuestro hidalgo con los cincuenta años; era de\n"
                + "complexión recia, seco de carnes, enjuto de rostro, gran madrugador y amigo de\n"
                + "la caza. Quieren decir que tenía el sobrenombre de Quijada, o Quesada, que en\n"
                + "esto hay alguna diferencia en los autores que deste caso escriben; aunque, por\n"
                + "conjeturas verosímiles, se deja entender que se llamaba Quejana. Pero esto importa\n"
                + "poco a nuestro cuento; basta que en la narración dél no se salga un punto de la\n"
                + "verdad.";
        int columns = 3;
        int columWidth = 35;
        
        JustifiedFormatterText fmt = new JustifiedFormatterText(simpleText, columWidth, columns, true);
        fmt.printText();
    }
}
