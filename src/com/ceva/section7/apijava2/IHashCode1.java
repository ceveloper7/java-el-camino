package com.ceva.section7.apijava2;

/**
 * hashcode retorna una firma unica para cada objeto de manera que dos instancias de una clase pueden ser comparables utilizando su hash code
 */
public class IHashCode1 {
    private static int stringHashCode(String s) {
        int result = 0;
        for (int n=0; n<s.length(); n++) {
            result = result + s.charAt(n);
        }
        return result;
        /*
        int result = 1;
        for (int n=0; n<s.length(); n++) {
            result = result * (result+s.charAt(n));
        }
        return result;
        */
    }

    public static void main(String[] args) {
        System.out.println("Hola = " + stringHashCode("Hola"));
        System.out.println("aloH = " + stringHashCode("aloH"));
    }
}
