package com.ceva.section18.javabricks.dev;

import java.awt.*;

/**
 * Clase donde almacenamos las configuraciones del juego
 */
public class Config {
    public static Color backgroundColor;
    public static Color padColor = Color.RED;
    public static Color ballColor = Color.YELLOW;
    public static Color brickColor = Color.YELLOW;
    public static Color brickFastColor = Color.MAGENTA;
    public static Color dialogBackgroundColor = Color.ORANGE;
    public static Color dialogTextColor = Color.BLACK;
    public static String scoreMessage = "Score: %d";
    public static String gameOver = "Game Over";
    public static String restartMessage = "Press <ENTER> to restart";
    public static String readyMessage = "Ready!";
    public static String useArrowKeys = "Use arrow keys.";
    public static int STARTLIVES = 3;
    public static int EXTRALIVESAT = 1000;
    public static int MAXLIVES = 20;
}
