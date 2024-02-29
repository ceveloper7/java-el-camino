package com.ceva.utils.dateconvert;

import java.util.StringTokenizer;

public class DateConvert {
    private final char SEPARATOR = '/';
    private String[] mapa = null;

    public DateConvert() {
        mapa = new String[6];
        mapa[0] = "";
        mapa[1] = "";
        mapa[2] = "";
        mapa[3] = "";
        mapa[4] = "";
        mapa[5] = "";
    }

    private void formatAnalyzer(String formatDate){
        if((formatDate.isBlank()) || (formatDate.isEmpty()))
            System.out.println("Error: debe ingresar un formato");
        StringTokenizer st = new StringTokenizer(formatDate, "/");

        if((st.countTokens() == 1))
            System.out.println("Error - Debe usar / como separador de dia, mes, año");

        while (st.hasMoreTokens()){
            String v = st.nextToken();
            for(int i = 0; i < v.length(); i++){
                char letra = v.charAt(i);
                System.out.println(letra);
            }
        }
    }

    public static void main(String[] args) {
        DateConvert dc = new DateConvert();
        dc.formatAnalyzer("dd/mm/yyyy");
    }
}
