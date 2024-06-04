package com.ceva.section5.poo;

public class PByReference {
    private int field;

    private static void byRef1(){
        PByReference a = new PByReference();
        a.field = 100;
        PByReference b = a;
        b.field++;
        System.out.println("El valor es " + a.field);
    }

    private static void byRef2(PByReference ref){
        ref.field++;
    }

    public static void main(String[] args) {
        byRef1();// 101
        PByReference a = new PByReference();
        a.field = 100;
        byRef2(a);
        System.out.println("El valor es " + a.field);
    }
}
