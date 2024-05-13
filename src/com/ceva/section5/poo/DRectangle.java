package com.ceva.section5.poo;

public class DRectangle extends BPoint{
    private int width;
    private int height;

    public DRectangle(){
        super(0,0);
    }

    public DRectangle(int x, int y, int width, int height){
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public double area(){
        return width * height;
    }
}
