package com.ceva.section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CSentinelControlledIteration {
    private int counter;
    private int total;
    private int grade;
    private double average;

    public CSentinelControlledIteration() {
        // 1. Inicializacion de datos
        this.counter = 0;
        this.total = 0;
        this.grade = 0;
        this.average = 0.0;
    }

    /**
     * Formulacion de Algoritmo: Iteracion controlada por un valor sentinela que es (-1)
     */
    private void askUserInput()throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            // 2. pedir al usuario datos
            System.out.println("Enter grade number...");
            this.grade = Integer.parseInt(br.readLine());
            // 3. Procesamiento mientras el usuario no hay ingresado el valor sentinela (-1)
            while(this.grade != -1){
                // 3.1 acumulacion de notas
                this.total += this.grade;
                // 3.2 incremento del contador
                this.counter++;
                // 3.3 pedid de nuevo valor
                System.out.println("Enter grade number...");
                // 3.4 asignacion del valor a propiedad grade
                this.grade = Integer.parseInt(br.readLine());
            }
        }
        processGradeAverage();
    }

    private void processGradeAverage(){
        // 4. Si por lo menos se ingreso una nota, entonces realizamos el calculo del promedio.
        if(this.counter != 0){
            // total y counter son valores int pero su division puede producir un valor double sin embargo la parte fracionaria se pierde
            // aunque average es double solo recibe la parte entera, para no perder la parte fraccionaria usamos una conversion explicita
            //
            this.average = (double) this.total / this.counter;
            printResult();
        }
    }

    private void printResult(){
        System.out.printf("%nTotal of all grades is %d%n", this.total);
        System.out.printf("Class average is %.2f%n", this.average);
    }

    public static void main(String[] args) throws IOException{
        CSentinelControlledIteration test = new CSentinelControlledIteration();
        test.askUserInput();
    }
}
