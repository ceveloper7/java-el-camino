package com.ceva.section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IFindLargestNumber {
    private static final int NUMBER_OF_SALES = 10;
    private int[] sales = new int[NUMBER_OF_SALES];
    private int largest = 0;
    private int lowest = 0;
    private int counter = 0;


    private void userInput(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.printf("%nEnter the last 10 sales of your employees: %n");
            while(counter < 10){
                int sale = Integer.parseInt(br.readLine());
                sales[counter] = sale;
                counter++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        highSale();
        lowSale();
    }

    private void lowSale(){
        // asumo que el primer elemento es el menor
        lowest = sales[0];
        for(int sale : sales){
            if(sale < lowest){
                lowest = sale;
            }
        }
        System.out.printf("%n%s %d", "The lowest sale number is: ", lowest);
    }

    private void highSale(){
        // asumimos que el primer elemento es el mayor
        largest = sales[0];
        for(int sale : sales){
            if(sale > largest){
                largest = sale;
            }
        }
        System.out.printf("%n%s %d", "The largest sale number is: ", largest);
    }


    public static void main(String[] args) {
        IFindLargestNumber find = new IFindLargestNumber();
        find.userInput();
    }
}
