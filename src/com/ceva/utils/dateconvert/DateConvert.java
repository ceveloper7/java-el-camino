package com.ceva.utils.dateconvert;



public class DateConvert {
    private final char SEPARATOR = '/';
    private String[] mapa = null;

    public DateConvert() {
        mapa = new String[6];
        mapa[0] = "dd";
        mapa[1] = "mm";
        mapa[2] = "yy";
        mapa[3] = "hh";
        mapa[4] = "MM";
        mapa[5] = "ss";
    }

    private void formatAnalyzer(String date, String formatDate) {
        if((date.isEmpty()) || (formatDate.isEmpty()))
            System.exit(0);
        int fmtSize = formatDate.length();
        int endIdx = -1;
        int count = 0;
        while (++endIdx < fmtSize){
            char letter = formatDate.charAt(endIdx);
            switch (letter){
                case 'd':
                    for(int l = 0; l < mapa[endIdx].length(); l++){
                        if(mapa[endIdx].contains(String.valueOf(letter))){
                            count++;
                        }
                    }
                    System.out.println("numero de d " + count);
                    count = 0;
                    break;
                case 'm':
                    for(int l = 0; l < mapa[l].length(); l++){
                        if(mapa[l].contains(String.valueOf(letter))){
                            count++;
                        }
                    }
                    System.out.println("numero de m " + count);
                    count = 0;
                    break;
                case 'y':
                    for(int l = 0; l < mapa[l].length(); l++){
                        if(mapa[l].contains(String.valueOf(letter))){
                            count++;
                        }
                    }
                    System.out.println("numero de y " + count);
                    count = 0;
                    break;
            }

        }
    }

    public static void main(String[] args) {
        DateConvert dt =  new DateConvert();
        dt.formatAnalyzer("06/03/2024", "mm/dd/yyyy");
    }
}
