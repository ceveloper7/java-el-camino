package com.ceva.core1.ch03.datatype;

public enum Gender {
    FEMALE(1, "f"),
    MALE(2, "m"),
    UNSPECIFIED(3, "u"){
        @Override
        public String comment() {
            return "to be decided later: " + getRepr() + ", " + getDescr();
        }
    };

    /**
     * Enums pueden tener campos y constructor
     * es una buena practica declarar los atributos como final
     */
    private final int repr;
    private final String descr;

    // el constructor solo puede ser privado, ya que una instancia de enum no puede ser creada externamente
    private Gender(int repr, String descr){
        this.repr =  repr;
        this.descr = descr;
    }

    public int getRepr() {
        return repr;
    }

    public String getDescr() {
        return descr;
    }

    public String comment(){
        return repr + ": " + descr;
    }
}
