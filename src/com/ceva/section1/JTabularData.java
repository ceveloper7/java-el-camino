package com.ceva.section1;
// 3.22
public class JTabularData {
    private void printTabularData(){
        int[][] tab = new int[5][3];

        for(int row = 0; row < tab.length; row++){
            int t = 10;
            for(int column = 0; column < tab[row].length; column++){
                tab[row][column] = t * (row + 1);
                t *= 10;
            }
        }

        System.out.printf("%8s %8s %8s%8s%n", "N", "10*N", "100*N", "1000*N");
        for(int data = 0; data < tab.length; data++){
            System.out.printf("%8d", data+1);
            for(int i : tab[data]){
                System.out.printf("%8d", i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        JTabularData jt = new JTabularData();
        jt.printTabularData();
    }
}
