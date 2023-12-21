package com.ceva.section24.enums;

public enum Resolution {
    LOW(1024),
    MID(4096),
    HIGH(16384);

    private int maxPolygons;

    public int getMaxPolygons(){
        return maxPolygons;
    }

    Resolution(int maxPolygons){
        this.maxPolygons = maxPolygons;
    }

}
