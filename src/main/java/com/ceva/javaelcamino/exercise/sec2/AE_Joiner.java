/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.exercise.sec2;

import java.util.Iterator;

/**
 *
 * @author PC
 */
public class AE_Joiner {
    private final String token;
    
    private AE_Joiner(String token){
        this.token = token;
    }
    
    private AE_Joiner(AE_Joiner joiner){
        this.token = joiner.token;
    }
    
    public static AE_Joiner on(char token){
        return new AE_Joiner(String.valueOf(token));
    }
    
    public String appendTo(Iterator<? extends Object> parts){
        StringBuilder sb = new StringBuilder();
        if(parts.hasNext()){
            sb.append(toString(parts.next()));
            while(parts.hasNext()){
                sb.append(token);
                sb.append((String)toString(parts.next()));
            }
        }
        return sb.toString();
    }
    
    public final String join(Iterable<? extends Object> parts){
        return appendTo(parts.iterator());
    }
    
    
    CharSequence toString(Object part){
        return (part instanceof CharSequence) ? (CharSequence)part : part.toString();
    }
    
    public AE_Joiner skipNulls(){
        return new AE_Joiner(this){
            @Override
            public String appendTo(Iterator<? extends Object> parts) {
                StringBuilder sb = new StringBuilder();
                while(parts.hasNext()){
                    Object part = parts.next();
                    if(part != null){
                        sb.append(AE_Joiner.this.toString(part));
                        break;
                    }
                }
                while(parts.hasNext()){
                    Object part = parts.next();
                    if(part != null){
                        sb.append(token);
                        sb.append(AE_Joiner.this.toString(part));
                    }
                }
                return sb.toString();
            }
        };
    }
    
    public AE_Joiner useForNull(String nullText){
        return new AE_Joiner(this){
            @Override
            CharSequence toString(Object part) {
                return (part == null) ? nullText : AE_Joiner.this.toString(part);
            }
        };
    }
    
    public static void main(String[] args) {
        String prueba = AE_Joiner.on('-').useForNull("0").join(java.util.Arrays.asList(1,2, null, 6, 7, null, 8,9));
        System.out.println(prueba);
    }
}
