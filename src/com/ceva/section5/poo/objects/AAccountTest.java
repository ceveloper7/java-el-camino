package com.ceva.section5.poo.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AAccountTest {
    private void userInput(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Please enter your name: ");
            String name = br.readLine();
            System.out.print("Please enter your deposit: ");
            double deposit = Double.parseDouble(br.readLine());
            System.out.printf("Adding %.2f to your account", deposit);
            AAccount account = new AAccount(name, deposit);

            System.out.println();
            System.out.printf("%nMr. %s ", account.getName());
            System.out.printf("your current balance is %.2f%n", account.getBalance());
        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        AAccountTest accountTest = new AAccountTest();
        accountTest.userInput();
    }
}
