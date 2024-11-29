package com.ceva.algorithms.fundaments;

public class Test {
    public static void main(String[] args) {
        int rpta = maximoComunDivisor(37, 2);
        System.out.println(rpta);
    }

    private static int maximoComunDivisor(int p, int q){
        if(q == 0) return p;
        int r = p % q;
        return maximoComunDivisor(q, r);
    }
}
