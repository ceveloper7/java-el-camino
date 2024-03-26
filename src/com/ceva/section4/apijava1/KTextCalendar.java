package com.ceva.section4.apijava1;

import java.time.LocalDate;

public class KTextCalendar {
    LocalDate local = LocalDate.now();
    private static final String DIV_LINE = "+-----+-----+-----+-----+-----+-----+-----+-----+";
    private String monthTitle = "";
    private static final String[] MONTH_NAMES = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    private void setCalendarTitle(){
        monthTitle = MONTH_NAMES[local.getMonthValue()-1] + " " + local.getYear();
    }

    static KTextCalendar cal = new KTextCalendar();

    /**
     * centrar titulo del calendario:
     * posicionar en la mitad del ancho del calendario - la midad del titulos
     * +-----+-----+-----+-----+-----+-----+-----+-----+
     * |              Octubre 2019                     |
     * +-----+-----+-----+-----+-----+-----+-----+-----+
     */
    private  void printHeadCalendar(){
        cal.setCalendarTitle();
        System.out.println("+-----------------------------------------------+");
        System.out.print("|");
        int position = 1;
        // ciclo para posicionar el cursor el titulo del calendario
        while(position < (DIV_LINE.length()/2) - monthTitle.length()/2){
            System.out.print(" ");
            position++;
        }
        System.out.print(monthTitle);
        position = position + monthTitle.length();
        while(position < (DIV_LINE.length()-1)){
            System.out.print(" ");
            position++;
        }
        System.out.println("|");
    }

    private void printHeadDayOfWeek(){
        System.out.println(DIV_LINE);
        System.out.println("| Dom | Lun | Mar | Mie | Jue | Vie | Sab | Dom |");
        System.out.println(DIV_LINE);

    }

    private void printCalendarContent(){

        System.out.println(DIV_LINE);
    }

    private void printCalendar(){
        printHeadCalendar();
        printHeadDayOfWeek();
        printCalendarContent();
    }
    public static void main(String[] args) {
        cal.printCalendar();
    }
}
