package com.ceva.section2.fundaments;

public class EPassArray {
    private static void modifyArray(int[] array2){
        for(int i=0; i<array2.length; i++){
            array2[i] *= 2;
        }
    }

    private static void modifyElement(int element){
        element *=2;
        System.out.printf("Value of element in modifyElement: %d%n", element);
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        System.out.printf(
                "Effects of passing reference to entire array:%n" +
                        "The values of the original array are:%n");

        for(int element : array){
            System.out.printf("   %d ", element);
        }

        modifyArray(array); // pass array by reference
        System.out.printf("%n%nThe values of the modified array are:%n");
        for(int element : array){
            System.out.printf("   %d ", element);
        }

        System.out.printf(
                "%n%nEffects of passing array element value:%n" +
                        "array[3] before modifyElement: %d%n", array[3]);

        modifyElement(array[3]); // pass array[3] element value

        System.out.printf(
                "array[3] after modifyElement: %d%n", array[3]);
    }
}
