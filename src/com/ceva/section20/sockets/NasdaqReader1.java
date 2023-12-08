package com.ceva.section20.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
public class NasdaqReader1 {
    public static void main(String[] args) throws IOException {
        URL nasdaq = new URL("ftp://ftp.nasdaqtrader.com/SymbolDirectory/nasdaqlisted.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(nasdaq.openStream()));
        String inputLine;
        while ((inputLine = br.readLine()) != null)
            System.out.println(inputLine);
        br.close();
    }
}
