package com.ceva.section24.enums;

public class DiasSemanaDemo {
    public static void main(String[] args) {
        DiasSemana hoy = DiasSemana.JUEVES;
        System.out.println("Hoy es el dia " + hoy.dayName);

        // obtenemos todos los valores del enum
        for(DiasSemana ds : DiasSemana.values()){
            System.out.println(ds);
        }

        // pasamos el nombre del simbolo y obtenemos el enum correspondiente
        System.out.println(DiasSemana.valueOf("MARTES").dayName);

        // Dado un enum con ordinr() sabemos en que posicion esta declarado
        System.out.println(DiasSemana.VIERNES.ordinal());
    }
}
