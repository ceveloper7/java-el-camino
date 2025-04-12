package com.ceva.core1.ch03;

import com.ceva.core1.ch03.datatype.D_Enums;

/*
 * Switch expression & Switch Statement
 */
public class F_Switch {
    public static void main(String[] args) {
        F_Switch swith = new F_Switch();
        swith.switchExpression();
        swith.switchStatement();
    }

    /*
     * Con tantas variaciones de switch, cual debemos usar
     * Debemos evitar la forma Fallthrough es poco comun necesitar fallthrough
     * Debemos preferir switch expression sobre switch statements
     */
    private void switchExpression(){
        // No Fallthrough: cada case termina con la flecha (->)
        D_Enums.Size size = D_Enums.Size.EXTRA_LARGE;
        int sizeValue = switch (size){
            case SMALL -> 1;
            case MEDIUM -> 2;
            case LARGE -> 3;
            case EXTRA_LARGE -> 4;
            // es permitido omitir default debido a que hay un case para cada constante del enum Size
            default -> -1;
        };
        System.out.println(sizeValue);

        // Fallthrough: Cada case termina con dos puntos (:)
        String seasonName = "Primavera";
        int seasonValue = switch(seasonName){
            case "Verano":
                // yield permite retornar un valor a la variable seasonValue
                yield 1;
            case "Otonio":
                yield 2;
            case "Invierno":
                yield 3;
            case "Primavera":
                yield 4;
            default:
                yield -1;
        };
        System.out.println(seasonValue);

        char grade = 'F';
        String message = switch (grade){
            case 'A', 'B' -> "VERY GOOD";
            case 'C', 'D' -> "GOOD";
            case 'E' -> "BAD";
            case 'F' -> "YOU GOT A PROBLEM BOY";
            default -> "UNKNOW";
        };
        System.out.println("Your grade " + grade + " is " + message);
    }

    private void switchStatement(){
        // Fallthrough
        D_Enums.Size size = D_Enums.Size.LARGE;
        int sizeValue = 0;
        switch (size){
            case SMALL:
                sizeValue = 1;
                break;

            case MEDIUM:
                sizeValue = 2;
                break;

            case LARGE:
                sizeValue = 3;
                break;

            case EXTRA_LARGE:
                sizeValue = 4;
                break;

            default: sizeValue = -1;
        }
        System.out.println(sizeValue);

        // No Fallthrough
        String seasonName = "Invierno";
        int seasonValue = 0;
        switch (seasonName){
            case "Verano" -> {
                seasonValue = 1;
            }
            case "Otonio" -> {
                seasonValue = 2;
            }
            case "Invierno" -> {
                seasonValue = 3;
            }
            case "Primavera" -> {
                seasonValue = 4;
            }
            default ->  seasonValue = -1;
        };
        System.out.println(seasonValue);
    }
}
