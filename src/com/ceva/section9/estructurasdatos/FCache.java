package com.ceva.section9.estructurasdatos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Ejemplo del manejo de Cache con LinkedList
 */
public class FCache {
    class Cache {
        int size;
        private java.util.LinkedList<SlowWord> list;

        Cache(int size) {
            this.size = size;
            list = new java.util.LinkedList<>();
        }

        void add(SlowWord element) {
            list.addFirst(element);
            if (list.size() > size) {
                list.removeLast();
            }
        }

        SlowWord get(String word) {
            for (Iterator<SlowWord> it = list.iterator(); it.hasNext(); ) {
                SlowWord sw = it.next();
                if (sw.word.equals(word)) {
                    it.remove(); // eliminamos el viejo nodo en el cache
                    list.addFirst(sw); // agregamos el nodo al principio de la lista
                    System.out.println("                     Hit");
                    return sw;
                }
            }

            SlowWord sw = new SlowWord(word);
            add(sw);
            System.out.println("                     Miss");
            return sw;
        }

        int getSize() {
            return list.size();
        }
    }

    class SlowWord {
        String word;

        SlowWord(String word) {
            this.word = word;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }

        String getWord() {
            return word;
        }
    }

    private void run() throws Exception {
        Set<String> words = new HashSet<>();

        int nLines = 0;
        int nWords = 0;

        Cache cache = new Cache(128);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(FCache.class.getResourceAsStream("/com/ceva/section7/apijava2/resources/test.txt")));
        String line = br.readLine();
        nLines++;
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                String word = st.nextToken().trim();
                if (word.length() > 0) {
                    words.add(word);

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

    public static void main(String[] args)throws Exception {
        new FCache().run();
    }
}
