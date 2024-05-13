package com.ceva.section5.poo;

/**
 * Herencia: clase super tipo BPoint
 *
 */
public class BPoint implements IDrawable{
    public int x, y;

    public BPoint(){}

    public BPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    // polimorfismo
    public void draw(){
        System.out.println("Dibujando un punto en la pantalla (" + x + ", " + y + ")");
    }
}
