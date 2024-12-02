package com.ceva.core1.ch06.clone;

import com.ceva.core1.ch04.model.Employee;

public class CloneTest {
    public static void main(String[] args) throws Exception {
        var emp = new Employee("Cosme Fulanito", 50000, 1999, 10, 22);
        var empCopy = emp.clone();
        empCopy.raiseSalary(10);
        System.out.println("Employee original" + emp);
        System.out.println("Employee cloned: " + empCopy);
    }
}
