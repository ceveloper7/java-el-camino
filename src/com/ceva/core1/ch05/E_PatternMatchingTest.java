package com.ceva.core1.ch05;

import com.ceva.core1.ch04.model.Employee;

public class E_PatternMatchingTest {
    public static void main(String[] args) {
        int r = (int) (4*Math.random());
        // switch expression exhaustiva
        Employee e = switch (r){
            case 0 -> new Employee("Harry Hacker", 50000, 1898,10,1);
            case 1 -> new Manager("Carl Cracker", 80000, 1987,12,15);
            case 2 -> new Executive("Sue Striver", "Senior Associative", 200000, 1985,1,20);
            default -> null; // manejamos el caso de un objeto null
        };

        String description = switch (e){
            /*
             * Switch expression exhaustiva
             * Declaramos una variable type matching exec
             * Uso de guard expressions (when)
             */
            case Executive exec when exec.getTitle().length() >= 20 -> "An Executive with an impressive title";
            case Executive exec -> "An executive with a title of " + exec.getTitle();
            case Manager m -> {
                m.setBonus(10000);
                yield "A manager who just got a bonus";
            }
            case null -> "No employee";
            default -> "A lowly employee with a salary of "+ e.getSalary();
        };
    }
}
