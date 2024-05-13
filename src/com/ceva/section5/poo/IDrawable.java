package com.ceva.section5.poo;

public interface IDrawable {
    void draw();

    // con default hacemos que las clases que implementan IDrawable ahora implicitamente
    // implementen en metodo extraMethod()
    default void extraMethod(){
        // todas las clases que implementan IDrawable cuentan con un nuevo metodo
    }
}
