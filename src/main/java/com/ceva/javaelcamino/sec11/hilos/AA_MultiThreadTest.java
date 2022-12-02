/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec11.hilos;

/**
 *
 * @author PC
 */
public class AA_MultiThreadTest implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            System.out.println("Prueba " + i);
        }
    }
    
    /**
     * El programa tiene 2 hilos. 1 hilo donde se ejecuta el metodo main() y 1 hilo donde el programa se esta creando
     * Luego de imprimir el mensaje "prgrama finalizado" el main() termina y por lo tanto termina el hilo que
     * inicio el programa. Pero aun existe el segundo hilo llamado hilo1
     * Cuando run() finaliza, tambien finaliza su respectivo hilo
     */
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new AA_MultiThreadTest());
        hilo1.start();
        System.out.println("Programa finalizado");
    }
    
}
