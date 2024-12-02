package com.ceva.algorithms.fundaments;

public class A_SimpleArrayProcessing {
    private static final int[] nums = new int[]{26,8,9,5,6};

    public static void main(String[] args) {
        A_SimpleArrayProcessing processing = new A_SimpleArrayProcessing();
        processing.reverseArray(nums);
    }

    private void reverseArray(int[] array){
        int size = array.length;
        for (int i = 0; i < size/2; i++){
            int temp = array[i];
            array[i] = array[size-1-i];
            array[size-1-i] = temp;
        }
        printArray(array);
    }

    private void copyToArray(int[] array){
        int[] target = new int[array.length];
        for(int i = 0; i < array.length; i++){
            target[i] = array[i];
        }
        printArray(target);
    }

    private void computeAvarageInArray(int[] array){
        int size = array.length;
        double sum = 0;
        for(int i = 0; i < size; i++){
            sum += array[i];
        }

        System.out.println("Average in array: " + (sum/size));
    }

    private void findMaximunValueInArray(int[] array){
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i] > max) max = array[i];
        }
        System.out.println("Max value in array: " + max);
    }

    private void printArray(int[] array){
        for (int n : array){
            System.out.println(n);
        }
    }
}
