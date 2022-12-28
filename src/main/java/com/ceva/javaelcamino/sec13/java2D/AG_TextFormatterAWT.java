/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec13.java2D;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class AG_TextFormatterAWT extends JPanel {
    private List<String> words;
    private boolean justify;
    private int numColumns;
    
    public AG_TextFormatterAWT(String text, int columns, boolean justify){
        super();
        this.numColumns = columns;
        this.justify = justify;
        setText(text);
    }
    
    public void setText(String text){
        if(words == null){
            words = new LinkedList<>();
        }else{
            words.clear();
        }
        StringTokenizer st = new StringTokenizer(text);
        while(st.hasMoreTokens()){
            String s = st.nextToken();
            words.add(s);
        }
    }
    
    private void drawText0(Graphics2D g2d){
        // 1. Establecemos el FontMetrics
        FontMetrics fm = g2d.getFontMetrics();
        // 2. definimos un marge izquierdo y derecho
        int margin = 10;
        // 3. Tamanio de la linea
        int lineWidth = getWidth() - margin;
        // 4. manejamos el numero de pixeles que ocupa un espacio
        int spaceWidth = fm.charWidth(' ');
        // 5 comenzando por el margin, dibujamos en la coordena xPos
        int xPos = margin;
        // 6 permite ubicarnos en la parte superior para dibujar
        int yPos = fm.getAscent();
        for(String word : words){
            // determinamos cual va a ser el ancho de la sgte palabra
            int width = fm.stringWidth(word);
            // validamos si se supera la linea, escribir la palabra en la linea siguiente
            if((xPos+width)> lineWidth){
                // pasamos a la sgte linea
                xPos = margin;
                yPos += fm.getHeight();
                // dibujamos mientras mientras haya espacio para el renglon completo
                /*if((yPos+fm.getDescent()) >= getHeight()){
                    break;
                }*/
                // el en caso que deseemos dibujar una linea parcialmente
                // a la linea base (yPos) restamos el ascent
                if((yPos - fm.getAscent()) >= getHeight()){
                    break;
                }
            }
            
            g2d.drawString(word, xPos, yPos);
            // actualizamos la posicion x, con el espacio despues de la palabra
            // xPos = espacio en blanco + tamaño de la palabra
            xPos += spaceWidth + width;
        }
    }
    
    private void drawText(Graphics2D g2d){
        FontMetrics fm = g2d.getFontMetrics();
        
        // numero de columna actual
        int curColumn = 0;
        int margin = 10;
        // ancho de la columna = (tamanio de ventana - el mananio del margen * (numero de columnas +1))/ numColumns
        int lineWidth = (getWidth() - margin * (numColumns+1)) / numColumns;
        int spaceWidth = fm.charWidth(' ');
        int xPos = margin;
        int yPos = fm.getAscent();
        // acumulado del ancho de una linea
        int accWidth = 0;
        
        // recorremos las palabras a dibujar
        for(String word : words){
            // calculamos el ancho de la palabra
            int width = fm.stringWidth(word);
            // validamos si el acumulado para el tamanio de la columna actual+tamanio de la nueva palabra es mayor
            // al tamanio maximo de la columna
            if((accWidth+width) > lineWidth){
                // cambiamos al sgte renglon
                yPos += fm.getHeight();
                // si el nuevo renglon supera el maximo
                if((yPos + fm.getDescent()) >= getHeight()){
                    // reset a la posicion y la colocamos al inicio de la sgte columna
                    yPos = fm.getAscent();
                    // pasamos a la sgte columna
                    curColumn++;
                    // dejamos de dibujar cuando superamos el numero de columnas
                    if(curColumn >= numColumns){
                        break;
                    }
                }
                // reset el acumulado ancho de columna
                accWidth = 0;
                // formula donde determina la posicion dond iniciamos a dibujar
                xPos = margin + (lineWidth+margin)*curColumn;
            }
            // dibujamos la palabra
            g2d.drawString(word, xPos, yPos);
            // actualizamos el acumulado para el tamanio de la columna actual
            accWidth += spaceWidth + width;
            // actualizamos xPost con el valor de la palabra (width) + un espacio (spaceWidth)
            xPos += spaceWidth + width;
        }   
    }
    /**
     * 
     * @param g2d
     * @param curLine
     * @param xPos
     * @param yPos
     * @param lineWidth ancho de la columna
     * @param curLineWidth tamanio que ocupan todas las palabra acumuladas
     */
    private void drawJustified(Graphics2D g2d, FontMetrics fm, List<String> curLine, int xPos, int yPos, int lineWidth, int curLineWidth){
        // validamos si es menos de una palabra, pues no hacemos nada
        if(curLine.size() <= 1){
            // cuando es una sola palabra, la dibujamos en la coordenada solicitada
            if(curLine.size() == 1 ){
                g2d.drawString(curLine.get(0), xPos, yPos);
            }
            return;
        }
        // calculamos cuantos espacios hay entre todas la palabra de esta linea
        // si la linea tiene 3 palabra, entonces, hay dos espacios
        int sp = curLine.size() - 1;
        // space1 = ancho de columna - el tamanio de todas la palabras acumuladas / sp
        // de acuerdo al nro de espacio que necesita la linea space1 representa el tamanio que debe tener cada space
        int space1 = (lineWidth - curLineWidth) / sp;
        // obtenemos elsobrante de espacio
        int mod = (lineWidth- curLineWidth) % sp;
        int space2 = (mod + (sp-1)) / sp;
        
        for(String s : curLine){
            int insCount = (mod > space2) ? space2 : mod;
            mod -= insCount;
            insCount += space1;
            g2d.drawString(s, xPos, yPos);
            xPos += insCount + fm.stringWidth(s);
        }
        
    }
    
    private void drawTextJustified(Graphics2D g2d){
        FontMetrics fm = g2d.getFontMetrics();
        // para dibujar justificado debo medir toda la columna y hasta encontrar las palabras que
        // va a ocupar la columna, calculamos el tamanio de cada espacio para que quede jstificado
        List<String> curLine = new LinkedList<>();
        // columna inicial
        int curColumn = 0;
        // margen por defecto
        int margin = 10;
        // ancho de cada columna
        int lineWidth = (getWidth() - margin * (numColumns+1)) / numColumns;
        // tamanio de un espacio
        int spaceWidth = fm.charWidth(' ');
        int xPos = margin;
        int yPos = fm.getAscent();
        // controlamos el espacio ya ocupado para la columna actual
        int accWidth = 0;
        // controlamos el ancho que ocupan las palabras sin tomar en cuenta los espacios
        int curLineWidth = 0;
        
        for(String word : words){
            // obtenemos ancho de la palabra
            int width = fm.stringWidth(word);
            // validamos si se ha superado el tamanio estimado para la columna
            if((accWidth + spaceWidth + width) > lineWidth){
                // pasamos a la sgte linea y dibujamos una linea justificada
                drawJustified(g2d, fm, curLine, xPos, yPos, lineWidth, curLineWidth);
                // reseteamos curLine que es el acumulado de palabras
                curLine.clear();
                curLineWidth = 0;
                accWidth = 0;
                
                // dibujar la en sgte linea\
                yPos += fm.getHeight();
                // validamos si nos pasamos del final de la ventana, si es asi pasamos a la sgte columna
                if((yPos + fm.getDescent()) >= getHeight()){
                    // si nos pasamos, cambiamos a la sgte columna
                    // reset del yPos
                    yPos = fm.getAscent();
                    // incrementamos la columna
                    curColumn++;
                    if(curColumn >= numColumns){
                        break;
                    }
                }
                // si no necesitamos pasar a la sgte columna. calculamos el inicio de linea para escribir
                xPos = margin + (lineWidth + margin)*curColumn;
            }
            
            // add la palabra a la coleccion de palabras
            curLine.add(word);
            curLineWidth += width;
            // acumulado
            accWidth += spaceWidth + width;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if(justify){
            drawTextJustified(g2d);
        }else{
            drawText(g2d);
        }
    }
    
    
    
    public static void main(String[] args) {
        String str = 
                "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo\n" +
            "que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y\n" +
            "galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches,\n" +
            "duelos y quebrantos los sábados, lantejas los viernes, algún palomino de añadidura\n" +
            "los domingos, consumían las tres cuartas partes de su hacienda. El resto della\n" +
            "concluían sayo de velarte, calzas de velludo para las fiestas, con sus pantuflos\n" +
            "de lo mesmo, y los días de entresemana se honraba con su vellorí de lo más fino.\n" +
            "Tenía en su casa una ama que pasaba de los cuarenta, y una sobrina que no llegaba\n" +
            "a los veinte, y un mozo de campo y plaza, que así ensillaba el rocín como tomaba\n" +
            "la podadera. Frisaba la edad de nuestro hidalgo con los cincuenta años; era de\n" +
            "complexión recia, seco de carnes, enjuto de rostro, gran madrugador y amigo de\n" +
            "la caza. Quieren decir que tenía el sobrenombre de Quijada, o Quesada, que en\n" +
            "esto hay alguna diferencia en los autores que deste caso escriben; aunque, por\n" +
            "conjeturas verosímiles, se deja entender que se llamaba Quejana. Pero esto importa\n" +
            "poco a nuestro cuento; basta que en la narración dél no se salga un punto de la\n" +
            "verdad.";
        
        int columns = 3;
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("TextFormatterAWT");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setMinimumSize(new Dimension(400, 400));
                frame.setLocationRelativeTo(null);
                frame.setContentPane(new AG_TextFormatterAWT(str, columns, true));
                frame.setVisible(true);
            }
        });
    }
}
