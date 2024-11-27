package com.ceva.core1.ch04;

import com.ceva.core1.ch04.model.Employee;

public class C_MethodParameters {
    public static void main(String[] args) {
        /*
         * Test 1: Metodo no pueden modificar parametros de tipo primitivo
         */
        System.out.println("Testing tripleValue");
        double percent = 10;
        System.out.println("Before: percent= " + percent);
        tripleValue(percent);
        System.out.println("After: percent= " + percent);

        /*
         * Test 2: Los metodos no pueden cambiar el estado de
         */
        System.out.println("\nTesting tripeSalary:");
        var harry = new Employee("Harry", 50000, 2020, 10, 15);
        System.out.println("Before salary= " + harry.getSalary());
        tripleSalary(harry);
        System.out.println("After salary= " + harry.getSalary());

        /*
         * 3. Los metodos no pueden adjuntar nuevos objetos a un parametro objeto
         */
        System.out.println("\nTesting swap");
        var a = new Employee("Alice", 70000, 2022, 10, 22);
        var b = new Employee("Bob", 60000, 2022, 11, 10);
        System.out.println("Before a= " + a.getName());
        System.out.println("Before b= " + b.getName());
        swap(a, b);
        System.out.println("After a= " + a.getName());
        System.out.println("After b= " + b.getName());
    }

    public static void swap(Employee x, Employee y){
        Employee temp = x;
        x = y;
        y = temp;
        System.out.println("End of method x= " + x.getName());
        System.out.println("End of method y= " + y.getName());
    }

    public static void tripleSalary(Employee x){
        x.raiseSalary(200);
        System.out.println("End of method salary= " + x.getSalary());
    }

    private static void tripleValue(double x){
        x = 3 * x;
        System.out.println("End of method: x= " + x);
    }
}
