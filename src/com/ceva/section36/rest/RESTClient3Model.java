package com.ceva.section36.rest;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;

/**
 * Modelo de datos de la UI
 */
public class RESTClient3Model {
    private String columnNames[] = {"id_product", "Name", "Price", "Description"};
    // representa la informacion de los productos
    private List<ProductData> data;
    // manejo de los listener que escucha el modelo
    private List<RESTClient3ModelListener> listeners;

    // preparamos el HttpClient
    private final HttpClient httpClient = HttpClient.newBuilder()
            .build();

    // Cada operacion como loadData, insertData, etc, tendra su propio Thread de trabajo
    private Thread workThread;
    // coleccion Thread Safe, permite agregar y quitar informacion de manera simultanea
    LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    private String basePath;

    RESTClient3Model(String basePath) {
        this.basePath = basePath;
        data = new ArrayList<>();
        listeners = new LinkedList<>();
    }

    /**
     * Metodo que permite realizar una serie de tareas en un segundo plano sin
     * interrumpir el Thread del EDT
     * @param r
     */
    private void addWork(Runnable r) {
        if (workThread == null) {
            workThread = new Thread(() -> {
                while (true) {
                    try {
                        /**
                         * si intentamos quitar un elemento con take() y la lista esta vacia
                         * entonces el Thread se quedara congelado.
                         * Cuando llegue un nuevo registro continuara trabajando
                         */
                        Runnable element = workQueue.take();
                        // ejecutamos el elemento de trabajo
                        element.run();
                    } catch (InterruptedException e) {
                    }
                }
            });
            workThread.setDaemon(true);
            workThread.start();
        }
        workQueue.add(r);
    }

    String getColumnName(int index) {
        return columnNames[index];
    }

    int getColumnCount() {
        return columnNames.length;
    }

    int getRowCount() {
        return data.size();
    }

    ProductData getRow(int index) {
        return data.get(index);
    }

    int add(ProductData product) {
        data.add(product);
        // retorna el nro de renglon donde fue agregado
        return data.size() - 1;
    }

    private int findRow(int id_product) {
        int row = 0;
        for (ProductData p : data) {
            if (p.id_product == id_product) {
                return row;
            }
            row++;
        }
        return -1;
    }

    /**
     * La primera vez que se ejecuta el programa hay un tiempo de demora hasta que carga toda la info
     * teoricamente loadData esta siendo invocado dentro del EDT y no podemos quedarnos esperando
     * a recibir los datos, si lo hacemos cogelariamos la UI. Este aspecto siempre debemos
     * considerar cada vez que nos comuniquemos con un servicio externo como es el servicio REST
     *
     * Todos estos metodos como loadData, insertData, etc se van a invocar en un Thread separado
     */
    public void loadData() {
        addWork(() -> {
            HttpRequest request = HttpRequest.newBuilder(URI.create(basePath + "/product"))
                    .GET()
                    .setHeader("accept", "application/json")
                    .build();
            String result;
            try {
                // recibimos la respuesta del servicio REST
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if ((response.statusCode() / 100) == 2) { // 2xx OK
                    result = response.body();
                } else
                    result = null;
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
                result = null;
            }

            /**
             * Tarea: al leer los datos del servidor no es la mejor idea limpiar los datos actuales
             * y despues comenzar a parsear porque si algo falla, ya nos quedamos sin datos o con
             * datos a medias. Lo mejor seria pensarlo como una transaccion:
             * 1. Parsear los datos
             * 2. Armar la estructura
             * 3. Si todo salio bien, entonces lo reemplazamos con los datos actuales y refrescamos
             *    si algo sale mal pues no perdemos lo que teniamos antes de hacer el refresh
             *  Por lo tanto, la tarea es modificar el codigo para que sea seguro en esos casos.
             */
            if (result != null) {
                data.clear();

                // si result es distinto de null iniciamos el proceso de parse
                ProductData p = null;
                JsonParser parser = Json.createParser(new StringReader(result));
                String key = null;
                while (parser.hasNext()) {
                    // cada vez que obtenemos un producto, lo agregamos a la coleccion
                    JsonParser.Event event = parser.next();
                    switch (event) {
                        case START_OBJECT:
                            p = new ProductData();
                            break;
                        case END_OBJECT:
                            if (p != null) {
                                add(p);
                                p = null;
                            }
                            break;
                        case KEY_NAME:
                            key = parser.getString();
                            break;
                        case VALUE_STRING:
                            if ("name".equals(key)) {
                                p.name = parser.getString();
                            } else if ("description".equals(key)) {
                                p.description = parser.getString();
                            }
                            break;
                        case VALUE_NUMBER:
                            if ("id_product".equals(key)) {
                                p.id_product = parser.getInt();
                            } else if ("price".equals(key)) {
                                p.price = parser.getBigDecimal().doubleValue();
                            }
                            break;
                    }
                }
                parser.close();

                // informamos a los listeners que los datos han cambiado
                for (RESTClient3ModelListener listener : listeners) {
                    listener.dataChanged();
                }
            }
        });
    }

    public void insertData(String name, double price, String description) {
        addWork(() -> {
            // creamos un paquete Json con la info a guardar en la BD
            JsonBuilderFactory factory = Json.createBuilderFactory(null);
            // creamos el payload
            JsonObject json = factory.createObjectBuilder()
                    .add("name", name)
                    .add("price", price)
                    .add("description", description)
                    .build();

            // construimos el request para enviarlos al servidor
            HttpRequest request = HttpRequest.newBuilder(URI.create(basePath + "/product"))
                    // construimos el body del mensaje
                    .PUT(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .setHeader("accept", "application/json")
                    .build();
            String result;
            try {
                // manejo de eventos
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if ((response.statusCode() / 100) == 2) { // 2xx OK
                    result = response.body();
                } else {
                    result = null;
                    System.out.println("ERROR: " + response.statusCode() + " " + response.body());
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
                result = null;
            }

            // hacemos parse del resultado. Leemos el sgte codigo del serve
            // response.getOutputStream().println("{\"result\":\"success\",\"id_product\":" + id_product + "}");
            // obtenemos el id_product
            if (result != null) {
                int id_product = -1;
                JsonParser parser = Json.createParser(new StringReader(result));
                String key = null;
                while (parser.hasNext()) {
                    JsonParser.Event event = parser.next();
                    switch (event) {
                        case KEY_NAME:
                            key = parser.getString();
                            break;
                        case VALUE_NUMBER:
                            if ("id_product".equals(key)) {
                                id_product = parser.getInt();
                            }
                            break;
                    }
                }
                parser.close();

                if (id_product != -1) {
                    ProductData p = new ProductData();
                    p.id_product = id_product;
                    p.name = name;
                    p.price = price;
                    p.description = description;
                    // obtenemos la fila en la que sera insertado el registro
                    int row = add(p);

                    // reportamos que se inserto un renglon
                    for (RESTClient3ModelListener listener : listeners) {
                        listener.dataInserted(p, row);
                    }
                }
            }
        });
    }

    public void updateData(int id_product, String name, double price, String description) {
        addWork(() -> {
            JsonBuilderFactory factory = Json.createBuilderFactory(null);
            // construimos el json
            JsonObject json = factory.createObjectBuilder()
                    .add("id_product", id_product)
                    .add("name", name)
                    .add("price", price)
                    .add("description", description)
                    .build();

            // enviamos el json
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(basePath + "/product/" + id_product))
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .setHeader("accept", "application/json")
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if ((response.statusCode() / 100) == 2) { // 2xx OK
                    int row = 0;
                    for (ProductData p : data) {
                        // actualizamos los datos dle product
                        if (p.id_product == id_product) {
                            p.name = name;
                            p.description = description;
                            p.price = price;

                            // volvemos a dibujar la UI
                            for (RESTClient3ModelListener listener : listeners) {
                                listener.dataInserted(p, row);
                            }
                            break;
                        }
                        // Recorremos la coleccion y vamos incrementando el nro de renglon
                        row++;
                    }
                } else {
                    System.out.println("Error code: " + response.statusCode() + " " + response.body());
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            }
        });
    }

    public void deleteData(int id_product) {
        addWork(() -> {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(basePath + "/product/" + id_product))
                    .DELETE()
                    .setHeader("accept", "application/json")
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if ((response.statusCode() / 100) == 2) { // 2xx OK
                    int row = 0;
                    for (Iterator<ProductData> it=data.iterator(); it.hasNext(); ) {
                        ProductData p = it.next();
                        if (p.id_product == id_product) {
                            it.remove();

                            for (RESTClient3ModelListener listener : listeners) {
                                listener.dataDeleted(p, row);
                            }
                            break;
                        }
                        row++;
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            }
        });
    }

    public void addListener(RESTClient3ModelListener listener) {
        listeners.add(listener);
    }
}
