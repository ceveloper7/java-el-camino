package com.ceva.section36.rest;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.Locale;
import javax.json.Json;
import javax.json.stream.JsonParser;


/**
 * Programa que obtiene la lista de productos
 */
public class RESTClient2 {

    // definimos al objeto Product
    private static class Product {
        private int id_product;
        private String name;
        private String description;
        private double price;

        private void set(String name, String value) {
            if ("name".equals(name))
                this.name = value;
            else if ("description".equals(name))
                description = value;
        }

        private void set(String name, int value) {
            if ("id_product".equals(name))
                id_product = value;
        }

        private void set(String name, double value) {
            if ("price".equals(name))
                price = value;
        }
    }

    private static void parseTest1() throws Exception {
        URL url = new URL("http://localhost:8080/SimpleStore-Restfull/product");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("accept", "application/json");

        JsonParser parser = Json.createParser(new InputStreamReader(conn.getInputStream()));
        Product p = null;
        String key = null;
        while (parser.hasNext()) {
            // iniciamos el proceso de Parser
            JsonParser.Event event = parser.next();
            switch (event) {
                /**
                 * START_OBJET -> el resultado se va a parecer a un arreglo de objetos
                 * por lo que, lo primero que recibimos es el inicio de un arreglo, luego
                 * una serie de objetos, luego fin del arreglo
                 * Cuando comienza un objeto sabemos que se trata de un product
                 */
                case START_OBJECT:
                    p = new Product();
                    break;
                /**
                 * se termina de describir al objeto, y tenemos toda la info del producto
                 */
                case END_OBJECT:
                    // formato de la salida de la info del product
                    System.out.printf("%d\t%s\t%s\t%f\n", p.id_product, p.name, p.description, p.price);
                    p = null;
                    break;
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    String value = parser.getString();
                    p.set(key, value);
                    break;
                case VALUE_NUMBER:
                    if (parser.isIntegralNumber())
                        p.set(key, parser.getInt());
                    else
                        p.set(key, parser.getBigDecimal().doubleValue());
                    break;
            }
        }
        parser.close();
    }

    private static void parseTest2() throws Exception {
        /**
         * HttpClient -> permite recibir solicitudes y enviar respuestas
         */
        HttpClient httpClient = HttpClient.newBuilder().build();
        // definimos las caracteristicas de la solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/SimpleStore-Restfull/product"))
                .GET()
                .setHeader("accept", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String result;
        // validamos si se trata de un status code de la clase 200
        if ((response.statusCode() / 100) == 2) { // 2xx OK
            result = response.body();
        } else
            result = null;

        if (result != null) {
            JsonParser parser = Json.createParser(new StringReader(result));
            Product p = null;
            String key = null;
            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                switch (event) {
                    case START_OBJECT:
                        p = new Product();
                        break;
                    case END_OBJECT:
                        System.out.printf("%d\t%s\t%s\t%f\n", p.id_product, p.name, p.description, p.price);
                        p = null;
                        break;
                    case KEY_NAME:
                        key = parser.getString();
                        break;
                    case VALUE_STRING:
                        String value = parser.getString();
                        p.set(key, value);
                        break;
                    case VALUE_NUMBER:
                        if (parser.isIntegralNumber())
                            p.set(key, parser.getInt());
                        else {
                            p.set(key, NumberFormat.getNumberInstance().format(parser.getBigDecimal().doubleValue()));
                        }
                        break;
                }
            }
            parser.close();
        }
    }

    public static void main(String[] args) throws Exception {

        parseTest2();
    }
}
