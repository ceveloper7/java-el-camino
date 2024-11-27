package com.ceva.core1.ch04.records;

import java.util.Date;

public class RecordTest {
    public static void main(String[] args) {
        var p = new Point(3, 4);
        System.out.println("Coordinates of p: " + p.x() + " " + p.y());
        System.out.println("Distance from origin: " + p.distanceFromOrigin());
        // el mismo calculo con campos static y metodo static
        System.out.println("Distance from origin: " + Point.distance(Point.ORGING, p));

        // record mutable
        var pt = new PointInTime(3,4, new Date());
        System.out.println("Before: " + pt);
        pt.when().setTime(0);
        System.out.println("After: " + pt);

        // invocando un constructor compacto
        var r = new Range(4,3);
        System.out.println("r: " + r);
    }
}
