/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

/**
 *
 * @author PC
 */
public class AD_LinkedListDemo {
    private static void nodeDemo(){
        // creamos un nodo
        AC_Node n = new AC_Node();
        n.name = "Uno";
        n.next = null;
        // cabeza de la lista apunta al primer registro con propiedad name =  Uno
        // head y tail apuntan al primer elemento "Uno"
        AC_Node head = n;
        AC_Node tail = head;
        
        // creamos un sgundo nodo
        n = new AC_Node();
        n.name =  "Dos";
        n.next = null;
        tail.next = n;
        tail = tail.next;
        
        // creamos un tercer nodo
        n = new AC_Node();
        n.name = "Tres";
        n.next = null;
        tail.next = n;
        tail = tail.next;
        
        // recorremos los nodos
        n = head;
        while(n != null){
            System.out.println(n.name);
            n = n.next;
        }
    }
    
    public static void main(String[] args) {
        AD_LinkedListDemo.nodeDemo();
    }
}
