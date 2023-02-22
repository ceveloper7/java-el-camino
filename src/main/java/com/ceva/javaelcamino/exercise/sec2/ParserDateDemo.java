/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.exercise.sec2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import jdk.nashorn.api.tree.ContinueTree;

/**
 *
 * @author PC
 */
public class ParserDateDemo {
    private int day;
    private int month;
    private int year;
    private String format;
    
    public ParserDateDemo(){}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    
    
    public Date parseDate(String date, String formatDate){
        if(formatDate.length() > 0){
            setFormat(formatDate);
        }else{
            return null;
        }
        
        int indexDate=0;
        for(int indexFrom = 0; indexFrom < format.length(); indexFrom++){
            char formatDateCharacter = format.charAt(indexFrom); // d
            if((formatDateCharacter == 'd') || (formatDateCharacter == 'm')){
                int indexTo = indexFrom + 1; // 1
                if((indexTo < format.length()) && (format.charAt(indexTo) == indexFrom)){
                    indexTo++; // 2
                }
                int partDate = Integer.parseInt(date.substring(indexFrom, indexTo)); // 17
                switch(formatDateCharacter){
                    case 'd':
                        day = partDate;
                        break;
                    case 'm':
                        month = partDate;
                        break;
                }                
                // nos saltamos los caracteres que ya interpretamos del formato
                indexFrom = indexTo - 1; // 1
            }else{
                if(date.charAt(indexDate) != formatDateCharacter){
                    return null;
                }
            }
            indexDate++;
        }
        // validaciones //
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month-1);
        
        return calendar.getTime();
    }
    
    public static void main(String[] args) {
        ParserDateDemo p = new ParserDateDemo();
        System.out.println("date " + p.parseDate("17/10", "dd/mm"));
    }
}
