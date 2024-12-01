package com.ceva.core1.ch05.sealed;

enum JSONBoolean implements JSONPrimitive {
    FALSE,
    TRUE;

    public String toString(){
        return super.toString().toLowerCase();
    }
}
