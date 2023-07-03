package com.ceva.section18.javabricks.dev;

/**
 * Representa la coleccion de animaciones y tiene los metodos necesarios para
 * controlarlas.
 */
public interface IAnimationLoopController {
    // introduce un pausa a todas las animaciones por el #de mili segundos deseados
    public void delay(long millis);
    // nos permite agregar una animacion, pasando la instancia que deseamos animar
    public void addAnimationLopp(IAnimationLoops loop);
    // quitamos una animacion de la lista de animaciones
    public void removeAnimationLoop(IAnimationLoops loop);
    // nos permite ejecutar codigo en el momento apropiado qe no afecte al motor de animacion
    // si dentro de una animacion se agrega una nueva, esto genera un conflicto con la coleccion
    // de animaciones xq no se puede modificar una animacion mientras haya codigo que lo
    // esta recorriendo
    public void invokeLater(Runnable callack);
}
