package com.ceva.radio;

public enum StudyType {
    SCTE("Sin CTE"),
    CCTE("Con CTE"),
    ARTERIAL("Arterial"),
    VENOSO("Venoso"),
    TARDIO("Tardio");

    private String name;

    StudyType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
