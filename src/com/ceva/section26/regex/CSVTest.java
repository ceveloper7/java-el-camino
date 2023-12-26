package com.ceva.section26.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CSVTest {
    public static void main(String[] args) throws IOException {
        String s = "\"A,B\",uno,dos y 2,tres";
        /**
         * ([\\w]*),? significa letras, numeros, guionbajo, se puede repetir cero o mas veces. La coma
         *            puede o no puede existir
         */
        String regex = "(\"[áéíóú\\- ,\\w]+\"|[áéíóú\\- \\w]*),?";

        Pattern p = Pattern.compile(regex);

        BufferedReader br = new BufferedReader(new InputStreamReader(CSVTest.class.getResourceAsStream("/com/ceva/section26/regex/test.csv")));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            Matcher m = p.matcher(line);
            while (m.find()) {
                System.out.println(m.group(1));
            }

            line = br.readLine();
            System.out.println();
        }
    }
}
