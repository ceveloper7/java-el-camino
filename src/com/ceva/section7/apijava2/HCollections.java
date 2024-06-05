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

    private static void iteratorTest() {
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
        Set set = new LinkedHashSet();
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

    private static void treeSetTest() {
        Set set = new TreeSet();
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

    private static void listTest() {
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

    private static void hashMapTest() {
        Map map = new HashMap();
        map.put(1, "uno");
        map.put(2, "dos");
        map.put(3, "tres");
        map.put(4, "cuatro");
        map.put(5, "cinco");

        for (int n=1; n<=5; n+=2)
            System.out.println(map.get(n));

        System.out.println();
        // Recorrer indices del diccionario
        for (Iterator it=map.keySet().iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }

        System.out.println();
        // Recorrer pares indice-valor del diccionario
        for (Iterator it=map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println("key: " + entry.getKey() + ", value=" + entry.getValue());
        }

        System.out.println();
        // Recorrer indices del diccionario
        for (Iterator it=map.keySet().iterator(); it.hasNext(); ) {
            System.out.println(map.get(it.next()));
        }
    }

    private static void treeMapTest() {
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

    private static void propertiesTest1() {
        Properties props = new Properties();
        props.put("color", "rojo");
        props.put("maxMem", "512");
        props.put("mensaje", "Hola mundo!");

        System.out.println(props.get("mensaje"));

        try (OutputStream out = new FileOutputStream("c:/temp/params.props")) {
            props.store(out, "Propiedades de mi aplicacion.");
        } catch (IOException e) {
            System.out.println("Error de escritura.");
        }
    }

    private static void propertiesTest2() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("c:/temp/params.props")) {
            props.load(in);
        } catch (IOException e) {
            System.out.println("Error de lectura.");
        }

        System.out.println(props.get("mensaje"));
    }

    public static void main(String[] args) {
        testSet();
        equalsTest();
        // iteratorTest();
        // linkedHashSetTest();
        // treeSetTest();
        // listTest();
        // hashMapTest();
        // treeMapTest();
        // propertiesTest1();
        // propertiesTest2();
    }
}
