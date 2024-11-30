package com.ceva.core1.ch05;

import com.ceva.core1.ch04.model.Employee;

public class A_EqualTest {
    public static void main(String[] args) {
        var alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        var alice2 = alice1;
        var alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        var bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);

        // poseen la misma referencia al objeto Employee
        System.out.println("alice1 == alice2: " + (alice1 == alice2));
        // cada variable apunta a una referencia distinta
        System.out.println("alice1 == alice3: " + (alice1 == alice3));
        // equals() evalua los valores de los campos
        System.out.println("alice1.equals(alice3): " + alice1.equals(alice3));
        // alice1 y bob poseen valores distintos
        System.out.println("alice1.equals(bob): " + alice1.equals(bob));

        var carl = new Manager("Carl Cracker", 80000, 1987,12,15);
        var boss = new Manager("Carl Cracker", 80000, 1987,12,15);
        boss.setBonus(5000);

        System.out.println("boss.toString(): " + boss);
        System.out.println("Carl.equals(boss) " + carl.equals(boss));
        System.out.println("alice1.hashCode(): " + alice1.hashCode());
        System.out.println("alice3.hashCode(): " + alice3.hashCode());
        System.out.println("bob.hashCode(): " + bob.hashCode());
        System.out.println("Carl.hashCode(): " + carl.hashCode());
    }
}
