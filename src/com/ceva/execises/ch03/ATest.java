package com.ceva.execises.ch03;

public class ATest {
    /**
     * exercise 3.25 - java how to program
     * Print:
     * +++++++
     * ****
     * +++++++
     * ****
     * +++++++
     * ****
     */
    public static void ex325(){
        int count = 1;
        while(count <= 10){
            System.out.print(count + " ");
            System.out.println(count % 2 == 1? "****" : "+++++++");
            ++count;
        }
    }

    /**
     * exercise 3.26 - java how to program
     * Print:
     * <<<
     * >>>
     * <<<
     */
    public static void ex326(){
        int row = 3;
        while(row >= 1){
            int column = 1;
            while(column <= 3){
                System.out.print(row % 2 == 1? "<" : ">");
                ++column;
            }
            --row;
            System.out.println();
        }
    }

    /**
     * exercise 3.27 java to program - else colgante
     */
    public static void ex327(){
        int x = 6;
        int y = 10;
        if(x > 5){
            if(y > 5){
                System.out.println("x and y are > 5");
            }
        }
        else{
            System.out.println("x is <= 5");
        }
    }

    /**
     * exercise 3.28 - java how to program - another else problem
     */
    public static void ex328(){
        int x = 11;
        int y = 9;
        if(x < 10){
            if(y > 10){
                System.out.println("*****");
            }
            else{
                System.out.println("#####");
            }
        }
        System.out.println("$$$$$");
    }

    public static void main(String[] args) {
        ATest.ex328();
    }
}
