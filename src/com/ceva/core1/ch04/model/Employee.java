package com.ceva.core1.ch04.model;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day){
        id = nextId;
        nextId++;
        // evitamos recibir un valor null
        this.name = Objects.requireNonNull(name, "The name cannot be null");
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public void raiseSalary(double byPercentage){
        double raise = salary * ( byPercentage / 100);
        this.salary += raise;
    }
}
