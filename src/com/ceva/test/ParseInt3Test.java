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
        return Arrays.asList(
                "20\n0\n",
                "\n\n",
                "-1,0"
        );
    }

    @Test
    public void testInput() throws IOException {
        Main main = new Main();

        ByteArrayInputStream bin = new ByteArrayInputStream(strInput.getBytes());
        main.start(bin);
    }

}

