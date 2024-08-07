package com.ceva.section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DNetedControlStatement {
    private static final int NUMBER_OF_STUDENTS = 3;
    private int passes;
    private int failures;
    private int studentCount;

    public DNetedControlStatement() {
        this.passes = 0;
        this.failures = 0;
        this.studentCount = 0;
    }

    private void askExamResult()throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ){
            while(this.studentCount < NUMBER_OF_STUDENTS){
                System.out.println("Enter exam result (1 = pass or 2 = fail)");
                String examResult = br.readLine();

                if(examResult.equals("1")){
                    this.passes++;
                }
                else{
                    this.failures++;
                }
                this.studentCount++;
            }
        }
        printStudentResults();
    }

    private void printStudentResults(){
        System.out.printf("%nTotal passes Exams %d%n ", this.passes);
        System.out.printf("%nTotal failures Exams %d%n ", this.failures);

        if(this.passes > 8){
            System.out.println("Bonus to instructor... congrats!");
        }
    }

    public static void main(String[] args) throws IOException {
        DNetedControlStatement test = new DNetedControlStatement();
        test.askExamResult();
    }
}
