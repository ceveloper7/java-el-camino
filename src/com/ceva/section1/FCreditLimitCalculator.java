package com.ceva.section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FCreditLimitCalculator {
    private String name;
    private String account;
    private double balance;
    private double totalCharge;
    private double totalCredit;
    private double allowedCredit;

    public FCreditLimitCalculator(){
        this.name = "";
        this.account = "";
        this.balance = 0.0;
        this.totalCharge = 0.0;
        this.totalCredit = 0.0;
        this.allowedCredit = 100000;
    }

    private void userInput()throws IOException {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in))){

            System.out.print("Enter your name: ");
            this.name = input.readLine();

            System.out.print("Enter your account Number: ");
            this.account = input.readLine();

            System.out.print("Enter your initial balance: ");
            this.balance = Double.parseDouble(input.readLine());

            System.out.print("Enter your total item charged : ");
            this.totalCharge = Double.parseDouble(input.readLine());

            System.out.print("Enter your total item credit: ");
            this.totalCredit = Double.parseDouble(input.readLine());
        }
        processData();
    }

    private void processData(){
        double newBalance = balance + totalCharge - totalCredit;
        if(newBalance > allowedCredit){
            System.out.printf("%nThe new balance %.2f exceed Credit limit%n", newBalance);
        }else{
            System.out.printf("%nYour new balance is %.2f", newBalance);
        }
    }

    public static void main(String[] args)throws IOException {
        FCreditLimitCalculator fc = new FCreditLimitCalculator();
        fc.userInput();
    }
}
