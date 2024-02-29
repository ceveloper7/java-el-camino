package com.ceva.section4.apijava1;

/**
 * Cadena de formato: %[argument_index$][flag][width][.precision]conversion
 * % -> significa que se trata de un argumento
 * [] -> Lo que esta entre corchetes es opcional
 * argument_index -> corresponde al nro de argumento a utilizar en lugar de orden por default
 *                  1er argument = 1$
 *                  2do argumento = 2$
 * width -> determina el tamano que ocupara el argumento si anteponemos un 0 se rellenaran los espacios
 *          con 0
 *
 */
public class HFormat_Printf {
    public static void main(String[] args) {
        int nLine = 100;
        String source = "Test.java";
        String message = "Este es un mensaje";

        // version 1 - usando printf
        System.out.printf("[%s %d]: %s\n", source, nLine, message);

        // version 2 - usando String.format()
        // ejem: String s = String.format("[%s %d]: %1$s\n", source, nLine, message);
        // imprime: [Test.java 100]: Test.java
        String s = String.format("[%s %d]: %s\n", source, nLine, message);
        System.out.print(s);
    }
}
