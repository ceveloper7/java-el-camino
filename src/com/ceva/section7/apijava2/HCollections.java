package com.ceva.section7.apijava2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class HCollections {
    static class Person {
        int primaryKey;
        String name;
        int age;

        @Override
        public boolean equals(Object o2) {
            Person p2 = (Person) o2;
            // si las primaryKey son distintas de 0 significa que ambos estan en la BD. Basta comprar las primaryKey para saber si son iguales.
            if ((primaryKey != 0) && (p2.primaryKey != 0)) {
                return primaryKey == p2.primaryKey;
            }
            if (name.equals(p2.name) && (age == p2.age)) {
                return true;
            }
            return false;
        }

    }

    private static void testSet() {
        Set s = new HashSet();
        s.add("uno");
        s.add("dos");
        s.add("tres");
        s.add("dos"); // la coleccion set no admite datos repetidos por lo que el size de s es de 3 items
        s.add("uno");

        System.out.println("s.size() = " + s.size());
    }

    /**
     * metodo equals() que retorna true si los objetos comparados son iguales
     * metodo hashCode() retorna un numero que es la representacion numerica de un objeto
     */
    private static void equalsTest() {
        Object i1 = 1; // crea una instancia del objeto Integer
        Object i2 = 1;

        System.out.println(i1.equals(i2));

        String s1 = new String("Hola");
        String s2 = new String("Mundo");

        System.out.println(s1.equals(s2));
    }

    private static void personTest() {
        Person p1 = new Person();
        p1.primaryKey = 100;
        p1.name = "Raul";
        p1.age = 21;

        Person p2 = new Person();
        p2.primaryKey = 0;
        p2.name = "Raul";
        p2.age = 21;
    }

    /**
     * EL objeto Iterator permite recorrer los objetos de una coleccion independiente de como internamente la coleccion esta implementada.
     * Un iterator recorrer todos los elementos de la coleccion pero lo hara de la manera mas conveniente y no garantiza se que se recorran
     * en el orden que fueron insertados.
     * .iterator() -> retorna un ojbeto iterator
     * .hasNext() -> retorna true si aun existen elementos por recorrer en la coleccion
     * .next() -> retorna el siguiente elemento en la coleccion
     */
    private static void iteratorTest() {
        /**
         * Implementacion de Set
         * HashSet -> guarda sus elementos utilizando una tabla basada en el hashcode de cada objeto. Esta implementacion es eficiente para encontrar
         *            elementos y muy eficiente para buscar aleatoriamente un elemento. Su desventaja es que cuando su tabla interna se llena es
         *            necesario expandirla y volver a acomadar todos sus elementos.
         *            Si nos lo que nos importa es mantener los datos pero no importa el orden, entonces, hashset es una buena opcion.
         */
        Set set = new HashSet();
        set.add("uno");
        set.add("dos");
        set.add("tres");
        set.add("cuatro");
        set.add("cinco");
        set.add("seis");

        for (Iterator it=set.iterator(); it.hasNext(); )  {
            String s = (String) it.next();
            System.out.println(s);
        }
    }

    private static void linkedHashSetTest() {
        /**
         * Implementacion de Set
         * LinkedHashSet -> Parecida a HashSet pero ademas de guardar sus elementos en una tabla hash tambien los guarda en una lista enlazada.
         *                  por lo que puede recorrerlos en el mismo orden que fueron agregados a la coleccion.
         *                  Si el orden de los elementos en una lista son importantes entonces LinkedHashSet es una buena opcion.
         */
        Set set = new LinkedHashSet();
        set.add("uno");
        set.add("dos");
        set.add("tres");
        set.add("cuatro");
        set.add("cinco");
        set.add("seis");

        for (Iterator it=set.iterator(); it.hasNext(); )  {
            String s = (String) it.next(); // retornamos los elementos en el mismo orden que fueron agregados
            System.out.println(s);
        }
    }

    private static void treeSetTest() {
        /**
         * Implementacion de Set
         * TreeSet -> esta implementacion guarda la info deacuerdo a su orden natural o de acuerdo a un comparador de tal forma que la info se
         *            retorna de manera ordenada. Se puede usar para odenar elementos rapidamente. Agregamos elementos y automaticamente
         *            los elementos seran ordenados.
         */
        Set set = new TreeSet();
        // al tratarse de string el orden natural es el ordenamiento alfabetico por lo que cuatro y cinco estaran al principio de la lista
        set.add("uno");
        set.add("dos");
        set.add("tres");
        set.add("cuatro");
        set.add("cinco");
        set.add("seis");

        for (Iterator it=set.iterator(); it.hasNext(); )  {
            String s = (String) it.next();
            System.out.println(s);
        }
    }

    /**
     * Implementacion de List: una lista es una coleccion de elementos que pueden estar repetidos. A diferencia de Set las listas pueden encontrar
     *                         elemento en un posicion especifica comenzando con 0. EL orden de numeracion de una lista es el mismo en que los
     *                         elementos fueron insertados.
     */
    private static void listTest() {
        /**
         * Implementacion List:
         * ArrayList -> es una lista respaldada por un arreglo de objetos, es decir, es un array. LOs array son buenos para encontar elementos
         *              aleatoriamente porque solo hay que consultar el indice correspondiente. La desventaja es que el tamano de un array es fijo
         *              y al llenarse, java debe crear un nuevo array mover todos los elementos para hacer mas espacio. A borrar un elementos java
         *              debera comodar el resto de la coleccion por lo que un arraylist no es la mejor opcion si se estan insertando y borrando
         *              datos constantemente
         */
        List list = new ArrayList();
        list.add("uno");
        list.add("dos");
        list.add("tres");
        list.add("cuatro");
        list.add("cinco");
        list.add("seis");

        for (int n=0; n<list.size(); n++) {
            System.out.println(list.get(n));
        }

        for (Iterator it=list.iterator(); it.hasNext(); )  {
            String s = (String) it.next();
            System.out.println(s);
        }
    }

    /**
     * Implementacion List
     * LinkedList -> esta es una lista respaldada por nodos doblemente enlazados. un nodo doblemente enlazado es objeto que contiene el valor del
     *               elemento y una referencia al elementos posterior y anterior.
     * Ejemplo: de una lista doblemente enlazada
     * Nodo1 esta conectado con el Nodo2
     * Nodo2 esta conectado con el Nodo1
     * Nodo2 esta conectado con el Nodo3
     * Nodo3 esta conectado con el Nodo2
     *
     *
     * Recorrer y hacer crecer una lista usando LinkedList es facil ya que lo necesario es crear un nuevo nodo cuando tengo un nuevo elemento. SIn
     * embargo, si queremos acceder a los elementos por indice ya no es tan facil. Por ejemplo si queremos acceder al nodo 2 linkedList empieza
     * por la posicion 0 hasta llegar al elemento solicitado.
     * Es mas eficiente borrar elementos de un linkedlist ya que para borrar un elemento es suficiente quitar las referencias o enlaces del nodo
     * en cuestion.
     * Conclusion: LinkedList es eficiente para agregar y para borrar elementos constantemente pero no es eficiente para acceder a un elemento
     * aleatoramente es decir por un posicion especifica.
     */

    /**
     * Implementacion List
     * Stack -> esta implementacion recibe este nombre porque se comporta como una pila de platos que utiliza el metodo LastIn-FirstOut o LIFO o
     *          decimos que el ultimo en entrar es el primero en salir.
     */


    /**
     * Diccionarios: un diccionario es una coleccion de elementos respaldados por un indice. los diccionarios son empleados para almacenar info
     *               que se necesatia consultar rapidamente. AL igual que set los elementos de un map son unicos
     */
    private static void hashMapTest() {
        /**
         * Implementacion Map: LOs elementos de un Map son unicos si se agrega dos objetos con el mismo key (Integer) el elemento sera reemplazado.
         * HashMap -> es un diccionario indexado con codigos hash para consultas rapidas pero debido a esto un iterator no retorna los
         *            elementos en el orden que fueron insertados
         */
        Map map = new HashMap();
        map.put(1, "uno");
        map.put(2, "dos");
        map.put(3, "tres");
        map.put(4, "cuatro");
        map.put(5, "cinco");

        // recorremos elementos impares
        for (int n=1; n<=5; n+=2)
            // .get() aplica un autoboxing y convierte int n a Integer n
            System.out.println(map.get(n));

        System.out.println();
        // Recorrer indices del diccionario. keySet() retorna un Set que apunta a todos los indices de map
        // entrySet() retorna un Set donde cada elemento contiene tanto el key y value del map.
        for (Iterator it=map.keySet().iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }

        System.out.println("Recorremos indice-valor del Map");
        // Recorrer pares indice-valor del diccionario. .entrySet() retorna un Set donde cada elemento contiene tanto el indice como el valor
        for (Iterator it=map.entrySet().iterator(); it.hasNext(); ) {
            // recorremos los entries
            Map.Entry entry = (Map.Entry) it.next();
            // este metodo es mas eficiente ya que con Entry podemos acceder al key y al value
            System.out.println("key: " + entry.getKey() + ", value=" + entry.getValue()); // print: key 1, value=uno
        }

        System.out.println("Recorremos solo valores del Map");
        // Recorrer indices del diccionario. .keySet() retorna un Set que apunta a todos los indices del Map
        for (Iterator it=map.keySet().iterator(); it.hasNext(); ) {
            // .it.next() retorna el indice y map.get() retorna el valor del indice
            System.out.println(map.get(it.next())); // esta busqueda es menos eficiente por lo que map.entrySet() es recomendable.
        }
    }

    /**
     * Implementacion de Map
     * LinkedHashMap -> Si necesitamos un HashMap y tambien nos importa el orde los elementos entonces LinkedHashMap es una buena opcion.
     *                  es como un hashmap pero con sus elementos respaldados por una lista enlazada que garantiza que los iteradores retornen
     *                  los elementos en el mismo orden en el que fueron creados.
     */

    private static void treeMapTest() {
        /**
         * Implementacion de Map
         * TreeMap -> es un diccionario que guarda los elementos de manera ordenada utilizando el comparador de cada objeto. Lo iteradores retornan
         *            la informacion ordenada. Por ordenada nos referimos al orden natural de las llaves.
         */
        Map map = new TreeMap();
        map.put("x", "equis");
        map.put("c", "asdffa");
        map.put("w", "wafsdf");
        map.put("a", "aasdfasdf");

        // Recorrer pares indice-valor del diccionario
        for (Iterator it=map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println("key: " + entry.getKey() + ", value=" + entry.getValue());
        }
    }

    /**
     * Implementacion de Map
     * Properties -> es un diccionario que representa un conjunto de datos persistentes, es decir que se puede guardar y cargar en memoria.
     */
    private static void propertiesTest1() {
        Properties props = new Properties();
        props.put("color", "rojo");
        props.put("maxMem", "512");
        props.put("mensaje", "Hola mundo!");

        System.out.println(props.get("mensaje"));

        // guardamos en disco el archivo de propiedades
        try (OutputStream out = new FileOutputStream("/home/ceva/Documents/params.props")) {
            props.store(out, "Propiedades de mi aplicacion.");
        } catch (IOException e) {
            System.out.println("Error de escritura.");
        }
    }

    // lectura de un archivo de propiedades
    private static void propertiesTest2() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("/home/ceva/Documents/params.props")) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("Error de lectura.");
        }

        System.out.println(props.get("mensaje"));
    }

    public static void main(String[] args) {
        // testSet();
        // equalsTest();
        // iteratorTest();
        // linkedHashSetTest();
        // treeSetTest();
        // listTest();
        // hashMapTest();
        // treeMapTest();
        propertiesTest1();
        // propertiesTest2();
    }
}
