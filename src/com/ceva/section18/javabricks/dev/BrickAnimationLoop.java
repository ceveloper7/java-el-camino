package com.ceva.section18.javabricks.dev;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class BrickAnimationLoop implements IAnimationLoops{
    private static final int PAD_RIGHT = 1;
    private static final int PAD_LEFT = 2;

    private final KeyListener keyListener;
    private Color backgroundColor;
    private Ball ball;
    private Brick pad;
    private int lastPadDir = 0;
    private int padDir = 0;
    private int score = 0;
    private boolean paused = false;
    private IAnimationLoops gameOverLoop = null;
    private List<Brick> bricks = new LinkedList<Brick>();
    private IAnimationLoopController mainLoopController;
    private LivesIndicator livesIndicator;
    private boolean gameOver = false;
    private Rectangle screenSizeBounds;
    // numero de vidas
    private int nextLifeAt;
    // variables para cargar en memoria los archivos de sonidos
    private SoundClip soundToc; // cuando la pelota toca el pad
    private SoundClip[] soundTic; // cuando la pelota toca los bricks
    public BrickAnimationLoop(IAnimationLoopController mainLoopController, Rectangle screenSizeBounds, Color backgroundColor){
        this.mainLoopController = mainLoopController;
        this.backgroundColor = backgroundColor;
        this.screenSizeBounds = screenSizeBounds;

        // en el momento que el user presione enter, llamamos a restartGame()
        keyListener = initKeyListener();

        // carga de los sonidos en memoria
        soundToc = new SoundClip("toc.wav", "/com/ceva/section18/javabricks/dev/");
        soundTic = new SoundClip[3];
        for(int n = 0; n < soundTic.length; n++){
            soundTic[n] = new SoundClip("tic.wav", "/com/ceva/section18/javabricks/dev/");
        }

        // inicializamos variables y objetos para un nuevo juego
        restartGame(false); // false, evita que se muestre el mensaje de ready

        // BrickAnimationLoop se agrega a si mismo en la lista de animaciones
        mainLoopController.addAnimationLopp(this);
        // ponemos en estado inicial el juego
        setInitialState();

    }

    private void slowBonus(){
        int lastSpeed = pad.getPadSpeed(); // obtenemos velocidad actual
        int s = lastSpeed / 2; // reducir velocidad a la metidad
        if(s > 1){
            // velocidad a la mitad para el pad
            pad.setPadSpeed(pad.getPadSpeed() / 2 );
        }
        // slowBonus va a durar una cantidad finita de tiempo
        mainLoopController.addAnimationLopp(new DelayLoop(
                mainLoopController,
                5*60,
                ()->{
                    // cuando se cumplan los 5 segundos, volvemos a la velocidad normal
                    pad.setPadSpeed(lastSpeed);
                }
        ));
    }

    private void fastBonus(){
        int lastSpeed = pad.getPadSpeed(); // obtenemos velocidad actual
        int s = lastSpeed * 2; // reducir velocidad a la metidad
        if(s < 20){
            // velocidad a la mitad para el pad
            pad.setPadSpeed(pad.getPadSpeed() / 2 );
        }
        // slowBonus va a durar una cantidad finita de tiempo
        mainLoopController.addAnimationLopp(new DelayLoop(
                mainLoopController,
                5*60,
                ()->{
                    // cuando se cumplan los 5 segundos, volvemos a la velocidad normal
                    pad.setPadSpeed(lastSpeed);
                }
        ));
    }

    private void bonusExtraLife() {
        if(livesIndicator.getLives() < Config.MAXLIVES){
            livesIndicator.setLives(livesIndicator.getLives()+1);
        }
    }

    private void bonusWidePad() {
        pad.setWide();
        mainLoopController.addAnimationLopp(new DelayLoop(mainLoopController, 5*60, () -> {
            pad.setNormal();
        }));
    }

    private void bonusNarrowPad() {
        pad.setNarrow();
        mainLoopController.addAnimationLopp(new DelayLoop(mainLoopController, 5*60, () -> {
            pad.setNormal();
        }));
    }

    enum BonusType{
        BONUS_SLOW, // PAD LENTO
        BONUS_FAST, // PAD RAPIDO
        BONUS_EXTRALIFE, // VIDAS EXTRAS
        BONUS_WIDE, // PAD LARGO
        BONUS_NARROW // PAD PEQUENO
    }

    private void addRandomBonus(Map<Integer, BonusType> map,
                                int brickCount, int count, BonusType bt){
        int n = 0;
        // agregamos los premios aleatoriamente
        while((n < count) && (map.size() < brickCount)){
            int random = (int)(Math.random() * brickCount);
            if(!map.containsKey(random)){
                map.put(random, bt);
                n++;
            }
        }
    }

    /*
     * Metodo que crea el escenario con la coleccion de bricks
     */
    private void initBricks() {
        // limpiamos la coleccion
        bricks.clear();
        // definimos el escenario
        int rows = 9;
        int cols = 9;

        // bonus
        Map<Integer, BonusType> map = new HashMap<>();
        /**
         * map -> map a agregar
         * row*col -> # de bricks en total, de alli elegie el # aleatorio
         * 3 -> numero de premios
         * BonusType.BONUS_SLOW -> Tipo del premio
         */
        addRandomBonus(map, rows*cols, 3, BonusType.BONUS_SLOW);
        addRandomBonus(map, rows*cols, 3, BonusType.BONUS_FAST);
        addRandomBonus(map, rows*cols, 3, BonusType.BONUS_EXTRALIFE);
        addRandomBonus(map, rows*cols, 3, BonusType.BONUS_WIDE);
        addRandomBonus(map, rows*cols, 3, BonusType.BONUS_NARROW);

        int nBonus;

        Rectangle r = new Rectangle(12, 50, 50, 12);
        for(int j = 0; j < rows; j++){
            /*
             * la velocidad del brick dependent de si es un brick fast
             * o un brick normal
             */
            int hitSpeed = j < 3 ? 6 : 3;

            /*
             * determinamos el color dependiendo del renglon donde estamos
             * los primeros renglones de la coleccion de brick van a ser bolas rapidas
             * j < 3 ? preguntamos si estamos en el renglon 0, 1, 2
             */
            Color c = j < 3 ? Config.brickFastColor : Config.brickColor;

            // creamos los bricks del juego
            for(int i = 0; i < cols; i++){
                Brick b = new Brick(r.x, r.y, r.width, r.height, c, hitSpeed);
                // conocer el # del brick
                nBonus = (j*rows) + i;
                // validamos si el brick ya esta premiado
                if(map.containsKey(nBonus)){
                    Rectangle brickBounds = b.getBounds();
                    Color cb = null;
                    Runnable m = null;
                    switch (map.get(nBonus)){
                        case BONUS_SLOW:
                            cb = Color.BLUE;
                            m = this::slowBonus;
                            break;
                        case BONUS_FAST:
                            cb = Color.red;
                            m = this::fastBonus;
                            break;
                        case BONUS_EXTRALIFE:
                            cb = Color.green;
                            m = this::bonusExtraLife;
                            break;
                        case BONUS_WIDE:
                            cb = Color.ORANGE;
                            m = this::bonusWidePad;
                            break;
                        case BONUS_NARROW:
                            cb = Color.PINK;
                            m = this::bonusNarrowPad;
                            break;
                    }
                    Bonus bonus = new Bonus(brickBounds.x + brickBounds.width/2,
                            brickBounds.y,
                            pad,
                            cb,
                            mainLoopController,
                            m);
                    // cuando la pelota toca el brick, se ejecuta el callback
                    // agregamos una animacion del bonus
                    b.hitCallback = () -> mainLoopController.addAnimationLopp(bonus);
                }

                bricks.add(b);

                // modificamos el rectangulo del sgte brick
                r.x += r.width + 12; //add margen 12px
            }
            r.y += r.height + 10; // add 10px hacia abajo
            // inicializamos el rectangulo en la coordenada 12 (sgte renglon)
            r.x = 12;
        }
    }

    // metodo para manejar el teclado
    private KeyListener initKeyListener() {
        KeyListener kbd = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    padDir |= PAD_LEFT;
                    lastPadDir = PAD_LEFT;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    padDir |= PAD_RIGHT;
                    lastPadDir = PAD_RIGHT;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT)
                    padDir = padDir & ~PAD_LEFT;
                else if (keyCode == KeyEvent.VK_RIGHT)
                    padDir = padDir & ~PAD_RIGHT;
            }
            @Override
            public void keyTyped(KeyEvent e) {
                // validamos si se presiona enter y gameOver igual a true
                if ((e.getKeyChar() == '\n') && gameOver) {
                    // reiniciamos el juego con mensaje ready loop
                    restartGame(true);
                }
            }
        };
        return kbd;
    }

    @Override
    public KeyListener getKeyListener() {
        return keyListener;
    }

    private void setInitialState(){
        gameOver = true;
        pause();
        showGameOver();
    }

    // cuando el juego se termina, mostramos un mensaje que dice Game Over
    private void showGameOver(){
        gameOverLoop = new BlinkMessageLoop(screenSizeBounds,
                Config.gameOver, Config.restartMessage,
                mainLoopController,this, Config.dialogBackgroundColor,
                Config.dialogTextColor, 0);
        mainLoopController.addAnimationLopp(gameOverLoop);
    }

    public void pause(){
        paused = true;
    }
    public void unPause(){
        paused = false;
    }

    private void addReadyLoop(){
        mainLoopController.addAnimationLopp(new BlinkMessageLoop(
                screenSizeBounds,
                Config.readyMessage,
                Config.useArrowKeys,
                mainLoopController,
                this,
                Config.backgroundColor,
                Config.dialogTextColor,
                8
        ));
    }

    private void resetPad(){
        Rectangle padBounds = pad.getBounds();
        // no es necesario reiniciar Y porque el pad nunca se mueve en la coordenada Y
        pad.setX(screenSizeBounds.width/2 - padBounds.width/2);
    }
    private void resetBall(){
        ball.setPlayerFailed(false);
        Rectangle padBounds = pad.getBounds();
        ball.setPosition(padBounds.x + padBounds.width/2 -
                ball.getSize()/2, padBounds.y - ball.getSize(), 1, -1);
    }

    /**
     *
     * @param withReadyLoop lanza el mensaje de Ready en la pantalla y comienza el juego
     */
    private void restartGame(boolean withReadyLoop){
        // validamos si se muestra el mensaje de game over
        if(gameOverLoop != null){
            // Quitamos el mensaje
            mainLoopController.removeAnimationLoop(gameOverLoop);
            gameOverLoop = null;
        }
        // para reiniciar el juego, reiniciamos las variables
        gameOver = false;
        score = 0;
        // si liveIndicator es null significa que estamos empezando en el constructor
        if(livesIndicator == null){
            // inicializamos liveIndicator
            livesIndicator = new LivesIndicator(screenSizeBounds, Config.STARTLIVES,
                    Config.padColor);
        }

        livesIndicator.setLives(Config.STARTLIVES); // inicio de vidas
        nextLifeAt = Config.EXTRALIVESAT;

        // inicializacion de Bricks
        initBricks();

        // si no existe pad lo inicializamos el pad
        if(pad == null){
            int padWidth = 75;
            int padHeight = 12;
            pad = new Brick(0, screenSizeBounds.height-padHeight*3,
                    padWidth, padHeight, Config.padColor, 0);
            pad.isPad = true;
        }
        resetPad();
        pad.setVisible(true);

        // si no existe la pelota, lo inicializamos
        if(ball == null){
            ball = new Ball(0,0, 1, -1);
            ball.setScreenSize(screenSizeBounds);
        }
        resetBall();

        /*
         * si el juego esta comenzando la primera vez, no va a iniciar sino que debe estar en
         * un estado de game over y al presional enter se comience a jugar
         * Si ponemos restartGame() para jugar una sgte vida entonce debe aparecer el mensaje
         * de Ready antes que inicie la sgte vida.
         */
        if(withReadyLoop){
            addReadyLoop();
        }else{
            // quitamos la pausa al juego
            unPause();
        }
    }

    private void nextChance(){
        // validamos que existan vidas,si no es asi, entonces Game Over
        if(livesIndicator.getLives() > 0){
            // obtenemos el rectangulo que ocupa la ultima vida en la pantalla
            Rectangle nextLifeBounds = livesIndicator.getLastLifeBounds();
            // disminuimos en 1 las vidas actuales
            livesIndicator.setLives(livesIndicator.getLives()-1);
            // reinicio del juego (pad y pelota en medio de la pantalla)
            resetPad();
            resetBall();

            /*
             * Interpolacion de la coordenada de la sgte vida (nextLifeBounds) hasta
             * la posicion de la pad centrado
             */
            BrickEffectLoop bel = new BrickEffectLoop(mainLoopController,
                    nextLifeBounds,
                    pad.getBounds(),
                    pad.getColor(),
                    pad.getColor(), 120);
            bel.setOnFinished(()->{
                // al terminar el effect, mostramos el pad
                pad.setVisible(true);
                // pequena pausa
                addReadyLoop();
            });
            mainLoopController.addAnimationLopp(bel);

        }else{
            // game over
            gameOver = true;
            showGameOver();
        }
    }

    /*
     * al momento de tocar con la pelota un brick aplicamos un efecto al borrado del brick
     * del escenario. El efecto que se aplica es una reduccion paulatina del brick
     */
    private void dismissBrickEffect(Brick b){
        // obtenemos el tamano del brick
        Rectangle r = b.getBounds();

        // calculamos la nueva dimension o dimension final del brick
        int width = (int)(r.width * 0.6); // terminal al 60% de su tamano inicial
        int height = (int)(r.height * 0.6);
        // nueva coordenada x, y centrada
        int x = r.x + r.width/2 - width/2;
        int y = r.y + r.height/2 - height/2;

        // aplicamos nuevos valores al brick redefinido
        r = new Rectangle(x, y, width, height);

        BrickEffectLoop el = new BrickEffectLoop(
                mainLoopController, b.getBounds(), r, b.getColor(), backgroundColor, 60);
        mainLoopController.addAnimationLopp(el);
    }

    private void playTicSound(){
        // buscamos cual de los 3 tic sounds esta disponible
        for(int n = 0; n < soundTic.length; n++){
            // si no se esta tocando en este momento, entonces, lo usamos
            if(!soundToc.isPlaying()){
                soundTic[n].play(false); // se toca pero no se repite una y otra vez
                break;
            }
        }
    }

    private void movePad() {
        if (padDir != 0) {
            if (lastPadDir == PAD_LEFT) {
                if ((padDir & PAD_LEFT) != 0)
                    pad.moveLeft();
                else if ((padDir & PAD_RIGHT) != 0)
                    pad.moveRight(screenSizeBounds.width);
            } else {
                if ((padDir & PAD_RIGHT) != 0)
                    pad.moveRight(screenSizeBounds.width);
                else if ((padDir & PAD_LEFT) != 0)
                    pad.moveLeft();
            }
        }
    }
    @Override
    public void nextFrame(Rectangle screenBounds) {
        // en cada cuadro actualizamos el tamano de la pantalla
        screenSizeBounds = screenBounds;
        // verificamos si paused es true
        if(paused)
            return;

        movePad();
        ball.checkForScreenSize();
        // validamos que existan bricks en el scenario y manejamos su colision
        if(bricks.size() > 0){
            // Iteramos los bricks de la pantalla para saber si hubo colision
            for(Iterator<Brick> it = bricks.iterator(); it.hasNext();){
                Brick b = it.next();
                // validamos si se produce colision
                if(ball.testForHit(b)){
                    // producida la colision, tocamos el sonido tic
                    playTicSound();
                    // efecto de desvanecimiento del brick de la escena
                    dismissBrickEffect(b);
                    // quitamos el brick del escenario
                    it.remove();

                    // manejamos la puntuacion
                    if(b.hitSpeed > 3){
                        score += 20;
                    }else{
                        score += 10;
                    }
                    // cada 1000 puntos, agrega una vida
                    if(score >= nextLifeAt){
                        int curLives = livesIndicator.getLives();
                        // validamos si las vidas actuales son menor a 20
                        if(curLives < Config.MAXLIVES){
                            // incrementamos el numero de vidas
                            livesIndicator.setLives(curLives + 1);
                        }
                        nextLifeAt += Config.EXTRALIVESAT;
                    }
                    // al encontrar el brick que hace colision, salimos del for
                    break;
                }
            }
        }

        // manejamos la colision de la pelota con el pad (sound toc)
        if(ball.testForHit(pad)){
            soundToc.play(false);
        }

        ball.nextFrame();

        // validamos si perdio
        if(ball.playerFailed()){
            pause(); // pausamos el juego
            // reinicio del pad, pelota y mensaje ready
            pad.setVisible(false);
            // aplicamos efecto de desvanecimiento del pad (lo escondemos)
            BrickEffectLoop bel =  new BrickEffectLoop(
                    mainLoopController,
                    pad.getBounds(),
                    pad.getBounds(),
                    pad.getColor(),
                    backgroundColor, 60);
            // comenzamos la sgte vida
            bel.setOnFinished(()->{
                nextChance();
            });
            mainLoopController.addAnimationLopp(bel);

        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        // dibujamos el score
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format(Config.scoreMessage, score), 20, 20);
        livesIndicator.paint(g2d);

        // dibujamos la coleccion de bricks
        for(Brick b : bricks){
            b.draw(g2d);
        }

        ball.draw(g2d);
        pad.draw(g2d);
    }
}
