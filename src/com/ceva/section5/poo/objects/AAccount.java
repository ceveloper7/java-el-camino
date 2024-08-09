package com.ceva.section5.poo.objects;

public class AAccount {
    private String name;
    private double balance;

    public AAccount(String name, double balance) {
        this.name = name;
        if(balance > 0.0){
            this.balance = balance;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount > 0.0){
            balance += amount;
        }
    }
}
