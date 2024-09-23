package com.ceva.execises.ch01;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateParser {

    String dayf, monthf, yearf;
    char separator;

    private int getDateSeparator(String date) {
        int firstDateSeparatorPosition = date.indexOf("/");
        if(firstDateSeparatorPosition == -1) {
            firstDateSeparatorPosition = date.indexOf("-");
            if(firstDateSeparatorPosition == -1) {
                firstDateSeparatorPosition = date.indexOf(".");
                if(firstDateSeparatorPosition == -1) {
                    throw new IllegalArgumentException("Illegal separator date format. the separators allowed are: /, - or .");
                }else{
                    separator = '.';
                }
            }else{
                separator = '-';
            }
        }else{
            separator = '/';
        }
        return firstDateSeparatorPosition;
    }

    private Date getUserDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    private Date validateDate(String date) {
        int day, month, year;
        if(date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date is empty");
        }

        int firstDateSeparatorPosition = getDateSeparator(date);

        String tmp = date.substring(0, firstDateSeparatorPosition);
        day = Integer.parseInt(tmp);
        if(day < 1 || day > 31) {
            throw new IllegalArgumentException("Error: date out of range.");
        }

        // buscamos la posicion del siguiente separador en la fecha
        int secondDateSeparatorPosition = date.indexOf(separator, firstDateSeparatorPosition + 1);
        if(secondDateSeparatorPosition == -1) {
            throw new IllegalArgumentException("Error: Invalid date");
        }
        tmp = date.substring(firstDateSeparatorPosition+1, secondDateSeparatorPosition);
        month = Integer.parseInt(tmp);
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Error: Month range from 1 to 12.");
        }

        //obtenemos year
        tmp = date.substring(secondDateSeparatorPosition + 1);
        year = Integer.parseInt(tmp);
        if(year < 1970 || year > 2099) {
            throw new IllegalArgumentException("Error: date out of range. Year range accept from 1970 to 2099.");
        }

        return getUserDate(day, month, year);

    }

    private void validateFormat(String format){
        if(format == null || format.isEmpty()) {
            throw new IllegalArgumentException(" Target format date parameter is empty");
        }
        int firstDateSeparatorPosition = getDateSeparator(format);

        String tmp = format.substring(0, firstDateSeparatorPosition);

    }

    public static void main(String[] args) {
        DateParser dateParser = new DateParser();
        Scanner input = new Scanner(System.in);
        boolean continueLoop = true;
        do{
            try{
                System.out.print("Enter date: ");
                String userDate = input.nextLine();
                Date date = dateParser.validateDate(userDate);
                System.out.print("Enter target date format: ");
                String targetFormat = input.nextLine();
                dateParser.validateFormat(targetFormat);
                //System.out.printf("%s %tB %<te, %<tY", "User date:", date);
                continueLoop = false;
            }
            catch (IllegalArgumentException ex){
                System.err.printf("%nException: %s%n", ex.getClass());
                System.err.printf("%s%n", ex.getMessage());
                System.out.printf("You must enter a valid date or format. Please try again.%n%n");
            }
        }
        while (continueLoop);
    }
}
