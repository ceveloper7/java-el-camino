package com.ceva.core1.ch05;

/*
 * clase concreta Student que extiende la clase abstracta Person.
 */
public class Student extends Person{
    private final String major;

    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescription() {
        return "a student majoring in " + major;
    }

    @Override
    public String toString(){
        return getClass().getName() +
                "[name=" + super.getName() + getDescription() + "]";
    }
}
