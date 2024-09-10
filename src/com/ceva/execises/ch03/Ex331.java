package com.ceva.execises.ch03;

import java.util.Scanner;

/*
 * Exercise 3.31 - Java how to program
 */
public class Ex331 {
    private String value;

    private boolean isDigit(char ch){
        return (ch >= '0') && (ch <= '9');
    }

    private boolean isAValidNumber(String userInput){
        int hasADot = -1;
        int nDigits = 0;

        for(int n = 0; n < userInput.length(); n++){
            char token = userInput.charAt(n);
            if(token == '.'){
                if((hasADot != -1) || n >= userInput.length()-1){
                    return false;
                }
                hasADot = n;
            }else if(token == '-'){
                return false;
            }else if(!isDigit(token)){
                return false;
            }
            else{
                nDigits++;
            }
        }
        return nDigits == 5;
    }


    private boolean isPalindrome(){
        boolean isPalindrome = false;
        StringBuilder sb = new StringBuilder();
        for(int n = value.length()-1; n < value.length(); n--){
            if(n < 0){
                break;
            }
            char ch = value.charAt(n);
            sb.append(ch);
        }
        if(sb.toString().equals(value)){
            isPalindrome = true;
        }
        return isPalindrome;
    }

    private void userInput(){
        boolean rpta;
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("--> Ingrese un numero entero de 5 digitos ");
            String val = input.nextLine();
            rpta = isAValidNumber(val);
            if(!rpta){
                System.err.println("--> Numero invalido. ");
            }
            this.value = val;

            if(isPalindrome()){
                System.out.println("El numero " + this.value + " es un numero palindromo");
            }else{
                System.out.println("El numero " + this.value + " no es un numero palindromo");
            }

        }
        while (!rpta);


    }

    public static void main(String[] args) {
        Ex331 ex331 = new Ex331();
        ex331.userInput();
    }
}
