package com.ceva.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

import com.ceva.section29.log4j_junit.ParseIntInterface;
import org.junit.Test;
public class ParseInt1Test {
    public ParseInt1Test() {
    }

    /**
     * Validamos el caso donde ingresa el primer numero valido y el segundo es null
     */
    @Test
    public void testValidate1() {
        ParseIntInterface main = new FakeMain();
        boolean b = main.validate("1", null);
        // si b no es false, retorna el mensaje de error
        assertEquals("Error no detectado", false, b);
    }

    /**
     * Validamos el caso donde ingresa el primer numero valido y el segundo es una letra
     */
    @Test
    public void testValidate2() {
        ParseIntInterface main = new FakeMain();
        boolean b = main.validate("1", "a");
        assertEquals("Error no detectado", false, b);
    }
}
