package com.ceva.ocp.ch1;

// Fig 1.1 Modelamos las coordenadas de un punto
public class Point2D {
    private int x;
    private int y;
    private static String info = "A point represented by (x,y) coordinates.";

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
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

    // obtenemos la distancia entre dos puntos.
    public static double getDistance(Point2D p1, Point2D p2) {
        int xDiff = p1.getX() - p2.getX();
        int yDiff = p1.getY() - p2.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    @Override
    public String toString() {
        return String.format("Point x: %d, Point y: %d%n", x, y);
    }

    public static void showInfo(){
        System.out.printf("%n%s",info);
    }
}
