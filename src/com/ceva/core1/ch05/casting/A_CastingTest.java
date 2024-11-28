package com.ceva.core1.ch05.casting;

import com.ceva.core1.ch04.model.Employee;
import com.ceva.core1.ch05.Manager;

public class A_CastingTest {
    public static void main(String[] args) {
        simpleCasting();
    }

    private static void convertingObjects(){
        var staff = new Employee[3];
        staff[0] = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        Manager boss = (Manager)staff[0];
    }

    private static void simpleCasting(){
        // 1.
        double x = 3.55;
        int nx = (int)x; // se descarta la parte fraccional
        System.out.println(nx);

        // 2.
        nx = (int)Math.round(x);
        System.out.println(nx);
    }
}
