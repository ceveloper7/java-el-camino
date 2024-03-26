package com.ceva.section4.apijava1;

import java.util.Calendar;
import java.util.Date;

public class JDates {

    // return objeto Calendar con la fecha y hora del sistema
    private Calendar cal = Calendar.getInstance();
    private void printDateInstance(){
        System.out.println(cal.getTime());
    }

    private void addOneDayAndHour(){
        cal.add(Calendar.DATE, 1); // agregamos 1 dia a la fecha
        cal.add(Calendar.HOUR, 1); // agregamos una hora a la fecha
        System.out.println(cal.getTime());
    }

    private void readComponentsDate(){
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        System.out.println("Year: " + year + " Month: " + month + " Day: " + day);
    }

    private void compareDates(){
        java.util.Date date1 = new java.util.Date();
        cal.add(Calendar.DATE, -1);
        java.util.Date date2 = cal.getTime();

        int cmp = date1.compareTo(date2);
        if(cmp > 0){
            System.out.println("Date1 " + date1.getTime() + " es mayor que Date2 " + date2.getTime());
        }else if(cmp < 0){
            System.out.println("Date1 es menor qye Date2");
        }else{
            System.out.println("Date1 y Date2 son iguales.");
        }
    }

    public static void main(String[] args) {
        JDates dates = new JDates();
        dates.printDateInstance();
        dates.readComponentsDate();
        dates.compareDates();
        dates.addOneDayAndHour();
    }
}
