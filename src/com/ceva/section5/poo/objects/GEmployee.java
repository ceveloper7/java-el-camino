package com.ceva.section5.poo.objects;

import java.util.Date;

// FIg 8.5
public class GEmployee {
    // static member demostration
    private static int count = 0;
    private String firstName;
    private String lastName;
    private FDate birthDate;
    private FDate hireDate;

    // constructor to initialize name, birth date and hire date
    public GEmployee(String firstName, String lastName, FDate birthDate,
                    FDate hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;

        count++;
    }

    public static void printCount(){
        System.out.printf("Employee Count = %d%n", count);
    }

    // convert Employee to String format
    public String toString() {
        return String.format("%s, %s  Hired: %s  Birthday: %s",
                lastName, firstName, hireDate, birthDate);
    }
}
