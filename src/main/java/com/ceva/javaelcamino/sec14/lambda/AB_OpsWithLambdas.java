/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda;

/**
 *
 * @author PC
 */
public class AB_OpsWithLambdas {
    private interface Filter<T>{
        boolean filter(T data);
    }
    
    private static void filterData(int data[], Filter<Integer> f){
        for(Integer i : data){
            if(f.filter(i)){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int data[] = {1,2,3,4,5,6,7,8,9,10};
        // filtramos numeros pares
        filterData(data, (n)-> {
             return (n % 2) == 0;
        });
        // filtramos numeros impares
        filterData(data, n -> (n % 2) == 1);
        
        // definimos un bloque
        filterData(data, (n)->{
            if(n == 1){
                return false;
            }
            for(int i = 2; i < (n-1); i++){
                if((n%i)==0){
                    return false;
                }
            }
            return true;
        });
    }
}
