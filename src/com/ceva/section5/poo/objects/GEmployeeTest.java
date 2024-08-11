package com.ceva.section5.poo.objects;

public class GEmployeeTest {
    public static void main(String[] args) {


        FDate birth = new FDate(7, 24, 1949);
        FDate hire = new FDate(3, 12, 1988);
        GEmployee.printCount();

        GEmployee employee = new GEmployee("Bob", "Blue", birth, hire);
        System.out.println(employee);
        GEmployee.printCount();

        GEmployee employee1 = new GEmployee("Peter", "Blue", birth, hire);
        System.out.println(employee1);
        GEmployee.printCount();

        GEmployee employee2 = new GEmployee("John", "Blue", birth, hire);
        System.out.println(employee2);
        GEmployee.printCount();

        GEmployee employee3 = new GEmployee("Pilar", "Blue", birth, hire);
        System.out.println(employee3);
        GEmployee.printCount();
    }
}
