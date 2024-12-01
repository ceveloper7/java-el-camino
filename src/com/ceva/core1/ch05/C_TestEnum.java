package com.ceva.core1.ch05;

import java.util.Scanner;

public class C_TestEnum {
    private final Size size = Enum.valueOf(Size.class, "EXTRA_LARGE");
    // Tambien valido size = Size.EXTRA_LARGE;


    public static void main(String[] args) {
        C_TestEnum enums = new C_TestEnum();
        enums.userInputEnum();
    }

    private void userInputEnum(){
        Scanner input = new Scanner(System.in);
        System.out.print("Ënter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE, EXTRA_EXTRA_LARGE): ");
        String in = input.next().toUpperCase();

        Size size = Enum.valueOf(Size.class, in);
        System.out.println("Size: " + size);
        System.out.println("Abbreviation: " + size.getAbbreviation());
        if(size==Size.SMALL){
            System.out.println("Good, is easy for you get cloths");
        }else if(size==Size.MEDIUM){
            System.out.println("Good shape!");
        }else if(size==Size.LARGE){
            System.out.println("cmon... do exercises");
        } else if (size==Size.EXTRA_LARGE) {
            System.out.println("well, stop sugar and bread");
        }else{
            System.out.println("Ok, think in your health");
        }
    }

    private void getOrdinalPosition(){
        int pos = switch (size){
            case SMALL -> Size.SMALL.ordinal();
            case MEDIUM -> Size.MEDIUM.ordinal();
            case LARGE -> Size.LARGE.ordinal();
            case EXTRA_LARGE -> Size.EXTRA_LARGE.ordinal();
            case EXTRA_EXTRA_LARGE -> Size.EXTRA_EXTRA_LARGE.ordinal();
            default -> -1;
        };
        System.out.println(pos);
    }

    private void caseSwitch(){

        int val = switch (size){
            case SMALL -> 1;
            case MEDIUM -> 2;
            case LARGE -> 3;
            case EXTRA_LARGE -> 4;
            case EXTRA_EXTRA_LARGE -> 5;
            default -> -1;
        };

        System.out.println(val);
    }

    private void printEnumValues(){
        Size[] sizes = Size.values();
        for(Size s : sizes){
            System.out.println(s);
        }
    }
}
