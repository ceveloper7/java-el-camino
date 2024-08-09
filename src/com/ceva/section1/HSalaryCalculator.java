package com.ceva.section1;

import java.util.Scanner;

public class HSalaryCalculator {
    private static final int HOUR_PER_WEEK = 40;
    private static final double COST_PER_HOUR = 2000.00;

    private int employees = 0;
    private int counter = 0;
    private int[] hoursWork;
    private double[] payroll;

    private void userInput(){
        Scanner input = new Scanner(System.in);
        System.out.printf("%s%n   %s%n   %s%n",
                "Type the end-of-file indicator to terminate input: Pres ENTER then",
                "On UNIX/Linux/macOS type <Ctrl>  + d",
                "On Windows type <Ctrl> + z");
        System.out.printf("%nEnter the number of employees to process: ");
        employees = input.nextInt();
        hoursWork = new int[employees];
        System.out.printf("%s%n", "Enter the number of hours to process: ");
        while(input.hasNext()){
            int hours = input.nextInt();
            hoursWork[counter] = hours;
            counter++;
        }
    }

    private void payroll(){
        payroll = new double[this.employees];
        for(int i = 0; i < hoursWork.length; i++){
            int hour = hoursWork[i];
            double pay = 0.0;
            if(hour > 40){
                int extraHour = 0;
                extraHour = hour - 40;
                pay = (HOUR_PER_WEEK * COST_PER_HOUR) +  (extraHour * (COST_PER_HOUR + COST_PER_HOUR/2));
                payroll[i] = pay;
            }else{
                pay = hour * COST_PER_HOUR;
                payroll[i] = pay;
            }
        }
        processData(payroll);
    }

    private void processData(double[] payroll){
        System.out.printf("%nSalary report: %n%n");
        for(int employee = 0; employee < payroll.length; employee++){
            System.out.printf("Employe %2d: %.2f%n", employee+1, payroll[employee]);
        }
    }

    public static void main(String[] args) {
        HSalaryCalculator calculator = new HSalaryCalculator();
        calculator.userInput();
        calculator.payroll();
    }
}
