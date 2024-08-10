package com.ceva.section5.poo.objects;

public class ETimeTest {
    public static void main(String[] args) {
        ETime t1 = new ETime(); // 00:00:00
        ETime t2 = new ETime(2); // 02:00:00
        ETime t3 = new ETime(21, 34); // 21:34:00
        ETime t4 = new ETime(12, 25, 42); // 12:25:42
        ETime t5 = new ETime(t4); // 12:25:42

        System.out.println("Constructed with:");
        displayTime("t1: all default arguments", t1);
        displayTime("t2: hour specified; default minute and second", t2);
        displayTime("t3: hour and minute specified; default second", t3);
        displayTime("t4: hour, minute and second specified", t4);
        displayTime("t5: Time2 object t4 specified", t5);

        // attempt to initialize t6 with invalid values
        try {
            ETime t6 = new ETime(27, 74, 99); // invalid values
        }
        catch (IllegalArgumentException e) {
            System.out.printf("%nException while initializing t6: %s%n",
                    e.getMessage());
        }
    }

    // displays a Time2 object in 24-hour and 12-hour formats
    private static void displayTime(String header, ETime t) {
        System.out.printf("%s%n   %s%n   %s%n",
                header, t.toUniversalString(), t.toString());
    }
}
