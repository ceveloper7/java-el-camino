package com.ceva.section17.multithread;

public class FutureTest1 {
    private static String proc1(){
        String res = "";
        for(char c = 'a'; c <= 'c'; c++){
            // nos tomamos 1 segundo para generar la letra
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){}
            res = res + c;
        }
        return res;
    }

    private static  String proc2(){
        String res = "";
        for(int n = 1; n <= 3; n++){
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException ex){}
            res = res + n;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print(proc1());
        System.out.println(proc2());
    }
}
