package com.ceva.execises.ch02.screenoutput;

public class A_SvgCircle1 {
    private int x, y;
    private double r;

    public A_SvgCircle1(int x, int y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public static void main(String[] args) {
        A_SvgCircle1 circle = new A_SvgCircle1(100, 110, 20.5);
        System.out.println("<svg height=\"400\" width=\"100\">");
        System.out.println("   <circle cx=\"" + circle.getX() + "\"" + " cy=\"" + circle.getY() + "\"" + " r=\"" + circle.getR() + "/>" );
        System.out.println("</svg>");
    }
}
