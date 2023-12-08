package com.ceva.section18.javabricks.dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que maneja toda las funcionalidades de las animaciones
 */
public class DrawingPanel extends JPanel implements Runnable, IAnimationLoopController{
    private final List<Runnable> callbackList = new LinkedList<>();
    private List<IAnimationLoops> animations = new LinkedList<>();
    private Thread thread;
    private boolean done;
    private long startTime;
    // 60 cuadros por segundo
    private final static int FPS = 50;
    private long delayMillis;

    // iniciamos un hilo para la animacion
    protected void startAnimationThread(){
        if(thread == null){
            thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public void removeAnimationLoop(IAnimationLoops loop) {

        invokeLater(() -> {
            animations.remove(loop);
            KeyListener k = loop.getKeyListener();
            if(k != null){
                removeKeyListener(k);
            }
        });
    }

    @Override
    public void delay(long millis) {
        delayMillis = millis;
    }

    @Override
    public void addAnimationLopp(IAnimationLoops loop) {

        invokeLater(() -> {
            animations.add(loop);
            KeyListener k = loop.getKeyListener();
            if(k != null){
                addKeyListener(k);
            }
        });
    }

    @Override
    public void invokeLater(Runnable callack) {
        // agregamos un runnable a la coleccion
        callbackList.add(callack);
    }

    @Override
    public void run() {
        done = false;
        // ciclo que funciona hasta que done igual true
        while(!done){
            // obtenemos la hora actual
            startTime = System.currentTimeMillis();
            Rectangle bounds = getBounds();

            // bloque donde sincronizamos el thread con el edt de swing
            synchronized (this){
                // validamos si callbackList contiene elementos
                if(callbackList.size() > 0){
                    for(Runnable callback : callbackList){
                        // invocamos al metodo run de todos los elementos
                        callback.run();
                    }
                    callbackList.clear();
                }
                // para cada elemento de la coleccion animations invocamos el metodo nextFrame()
                for(IAnimationLoops loop : animations){
                    loop.nextFrame(bounds);
                }
                // se hace el repaint cuando el edt de swing lo decida
                repaint();
                // detenemos el programa para que se haga el repaint
                try{
                    wait();
                }
                catch(InterruptedException e){

                }
            }
            waitForNextFrame();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        synchronized (this){
            // recorremos la lista de animaciones
            for(IAnimationLoops loop : animations){
                // invocamos el metodo paint() de cada elemento de la coleccion
                loop.paint(g2d);
            }
            // invocamos notify() para hacer que el thread principal vuelva a la vida
            notify();
        }
    }

    private void waitForNextFrame(){
        // caso normal: delayMillis == 0
        if(delayMillis == 0){
            long time = System.currentTimeMillis();
            long timeEllapsed = time - startTime;
            // si el tiempo transcurrido es menor al numero de milisegundos que representa FPS
            // 1000/60 es igual a 16.667 milisegundos, que es el tiempo de ejecucion para cada iteracion
            // si el ultimo cuadro tardo menos de 16 milisegundos, hacemos una pausa
            if(timeEllapsed < (100/FPS)){
                // hacemos una pausa con Thread.sleep()
                // asi, cada iteracion ocurre en el tiempo correcto
                try{
                    Thread.sleep((1000/FPS) - timeEllapsed);
                }
                catch(InterruptedException e){

                }
                delayMillis = 0;
            }
        }
    }
}
