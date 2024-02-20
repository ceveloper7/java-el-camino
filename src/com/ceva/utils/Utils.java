package com.ceva.utils;

public class Utils {
    // funcion que valida si se trata de un digito
    public boolean isDigit(char ch){
        return (ch >= '0') && (ch <= '9');
    }

    public int convertStringToInteger(String value){
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
}
