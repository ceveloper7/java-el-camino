package com.ceva.core1.ch03;

public class C_ControlFlow {
    private double sales = 0.0;
    private double bonus = 0;
    private double targetSales = 0.0;
    private String comment = "";

    public static void main(String[] args) {
        C_ControlFlow cf = new C_ControlFlow();
        cf.conditionalStatements();
    }

    private void conditionalStatements(){
        sales = 1800;
        targetSales = 2000;

        // if - else
        if(sales >= targetSales) {
            comment = "Satisfactorias";
            bonus = 100 + 0.01 * (sales-targetSales);
        }else{
            comment = "Insatisfactorias";
        }

        // if - else if
        if(sales >= 2 * targetSales){
            comment = "Asombrosas";
            bonus = 1000;
        }else if(sales >= 1.5 * targetSales){
            comment = "Muy buenas";
            bonus = 500;
        }else if(sales >= targetSales){
            comment = "Satisfactorias";
            bonus = 100;
        }else{
            comment = "para despedirlo";
            bonus = 0;
        }

        System.out.printf("Las ventas de %,#8.2f fueron %s, recibe un bono de %,#5.2f", sales, comment, bonus);
    }
}
