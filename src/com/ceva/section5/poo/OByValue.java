package com.ceva.section5.poo;

public class OByValue {

    // pasando parametros por valor
    private static void getDate(int day, String month, int year){
        day = 13;
        month = "Mayo";
        year = 2024;
    }

    private static void formatDate(int day, String month, int year){
        System.out.println("dia " + day + " month: " + month + " year " + year);
    }

    static int day = 0;
    static String month = "";
    static int year = 0;

    public static void main(String[] args) {
        getDate(day, month, year);
        formatDate(day, month, year);
    }
}
