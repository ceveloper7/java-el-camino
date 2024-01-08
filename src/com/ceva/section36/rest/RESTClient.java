package com.ceva.section36.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Programa que obtiene la lista de productos en formato JSON
 */
public class RESTClient {
    public static void main(String[] args) throws Exception {
        //URL url = new URL("http://localhost:8080/SimpleStore-Restfull/product");
        URL url = new URL("http://localhost:8080/SimpleStore-RestJaxRs/rest/product");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }
}
