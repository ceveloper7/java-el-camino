/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

import java.util.Calendar;

/**
 *
 * @author PC
 */
public class AA_Employee {

    private String name;
    private String address;
    private boolean gender;
    private double salary;
    private int age;
    private java.util.Date birthdate;
    // referencia al registro del empleado
    private AA_Employee supervisor;

    public AA_Employee() {
    }
    
    public void setSupervisor(AA_Employee supervisor){
        this.supervisor = supervisor;
    }

    public double getSalary() {
        return salary;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthdate = birthDate;
        age = 0;
    }

    public int getAge() {
        if (age == 0) {
            Calendar cnow = Calendar.getInstance(); // fecha actual
            Calendar cdate = Calendar.getInstance(); // fecha nacimiento
            cdate.setTime(birthdate); // al calendario cdate asignamos la fecha de nacimiento

            int age = cnow.get(Calendar.YEAR) - cdate.get(Calendar.YEAR); // calculo de la edad
            // a la fecha de nacimiento, le colocamos el anio de la fecha actual
            cdate.set(Calendar.YEAR, cnow.get(Calendar.YEAR));
            //  comparamos la fecha nacimiento con la fecha actual
            // si la fecha de cumpleanios es mayor a la fecha actual significa q aun no es cumpleanios de la persona
            // asi que le restamos 1 a la edad.
            if (cdate.getTime().compareTo(cnow.getTime()) > 0) {
                age--;
            }
        }
        return age;
    }

    public void print() {
        System.out.println(getAge());
    }
}
