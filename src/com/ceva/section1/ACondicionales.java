package com.ceva.section1;

public class ACondicionales {
    // if-else anidado
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

    private static void studentGrade(int studentGrade){
        if(studentGrade >= 90){
            System.out.println("A");
        }else if(studentGrade >= 80){
            System.out.println("B");
        }else if(studentGrade >= 70){
            System.out.println("C");
        }else if(studentGrade >= 60){
            System.out.println("D");
        }else{
            System.out.println("F");
        }
    }

    private static void studentGradeTernaryOperator(int studentGrade){
        System.out.println(studentGrade>= 90 ? "A"
                : studentGrade>= 80 ? "B"
                : studentGrade >= 70 ? "C"
                : studentGrade >= 60 ? "D" : "F");
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
        studentGrade(75);
        //numeroMayor(9,2,8,1);
        //calificar(7);
    }
}
