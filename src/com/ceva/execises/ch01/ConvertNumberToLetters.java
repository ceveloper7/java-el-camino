package com.ceva.execises.ch01;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ConvertNumberToLetters {
    StringBuilder letras = new StringBuilder();
    StringBuilder token = new StringBuilder();
    private static final int UNIDAD = 1;
    private static final int DECENA = 2;
    private static final int CENTENA = 3;
    private static final int UNIDAD_DE_MILLAR = 4;
    private static final int DECENA_DE_MILLAR = 5;
    private static final int CENTENA_DE_MILLAR = 6;

    private String number;
    private int size;

    Map unidades = new HashMap();
    Map decenas = new HashMap();
    Map centenas = new HashMap();


    public ConvertNumberToLetters(){

        unidades.put("1", "uno");
        unidades.put("2", "dos");
        unidades.put("3", "tres");
        unidades.put("4", "cuatro");
        unidades.put("5", "cinco");
        unidades.put("6", "seis");
        unidades.put("7", "siete");
        unidades.put("8", "ocho");
        unidades.put("9", "nueve");


        decenas.put("10", "diez");
        decenas.put("11", "once");
        decenas.put("12", "doce");
        decenas.put("13", "trece");
        decenas.put("14", "catorce");
        decenas.put("15", "quince");
        decenas.put("16", "dieciseis");
        decenas.put("17", "diecisite");
        decenas.put("18", "dieciocho");
        decenas.put("19", "diecinueve");
        decenas.put("2", "veinti");
        decenas.put("20", "veinte");
        decenas.put("3", "treinta");
        decenas.put("4", "cuarenta");
        decenas.put("5", "cincuenta");
        decenas.put("6", "sesenta");
        decenas.put("7", "setenta");
        decenas.put("8", "ochenta");
        decenas.put("9", "noventa");

        centenas.put("100", "cien");
        centenas.put("1", "ciento ");
        centenas.put("2", "doscientos ");
        centenas.put("3", "trescientos ");
        centenas.put("4", "cuatrocientos ");
        centenas.put("5", "quinientos ");
        centenas.put("6", "seiscientos ");
        centenas.put("7", "setecientos ");
        centenas.put("8", "ochecientos ");
        centenas.put("9", "novecientos ");

    }

    private boolean isDigit(char ch){
        return (ch >= '0') && (ch <= '9');
    }

    private boolean isAValidNumber(String userInput){
        int hasADot = -1;
        int nDigits = 0;

        for(int n = 0; n < userInput.length(); n++){
            char token = userInput.charAt(n);
            if(token == '.'){
                if((hasADot != -1) || n >= userInput.length()-1){
                    return false;
                }
                hasADot = n;
            }else if(token == '-'){
                return false;
            }else if(!isDigit(token)){
                return false;
            }
            else{
                nDigits++;
            }
        }
        return true;
    }

    private void getItem(Map mapa, String token){
        for(Object o : mapa.entrySet()){
            Map.Entry entry = (Map.Entry)o;
            var key = entry.getKey().toString();
            if(key.equals(token)){
                letras.append(entry.getValue().toString());
                break;
            }
        }
    }

    private String getUnidad(){
        token.append(number.charAt(number.length()-1));
        getItem(unidades, token.toString());
        return letras.toString();
    }

    private String getDecena(){
        token.append(number.charAt(UNIDAD - 1));
        if("1".contentEquals(token)){
            getItem(decenas, number);
            return letras.toString();
        }

        if("2".contentEquals(token)){
            token.append(number.charAt(number.length() -1));
            if("20".contentEquals(token)){
                getItem(decenas, token.toString());
            }else{
                getItem(decenas, String.valueOf(token.charAt(UNIDAD-1)));
                getItem(unidades, String.valueOf(token.charAt(number.length() - 1)));
            }
            return letras.toString();
        }

        token.append(number.charAt(number.length()-1));
        if("0".equals(String.valueOf(token.charAt(token.length()-1)))){
            getItem(decenas, String.valueOf(token.charAt(UNIDAD - 1)));
            return letras.toString();
        }else{
            getItem(decenas, String.valueOf(token.charAt(UNIDAD - 1)));
            letras.append(" y ");
            getItem(unidades, String.valueOf(token.charAt(number.length() - 1)));
        }

        return letras.toString();
    }

    private String getCentena(){
        token.append(number);
        if("100".contentEquals(token)){
            getItem(centenas, token.toString());
            return letras.toString();
        }

        String pos = token.substring(0,2);
        if(pos.contains("0")){
            getItem(centenas, String.valueOf(token.charAt(UNIDAD - 1)));
            getItem(unidades, String.valueOf(token.charAt(token.length() - 1)));
            return letras.toString();
        }

        getItem(centenas, String.valueOf(token.charAt(UNIDAD - 1)));
        token.setLength(0);
        number = number.substring(UNIDAD);
        getDecena();
        
        return letras.toString();
    }

    //1000
    /*
     * 1005
     *      1000 -> Millar
     *       000 -> Centena
     *        00 -> Decena
     *         5 -> Unidad
     *
     * 2020
     *      2000 -> Millar
     *       000 -> Centena
     *        20 -> Decena
     *         0 -> Unidad
     *
     * 3033
     *      3000 -> Millar
     *       000 -> Centena
     *        30 -> Decena
     *         3 -> Unidad
     *
     * 6549
     *      6000 -> Millar
     *       500 -> Centena
     *        40 -> Decena
     *         9 -> Unidad
     */
    private String getUnidadMillar(){
        String tmp = number;

        token.append(tmp);
        if("1000".contentEquals(token)){
            letras.append("mil");
            return letras.toString();
        }

        // millares
        token.setLength(0);
        token.append(number.charAt(UNIDAD - 1));
        number = String.valueOf(tmp.charAt(UNIDAD - 1));
        if("1".contentEquals(token)){
            letras.append("mil ");
        }else{
            token.setLength(0);
            getUnidad();
            letras.append( " mil ");
        }

        token.setLength(0);
        token.append(tmp.charAt(UNIDAD));
        if("0".contentEquals(token)){
            token.setLength(0);
            token.append(tmp.charAt(DECENA));
            if("0".contentEquals(token)){
                token.setLength(0);
                number = String.valueOf(tmp.charAt(tmp.length()-1));
                getUnidad();
            }else{
                token.setLength(0);
                number = tmp.substring(DECENA);
                getDecena();
            }
        }else{
            token.setLength(0);
            number = tmp.substring(UNIDAD);
            getCentena();
        }

        return letras.toString();
    }

    private String getDecenaMillar(){
        String tmp = number;
        token.setLength(0);
        number = tmp.substring(UNIDAD-1, DECENA);
        getDecena();
        letras.append(" mil ");

        token.setLength(0);
        token.append(tmp.charAt(DECENA));
        if("0".contentEquals(token)){
            token.setLength(0);
            token.append(tmp.charAt(CENTENA));
            if("0".contentEquals(token)){
                token.setLength(0);
                number = String.valueOf(tmp.charAt(tmp.length()-1));
                getUnidad();
            }else{
                token.setLength(0);
                number = tmp.substring(CENTENA);
                getDecena();
            }
        }
        else{
            token.setLength(0);
            number = tmp.substring(DECENA);
            getCentena();
        }
        return letras.toString();
    }

    //108000
    private String getCentenaMillar(){
        String tmp = number;

        number = tmp.substring(UNIDAD, CENTENA);
        if("00".contentEquals(number)){
            number = tmp.substring(0, CENTENA);
            getCentena();
            letras.append(" mil ");
        }

        number = tmp.substring(0, CENTENA);
        getCentena();
        letras.append(" mil ");

        token.setLength(0);
        number = tmp.substring(CENTENA);
        getCentena();

        return letras.toString();
    }

    private void convertNumberToLetters(){
        switch(size){
            case UNIDAD:
                System.out.println(getUnidad());
                break;
            case DECENA:
                System.out.println(getDecena());
                break;
            case CENTENA:
                System.out.println(getCentena());
                break;
            case UNIDAD_DE_MILLAR:
                System.out.println(getUnidadMillar());
                break;
            case DECENA_DE_MILLAR:
                System.out.println(getDecenaMillar());
                break;
            case CENTENA_DE_MILLAR:
                System.out.println(getCentenaMillar());
                break;
        }
    }

    private void getUserInput(){
        boolean rpta;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un numero");
        String input = scanner.nextLine();
        rpta = isAValidNumber(input);
        if(!rpta){
            System.err.println("--> Numero invalido. ");
        }
        this.number = input;
        this.size = this.number.length();

        convertNumberToLetters();
    }

    public static void main(String[] args) {
        ConvertNumberToLetters convertNumberToLetters = new ConvertNumberToLetters();
        convertNumberToLetters.getUserInput();
    }
}
