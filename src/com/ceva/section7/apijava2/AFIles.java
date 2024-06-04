package com.ceva.section7.apijava2;

import java.io.File;

public class AFIles {
    private void createNewFile(){
        File file = new File("/home/ceva/archivo.txt");
        System.out.println(file.length());
    }

    private void listFiles(){
        File folder = new File("/home/ceva" + File.separator + "Documents"); // utilizando separator
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles){
            System.out.println(file.getName());
        }
    }


    private void createDirectory(){
        File file = new File(File.separator + "home" + File.separator + "ceva" + File.separator + "Documents" + File.separator + "java");
        file.mkdir();
    }

    public static void main(String[] args) {
        AFIles files = new AFIles();

        files.createDirectory();
        files.listFiles();
        files.createNewFile();
    }
}
