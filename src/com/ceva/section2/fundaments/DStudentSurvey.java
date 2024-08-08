package com.ceva.section2.fundaments;

public class DStudentSurvey {

    public static void main(String[] args) {
        int[] responses= {1, 2, 5, 4, 3, 5, 2, 1, 3, 3, 1, 4, 3, 3, 3, 2, 3, 3, 2, 14};
        int[] frecuency = new int[6];

        // leemos las respuesta de array responses
        for(int answer = 0; answer < responses.length; answer++){
            try{
                // incrementamos uno de los contadores de frecuency[1] a frecuency[5]
                frecuency[responses[answer]]++;
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println(e);
                System.out.printf("   responses[%d] = %d%n%n", answer, responses[answer]);
            }
        }

        System.out.printf("%s%10s%n", "Rating", "Frecuency");

        // output each array element's value
        for (int rating = 1; rating < frecuency.length; rating++) {
            System.out.printf("%6d%10d%n", rating, frecuency[rating]);
        }
    }
}
