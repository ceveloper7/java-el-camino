package com.ceva.core1.ch05;

public class Executive extends Manager{
    private String title;
    public Executive(String name, String title, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public String description(){
        if(title.length() > 30){
            return "An executive with a impressive title";
        }else{
            return "An executive with a title of " + getTitle();
        }
    }
}
