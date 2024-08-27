package com.ceva.radio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public abstract class Person {
    private int dni;
    private String lastName;
    private String name;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private int age;

    public Person(){}
    public Person(int dni, String lastName, String name, String phone, String email, LocalDate birthDate) {
        this.dni = dni;
        this.lastName = lastName;
        this.name = name;

        if(phone == null || phone.isEmpty()){
            this.phone = "";
        }
        this.phone = phone;

        if(email == null || email.isEmpty()){
            this.email = "";
        }
        this.email = email;

        if(birthDate == null){
            this.birthDate = null;
            this.age = 0;
        }else{
            this.birthDate = birthDate;
            this.age = getAge();
        }
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
        this.age = 0;
    }
    
    public int getAge(){
        if(age == 0){
            Calendar cnow = Calendar.getInstance();
            Calendar cdate = Calendar.getInstance();
            cdate.set(birthDate.getYear(), birthDate.getMonth().getValue(), birthDate.getDayOfMonth());
            int age = cnow.get(Calendar.YEAR) - cdate.get(Calendar.YEAR);
            cdate.set(Calendar.YEAR, cnow.get(Calendar.YEAR));
            if(cdate.getTime().compareTo(cnow.getTime()) > 0){
                age--;
            }
        }
        return age;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return String.format("%d, %s, %s, %s, %s, %s, %d", dni, lastName, name, phone, email, birthDate.format(formatter), getAge());
    }
}
