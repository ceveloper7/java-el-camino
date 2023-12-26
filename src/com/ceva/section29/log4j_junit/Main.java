package com.ceva.section29.log4j_junit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Vamos a leer del teclado 2 numeros, los convertimos a enteros y reportamos su valor
 * multiplicado y dividido
 *
 * Reglas
 * ======
 * Los numeros deben ser enteros positivos sin punto flotante.
 * El segundo numero no puede ser cero porque la division fallaria.
 */
public class Main implements ParseIntInterface {

    private int mul(int a, int b) {
        return a * b;
    }

    private int div(int a, int b) {
        return a / b;
    }

    @Override
    public boolean validate(String str1, String str2) {
        try {
            int i1 = Integer.parseInt(str1);
            int i2 = Integer.parseInt(str2);
            if ((i1 < 0) || (i2 <= 0))
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void start(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        System.out.print("Primer numero:  ");
        String str1 = br.readLine();
        System.out.print("Segundo numero: ");
        String str2 = br.readLine();
        System.out.println();

        if (!validate(str1, str2)) {
            System.out.println("Datos incorrectos.");
            return;
        }
        int i1 = Integer.parseInt(str1);
        int i2 = Integer.parseInt(str2);
        int res = mul(i1, i2);
        System.out.printf("%s * %s = %d\n", str1, str2, res);
        res = div(i1, i2);
        System.out.printf("%s / %s = %d\n", str1, str2, res);
    }

    public static void main(String[] args) throws IOException {
        new Main().start(System.in);
    }
}

