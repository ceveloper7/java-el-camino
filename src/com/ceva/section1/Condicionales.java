package com.ceva.section1;

public class Condicionales {
    private static void calificar(int nota){
        if(nota < 0 || nota >= 20){
            System.out.println("La nota no puede ser menor a 0 o mayor a 20");
        }else if(nota <= 5){
            System.out.println("Su calificion es D");
        }else if(nota <= 10){
            System.out.println("Su calificacion es C");
        }else if(nota <= 15){
            System.out.println("Su calificacion es B");
        }else if(nota <= 18){
            System.out.println("Su calificacion es A");
        }else{
            System.out.println("Su calificacion es A+.. Excelente!");
        }
    }

    // 9 2 8 1
    private static void numeroMayor(int a, int b, int c, int d){
        if (a > b) {
            if(a > c){
                if(a > d){
                    System.out.println("El numero mayor es " + a);
                }else{
                    System.out.println("El numero mayor es " + d);
                }
            }else if(c > d){
                System.out.println("El numero mayor es " + c);
            }else{
                System.out.println("El numero mayor es " + d);
            }
        }else if(b > c){
            if(b > d){
                System.out.println("El numero mayor es " + b);
            }else{
                System.out.println("El numero mayor es " + d);
            }
        }else{
            System.out.println("El numero mayor es " + c);
        }
    }
    public static void main(String[] args) {
        numeroMayor(9,2,8,1);
        //calificar(7);
    }
}
