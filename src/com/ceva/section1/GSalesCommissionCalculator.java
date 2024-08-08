package com.ceva.section1;

import java.util.Scanner;

public class GSalesCommissionCalculator {
    private String salerName;
    private double salesPerWeek;
    private double payPerWeek;
    private double commissionRate;
    private double commisionPay;

    public GSalesCommissionCalculator() {
        this.salerName = "";
        this.salesPerWeek = 0.00;
        this.payPerWeek = 200;
        this.commissionRate = 0.09;
        this.commisionPay = 0.00;
    }

    private void askUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name:");
        this.salerName = input.nextLine();
        System.out.printf("%s%n%s%n   %s%n   %s%n",
                "Enter the amount of each sales.",
                "Type the end-of-file indicator to terminate input:",
                "On UNIX/Linux/macOS type <Ctrl> d then press Enter",
                "On Windows type <Ctrl> z then press Enter");
        while(input.hasNext()){
            this.salesPerWeek += input.nextDouble();
        }
        printResult();
    }

    private void printResult(){
        System.out.printf("%nSales commission report%n");
        System.out.println("=======================");
        System.out.printf("%nSale person: %s%n", this.salerName);
        System.out.printf("Sales per week: %.2f%n", this.salesPerWeek);
        System.out.printf("Your pay per week: %.2f%n", this.payPerWeek);

        this.commisionPay = this.salesPerWeek * this.commissionRate;
        System.out.printf("Your commission is: %.2f%n", this.commisionPay);
        System.out.printf("%nYour total pay is: %.2f%n", (this.payPerWeek + this.commisionPay));
    }

    public static void main(String[] args) {
        GSalesCommissionCalculator commissionCalculator = new GSalesCommissionCalculator();
        commissionCalculator.askUserInput();
    }
}
