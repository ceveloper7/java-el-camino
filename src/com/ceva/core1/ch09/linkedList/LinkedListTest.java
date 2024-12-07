package com.ceva.core1.ch09.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        var b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // uso de Iterator y ListIterator. Agregar los elementos de b en a
        ListIterator<String> aIter = a.listIterator(); // retornamos un objeto Iterator que implementa ListIterator
        Iterator<String> bIter = b.iterator();

        while(bIter.hasNext()){
            if(aIter.hasNext()) {
                aIter.next();
            }
            aIter.add(bIter.next());
        }

        System.out.println(a);

        // remove every second element from b (Doug, Gloria)

        bIter = b.iterator();
        while (bIter.hasNext())
        {
            bIter.next(); // skip one element
            if (bIter.hasNext())
            {
                bIter.next(); // skip next element
                bIter.remove(); // remove that element
            }
        }

        System.out.println(b);

        // bulk operation: remove all elements in b from a (Bob, Frances)
        a.removeAll(b);

        System.out.println(a);

        //
    }
}
