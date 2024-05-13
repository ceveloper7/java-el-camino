package com.ceva.section5.poo;

public class JBase {
    private String field;

    public JBase(){
        field = "Hello";
    }

    public class Nested{
        public void run(){
            System.out.println("Nested.run: " + field);
        }
    }

    public void run(){
        System.out.println("Base.run: " + field);
        new Nested().run();
    }

    public static void main(String[] args) {
        new JBase().run();
    }
}
