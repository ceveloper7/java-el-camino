package com.ceva.section4.apijava1;

import java.time.LocalDate;

public class KTextCalendar {
    LocalDate localDate = LocalDate.now();
    private static final String DIV_LINE = "+-----+-----+-----+-----+-----+-----+-----+";
    private String monthTitle = "";
    private static final String[] MONTH_NAMES = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    private void setCalendarTitle(){
        monthTitle = MONTH_NAMES[localDate.getMonthValue()-1] + " " + localDate.getYear();
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
        System.out.println("+-----------------------------------------+");
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
        System.out.println("| Dom | Lun | Mar | Mie | Jue | Vie | Sab |");
        System.out.println(DIV_LINE);

    }

    private void printCalendarContent(){
        boolean pipe = false; // variable de control que indica si escribimos el pipe inicial ]
        int day = 0;

        // 0 equivale al primer dia del mes
        // calculamos primer dia del mes con localDate.minusDays(localDate.getDayOfMonth()-1)
        // con el primer dia del mes, obtenemos cual es ese dia primero del mes (lunes, martes, etc) con getDayOfWeek()
        // obtenemos el valor entero de ese dia con getValue()
        // con modulo % 7 hacemos que el rango sea de 0 (domingo) a 6 (sabado)
        // si el calculo retorn de 1 a 6, con la operacion modulo el resultado sera de 1 a 6
        // pero si el calculo retorna 7, con la operacion de modulo el resultado sera 0
        int dayStart = (localDate.minusDays(localDate.getDayOfMonth()-1).getDayOfWeek().getValue())%7;
        if(day < dayStart){
            System.out.print("|");
            while(day < dayStart){
                System.out.print("     |");
                day++;
            }
            pipe=true;
        }

        for(int n = 0; n < localDate.lengthOfMonth(); n++){
            if(day > 6){
                pipe = false;
                System.out.println();
                day = 0;
            }
            // si no pipe inicial lo imprimimos
            if(!pipe){
                System.out.print("|"); // dibujamos el pipe de inicio de linea
                pipe = true;
            }
            if((n+1) == localDate.getDayOfMonth()){
                System.out.printf(" [%2d]|", n+1);
            }else{
                System.out.printf("  %2d |", n+1);
            }
            day++;
        }

        // una vez que se imprime los 31 dias, imprimimos los casillero en blanco restantes
        // para completar el calendario
        while(day < 7){
            System.out.print("     |");
            day++;
        }

        System.out.println();

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
