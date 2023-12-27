package com.ceva.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import com.ceva.section29.log4j_junit.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value=Parameterized.class)
public class ParseInt3Test {
    private String strInput;

    public ParseInt3Test(String strInput) {
        this.strInput = strInput;
    }

    @Parameterized.Parameters
    public static Collection<String> getTestValues() {
        // simulamos que el usuario escribe 20 enter, 0 enter, enter enter, -1 0
        return Arrays.asList(
                "20\n0\n",
                "\n\n",
                "-1,0"
        );
    }

    @Test
    public void testInput() throws IOException {
        Main main = new Main();

        // convertimos la cadena strInput a un array de bytes, el resultado sera un
        // iNputStream que estara en memoria
        ByteArrayInputStream bin = new ByteArrayInputStream(strInput.getBytes());
        // pasamos el InputStream y simulamos que el user esta escribiendo 3 valores
        main.start(bin);
    }

}

