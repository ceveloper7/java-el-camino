package com.ceva.section9.estructurasdatos;

/**
 * Listas enlazadas: Son listas de datos que crecen ilimitadamente. Son eficientes solo si se consultan en el orden que fueron creadas.
 * Una desventaja de ellas es que si queremos ubicar un elemento de la lista debemos recorrer toda la lista desde el principio a fin
 * hasta encontrar el elemento deseado, lo cual es menos eficiente mientras la lista sigue creciendo.
 */
public class BLinkedListDemo {
    private static void nodeDemo() {
        /**
         * Lista enlazada que permite crear una estructura de elementos
         */
        ANode n = new ANode();
        n.name = "uno";
        n.next = null;

        // head y tail apuntal primer elemento de la lista
        ANode head = n;
        ANode tail = head; // nodo que apunta al ultimo elemento de la estructura.

        n = new ANode();
        n.name = "dos";
        n.next = null;
        tail.next = n;
        tail = tail.next;

        n = new ANode();
        n.name = "tres";
        n.next = null;
        tail.next = n;
        tail = tail.next;

        // recorremos los nodos de la lista
        n = head;
        while (n != null) {
            System.out.println(n.name);
            n = n.next;
        }
    }

    public static void main(String[] args) {

    }
}
