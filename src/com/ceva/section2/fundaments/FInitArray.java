package com.ceva.section2.fundaments;

// Fig. 6.12: InitArray.java
// Initializing two-dimensional arrays.
public class FInitArray {
    public static void main(String[] args) {
        int[][] array1 = {{1,2,3},{4,5,6}};
        int[][] array2 = {{1,2}, {3}, {4,5,6}};

        System.out.println("Values in array1 by row are");
        outputArray(array1);

        System.out.printf("%nValues in array2 by row are%n");
        outputArray(array2);
    }

    public static void outputArray(int[][] array) {
        // recorremos las filas del array
        for(int row = 0; row < array.length; row++){
            // recorremos las columnas del array
            for(int column = 0; column < array[row].length; column++){
                System.out.printf("%d  ", array[row][column]);

            }
            System.out.println();
        }
    }
}
