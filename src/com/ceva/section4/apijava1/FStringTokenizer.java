package com.ceva.section4.apijava1;

import java.util.StringTokenizer;

public class FStringTokenizer {
    public static void main(String[] args) {
        String linea = "1,2,4,5,7,8,94,5,6,7,5,3,5,7,85,8,6,6,4,3,4,5";
        StringTokenizer st = new StringTokenizer(linea, ",");
        while(st.hasMoreTokens()){
            String numero = st.nextToken();
            System.out.println(numero);
        }
    }
}
