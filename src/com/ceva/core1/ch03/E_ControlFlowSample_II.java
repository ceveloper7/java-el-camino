package com.ceva.core1.ch03;

import java.util.Scanner;

public class E_ControlFlowSample_II {
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        E_ControlFlowSample_II control = new E_ControlFlowSample_II();
        control.userInput();
    }

    private void userInput(){

        System.out.print("How much money will you contribute every day? ");
        double payment = in.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate = in.nextDouble();

        processRetirement(payment, interestRate);
    }

    private void processRetirement(double payment, double interestRate){
        double balance = 0;
        int years = 0;
        String rpta = "";

        do {
            balance += payment;
            double interest = balance * (interestRate/100);
            balance += interest;
            years++;

            printData(years, balance);

            System.out.print("Ready to retired? ");
            rpta = in.next();
        }
        while (rpta.equalsIgnoreCase("N"));
    }

    private void printData(int years, double balance){
        System.out.printf("After year %d, you balance is %,#5.2f%n", years, balance);
    }
}
