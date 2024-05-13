package com.ceva.section5.poo;

import edu.princeton.cs.algs4.CC;

public class CCircle extends BPoint{
    private double radius;

    public CCircle(int x, int y, int radius){
        super(x, y);
        this.radius = radius;
    }

    public CCircle(double radius){
        this.radius = radius;
    }

    @Override
    public void draw(){
        System.out.println("Dibujando un CIRCULO en la pantalla (" + super.x + ", " + super.y + "," + radius + ")");
    }

    public void area(){
        System.out.println("El area del circulo es " + Math.PI * (radius*radius));
    }
}
