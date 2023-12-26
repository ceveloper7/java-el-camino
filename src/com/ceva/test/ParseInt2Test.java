package com.ceva.test;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

import com.ceva.section29.log4j_junit.Main;
import com.ceva.section29.log4j_junit.ParseIntInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Con loa anotacion @Parameterized podemos establecer un conjunto de valores de prueba y
 * JUnit se encargara de crear una prueba para cada uno. Para usar una prueba parametrizada es
 * necesario cumplir los sgtes requisitos:
 * 1. La clase se deba notar con @RunWith(value=Parametrizedc.class)
 * 2. La clase va a proveer los valores de prueba a utilizar
 * 3. La clase debe tener un constructor  que reciba como parametro los valores de prueba
 */
@RunWith(value=Parameterized.class) // prueba parametrizada
public class ParseInt2Test {
    private String str1;
    private String str2;

    // proveemos los valores para la prueba parametrizada
    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters() {
        // JUnit creara para cada par de valores una instancia de esta clase,
        // le pasara ese par de valores al constructor
        // finalmente va a llamar al metodo de prueba testValidate()
        return Arrays.asList(
                new String[][] {
                        {"1", null},
                        {null, "1"},
                        {null, null},
                        {"1", "a"},
                        {"a", "2"},
                        {"a", "b"},
                        {"-1", "-2"},
                        {"10", "0"}
                }
        );
    }

    // constructor que recibira cada valor del Array para hacer la prueba
    public ParseInt2Test(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    // metodo de prueba
    @Test
    public void testValidate() {
        ParseIntInterface main = new Main();
        boolean b = main.validate(str1, str2);
        assertEquals(String.format("Error no detectado para \"%s\" y \"%s\".", str1, str2), false, b);
    }

}
