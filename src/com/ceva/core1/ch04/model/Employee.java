package com.ceva.core1.ch04.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.random.RandomGenerator;

public class Employee {
    private static final RandomGenerator generator = RandomGenerator.getDefault();
    private static int nextId;

    private int id;
    private String name;
    private double salary;
    private LocalDate hireDay;

    /*
     * Bloque de Inicializacion. Hay tres formas de inicializar un campo de instancia
     * 1. Asigandole un valor en el constructor
     * 2. Asignandole un valor en la declaracion
     * 3. Mediante un bloque de inicializacion
     *
     * Un clase puede tener todos los bloques de inicializacion que necesite. Estos bloques
     * se ejecutan primero cuando un objeto de clase es construido.
     *
     */
//    {
//        id = advancedId(); // inicializacion llamando a un metodo.
//    }

    // bloque de inicializacion static
    static {
        nextId = generator.nextInt(10000);
    }

    // Constructor por defecto
    public Employee(){
        name = "";
        salary = 0;
        hireDay = LocalDate.now();
    }

    public Employee(String name, double salary, int year, int month, int day){
        id = advancedId(); // inicializacion llamando a un metodo.
        // evitamos recibir un valor null
        this.name = Objects.requireNonNull(name, "The name cannot be null");
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public static int advancedId(){
        int r = nextId;
        nextId++;
        return r;
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
