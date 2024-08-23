package com.ceva.utils;

import java.util.Calendar;
import java.util.Date;

public class Util {
    // funcion que valida si se trata de un digito
    public static boolean isDigit(char ch){
        return (ch >= '0') && (ch <= '9');
    }
    public static boolean isDigit(String str){
        return str.matches("[0-9]{1,13}(\\.[0-9]*)?");
    }
    public static Date convertStringToDate(String strDate){
        int day, month, year;

        if((strDate.isEmpty()) || (strDate.isBlank()))
            return null;

        int idx0 = strDate.indexOf('/');
        if(idx0 == -1)
            return null;// fecha invalida xq no tiene / como separador

        String tmp = strDate.substring(0,idx0);
        day = Integer.parseInt(tmp);
        if((day < 0)|| (day > 31))
            return null;

        // buscamos la sgte aparicion del /
        int idx1 = strDate.indexOf('/', idx0+1);
        if(idx1 == -1)
            return null; // dia invalido

        tmp = strDate.substring(idx0+1,idx1);
        month = Integer.parseInt(tmp);
        if((month < 1) || (month > 12))
            return null; // mes in valido

        tmp = strDate.substring(idx1+1);
        year = Integer.parseInt(tmp);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    public static int convertStringToInteger(String value){
        boolean negative = false;
        int result = 0;
        for(int i = 0; i < value.length(); i++){
            char ch = value.charAt(i);
            // validamos si tiene signo negativo
            if((i == 0) && (ch == '-')){
                negative = true;
                continue;
            }
            if(!isDigit(ch))
                return 0;
            int digit = ch - '0';
            result = result*10 + digit;
        }
        if(negative)
            return -result;
        return result;
    }

    public static void convertDecimalToBinary(int value){
        if((value <= 0))
            return;
        boolean isZero = false;
        StringBuilder binary = new StringBuilder();
        int cosciente = value/2;
        binary.append(value%2);

        do{
            binary.append(cosciente%2);
            cosciente = cosciente / 2;
            if(cosciente == 0){
                isZero = true;
            }
        }
        while (!isZero);

        System.out.println(binary.reverse());
    }

    public static int findIndexOf (String str, String search)
    {
        if (str == null || search == null || search.length() == 0)
            return -1;
        //
        int endIndex = -1;
        int parCount = 0;
        boolean ignoringText = false;
        int size = str.length();
        while (++endIndex < size)
        {
            char c = str.charAt(endIndex);
            if (c == '\'')
                ignoringText = !ignoringText;
            else if (!ignoringText)
            {
                if (parCount == 0 && c == search.charAt(0))
                {
                    if (str.substring(endIndex).startsWith(search))
                        return endIndex;
                }
                else if (c == ')')
                    parCount--;
                else if (c == '(')
                    parCount++;
            }
        }
        return -1;
    }   //  findIndexOf
}
