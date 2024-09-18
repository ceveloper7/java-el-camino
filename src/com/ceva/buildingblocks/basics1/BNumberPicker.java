package com.ceva.buildingblocks.basics1;

import java.util.Random;

/**
 * Declarando imports
 * 1. * no significa que ingresara a las subcarpetas en el paquete Random para buscar la clase, * significa que solo buscara la clase
 *    en la carpeta Random como raiz.
 * 2. POr convencion los paquetes debe escribirse en minuscula.
 */
public class BNumberPicker {
    // nombre cualificado de clase
    java.util.Date myDate;
    java.sql.Date mySqlDate;

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(50));
    }
}
