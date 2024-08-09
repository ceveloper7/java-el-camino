package com.ceva.section5.poo.objects;

// Fig. 7.15: GradeBookTest.java
// GradeBookTest creates a GradeBook object using an array of grades,
// then invokes method processGrades to analyze them.
public class BBGradeBookTest {
    public static void main(String[] args) {
        // array of student grades
        int[] gradesArray = {87, 68, 94, 100, 83, 78, 85, 91, 76, 87};

        BGradeBook myGradeBook = new BGradeBook(
                "CS101 Introduction to Java Programming", gradesArray);
        System.out.printf("Welcome to the grade book for%n%s%n%n",
                myGradeBook.getCourseName());
        myGradeBook.processGrades();
    }
}
