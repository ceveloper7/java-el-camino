package com.ceva.section1;

import com.ceva.utils.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Libro: Java how to program, Late objects, Global Edition
 * Ejercicio 3.17 - Pagina 158
 */
public class EGasMileage {
    private double totalMiles;
    private double totalGallons;
    private int counter;
    private String val = "";


    public EGasMileage(){
        this.totalMiles = 0;
        this.totalGallons = 0;
        this.counter = 0;

    }

    private void askUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Cuantos viajes va a registra ? ");
        val = input.nextLine();
        if(!Util.isDigit(val)){
            throw new IllegalArgumentException("Error: Numero de viajes debe ser un numero entero");
        }

        int userInput = Integer.parseInt(val);
        if(userInput <= 0){
            throw new IllegalArgumentException("Error: Numero de viajes debe ser un numero entre 1-9");
        }
        double miles, gallons = 0;
        double[] milesPerGallon = new double[userInput];
        while(userInput > this.counter){
            System.out.print("Ingrese millas realizadas para el viaje " + (counter+1) + ": ");
            val = input.nextLine();
            if(!Util.isDigit(val)){
                throw new IllegalArgumentException("Error: Numero de viajes debe ser un numero valido");
            }
            miles = Double.parseDouble(val);
            this.totalMiles += miles;

            System.out.print("Ingrese galones consumidos para el viaje " + (counter+1) + ": ");
            val = input.nextLine();
            if(!Util.isDigit(val)){
                throw new IllegalArgumentException("Error: Numero de galones ser un numero valido");
            }
            gallons = Double.parseDouble(val);
            this.totalGallons += gallons;

            milesPerGallon[counter] = miles/gallons;
            this.counter++;
        }
        processDataTrips(milesPerGallon);
    }

    private void processDataTrips(double[] milesPerGallon){
        for(int i = 0; i < milesPerGallon.length; i++){
            System.out.printf("%nPara el viaje " + (i+1) + " millas recorridad por galon: %.2f", milesPerGallon[i]);
        }

        System.out.printf("%nPromedio combinado para los %d viajes es de %.2f millas x galon%n", this.counter, (double)(this.totalMiles /this.totalGallons));
    }

    public static void main(String[] args){
        EGasMileage mileage = new EGasMileage();
        try{
            mileage.askUserInput();
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
