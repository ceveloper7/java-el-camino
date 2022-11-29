/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

/**
 *
 * @author PC
 */
public class AF_DoubleLinkedListDemo {
    private AE_DoubleNode head;
    
    public AE_DoubleNode add(String name){
        AE_DoubleNode node = new AE_DoubleNode();
        node.name = name;
        node.prev = null;
        node.next = null;
        
        if(head == null){
            head = node;
        }else{
            AE_DoubleNode temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = node;
            node.prev = temp;
        }
        return node;
    }
    
    public void remove(String name){
        if(head == null)
            return;
        
        // cuando queremos remover un elemento:
        AE_DoubleNode temp = head;
        AE_DoubleNode prev = null;
        // encuentro el elemento anterior del elemento que quiero remover, borro el node next
        // encuentro el elemento siguiente del elemento que quiero remover booro el node prev
        // la propiedad next del elemento anterior al elemento que quiero remover lo conecto con el elemento
        // posterior al elemento a eliminar
        // la propiedad prev del elemento posterior al elemento que quiero remover lo conecto con el elemento
        // previo al elemento a eliminar
        
        while((temp != null) &&  !temp.name.equals(name)){
            prev =  temp;
            temp = temp.next;
        }
        if(temp != null){
            if(prev == null){
                head = head.next;
                head.prev = null;
            }else{
                prev.next = temp.next;
                if(temp.next != null){
                    temp.next.prev = prev;
                }
            }
        }
        
    }
}
