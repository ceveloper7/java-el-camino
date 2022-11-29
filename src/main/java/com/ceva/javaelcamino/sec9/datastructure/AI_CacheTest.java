/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
/**
 *
 * @author PC
 */
public class AI_CacheTest {
    
    class Cache {
        int size;
        private LinkedList<SlowWord> list;
        
        Cache(int size) {
            this.size = size;
            list = new LinkedList<>();
        }
        
        void add(SlowWord element) {
            list.addFirst(element);
            if (list.size() > size) {
                list.removeLast();
            }
        }
        
        SlowWord get(String word) {
            for(Iterator<SlowWord> it = list.iterator(); it.hasNext();){
                SlowWord sw = it.next();
                if(sw.word.equals(word)){
                    it.remove();
                    list.addFirst(sw);
                    System.out.println("                   Hit");
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
        String path = "/com/ceva/javaelcamino/sec7/";
        
        Cache cache = new Cache(128);
        
        BufferedReader br = new BufferedReader(
                new InputStreamReader(AI_CacheTest.class.getResourceAsStream(path + "quijote.txt"), "UTF-8"));
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
    
    public static void main(String[] args) throws Exception {
        new AI_CacheTest().run();
    }
}
