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
public class AH_CacheTest {
    
    class CacheNode {
        SlowWord slowWord;
        CacheNode next;
        
        CacheNode(SlowWord slowWord) {
            this.slowWord = slowWord;
            next = null;
        }
    }
    
    class Cache {
        int size;
        int count;
        private CacheNode head;
        
        Cache(int size) {
            this.size = size;
            count = 0;
        }
        
        private void addNode(CacheNode newNode) {
            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            count++;
        }
        
        private void removeLastNode() {
            if (head == null)
                return;
            CacheNode node = head;
            while (node.next != null)
                node = node.next;
            removeNode(node);
        }
        
        private void removeNode(CacheNode old) {
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
            CacheNode newNode = new CacheNode(element);
            
            addNode(newNode);
            if (count > size) {
                removeLastNode();
            }
        }
        
        SlowWord get(String word) {
            CacheNode node = head;
            while (node != null) {
                if (node.slowWord.word.equals(word)) {
                    removeNode(node);
                    addNode(node);
                    System.out.println("                     Hit");
                    return node.slowWord;
                }
                node = node.next;
            }
            
            SlowWord sw = new SlowWord(word);
            add(sw);
            System.out.println("                     Miss");
            return sw;
        }
        
        int getSize() {
            return count;
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
                new InputStreamReader(AH_CacheTest.class.getResourceAsStream(path + "quijote.txt"), "UTF-8"));
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
        new AH_CacheTest().run();
    }
}
