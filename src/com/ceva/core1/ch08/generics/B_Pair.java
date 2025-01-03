package com.ceva.core1.ch08.generics;

public class B_Pair<T> {
    private T first;
    private T second;

    public B_Pair() { first = null; second = null; }
    public B_Pair(T first, T second) { this.first = first;  this.second = second; }

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T newValue) { first = newValue; }
    public void setSecond(T newValue) { second = newValue; }
}
