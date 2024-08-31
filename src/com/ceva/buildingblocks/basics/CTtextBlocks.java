package com.ceva.buildingblocks.basics;

public class CTtextBlocks {

    public static void main(String[] args) {
        String texto1 = "\"Texto entre comillas\"";
        System.out.println(texto1);

        String textBlock = """
                A                           D
                La soledad, se aduenia de toda emocion
                """;

        System.out.println(textBlock);

        String textBlock1 = """
                doe \n
                run
                """; // se imprimen 5 lineas
        System.out.println(textBlock1);

        String textBlock2 = """
                "doe\"\"\"
                \"deer\"""
                """;
        System.out.println(textBlock2);
    }
}
