package com.ceva.section8.generics;

public class CMutableObject<T> {
    private T value;

    public CMutableObject() {
    }

    public CMutableObject(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public static void copy(CMutableObject<? extends Number>m1, CMutableObject<? extends Number>m2) {}

    public static void main(String[] args) {

    }
}
