package com.ceva.radio;

import java.time.LocalDate;

public class Patient extends Person{
    private String socialSecurityNumber;

    public Patient(int dni, String lastName, String name, String phone, String email, LocalDate birthDate, String socialSecurityNumber){
        super(dni, lastName, name, phone, email, birthDate);
        this.socialSecurityNumber = socialSecurityNumber;
    }
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public String toString() {
        return String.format("Datos del paciente: %n%s %s: %s", super.toString(), " Seguro Social", socialSecurityNumber);
    }
}
