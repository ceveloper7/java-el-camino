/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author PC
 */
public class TodoTest1 {

    public static void main(String[] args) throws IOException {
        // TODO: Este programa podria funcionar mejor utilizando la clase Scanner
        System.out.print("Introduce un numero: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numero = br.readLine();
        // TODO: Validar que el usuario ingrese un numero permitido o valido
        br.close();
        // TODO: Validar el input
        int num = Integer.parseInt(numero);
        System.out.println("El numero" + num + " multiplicado por 10 es igual a " + (num * 10));
    }
}
