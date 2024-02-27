package com.ceva.utils.textgraphics;

public class TextGrpah1 {
    // espacio en memoria donde generamos el dibujo
    private char[][] canvas;

    public  TextGrpah1(int cols, int rows){
        canvas = new char[rows][cols];
        clearCanvas();
    }

    // metodo que limpia la pantalla
    private void clearCanvas(){
        for(int r = 0; r < canvas.length; r++){
            for(int c = 0; c < canvas[r].length; c++){
                canvas[r][c] = ' ';
            }
        }
    }

    // metodo que guarda un caracter especifico en canvas de acuerdo a la coordenada deseada
    public void setCharAt(int colX, int rowY, char value){
        // validacion para evitar dibujar fuera de canvas
        if((colX < 0) || (colX >= canvas[0].length) || (rowY < 0) || (rowY >= canvas.length)){
            return;
        }
        canvas[rowY][colX] =  value;
    }

    /**
     * Metodo para dibujar un rectangulo
     * +----------------+
     * |                |
     * |                |
     * |                |
     * +----------------+
     */
    public void drawRectangle(int x, int y, int width, int height){
        // barras verticales
        for(int row = y; row <(y+height); row++){
            // dibujamos la barra verticale de la izquierda
            setCharAt(x, row, '|');
            // dibujamos la barra vertical de la derecha
            setCharAt(x+width, row, '|');
        }

        // barras horizontales
        for(int col = x; col < (x+width); col++){
            // barra horizontal superior
            setCharAt(col, y, '-');
            // barra horizontal inferior
            setCharAt(col, y+height, '-');
        }

        // dibujamos las 2 esquinas + supeior
        setCharAt(x, y, '+');
        setCharAt(x+width, y, '+');

        // dibujamos las 2 esquinas + inferior
        setCharAt(x, y+height, '+');
        setCharAt(x+width, y+height, '+');
    }

    public void println(){
        for(int row = 0; row < canvas.length; row++){
            System.out.println(canvas[row]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TextGrpah1 graph = new TextGrpah1(80, 25);
        graph.drawRectangle(0, 0, 79, 24);
        for(int x = 0; x < 80; x++){
            /**
             * Valor a dibujar
             * La funcion seno retorna un valor que corresponde a la coordenada y
             * de un punto que se encuentra en un angulo especifico, en un circulo unitario
             * Un circulo tiene 360 grados
             */
            double value = Math.sin(Math.toRadians((2*360.0/80) * x));
            value = value * 10;
            graph.setCharAt(x, (int)(12-value), 'x');
        }

        graph.println();
    }
}
