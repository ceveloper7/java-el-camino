package com.ceva.buildingblocks.operators2;

import java.io.File;

/*
 * Operadores de comparacion
 */
public class Lesson5 {

    private void case1(){
        boolean a = false;
        boolean b = (a = true);
    }

    // comprando objetos
    private void case2(){
        var file1 = new File("test.txt");
        var file2 = new File("test2.txt");
        var file3 = file2;

        System.out.println((file1 == file2)); // print false xq son dos objetos con referencia de memoria distintas

        System.out.println((file2 == file3)); // print true xq ambas variables apuntan a la misma direccion o referencia de memoria

        Number val = 5;
        Integer val1 = 9;
        Object val2 = 11;

        case3(val);
        case3(val1);
        //case3(val2); no compila
    }

    // La clase Integer tiene a la clase Number como padre o super class
    private void case3(Number t){
        if(t instanceof Integer){
            System.out.println((Integer)t + " o clock");
        }else{
            System.out.println(t);
        }
    }


    public static void main(String[] args) {
        Lesson5 lesson5 = new Lesson5();
        lesson5.case2();
    }
}
