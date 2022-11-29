/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec9.datastructure;

import java.util.StringTokenizer;

/**
 * Recibimos del usuario un string que debemos descomponer para realizar la operacon
 */
public class AL_PostFijoCalculadora {
    private AK_Stack stack;
    
    public AL_PostFijoCalculadora(){
        stack = new AK_Stack(100);
    }
    /**
     * 1. Tomar el siguiente componente
     * 2. Si el componente es un numero, guardarlo en el stack
     * 3. Si el componente es un operador, realizo la operacion y guardo el resultado en el stack
     */
    
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
            }else{
                // encontramos un digito
                nDigits++;
            }
        }
        return nDigits > 0;
    }
    
    public boolean evaluate(String expr){
        StringTokenizer st = new StringTokenizer(expr, " ");
        while(st.hasMoreTokens()){
            String s = st.nextToken();
            if(isNumber(s)){
                double d = Double.parseDouble(s);
                stack.push(d);
            }else if("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)){
                // si encontramos un operador + - * / tomamos los dos ultimos elementos del stack
                // realizamos la operacion y guardamos el resultado
                Object o2 = stack.pop();
                Object o1 = stack.pop();
                // validamos qu haya dos numeros en el stack necesarios para realizar una operacion
                if(!(o2 instanceof Double) || !(o1 instanceof Double)){
                    System.out.println("Operator are not numbers");
                    return false;
                }
                Double d1 = (Double)o1;
                Double d2 = (Double)o2;
                
                switch(s){
                    case "+":
                        // hacemos la suma y guardamos el resultado en el stack
                        stack.push(d1+d2);
                        break;
                    case "-":
                        stack.push(d1-d2);
                        break;
                    case "*":
                        stack.push(d1*d2);
                        break;
                    case "/":
                        stack.push(d1/d2);
                        break;
                }
            }
        }
        
        return true;
    }
    
    public void printResult(){
        while(stack.size() > 0){
            Object o = stack.pop();
            System.out.println(o);
        }
    }
    
    public static void main(String[] args){
        AL_PostFijoCalculadora calc = new AL_PostFijoCalculadora();
        calc.evaluate("5 6 + 9 + 2 / 5 * -1 *");
        calc.printResult();
    }
}
