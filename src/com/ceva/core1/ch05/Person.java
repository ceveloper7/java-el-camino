package com.ceva.core1.ch05;

public abstract class Person {
    private String name;

    public Person(String name){
        this.name = name;
    }

    public abstract String getDescription();

    // las clases abstractas puede aceptar metodos concretos.
    public String getName(){
        return name;
    }
}
