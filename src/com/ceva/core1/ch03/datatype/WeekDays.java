package com.ceva.core1.ch03.datatype;

public enum WeekDays {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public String toString(){
        // get string representation of each constant
        String s = super.toString();
        // retornamos la primera letra en minuscula
        return s.charAt(0) + s.substring(1).toLowerCase();
    }
}
