package com.ceva.core1.ch03.datatype;

/*
 * Secuencias de escapa para Caracteres especiales
 * \b Backspace \u0008
 * \t Tab \u0009
 * \n Line feed \u000a
 * \r Carriage return \u000d
 * \f Form feed \u000c
 * \' Double quote \u0022
 * \'Single quote \u0027
 * \\ Backslash \u005c
 * \s Space. usado en text block \u0020
 * \newline in text block only. une esta linea con ka siguiente --
 */

import java.util.stream.IntStream;

public class B_Char {



    public static void main(String[] args) {
        char a = 'A';
        // char acepta valor hexadecimal
        char b = '\u0000';
        char c = '\uFFFF';

        System.out.println("min char value: " + b);
        System.out.println("max char value: " + c);

        IntStream.range(0, 65536)
                .forEach(i -> System.out.println("c[" + i + "]=" + (char) i));
    }
}
