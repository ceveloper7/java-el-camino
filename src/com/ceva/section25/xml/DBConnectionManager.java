package com.ceva.section25.xml;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class DBConnectionManager {
    // map de conexion que nos permite encontrar un tipo de conexion
    // la llave de map sera el nombre de la conexion (dev, test, prod )
    private Map<String,ConnectionRec> connectionMap;

    /**
     * ConnectionRec almacena la informacion de cada conexion
     * como clase interna ConnectRect nadie ajeno a DBConnetionManager concoera estos datos
     */
    private class ConnectionRec {
        private String name;
        private String driverName;
        private String url;
        private String usr;
        private String pwd;
    }

    public DBConnectionManager(InputStream xmlConfig) throws IOException {
        Map<String,ConnectionRec> buildMap = new HashMap<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlConfig, new DefaultHandler() {
                ConnectionRec curConnectionRec = null;
                StringBuilder text = new StringBuilder();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // validamos si el tag se llama connection
                    if ("connection".equals(qName)) {
                        curConnectionRec = new ConnectionRec(); // construimos un nuevo ConnectionRec
                        curConnectionRec.name = attributes.getValue("name");
                    }
                    // cuando se llega al node, inicializamos text
                    text.setLength(0);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    /**
                     * cuando recibamos endElement para connection, significa que tenemos toda la
                     * informacion disponible como driver, url, usr, pwd
                     */
                    if ("connection".equals(qName)) {
                        if (curConnectionRec != null) {
                            // agregamos la conexion al diccionario
                            buildMap.put(curConnectionRec.name, curConnectionRec);
                        }
                        curConnectionRec = null;
                    } else if ("driver".equals(qName)) {
                        String str = text.toString();
                        if (str.length() == 0)
                            str = null;
                        curConnectionRec.driverName = str;
                    } else if ("url".equals(qName)) {
                        String str = text.toString();
                        if (str.length() == 0)
                            str = null;
                        curConnectionRec.url = str;
                    } else if ("usr".equals(qName)) {
                        String str = text.toString();
                        if (str.length() == 0)
                            str = null;
                        curConnectionRec.usr = str;
                    } else if ("pwd".equals(qName)) {
                        String str = text.toString();
                        if (str.length() == 0)
                            str = null;
                        curConnectionRec.pwd = str;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    // eliminamos los espacios que hay antes y despues de la cadena
                    text.append(new String(ch, start, length).trim());
                }
            });
            // le pasamos toda la informacion de las conexion a connectionMap
            connectionMap = buildMap;
        } catch (SAXException e) {
            throw new IOException(e);
        } catch (ParserConfigurationException e) {
            throw new IOException(e);
        }
    }

    private Connection createConnection(ConnectionRec params) throws SQLException {
        if (params.driverName != null) {
            try {
                Class.forName(params.driverName);
            } catch (ClassNotFoundException e) {
                System.out.println("Error: Clase " + params.driverName + " no encontrada.");
                return null;
            }
        }
        return DriverManager.getConnection(params.url, params.usr, params.pwd);
    }

    public Connection getConnection(String name) throws SQLException {
        if (connectionMap != null) {
            ConnectionRec params = connectionMap.get(name);
            if (params != null)
                return createConnection(params);
        }
        return null;
    }
}
