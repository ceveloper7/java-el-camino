/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.exercise.sec2;

import com.ceva.javaelcamino.util.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 */
public class AB_Statistics {

    private int inputData() throws IOException {
        System.out.println("Ingrese un numero entero ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String input = userInput.readLine();
        if (!Utils.isNumber(input)) {
            System.out.println("Dato invalido");
            return 0;
        }
        return Integer.valueOf(input);
    }

    private int[] uniqueElementsUsingLoop(int[] result) {
        int[] rpta = new int[result.length];
        int indice = 0;
        boolean duplicados;
        for (int i = 0; i < result.length; i++) {
            duplicados = false;
            for (int j = 0; j < i; j++) {
                if (result[i] == result[j]) {
                    duplicados = true;
                    break;
                }
            }
            if (!duplicados) {
                rpta[indice] = result[i];
                indice++;
            }
        }
        return rpta;
    }

    private int[] uniqueElementsUsingSorting(int[] result) {
        Arrays.sort(result);
        int[] rpta = new int[result.length];
        int indice = 0;
        for (int i = 0; i < result.length; i++) {
            while ((i < result.length - 1) && (result[i] == result[i + 1])) {
                i++;
            }
            rpta[indice] = result[i];
            indice++;
        }
        return rpta;
    }

    private int[] uniqueElementsUsingStream(int[] result) {
        return Arrays.stream(result).distinct().sorted().toArray();
    }

    public int[] calcSumAndCountAllNumbersDivBy_2_Or_7(int num) {
        int divBy_2_Or_7[] = new int[num];
        int index = 0;
        for (int i = 2; i < num; i = i + 2) {
            divBy_2_Or_7[index] = i;
            index += 1;
        }

        for (int j = 7; j < num; j = j + 7) {
            divBy_2_Or_7[index] = j;
            index += 1;
        }

        int result[] = new int[index];
        for (int k = 0; k < divBy_2_Or_7.length; k++) {
            if (divBy_2_Or_7[k] > 0) {
                result[k] = divBy_2_Or_7[k];
            }
        }
        return uniqueElementsUsingStream(result);
    }

    public void process() throws IOException {
        int input = inputData();
        int[] result = calcSumAndCountAllNumbersDivBy_2_Or_7(input);
        outputResult(result);
    }

    private void outputResult(int[] result) {
        int count = 0;
        int suma = 0;

        for (int i = 0; i < result.length; i++) {
            if(result[i] > 0){
                System.out.print(result[i] + " ");
                count++;
                suma += result[i];
            }
        }
        System.out.println();
        System.out.println("Numero de divisores " + count);
        System.out.println("Suma de divisores " + suma);
    }

    public static void main(String[] args) throws IOException {
        AB_Statistics test = new AB_Statistics();
        test.process();
    }
}
