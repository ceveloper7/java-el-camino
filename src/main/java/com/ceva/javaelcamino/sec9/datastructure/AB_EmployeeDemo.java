/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

/**
 *
 * @author PC
 */
public class AB_EmployeeDemo {
    public static void main(String[] args) {
        AA_Employee employees[] = new AA_Employee[100];
        AA_Employee supervisor = new AA_Employee();
        AA_Employee e = new AA_Employee();
        e.setSupervisor(supervisor);
        e.print();
        
        for(AA_Employee ee : employees){
            if(ee.getSalary() > 10000)
                ee.print();
        }
    }
}
