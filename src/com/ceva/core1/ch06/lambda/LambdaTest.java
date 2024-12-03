package com.ceva.core1.ch06.lambda;

import com.ceva.core1.ch04.model.Employee;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.Timer;

import static java.util.stream.Collectors.toList;

public class LambdaTest {
    private static final String[] planets = new String[]{
            "Mercury", "Venus", "Earth", "Mars",
            "Jupiter", "Saturn", "Uranus", "Neptune"
    };

    private static final List<Employee> staff = Arrays.asList(
            new Employee("Pedro Ramirez", 30000, 2004,5,11),
            new Employee("Mirando Palacios", 35000, 2005,6,10),
            new Employee("Julio Acosta", 40000, 2006,7,20),
            new Employee("Yolanda Parodi", 37000, 2008,10,8),
            new Employee("Rodrigo Pasos", 22000, 2008,9,11),
            new Employee("Jose Gonzalez", 55000, 2008,7,16),
            new Employee("Angela Quispe", 60000, 2009,1,1),
            new Employee("Christian Peralta", 90000, 2008,6,25)
    );

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.createFakeEmployees();
    }

    private void createFakeEmployees(){
        Supplier<Employee> fakeEmp = () -> new Employee("Nuevo Empleado", 10000, 2024, 12, 3);
        Employee[] employees = new Employee[3];
        for (int i = 0; i < employees.length; i++){
            employees[i] = fakeEmp.get();
        }

        System.out.println(Arrays.stream(employees).toList());
    }

    private void employeesHiredInAYear(List<Employee> staff){
        Predicate<Employee> hiredInAYear = employee -> employee.getHireDay().getYear() == 2008;
        for (Employee emp : staff){
            if(hiredInAYear.test(emp)){
                System.out.println(emp);
            }
        }
    }

    private void increseSalary(List<Employee> staff){
        double INCREASE = 10.0;

        BiFunction<Employee, Double, Employee> increasySalary = ((employee, percentage) -> {
            double newSalary = employee.getSalary() * (1 + percentage / 100);
            employee.setSalary(newSalary);
            return employee;
        });

        // aumento salario a todos los empleados
        for(Employee emp : staff){
            increasySalary.apply(emp, INCREASE);
        }
        System.out.println(staff);
    }

    private void sumSalaries(List<Employee> staff){
        double salaries = staff.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.printf("Total salaries: %,9.2f", salaries);
    }

    private void getMaxSalary(List<Employee> staff){
        Optional<Employee> empWithMaxSalary = staff.stream()
                .max(Comparator.comparing(Employee::getSalary));
        System.out.println(empWithMaxSalary);
    }

    private void passingLambdaAction(){
        // el tipo de event es ActionEvent pero el tipo es inferido
        var timer = new Timer(1000, (event) ->
                System.out.println("The time is " + new Date()));
        timer.start();
        // keep program running until user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

    private void sortString(){
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        System.out.println("Sorted by length (using lambdas):");
        // omitimos los tipos de parameters (String), ya que son inferidos
        // tampoco se especifica el tipo de retorno, porque, se infiere del contexto
        Arrays.sort(planets, (first, second)->first.length() - second.length());
        System.out.println(Arrays.toString(planets));
    }
}
