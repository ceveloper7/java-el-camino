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
    
    private void drawText(Graphics2D g2d){
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
        int yPost = fm.getAscent();
        for(String word : words){
            // determinamos cual va a ser el ancho de la sgte palabra
            int width = fm.stringWidth(word);
            g2d.drawString(word, xPos, yPost);
            // actualizamos la posicion x, con el espacio despues de la palabra
            // xPos = espacio en blanco + espacio de la palabra
            xPos = spaceWidth + width;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        drawText(g2d);
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
                frame.setMinimumSize(new Dimension(400,400));
                frame.setLocationRelativeTo(null);
                frame.setContentPane(new AG_TextFormatterAWT(str, columns, true));
                frame.setVisible(true);
            }
        });
    }
}
