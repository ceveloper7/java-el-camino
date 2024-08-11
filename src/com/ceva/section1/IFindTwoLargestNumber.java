package com.ceva.section1;
// fig 3.23
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IFindTwoLargestNumber {
    private static final int NUMBER_OF_SALES = 10;
    private int[] sales = new int[NUMBER_OF_SALES];
    private int firstLargest = 0;
    private int secondLargest = 0;
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

    // 6,8,9,7,
    private void highSale(){
        // asumimos que el primer elemento es el mayor
        firstLargest = sales[0];
        for(int sale : sales){
            if(sale > firstLargest){
                secondLargest = firstLargest;
                firstLargest = sale;
            }
        }
        System.out.printf("%n%s %d", "The first largest sale number is: ", firstLargest);
        System.out.printf("%n%s %d", "The second largest sale number is: ", secondLargest);
    }


    public static void main(String[] args) {
        IFindTwoLargestNumber find = new IFindTwoLargestNumber();
        find.userInput();
    }
}
