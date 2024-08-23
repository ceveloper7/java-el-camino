package com.ceva.radio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person {
    private int dni;
    private String lastName;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;

    public Person(){}
    public Person(int dni, String lastName, String name, String phone, String email, LocalDate birthDate) {
        this.dni = dni;
        this.lastName = lastName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return String.format("%d, %s, %s, %s, %s, %s", dni, lastName, name, phone, email, birthDate.format(formatter));
    }
}
