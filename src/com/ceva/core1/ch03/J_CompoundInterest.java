package com.ceva.core1.ch03;

public class J_CompoundInterest {
    private final double START_RATE = 5;
    private final int NRATES = 6;
    private final int NYEARS = 10;

    double[] interestRate = new double[NRATES];
    double[][] balances = new double[NYEARS][NRATES];

    public static void main(String[] args) {
        J_CompoundInterest compoundInterest = new J_CompoundInterest();
        compoundInterest.printHeader();
        compoundInterest.computeInterest();
        compoundInterest.printBalanceArray();
    }

    private void printHeader(){
        for(int j = 0; j < interestRate.length; j++){
            interestRate[j] = (START_RATE + j) / 100;
        }

        for(int j = 0; j < interestRate.length; j++){
            System.out.printf("%9.0f%%", 100*interestRate[j]);
        }
        System.out.println();
    }

    private void computeInterest(){

        // primera fila valor 10000
        for(int j = 0; j < balances[0].length; j++){
            balances[0][j] = 10000;
        }

        // calcular el interes para anios futuros
        for(int i = 1; i < balances.length; i++){
            for(int j = 0; j < balances[i].length; j++){
                // obtenemos el balance anterior de la fila previa
                double oldBalance = balances[i-1][j];

                // calculamos el interes
                double interest = oldBalance * interestRate[j];

                // calculamos el balance del anio
                balances[i][j] =  oldBalance + interest;
            }
        }
    }

    private void printBalanceArray(){
        // get balance row
        for(double[] row : balances){
            // print row item
            for(double b : row){
                System.out.printf("%,10.2f", b);
            }
            System.out.println();
        }
    }
}
