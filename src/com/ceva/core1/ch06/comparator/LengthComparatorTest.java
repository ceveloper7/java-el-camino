package com.ceva.core1.ch06.comparator;

import java.util.Arrays;

public class LengthComparatorTest {
    public static void main(String[] args) {
        var names = new String[3];
        names[0] = "Josefina";
        names[1] = "Antonia";
        names[2] = "Manu";


        Arrays.sort(names, new LengthComparator());

        for(String name : names){
            System.out.println(name);
        }
    }
}
