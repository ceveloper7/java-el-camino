package com.ceva.core1.ch04.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.random.RandomGenerator;

/*
 * En lo casos donde una clase parece requerir multiples constructores con la misma firma, reemplace
 * los constructores con metodos de fabrica static y nombres cuidadosamente elegidos para resaltar
 * sus diferencias.
 */
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

    // Constructor con valores por defecto
    public Employee(){
        name = "";
        salary = 0;
        hireDay = LocalDate.now();
    }

    // Metodo static de Fabrica
    public static Employee create(String name, double salary, int year, int month, int day){
        return new Employee(name, salary, year, month, day);
    }

    // Constructor publico
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

    /*
     * equals() prueba si dos referencias a objetos son identicas. Hay ocasiones donde se necesita
     * implementar pruebas de igualdad basadas en el estado, en las que dos objetos se consideran
     * iguales cuando tienen el mismo estado.
     */
    @Override
    public boolean equals(Object otherObject){
        // una rapida prueba si los objetos son identicos
        if(this == otherObject) return true;

        // false si el parametro es null
        if(otherObject == null) return false;

        // getClass retorna la clase del objeto. Si las clases no coinciden no pueden ser iguales
        // 2 objetos solo pueden ser iguales cuando pertenecen a la misma clase.
        if(this.getClass() != otherObject.getClass()) return  false;

        // ahora sabemos que otherObject no es un Employee null
        var other = (Employee) otherObject;

        // pruebamos si los campos contienen identicos valores
        return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
    }

    // hashCode safe-null with Objects.hashCode
    @Override
    public int hashCode(){
//        return 7 * Objects.hashCode(this.name) + 11 * Double.hashCode(this.salary) +
//                13 * Objects.hashCode(this.hireDay);
        return Objects.hash(name, salary, hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "[name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay + "]";
    }
}
