package com.ceva.section2.fundaments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ACompoundInterestCalculation {

    private double amountInvested;
    private double interestRate;
    private int years;

    public ACompoundInterestCalculation() {
        this.amountInvested = 0.0;
        this.interestRate = 0.0;
        this.years = 0;
    }

    private void userInput()throws IOException {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Enter amount invested: ");
            amountInvested = Double.parseDouble(input.readLine());
            System.out.print("Enter interest rate: ");
            interestRate = Double.parseDouble(input.readLine());
            System.out.print("Enter years: ");
            years = Integer.parseInt(input.readLine());
        }
        processData();
    }

    private void processData(){
        for(int y = 1; y <= years; y++){
            double amountDeposited = this.amountInvested * Math.pow(1.0 + (this.interestRate / 100), y);
            double rate = this.interestRate / 100;
            System.out.printf("%4d%,20.2f%n", y, amountDeposited);
        }

    }

    public static void main(String[] args) throws IOException {
        ACompoundInterestCalculation compoundInterestCalculation = new ACompoundInterestCalculation();
        compoundInterestCalculation.userInput();
    }
}
