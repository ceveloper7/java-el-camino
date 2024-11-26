package com.ceva.core1.ch03.datatype;

/*
 * Retirement V1
 */

import java.util.Scanner;

public class D_ControlFlow_Sample {
    public static void main(String[] args) {
        D_ControlFlow_Sample control = new D_ControlFlow_Sample();
        control.userInput();
    }

    private void userInput(){
        Scanner input = new Scanner(System.in);

        System.out.print("How much money do you need to retire? ");
        double goal = input.nextDouble();

        System.out.print("How much money will you contribute every year");
        double payment = input.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate = input.nextDouble();

        process(goal, payment, interestRate);
    }

    private void process(double goal, double payment, double interestRate){
        double balance = 0.0;
        int years = 0;

        while(balance < goal){
            balance += payment;
            double interest = balance * (interestRate/100);
            balance += interest;
            years++;
        }

        printData(years);
    }

    private void printData(int years){
        System.out.printf("You can retire in %d years", years);
    }
}
