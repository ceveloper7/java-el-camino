package com.ceva.core1.ch03.datatype;

public class F_Operators {
    public static void main(String[] args) {
        increment_decrement();
        switchExp();
    }



    private static void switchExp(){
        // switch expresion
        int codigoEstacion = 2;
        String nombreEstacion = switch (codigoEstacion){
            case 0 -> "Verano";
            case 1 -> "Otonio";
            case 2 -> "Invierno";
            case 3 -> "Primavera";
            default -> "Estacion desconocida";
        };

        System.out.println(nombreEstacion); // print Invierno

        // switch expresion con multiples etiquetas
        String rol = "invitado";
        int rolValue = switch (rol){
            case "invitado", "anonimo", "temporal" -> 1;
            case "user" -> 2;
            case "administrador" -> 3;
            default -> -1;
        };
        System.out.println(rolValue);

        // switch expresion with enums
        // cuando el selector es un enum y no se tienen casos para las constantes, necesitamos un default
        D_Enums.Size size = D_Enums.Size.MEDIUM;
        String sizeVal = switch (size){
            case SMALL -> "S";
            case MEDIUM -> "M";
            case LARGE -> "L";
            case EXTRA_LARGE -> "XL";
            case null -> "??"; // en caso de null evitamos el NullPointerException
        };
        System.out.println(sizeVal);
    }

    /*
     * Los operadores de increment y decrement solo se aplican a variables por lo que 5++ o 8--
     * son statements erroneas.
     *
     * 2 formas de increment: postfix & prefix
     * postfix -> increment o decrement operator es colocado despues del operando n++, n--
     * prefix -> increment o decrement operator es colocado antes del operando ++n , --n
     * ambos operadores de incremento modifican el valor del operando en 1
     */
    private static void increment_decrement(){
        // increment
        int a = 5;
        a++;

        // decrement
        int b = 6;
        b--;

        // usando operador de prefix en una expresion
        // prefix primero aplica el increment o decrement al operando
        int c = 7;
        int d = 7;
        int e = 2 * ++c; // print 16, c es 8
        System.out.println(e);

        // usando el operador de postfix en una expresion
        // postfix primero evalua el viejo valor de la variable
        e = 2 * d++; // print 14
        System.out.println(e);

    }
}
