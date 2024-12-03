package com.ceva.core1.ch06.lambda;

import com.ceva.core1.ch04.model.Employee;

import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

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
        lambdaTest.sortString();
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
