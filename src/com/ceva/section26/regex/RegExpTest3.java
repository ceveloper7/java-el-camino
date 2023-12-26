package com.ceva.section26.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest3 {
    public static void main(String[] args) {
        String dates[] = {"01/01/2020", "01-01-2020", "31/2/2020", "1-1-2020", "1/1-2020", "1-1/2020", "100/01/2020"};

        /**
         * ^ significa que debe comenzar con toda la expresion
         * $ significa que al terminar la expresion alli debe terminar la informacion
         * (\d{1,2}) : digito de 1 o dos caracteres
         * ([/-]) : significa que sigue una / o un signo -
         * (\d{1,2}) : Significa que sigue un digito  que puede ser de 1 o 2 caracteres
         * \\2 : hace referencia al grupo 2 que es ([/-])
         * (\d{4}) : viene un digito de 4 caracteres
         */
        Pattern pattern = Pattern.compile("^(\\d{1,2})([/-])(\\d{1,2})\\2(\\d{4})$");
        for (String date : dates) {
            Matcher matcher = pattern.matcher(date);
            if (matcher.find()) {
                System.out.printf("La fecha %s es valida %d.\n", date, matcher.groupCount());

                // obtenemos elprimer digito de la fecha
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(3));
                int year = Integer.parseInt(matcher.group(4));
                System.out.printf("day: %d, month: %d, year: %d\n", day, month, year);
            } else
                System.out.printf("La fecha %s es inválida.\n", date);
        }
    }
}
