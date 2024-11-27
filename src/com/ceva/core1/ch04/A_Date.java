package com.ceva.core1.ch04;

import java.time.LocalDate;
import java.util.Date;

public class A_Date {
    public static void main(String[] args) {
        A_Date dates = new A_Date();
        dates.dateBasics();
    }

    private void dateBasics(){
        // 1. Se construye un objeto Date con la fecha actual.
        // La instancia de una clase Date tiene un estado, que es un punto particular en el tiempo.
        System.out.println(new Date());

        // 2. Representacion String del objeto Date
        String currentDate = new Date().toString();
        System.out.println(currentDate);

        // 3. Variable now hace referencia a un objeto Date; Las variable now y startTime no son objetos
        // la variable now tiene la referencia en memoria al objeto Date creado
        Date now = new Date();

        // start time copia de la variable now la referencia de memoria que apunta al objeto Date creado
        // en este punto now y startTime hacen referencia al mismo objeto creado en memoria.
        Date startTime = now;

        System.out.println("Now: " + now.toString());
        System.out.println("Start time: " + startTime.toString());

        // LocaleDate permite expresar dias en una forma familiar a los calendarios.
        LocalDate rightNow = LocalDate.now();
        // 2. Construir un objeto LocaleDate especifico
        LocalDate today = LocalDate.of(2024, 11, 26);

        // Podemos obtener year, month, day de un objeto LocaleDate
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        // Sumando dias a una fecha. plusDays() crea un nuevo objecto LocaleDate y lo asigna a nextMaintenance
        // el objeto original permanece sin cambios por lo que plusDays() no mutate el objeto donde es invocado
        LocalDate nextMaintenance = today.plusDays(14);
        int m = nextMaintenance.getMonthValue();
        int d = nextMaintenance.getDayOfMonth();
        System.out.println("Next maintenance month: " + m + ", day: " + d);
    }
}
