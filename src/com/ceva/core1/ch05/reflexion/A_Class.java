package com.ceva.core1.ch05.reflexion;

import com.ceva.core1.ch04.model.Employee;
import com.ceva.core1.ch05.Executive;
import com.ceva.core1.ch05.Manager;

import java.util.Objects;

public class A_Class {
    private final Employee e = new Employee("Harry Cracker", 80000, 1990,10,25);
    public static void main(String[] args) throws Exception {
        A_Class reflexion = new A_Class();
        reflexion.createInstances();
    }

    private void workWithClass() throws ClassNotFoundException {
        /*
         * La clase Class es un tipo generico Class<?>
         * retorna una instancia de tipo Class
         */
        Class<?> cl = e.getClass();
        System.out.println(cl);

        // obtener el nombre de una clase. si la clase esta en un paquete, el paquete es parte del nombre
        System.out.println(e.getClass().getName() + " " + e.getName());

        // podemos obtener un objeto Class correspondiente al nombre de la clase.
        // funciona si className es nombre d una clase o interface
        String className = "com.ceva.core1.ch04.model.Employee";
        Class<?> cl1 = Class.forName(className);
        System.out.println(cl1.getName() + " " + e.getName());
    }

    private void comparingClasses(){
        Employee e1 = new Manager("Harry Cracker", 80000, 1990,10,25);
        //JVM maneja un unico objeto Class para cada tipo por lo que podemos usar el operador ==
        // para comparar objetos class
        if(e.getClass() == e1.getClass()){
            System.out.println("e: " + e.getClass().getName());
            System.out.println("e1: " + e1.getClass().getName());
            System.out.println("e y e1 son instancias de Employee" );
        }else{
            System.out.println("e: " + e.getClass().getName());
            System.out.println("e1: " + e1.getClass().getName());
            System.out.println("e y e1 no son clases del mismo tipo");
        }
    }

    private void createInstances() throws Exception{
        var className = "java.util.Date";
        Class<?> cl = Class.forName(className);
        // obtenemos un objeto de tipo Constructor. si la clase no tiene un constructor
        // sin argumentos, el metodo lanza una checked exception.
        Object obj = cl.getConstructor().newInstance();
        System.out.println(obj);
    }
}
