package com.ceva.section4.apijava1;

import java.util.StringTokenizer;

public class GTextFormatter {
    public static void main(String[] args) {
        String text = "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque rhoncus consequat enim eu tincidunt. Suspendisse auctor tellus quis enim consequat, quis vestibulum massa fringilla. In hac habitasse platea dictumst. Suspendisse consequat tristique sapien, sed auctor erat commodo ac. Nulla aliquam mi sit amet neque commodo accumsan. Proin elementum leo ac libero blandit, ac congue arcu interdum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nulla tempor libero eget volutpat lacinia. Pellentesque laoreet eros non nunc venenatis gravida. Duis ac nunc nec tortor accumsan dictum efficitur vitae augue. Sed posuere erat id nulla gravida vestibulum. Proin id odio ac dolor pulvinar scelerisque at nec est. Nulla ante leo, tristique sed sagittis ut, dignissim id purus. Suspendisse metus neque, varius sed facilisis quis, blandit in quam.";
        // construimos la regla
        int lineWidth = 40;
        for(int n = 0; n<lineWidth; n++){
            // en cada multiplo de 10 escribimos el numero de la columna
            if(((n+1)%10) == 0){
                System.out.print(((n/10)+1)%10);
            }else{
                System.out.print(" ");
            }
        }
        System.out.println();

        for(int n=0; n<lineWidth; n++){
            // n%10 retorna un valor entre 0-9
            // +1 es para que inicie desde el numero 1
            System.out.print((n+1)%10);
        }
        System.out.println();
        // subrayado
        for(int n=0; n < lineWidth; n++){
            System.out.print("-");
        }
        System.out.println();
        // fin de la regla

        // texto formateado
        int currentPosition = 0;
        boolean space = false; // space sera falso solo para la primera palabra qe se escriba
        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()){
            String theWord = st.nextToken();
            // validamos si superamos los 40 caracteres
            if((currentPosition + theWord.length())> lineWidth){
                // cambio de linea
                System.out.println();
                currentPosition = 0;
                space=false;
            }
            if(space){
                System.out.print(" ");
            }
            System.out.print(theWord);
            // currentPosition = currentPosition + longitud de la palabra + espacio a incluir
            currentPosition = currentPosition + theWord.length() + 1;
            space=true;
        }
        System.out.println();
    }
}
