package com.ceva.core1.ch05;

import com.ceva.core1.ch04.model.Employee;

public class ManagerTest {
    public static void main(String[] args) {
        Manager boss = new Manager("Carl Cracker", 80000, 1987,12,15);
        boss.setBonus(5000);

        // Todos los elementos del array staff son de tipo Employee
        Employee[] staff = new Employee[3];
        staff[0] = boss;
        // staff[0].setBonus(500) ERROR

        staff[1] = new Employee("Harry Hacker", 50000, 1989,10,1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        // e de tipo Employee
        for(Employee e : staff){
            System.out.printf("Id: %d, Name: %s, Hire Date: %s, Salary: %,8.2f%n", e.getId(), e.getName(), e.getHireDay().toString(), e.getSalary());
        }
    }
}
