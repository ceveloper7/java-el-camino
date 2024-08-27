package com.ceva.section9.estructurasdatos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ECacheTest {
    // clase que simula un nodo
    class CacheNode {
        SlowWord slowWord;
        CacheNode next;

        CacheNode(SlowWord slowWord) {
            this.slowWord = slowWord;
            next = null;
        }
    }

    // clase que hace mas eficiente el procesamiento de palabras de un archivo de texto.
    class Cache {
        int size; // tamanio del cache
        int count; // contador de palabras almacenadas
        private CacheNode head; // almacenamiento de datos

        Cache(int size) {
            this.size = size;
            count = 0;
        }

        private void addNode(CacheNode newNode) {
            if (head == null) {
                // newNode es el primer nodo
                head = newNode;
            } else {
                // newNode es igual a head para que se inserte al inicio d la lista
                newNode.next = head;
                head = newNode; // actualizamos head
            }
            count++; // incrementamos el nro de elementos
        }

        private void removeLastNode() {
            if (head == null)
                return;
            CacheNode node = head;
            // recorremos los nodos hasta llegar al ultimo de la lista para eliminarlo
            while (node.next != null)
                node = node.next;
            removeNode(node);
        }

        private void removeNode(CacheNode old) {
            // vacio el cache?
            if (head == null)
                return;
            CacheNode prev = null;
            CacheNode cur = head;

            while ((cur != null) && (cur != old)) {
                prev = cur;
                cur = cur.next;
            }
            if (cur != null) {
                if (prev == null)
                    head = cur.next;
                else
                    prev.next = cur.next;
                count--;
            }
        }

        void add(SlowWord element) {
            CacheNode newNode = new CacheNode(element); // creamos un nodo
            // Agregamos un nuevo nodo al Cache
            addNode(newNode);
            // validamos si el numero de elementos es mayor al tamanio permitido de la coleccion
            if (count > size) {
                removeLastNode(); // eliminamos el ultimo nodo
            }
        }

        SlowWord get(String word) {
            CacheNode node = head;
            while (node != null) {
                // buscamos la palabra en el Cache
                if (node.slowWord.word.equals(word)) {
                    // se encontro la palabra en el cache
                    removeNode(node); // eliminamos el nodo antiguo
                    addNode(node); // colocamos el nodo al inicio de la lista
                    System.out.println("                     Hit"); // cuando se encuentra la palabra en el Cache se imprime Hit
                    return node.slowWord; // devolvemos el resultado
                }
                // no encontro el elemento
                node = node.next;
            }
            // no se encontro la palabra en el Cache
            SlowWord sw = new SlowWord(word);
            // agregamos la palabra al Cache
            add(sw);
            System.out.println("                     Miss"); // la palabra no se encontrol en el cache
            return sw;
        }

        int getSize() {
            return count;
        }
    }

    // clase "lenta" que permite almacenar una palabra
    class SlowWord {
        String word;

        SlowWord(String word) {
            this.word = word;
            // como es una clase pesada, lo simulamos con una pausa
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        // retorna palabra para imprimir
        String getWord() {
            return word;
        }
    }

    private void run() throws Exception {
        // coleccion de palabras no repetidas en el archivo
        Set<String> words = new HashSet<>();

        int nLines = 0; // contador de lineas
        int nWords = 0; // contador de palabras

        // inicializamos el cache con 128 elementos.
        Cache cache = new Cache(128);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(ECacheTest.class.getResourceAsStream("/com/ceva/section7/apijava2/resources/test.txt")));
        // leemos linea x linea
        String line = br.readLine();
        nLines++;
        while (line != null) {
            // procesamos cada palabra
            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                String word = st.nextToken().trim();
                if (word.length() > 0) {
                    words.add(word);
                    // recuperamos la referencia al SlowWord, si no hay, generamos un nuevo SlowWord y obtenemos la referencia.
                    SlowWord sw = cache.get(word);
//                    SlowWord sw = new SlowWord(word);
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
        System.out.println("Distict words: " + words.size());
    }

    public static void main(String[] args) throws Exception {
        new ECacheTest().run();
    }
}
