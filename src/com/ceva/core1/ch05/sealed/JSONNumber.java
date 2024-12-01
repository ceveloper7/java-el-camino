package com.ceva.core1.ch05.sealed;

final record JSONNumber(double value) implements JSONPrimitive{
    public String toString(){
        return "" + value;
    }
}
