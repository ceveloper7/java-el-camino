package com.ceva.core1.ch03.datatype;

public enum AppleTypes {

    AURORA(10),
    BELMAC(12),
    CORTLAND(15),
    EMPIRE(8),
    GRAVENSTEIN(11);

    private int price;

    AppleTypes(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
