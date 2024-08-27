package com.ceva.section9.estructurasdatos;

public class DLinkedList {
    // referencia al Primer elemento
    private CDoubleLinkedList head;

    // crea un nuevo nodo y retorna la referencia del mismo
    public CDoubleLinkedList add(String name) {
        CDoubleLinkedList node = new CDoubleLinkedList();
        node.name = name;
        node.prev = null;
        node.next = null;

        if (head == null) {
            // inicializamos - link head con el primer nodo
            head = node;
        } else {
            CDoubleLinkedList temp = head;
            // recorremos hasta llegar al ultimo nodo de la lista
            while (temp.next != null)
                temp = temp.next;
            temp.next = node;
            node.prev = temp;
        }
        return node;
    }

    // eliminamos un nodo
    public void remove(String name) {
        // validacion
        if (head == null)
            return;
        // temp apunta al primer elemento
        CDoubleLinkedList temp = head;
        CDoubleLinkedList prev = null;
        // mientras que temp no este al final de la lista y no se haya encontrado el nombre
        while ((temp != null) && !temp.name.equals(name)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp != null) {
            if (prev == null) {
                head = head.next;
                head.prev = null;
            } else {
                prev.next = temp.next;
                if (temp.next != null) {
                    temp.next.prev = prev;
                }
            }
        }
    }
}
