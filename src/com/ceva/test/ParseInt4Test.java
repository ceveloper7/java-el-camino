package com.ceva.test;

import java.io.IOException;

import com.ceva.section29.log4j_junit.Main;
import org.junit.Test;

/**
 * Programa que arroja una excepcion
 * Cuando se pase un valor null, el programa debe arrojar un NullPointerException
 */
public class ParseInt4Test {
    // esperamos que se produzca una excepcion, si no ocurre entonces el test fallo
    @Test(expected = NullPointerException.class)
    public void testInput() throws IOException {
        Main main = new Main();
        main.start(null);
    }
}
