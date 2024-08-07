package com.ceva.section1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BCounterControlledIteration {
    private int gradeCounter;
    private int total;
    private int average;

    public BCounterControlledIteration() {
        this.gradeCounter = 1;
        this.total = 0;
        this.average = 0;
    }

    private void processData()throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            while(gradeCounter <= 10){
                System.out.println("Enter grade number: ");
                int grade = Integer.parseInt(br.readLine());
                this.total = this.total + grade;
                this.gradeCounter++;
            }
        }
        this.average = (int)this.total / this.gradeCounter;
    }

    private void printResult(){
        System.out.printf("%nTotal of all 10 grades is %d%n", this.total);
        System.out.printf("Class average is %d%n", this.average);
    }

    public static void main(String[] args) throws IOException{
        BCounterControlledIteration test = new BCounterControlledIteration();
        test.processData();
        test.printResult();
    }
}
