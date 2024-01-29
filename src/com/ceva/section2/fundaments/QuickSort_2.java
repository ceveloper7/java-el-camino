package com.ceva.section2.fundaments;

public class QuickSort_2 {
    private static void sort(int[] arr, int low, int high){
        if(low < high){
            int pivot = arr[high];
            int i = (low - 1);
            for(int j = low; j <= high - 1; j++){
                if(arr[j] < pivot){
                    i++;
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
            int tmp = arr[i+1];
            arr[i+1]=arr[high];
            arr[high] = tmp;
            pivot = i + 1;

            sort(arr, low, pivot-1);
            sort(arr, pivot+1, high);
        }

    }

    public static void main(String[] args) {
        int array[] = {9,1,34,20,2,7,12,4,6,8};
        for(int i : array){
            System.out.print(" " + i);
        }
        System.out.println();
        sort(array, 0, array.length-1);
        for(int i : array){
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
