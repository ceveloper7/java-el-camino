package com.ceva.section13.java2D;

import java.awt.*;

/* Imprime en pantalla una lista de fonts instaladas
* */
public class EGetListFonts {

    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String familyNames[] = ge.getAvailableFontFamilyNames();

        for (String s : familyNames)
            System.out.println(s);
    }
}
























































