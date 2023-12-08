package com.ceva.section20.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {
    public static void main(String[] args) throws Exception {
        URL myUrl = new URL("http://www.bautistajosecpaz.com/");

        BufferedReader in = new BufferedReader(new InputStreamReader(myUrl.openStream()));
        String inputInfo;
        while ((inputInfo = in.readLine()) != null){
            System.out.println(inputInfo);
        }
        in.close();
    }
}
