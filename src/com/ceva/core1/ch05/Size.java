package com.ceva.core1.ch05;

public enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL"),
    EXTRA_EXTRA_LARGE("XXL"){
        // se puede sobreescribir metodos
        public String toString(){
            return "LXX";
        }
    };

    // Agregamos un campo al enum
    private final String abbreviation;

    // agregamos un constructor. El constructor de un enum siempre es private, se puede omitir el modificador
    Size(String abbreviation){
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation(){
        return abbreviation;
    }
}
