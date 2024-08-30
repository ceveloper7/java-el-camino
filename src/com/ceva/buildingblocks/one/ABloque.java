package com.ceva.buildingblocks.one;

/**
 * Bloque de inicializacion
 * Bloque de inicializacion de Instancia.
 */
public class ABloque {
    // si necesito usar variables de instancia dentro de un bloque de inicializacion, las variables se deben definir primero.
    {
        System.out.println("1.-Bloque de inicializacion de instancia");
    }

    public ABloque() {
        System.out.println("4.- ABloque Constructor");
    }

    public static void main(String[] args) {
        ABloque bloque = new ABloque();

        // bloque de inicializacion - si compila
        {
            System.out.println("2.- Bloque de inicializacion");
        }
    }

    // bloque de inicializacion de instancia, para que se ejecuta es necesario crea un objeto de tipo ABloque
    {
        System.out.println("3.- Bloque de inicializacion de instancia");
    }
}
