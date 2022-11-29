/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author PC
 */
public class AG_CacheTest {

    class SlowWord {

        private String word;

        public SlowWord(String word) {
            this.word = word;
            // incluimos una pausa de 50 milisegundos
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
        }

        public String getWord() {
            return word;
        }
    }

    // con esta clase tipo cache haremos la lectura del archivo mas rapido
    class CacheNode {

        SlowWord slowWord;
        CacheNode next;

        public CacheNode(SlowWord slowWord) {
            this.slowWord = slowWord;
            this.next = null;
        }
    }

    class Cache {

        // cache size
        int size;
        // number of elements
        int count;
        private CacheNode head;

        public Cache(int size) {
            this.size = size;
            this.count = 0;
        }
        
        int getSize(){
            return size;
        }

        private void putNewNodeAsFirst(CacheNode newNode) {
            if (head == null) {
                head = newNode;
            } else {
                // insertamos el nuevo nodo al inicio de la lista
                newNode.next = head;
                // actualizamos el head
                head = newNode;
            }
            count++;
        }

        private void removeLastNode() {
            if (head == null) {
                // cache vacio
                return;
            }
            
            CacheNode node = head;
            // nos vamos al final de la lista
            while(node.next != null)
                node =  node.next;
            
            // estando en el final, eliminamos el nodde
            removeNode(node);
        }

        // agregamos un elemento al cache
        void add(SlowWord element) {
            // creamos un nodo
            CacheNode newNode = new CacheNode(element);
            putNewNodeAsFirst(newNode);
            // si se supera el tamanio del cache, eliminamos el ultimo elemento
            if(count > size){
                removeLastNode();
            }
        }
        
        // eliminamos un nodo sin importar donde se encuentre
        private void removeNode(CacheNode oldNode){
            if(head == null)
                return;
            // si hay 1 o mas elementos. eliminamos el ultimo elemento
            CacheNode prev = null;
            CacheNode currentNode = head;
            // vamos al final de la lista
            while((currentNode != null) && (currentNode != oldNode)){
                // guardamos una referencia del nodo anterior
                prev = currentNode;
                currentNode = currentNode.next;
            }
            
            if(currentNode != null){
                // se encontro el elemento buscado
                if(prev == null){
                    // se trata del primer o el unico elemento del cache
                    head = currentNode.next;
                }else{
                    // eliminamos el registro
                    prev.next = currentNode.next;
                }
                count--;
            }
        }

        SlowWord getFromCache(String word) {
            // referencia temporal
            CacheNode node = head;
            while (node != null) {
                // si encontramos el nodo dentro del cache
                if (node.slowWord.word.equals(word)) {
                    // si lo encontramos, eliminamos de su posicion y colocamos el nodo al incio de la lista
                    removeNode(node);
                    putNewNodeAsFirst(node);
                    // cuando lo encuentra imprime hit
                    System.out.println("                  Hit");
                    return node.slowWord;
                }
            }
            // no se encontro el elemento. Creamos un nuevo elemento
            SlowWord sw = new SlowWord(word);
            // agregamos el element (palabra) al cache
            add(sw);
            // cuando no lo encuentra imprime Miss
            System.out.println("                   Miss");
            return sw;
        }
    }

    private void run() throws Exception {
        Set words = new HashSet();
        int nLines = 0;
        int nWords = 0;

        String path = "/com/ceva/javaelcamino/sec7/";

        // inicializamos el cache
        Cache cache = new Cache(128);

        BufferedReader br = new BufferedReader(new InputStreamReader(AG_CacheTest.class.getResourceAsStream(path + "quijote.txt"), "UTF-8"));
        String line = br.readLine();
        nLines++;
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                String word = st.nextToken().trim();
                if (word.length() > 0) {
                    // leemos una palabra
                    words.add(word);

                    // retornamos la referencia a SlowWord, si no la encuentra genera una nueva
                    // y retorna la referencia
                    SlowWord sw = cache.getFromCache(word);
                 
                    System.out.println(sw.getWord() + "(" + cache.getSize() + ")");
                    nWords++;
                }
            }
            line = br.readLine();
            nLines++;
        }
        br.close();

        System.out.println("Lines: " + nLines);
        System.out.println("Words: " + nWords);
        System.out.println("Distinct words: " + words.size());
    }

    public static void main(String[] args) throws Exception {
        new AG_CacheTest().run();
    }
}
