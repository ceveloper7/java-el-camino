package com.ceva.core1.ch05.sealed;

enum JSONNull implements JSONPrimitive {
    INSTANCE;

    public String toString(){
        return "null";
    }
}
