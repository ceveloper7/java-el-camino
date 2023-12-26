package com.ceva.section26.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Programa que evalua el formato de correo valido
 */
public class RegExpTest1 {
    public static void main(String[] args) {
        // lista emails para hacer pruebas
        String mails[] = {"yomismo@rcosio.com",
                "suservidor@miurl.com",
                "1@hotmail.com",
                "raul@",
                "empleado@aid2do.",
                "@aid2do.com"};

        /**
         * Creamos la expresion regular
         */
        Pattern pattern = Pattern.compile("^[\\w\\.]+@[\\w\\.]+\\.[\\w]+$");
        for (String mail : mails) {
            Matcher matcher = pattern.matcher(mail);
            // validamos si matcher encontró una expresion que cumple con las reglas de la expresion
            if (matcher.find())
                System.out.printf("El correo %s es valido.\n", mail);
            else
                System.out.printf("El correo %s es invalido.\n", mail);
        }
    }
}
