package com.ceva.section5.poo;

public class EPerson implements IDrawable{
    private String name;

    public EPerson(){}

    public EPerson(String name){
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println("El nombre de la persona es: " + name);
    }
}
