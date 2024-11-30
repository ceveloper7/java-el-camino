package com.ceva.core1.ch05;

import com.ceva.core1.ch04.model.Employee;

import java.util.ArrayList;

public class B_ArrayList {
    // 1. Declaracion
    private ArrayList<Employee> staff = new ArrayList<>();

    public static void main(String[] args) {
        B_ArrayList array = new B_ArrayList();
        array.operationsWithArrayList();
        array.accessingIndexs();
    }

    /*
     * Usar el metodo add() en lugar de set() para llenar una array list.
     * Usamos set() solo para reemplazar un elemento agregado previamente.
     */
    private void operationsWithArrayList(){
        staff.add(new Employee("Harry Hacker", 80000, 1987,12,10));
        staff.add(new Employee("Tony Tester", 75000, 1990, 11,20));

        // add element in the middle of list
        int n = staff.size()/2;
        staff.add(n, new Employee("Cosme Fulanito", 45000, 2015, 10, 11));

        // remove element
        staff.remove(staff.size()-1); // removeLast() remueve el ultumo elemento del array

        // recorremos el contenid de un Arraylist
        staff.forEach(System.out::println);

        System.out.println("Size list: " + staff.size());
    }

    private void accessingIndexs(){
        Employee ee = staff.get(1); // getFirst() obtiene el elemento 0
        System.out.println(ee);
    }
}
