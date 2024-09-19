package com.ceva.buildingblocks.operators2;

/*
 * Operadores Logicos
 */
public class Lesson6 {

    private void logicalOperators(){
        boolean eyesClose = true;
        boolean breathingSlowly = true;

        // logical: | & ^
        boolean resting = eyesClose | breathingSlowly; // true
        boolean asleep = eyesClose & breathingSlowly; // true
        boolean awake = eyesClose ^ breathingSlowly; // false

    }

    private void conditionalOperators(){

        // conditional: && ||
    }

    public static void main(String[] args) {
        Lesson6 lesson6 = new Lesson6();
        lesson6.logicalOperators();
    }
}
