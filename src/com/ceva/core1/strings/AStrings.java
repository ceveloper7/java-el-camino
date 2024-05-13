package com.ceva.core1.strings;

public class AStrings {
    private void simpleStr(){
        String e = "";
        String saludo = "Hello world";
        System.out.println("empty string " + e);
        System.out.println("greeting " + saludo);
    }

    private void applySubStr(){
        String greeting = "Hello";
        String subStr = greeting.substring(0, 3);
        String subStr1 = greeting.substring(2, greeting.length());
        System.out.println("-- content -- " + greeting);
        System.out.println("-- substring(0,3) -- " + subStr);
        System.out.println("-- substring(2, greetin.length()) -- " + subStr1);
    }

    private void StrConcat(){
        String txt1 = "Java";
        String txt2 = "Rocks";
        System.out.println("-- concat string -- " + txt1 + " " + txt2);
    }

    private void repeatStr(){
        String txt = "Love";
        System.out.println(txt.repeat(3));
    }
    public static void main(String[] args) {
        AStrings strings = new AStrings();
        strings.repeatStr();
        //strings.StrConcat();
        //strings.applySubStr();
        //strings.simpleStr();
    }
}
