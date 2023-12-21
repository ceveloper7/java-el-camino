package com.ceva.section24.enums;

public enum DiasSemana {
    DOMINGO("Domingo"),
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miercoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sabado");

    String dayName;

    DiasSemana(String dayName){
        this.dayName = dayName;
    }
}
