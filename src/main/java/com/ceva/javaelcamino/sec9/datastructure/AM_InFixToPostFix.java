/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

import java.util.StringTokenizer;

/**
 *
 * @author PC
 */
public class AM_InFixToPostFix {
    
    // funcion que retorna true si el argumento es un digito
    private boolean isDigit(char ch){
        return (ch > '0') && (ch <= '9');
    }
    
    // recorremos cada uno de los caracteres del String para confirmar que es un numero
    // Excepciones: el primera caracter puede ser signo negativo. Un caracter intermedio puede ser punto decimal
    private boolean isNumber(String str){
        // variable que almacena la posicion donde esta el punto decimal. -1 significa q no se encontro el punto
        int dot = -1;
        // variable que almacena el numero de digitos encontrados
        int nDigits = 0;
        for(int n = 0; n < str.length(); n++){
            char ch = str.charAt(n);
            // validamos si contiene punto decimal
            if(ch == '.'){
                // si encontramos un punto y dot no es -1, significa q la cadena contiene un segundo . que es un error
                // si encontramos un punto y si la posicion que verifico es igual al ultimo caracter, entoncec error
                if((dot != -1) || (n > (str.length()-1))){
                    return false;
                }
                // establecemos donde esta el punto
                dot = n;
            }
            // validamos si contiene signo
            else if(ch == '-'){
                // si no estamos en la primera posicion
                if( n != 0){
                    return false; // solo se acepta el signo - en la priemra posicion si no es asi error
                }
            }
            // si no es un digito
            else if(!isDigit(ch)){
                return false;
            } else{
                // encontramos un digito
                nDigits++;
            }
        }
        return nDigits > 0;
    }
    
    // funcion que valida si en la expresion viene un operador
    private boolean isOperator(String s){
        return "+".equals(s)|| "-".equals(s) || "*".equals(s) || "/".equals(s);
    }
    
    // funcion que establece la prioridades de precedencia de operadores
    private int operatorPrecedence(String s){
        if("+".equals(s) || "-".equals(s)){
            return 1;
        }else if("*".equals(s) || "/".equals(s)){
            return 2;
        }
        return 0;
    }
    
    public String toPostFix(String expr){
        StringBuilder result = new StringBuilder();
        AK_Stack stack = new AK_Stack(100);
        
        // asumimos que cada componete esta separado por un espacio
        StringTokenizer st = new  StringTokenizer(expr, " ");
        while(st.hasMoreTokens()){
            // obtenemos el primer elemento
            String s = st.nextToken();
            // validamos si el elemento es un parentesis abierto
            if("(".equals(s)){
                stack.push(s);
            } // valido si es un numero lo agregamos a result
            else if(isNumber(s)){
                // hacemos que result tenga espacios
                if(result.length() > 0){
                    result.append(" ");
                }
                result.append(s);
            }// valido si el elemento es un operador matematico
            else if(isOperator(s)){
                // si stack no esta vacio,reviso el ultimo elemento del stack
                if(stack.size() > 0){
                    String peek = (String)stack.peek();
                    // hacemos pop() solo si hay elemento y si se cumplen las siguientes reglas:
                    // que sea un operador lo que se encuentra en el stack
                    // la precedencia del ultimo elemento mientras sea mayor o igual a la precedencia del
                    // operador que encontramos ene l stack
                    // mientras que no sea un parentesis "("
                    while((stack.size() > 0) && 
                            isOperator(peek) &&
                            (operatorPrecedence(peek) >= operatorPrecedence(s)) &&
                            !"(".equals(peek)){
                        if(result.length() > 0){
                            result.append(" ");
                        }
                        // agrego el elemento del stack
                        result.append(peek);
                        peek = (String)stack.pop();
                    }
                }
                // guardo el elemeto que encontre en el stack
                stack.push(s);
            }// si el elemento es un parentesis cerrardo )"
            else if(")".equals(s)){
                // mientras haya elementos en el stack y no sea un parentesis abierto
                while((stack.size() > 0) && !"(".equals(stack.peek())){
                    // agrego al resultado
                    if(result.length() > 0){
                        result.append(" ");
                    }
                    result.append(stack.pop());
                }
                // hago pop de "(" al stack
                stack.pop();
            }
        }
        // se terminan de procesar todos los elements del stack
        while(stack.size() > 0){
            String s = (String)stack.pop();
            if(result.length() > 0){
                result.append(" ");
            }
            result.append(s);
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        AM_InFixToPostFix convertInfixToPostFix = new AM_InFixToPostFix();
        String result = convertInfixToPostFix.toPostFix("1 - 2 + 3");
        System.out.println(result);
    }
}
