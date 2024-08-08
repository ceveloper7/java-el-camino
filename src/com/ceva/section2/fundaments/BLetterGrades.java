package com.ceva.section2.fundaments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class BLetterGrades {
    private int aCount;
    private int bCount;
    private int cCount;
    private int dCount;
    private int fCount;
    private int total;
    private int gradeCounter;

    public BLetterGrades(){
        this.aCount = 0;
        this.bCount = 0;
        this.cCount = 0;
        this.dCount = 0;
        this.fCount = 0;
        this.total = 0;
        this.gradeCounter = 0;
    }

    private void userInput(){
        try{
            Scanner input = new Scanner(System.in);
            System.out.printf("%s%n%s%n   %s%n   %s%n",
                    "Enter the integer grades in the range 0-100.",
                    "Type the end-of-file indicator to terminate input:",
                    "On UNIX/Linux/macOS type <Ctrl> d then press Enter",
                    "On Windows type <Ctrl> z then press Enter");
            while(input.hasNext()){
                int grade = input.nextInt();
                this.total += grade;
                this.gradeCounter++;

                switch (grade / 10){
                    case 10, 9:
                        this.aCount++;
                        break;
                    case 8:
                        this.bCount++;
                        break;
                    case 7:
                        this.cCount++;
                        break;
                    case 6:
                        this.dCount++;
                        break;
                    default:
                        this.fCount++;
                        break;
                }
            }
            printResult();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void printResult(){
        System.out.printf("%nGrade Report:%n");
        if(this.gradeCounter != 0){
            double average = (double)this.total/this.gradeCounter;
            System.out.printf("Total of the %d grades entered is %d%n", this.gradeCounter, this.total);
            System.out.printf("Class average is %.2f%n", average);
            System.out.printf("%n%s%n%s%d%n%s%d%n%s%d%n%s%d%n%s%d%n",
                    "Number of students who received each grade:",
                    "A: ", aCount, // display number of A grades
                    "B: ", bCount, // display number of B grades
                    "C: ", cCount, // display number of C grades
                    "D: ", dCount, // display number of D grades
                    "F: ", fCount);
        }else{
            System.out.println("No grades were entered");
        }
    }

    public static void main(String[] args){
        BLetterGrades bletterGrades = new BLetterGrades();
        bletterGrades.userInput();
    }
}
