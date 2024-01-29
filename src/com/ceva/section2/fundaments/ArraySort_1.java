package com.ceva.section2.fundaments;

/**
 * <p><b>Ordenamiento de numeros: Ordenamiento Burbuja</b></p>
 * Dado un array de numeros, el siguiente algoritmo realiza un ordenamiento del array
 * en forma ascendente. Este algoritmo de burbuja es muy ineficiente.
 * <pre>[9,1,34,20,2,7,12,4,6,8]</pre>
 * <pre>[1,2,4,6,7,8,9,12,20,34]</pre>
 * <p>
 *     Si dos numeros de la lista estan en el orden equivocado, lo intercambiamos de posicion,
 *     en el momento en que no existan dos numeros en el orden inverso, la lista esta ordenada.
 * </p>
 */
public class ArraySort_1 {
    private static void sort(int list[]){
        for(int i : list){
            System.out.print(" " + i);
        }
        System.out.println();

        for(int i = 0; i < list.length; i++){
            for(int j = 0; j < list.length; j++){
                if(list[i] < list[j]){
                    int tmp = list[i];
                    list[i] = list[j];
                    list[j] = tmp;
                }
            }
        }

        for(int i : list){
            System.out.print(" " + i);
        }
        System.out.println();
    }

    private static void sort_v1(int[] list){
        for(int i : list){
            System.out.print(" " + i);
        }
        System.out.println();

        // contamos las iteraciones
        int iterations = 0;
        for(int i = 0; i < list.length; i++){
            for(int j = i+1; j < list.length; j++){
                if(list[i] > list[j]){
                    int tmp = list[i];
                    list[i] = list[j];
                    list[j] = tmp;
                }
                iterations++;
            }
        }

        for(int i : list){
            System.out.print(" " + i);
        }
        System.out.println();
        System.out.println("Terminado en " + iterations + " iteraciones");
    }
    public static void main(String[] args) {
        int array[] = {9,1,34,20,2,7,12,4,6,8};
        sort_v1(array);
    }
}
