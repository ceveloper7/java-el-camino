package com.ceva.execises.ch03;

import java.util.Scanner;

/**
 * Exercise 3.30 - Java how to program
 */
public class Ex330 {
    private int size = 0;

    private boolean isDigit(char ch) {
        return (ch >= '0') && (ch <= '9');
    }

    private boolean isNumber(String str) {
        int dot = -1;
        int nDigits = 0;
        for (int n=0; n<str.length(); n++) {
            char ch = str.charAt(n);
            if (ch == '.') {
                if ((dot != -1) || (n >= (str.length() - 1)))
                    return false;
                dot = n;
            } else if (ch == '-') {
                if (n != 0)
                    return false;
            } else if (!isDigit(ch))
                return false;
            else
                nDigits++;
        }
        // si nDigits es mayor a cero quiere decir que si hay numeros.
        return nDigits > 0;
    }

    private void printTopAndBottom(){
        for(int i = 0; i<size; i++){
            System.out.print("*");
        }
        System.out.println();
    }

    private void printBody(){
        int spaces = size - 2;
        for(int i = 0; i<size; i++){
            System.out.print("*");
            for(int j = 1; j<=spaces; j++){
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
    }

    private void userInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el tamanio del cuadrado a dibujar (entre 5 - 20): ");
        String userInput = input.nextLine();

        if(!isNumber(userInput)){
            throw new NumberFormatException("Numero invalido");
        }

        int value = Integer.parseInt(userInput);
        if(value >= 5 && value <= 20){
            this.size = value;
            printTopAndBottom();
            printBody();
            printTopAndBottom();
        }else{
            throw new IllegalArgumentException("Debe ingresar un numero entre 5 - 20");
        }
    }

    public static void main(String[] args) {
        Ex330 obj = new Ex330();
        obj.userInput();
    }
}
