package com.ceva.core1.ch03;

public class A_String {
    public static void main(String[] args) {
        textBlocks();
    }

    private static void textBlocks(){
        String msg = """
                Hello
                World
                Java
                """;
        System.out.println(msg);

        // textblock para escribir sql o html
        String html = """
                <h1>Java basics</h1>
                <div>
                    <p>hello world Java</p>
                </div>""";
        System.out.println(html);

        // el signo \ antes del final de una linea une esta linea con la siguiente
        String msg1 = """
                Hello
                Java is a programming \
                language awsome
                """;
        System.out.println(msg1);

        // \s agrega una espacio despues de los dos puntos (:)
        String msg2 = """
                Hello, my name is Carlos. \
                Please enter your name:\s""";
        System.out.println(msg2);

        //
        System.out.println();
        String html2 = """
                <div class="warning">
                    Beware of those who say "Hello" to the world
                </div>
                """;
        System.out.println(html2);
    }

    /*
     * Usar la concatenacion de String para construir un String es un procedimiento ineficiente
     * StringBuilder -> Esta clase es la recomendada a utilizar cada vez que necesitamos construir un string
     *
     */
    private static void buildingString(){
        StringBuilder sb = new StringBuilder("Invoice");
        sb.append(" PE");
        System.out.println(sb.toString());

        // Reverse String. La clase String no tiene un metodo para reverse pero StringBuilder si
        System.out.println(sb.reverse().toString());
    }

    /*
     * Un string vacio es un objeto java cuya longitud es 0 y su contenido es empty
     * Un objeto String puede contener un valor null que significa que ningun objeto esta
     * asociado a dicha variable String.
     */
    private static void emptyNullStr(){
        String msg = "God is love";
        // preguntando si la cadena esta vacia (forma eficiente)
        System.out.println(msg.isEmpty()); // false

        //
        System.out.println(msg.length() == 0); // false
        System.out.println(msg.equals("")); // false

        System.out.println();
        // preguntado si la cadena no es null ni empty
        String msg1 = "God";
        System.out.println(msg1 != null && msg1.isEmpty()); // false
    }

    /*
     * Para evaluar la igualdad de dos String no se debe usar ==
     * El operador == determina si o no las String estan almacenadas en la misma ubicacion.
     */
    private static void string_equality(){
        // 1. test if 2 string son iguales
        String msg1 = "Hello";
        String msg2 = "hello";
        System.out.println(msg1.equals(msg2)); // false

        // 2.
        System.out.println("Hello".equals(msg1)); // true

        // 3. comparamos dos strings no haciendo distincion entre mayusculas y minusculas
        System.out.println("Hello".equalsIgnoreCase(msg2)); // true

        //
        System.out.println();
        System.out.println(msg1.substring(0, 2) == "He"); // false
        System.out.println(msg1.substring(0,2).equals("He")); // true
        System.out.println("He".equals(msg1.substring(0,2))); // true

        // compareTo()
        System.out.println();
        System.out.println(msg1.substring(0,2).compareTo("He")); // 0 -> equals
        System.out.println("He".compareTo(msg1.substring(0,2))); // 0 -> equals
        System.out.println(msg1.substring(0,2).compareTo("he")); // -1 -> not  equals
    }

    private static void string_inmutable(){
        String msg = "Hello";
        int pos = msg.indexOf("lo");
        String msg1 = msg.substring(0, pos) + "p!"; // Help!
    }

    /*
     * Indices y Substring
     * String -> sequence char values -> char is used for representing UTF-16 encoding
     */
    private static void subStr_idx(){
        // 1. length() -> # of char values in a string
        String s = "Love is love";
        System.out.println(s.length()); // 12

        // 2. s.charAt(n) -> retorna el char value en la posicion n
        System.out.println(s.charAt(5)); // i

        // 3. s.indexOf(n) -> obtenemos la posicion del char value en el string
        System.out.println(s.indexOf('s')); // 6

        // 4. s.substring(a,b) obtenemos una subcadena de una cadena. a string, b pos que no queremos copiar
        int sp = s.indexOf(" ");
        System.out.println(s.substring(0, sp)); // Love
    }

    // String.split() produce un array
    private static void splitting(){
        String msg = "Hello World";
        String[] r = msg.split("\\s+"); // white space
        System.out.println(r[0]);
        System.out.println(r[1]);

        // Splitting patterns
        String msg1 = "Love, love, is all";
        String[] r1 = msg1.split("\\s*,\\s*"); // comma with optional white space
        System.out.println(r1[0]);
        System.out.println(r1[1]);
        System.out.println(r1[2]);

        // Punto
        String msg2 = "Ella.Tu.Yo";
        String[] r2 = msg2.split("\\.");
        System.out.println(r2[0]);
        System.out.println(r2[1]);
        System.out.println(r2[2]);

        // cualquiera q no sea una letra
        String msg3 = "Love@Love";
        String[] r3 = msg3.split("\\PL+");
        System.out.println(r3[0]);
        System.out.println(r3[1]);

        // salto de linea
        String msg4 = """
                love
                love
                Hi
                """;
        String[] r4 = msg4.split("\\R");
        System.out.println(r4[0]);
        System.out.println(r4[1]);
        System.out.println(r4[2]);

        // Graphema cluster
        String msg5 = "Hi";
        String[] r5 = msg5.split("\\b{g}");
        System.out.println(r5[0]);
        System.out.println(r5[1]);
    }

    private static void joinStr(){
        // join multiple string
        String allSizes = String.join("/","S", "M", "L", "XL");
        System.out.println(allSizes);

        // repeat same string
        String msg = "love ";
        System.out.println(msg.repeat(3));
    }

    private static void avisos(){
        // 1. Concatenando expresiones usando +
        int edad = 21;
        String msg = "Next year, you will be " + edad + 1; // print Next year you will be 211
        String msg1 = "Next year you will be " + (edad + 1); // print Next year you will be 22



    }
}
