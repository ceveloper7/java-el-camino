package com.ceva.core1.ch03.datatype;

public class EnumTest {
    public static void main(String[] args) {
        for(Gender g : Gender.values()){
            System.out.println(g.name() + " : " + g.getClass());
            System.out.println("\tcomment: " + g.comment());
        }

        for(WeekDays w : WeekDays.values()){
            System.out.printf("%s ", w);
        }

        for (AppleTypes apple : AppleTypes.values()){
            System.out.println(apple + " costs" + apple.getPrice() + " cents");
        }
    }
}
