package com.ceva.core1.ch08.generics;

import java.time.LocalDate;

public class B_PairTest {
    public static void main(String[] args)
    {
        LocalDate[] birthdays =
                {
                        LocalDate.of(1906, 12, 9), // G. Hopper
                        LocalDate.of(1815, 12, 10), // A. Lovelace
                        LocalDate.of(1903, 12, 3), // J. von Neumann
                        LocalDate.of(1910, 6, 22), // K. Zuse
                };
        B_Pair<LocalDate> mm = C_ArrayAlg.<LocalDate>minmax(birthdays);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}
