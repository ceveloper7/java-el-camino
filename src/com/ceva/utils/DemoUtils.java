package com.ceva.utils;

import java.util.Date;

public class DemoUtils {
    public static void main(String[] args) {
        System.out.println(Util.convertStringToInteger("-20"));
        Util.convertDecimalToBinary(2);

        Date today = Util.convertStringToDate("29/02/2024");
        System.out.println("El dia de hoy es: " + today);
    }
}
