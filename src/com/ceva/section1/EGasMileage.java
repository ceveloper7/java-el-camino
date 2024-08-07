package com.ceva.section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Libro: Java how to program, Late objects, Global Edition
 * Ejercicio 3.17 - Pagina 158
 */
public class EGasMileage {
    private int miles;
    private int gallons;
    private int counter;


    public EGasMileage(){
        this.miles = 0;
        this.gallons = 0;
        this.counter = 0;

    }

    private void userInput() throws IOException {
        int input = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Cuantos viajes va a registra ? ");
            int trip = Integer.parseInt(br.readLine());
            int m = 0;
            int g = 0;
            double[] milesPerGallon = new double[trip];
            while(trip > this.counter){
                System.out.print("Ingrese millas realizadas para el viaje " + (counter+1) + ": ");
                m = Integer.parseInt(br.readLine());
                this.miles += m;
                System.out.print("Ingrese galones consumidos para el viaje " + (counter+1) + ": ");
                g = Integer.parseInt(br.readLine());
                this.gallons += g;

                milesPerGallon[counter] = (double) m/g;
                this.counter++;
            }
            processDataTrips(milesPerGallon);
        }
    }

    private void processDataTrips(double[] milesPerGallon){
        for(int i = 0; i < milesPerGallon.length; i++){
            System.out.printf("%nPara el viaje " + (i+1) + " millas recorridad por galon: %.2f", milesPerGallon[i]);
        }

        System.out.printf("%nPromedio combinado para los %d viajes es de %.2f millas x galon%n", this.counter, (double)(this.miles/this.gallons));
    }

    public static void main(String[] args) throws IOException{
        EGasMileage eGasMileage = new EGasMileage();
        eGasMileage.userInput();
    }
}
