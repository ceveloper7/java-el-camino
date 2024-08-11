package com.ceva.section5.poo.objects;

import java.util.EnumSet;

// 8.11
public class IEnumBookTest {
    public static void main(String[] args) {
        System.out.println("All books:");

        // print all books in enum Book
        for (IEnumBook book : IEnumBook.values()) {
            System.out.printf("%-10s%-45s%s%n", book,
                    book.getTitle(), book.getCopyrightYear());
        }

        System.out.printf("%nDisplay a range of enum constants:%n");

        // print first four books
        for (IEnumBook book : EnumSet.range(IEnumBook.JHTP, IEnumBook.CPPHTP)) {
            System.out.printf("%-10s%-45s%s%n", book,
                    book.getTitle(), book.getCopyrightYear());
        }
    }
}
