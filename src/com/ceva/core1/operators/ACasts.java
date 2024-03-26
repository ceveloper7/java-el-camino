package com.ceva.core1.operators;

public class ACasts {
    public static void main(String[] args) {
        double val1 = 34.60;
        int n = (int)val1; // double -> int se pierde informacion
        System.out.println(n);

        // para redondear un double y pasarlo a un int aun asi se debe usar cast
        n = (int)Math.round(val1);
        System.out.println(n);

        // incremento sufijo y prefijo
        int a = 7;
        int b = 7;

        int ope1 = 2 * ++a; // 16
        System.out.println(ope1);
        int ope2 = 2 * b++;
        System.out.println(ope2); // 14
        System.out.println("value a es " + b);

        // expresion switch
        int seasonCode = 0;
        String seasonName = switch (seasonCode){
            case 0 -> "Spring";
            case 1 -> "Summer";
            case 2 -> "Fall";
            case 3 -> "Winter";
            default -> "???";
        };
        System.out.println(seasonName);

        String role = "Custom";
        int roleId = switch (role){
            case "Admin", "Administrator", "Administrador" -> 0;
            case "User" -> 1;
            default -> 2;
        };
        System.out.println("roleId is " + roleId);
    }


}
