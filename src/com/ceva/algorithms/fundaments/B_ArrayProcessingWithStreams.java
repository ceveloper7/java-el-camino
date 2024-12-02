package com.ceva.algorithms.fundaments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class B_ArrayProcessingWithStreams {
    private static final double[] nums = new double[]{26,8,9,5.6,37,4};
    private static final ArrayList<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        numbers.add(2);
        numbers.add(9);
        numbers.add(6);
        numbers.add(7);
        numbers.add(4);
        numbers.add(8);

        B_ArrayProcessingWithStreams processing = new B_ArrayProcessingWithStreams();
        processing.computeAvarage(nums);
    }

    private void findMaxValue(ArrayList<Integer> nums){
        Optional<Integer> maxVal = nums.stream()
                .max(Comparator.naturalOrder());
        System.out.println(maxVal);
    }

    private void findMaxValue(double[] numbers){
        double findMaxValue = Arrays.stream(numbers)
                .max()
                .orElse(Double.NEGATIVE_INFINITY);
        System.out.println("max value in array: " + findMaxValue);
    }

    private void computeAvarage(double[] array){
        double avg = Arrays.stream(array)
                .average()
                .orElse(Double.NEGATIVE_INFINITY);
        System.out.println("El promedio de la lista es: " + avg);
    }
}
