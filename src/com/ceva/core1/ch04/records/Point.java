package com.ceva.core1.ch04.records;

import java.util.Date;

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
    // no-arguments constructor
    public Point(){
        // canonical constructor
        this(0,0);
    }
    // static field allowed
    public static Point ORGING = new Point(0,0);

    // los record permiten agregar propios metodos
    public double distanceFromOrigin(){
        return Math.hypot(x, y);
    }

    // los record permiten metodos static
    public static double distance(Point p, Point q){
        return Math.hypot(p.x() - q.x(), p.y() - q.y());
    }
}

// record mutable
record PointInTime(double x, double y, Date when){}

record Range(int from, int to){
    // constructor compacto
    public Range{
        if(from > to){
            int temp = from;
            from = to;
            to = temp;
        }
    }
}
