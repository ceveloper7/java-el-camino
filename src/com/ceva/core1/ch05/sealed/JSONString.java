package com.ceva.core1.ch05.sealed;

final record JSONString(String value) implements JSONPrimitive {
    public String toString(){
        return "\"" + value.translateEscapes();
    }
}
