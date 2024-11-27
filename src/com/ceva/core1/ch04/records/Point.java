package com.ceva.core1.ch04.records;

/*
 * El resultado es tener una clase con 2 campos de instancia
 * private final double x
 * private final double y
 *
 * El constructor del record Point es
 * Point(double x, double y)
 *
 * Los metodos de acceso (getters)
 * public double x()
 * public double y()
 */
public record Point(double x, double y) {
    // static field allowed
    public static Point ORGING = new Point(0,0);

    // los record permiten agregar propios metodos
    public double distanceFromOrigin(){
        return Math.hypot(x, y);
    }

    // los record permiten metodos static
    public static double distanceFromOrigin(Point p, Point q){
        return Math.hypot(p.x() - q.x(), p.y() - q.y());
    }

    public static void main(String[] args) {
        var p = new Point(3,4);
        System.out.println(p.x() + ", " + p.y());
    }
}
