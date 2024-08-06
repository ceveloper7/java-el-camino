package com.ceva.section8.generics;

import java.util.*;

/**
 * Los genericos permiten definir a los tipos de datos como un parametro para definir clases, interfaces y metodos.
 * Parametro de tipo: Set<T>. el parametro de tipo es el que esta declarado
 * Argumento de tipo: Set<String>. el argumento de tipo es el tipo real que se utilizara.
 * Tipos crudos o raw types: ocurren cuando no especificamos un tipo por ejemplo: List products;
 *
 * Parametros de tipos delimitados: hay ocasiones donde necesitamos que un algoritmo se aplique solo se aplique a ciertos tipos de objetos
 * para ello usamos extendes despues del tipo seguido de la clase base. Por ejemplo: <T extends MyClass> estos significa que T recibe
 * cualquier clase o interface de tipo MyClass o alguna derivada de MyClass
 *
 */
public class AGenericMethods {
    /**
     * Metodos genericos: son lo que definen sus propios parametros de tipo, se permiten los metodos static y non static y constructores.
     * La definicion del parametro de tipo se coloca antes del tipo que el metodo retorna.
     * En el metodo copySetToLIst el parametro de tipo es <T>, que es usado en la firma del metodo e incluso dentro del cuerpo de la funcion.
     * En el metodo copySetToList el tipo de dato que retorna es un List<T>
     */
    public static <T> List<T> copySetToList(Set<T> set, List<T> list){
        list.clear();
        for(T element : set){
            list.add(element);
        }
        return list;
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("Uno");
        set.add("Dos");
        set.add("Tres");

        Integer i;
        List<String> list = new LinkedList<>();
        list = AGenericMethods.<String>copySetToList(set, list);
    }
}
