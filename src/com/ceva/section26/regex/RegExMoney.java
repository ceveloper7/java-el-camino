package com.ceva.section26.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExMoney {
    public static void main(String[] args) {
        String s = "$100,000.50";
        /**
         * ^\$?\d{1,3}(,\d{3})+(\.\d{1,2})?$ -> aceptamos opcional signo peso, seguido de coma, seguido decimal
         * ^\\$? significa que puede el signo $ puede estar una vez o ninguna
         * \d{1,3} significa que puede haber por lo menos 1 digito, maximo 3
         * (,\d{3})+ significa del digito o maximo 3 puede haber una coma seguido de 3 digitos
         *         lo agrupamos en parentesis para decir que esta secuencia se puede repetir con
         *         el signo + indicamos que la repeticion puede ser de una o mas veces
         * (\.\d{1,2})? significa que luego puede venir un punto . seguido de uno o dos digitos
         * |^\$?\d+(\.\d{1,2})?$ concatenamos 2 expresiones regulares cumple con una o con esta
         *      significa que signo pesos es opcional, no hay comas y punto con dos decimales
         */
        String regex = "^\\$?\\d{1,3}(,\\d{3})+(\\.\\d{1,2})?$|^\\$?\\d+(\\.\\d{1,2})?$";
        //"\\d+(\\.\\d{1,2})?";
        //"^\\$?\\d{1,3}(,\\d{3})+(\\.\\d{1,2})?$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        if (m.find())
            System.out.println(": " + m.group());
        else
            System.out.println("No match.");
    }
}
