package com.ceva.core1.ch06.interfaces;

import com.ceva.core1.ch04.model.Employee;

import java.util.Arrays;

public class EmployeeSortTest {
    public static void main(String[] args) {
        var staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 95000, 1980,10,5);
        staff[1] = new Employee("Carl Cracker", 75000, 1995, 7,20);
        staff[2] = new Employee("Tony Tester", 38000, 1990,9,11);

        /*
         * El metodo sort de la clase Arrays ofrece el ordenamiento de una array solo si los objetos
         * deben pertenecer a clases que implementan la interface Comparable. La clase Employee
         * del capitulo4 tiene la siguiente forma:
         * public class Employee extends Person implements Comparable<Employee>
         * Luego en la clase Employee agregamos la implementacion del metodo compareTo(Employee other)
         */
        Arrays.sort(staff);

        // print out information about all Employee objects
        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
    }
}
