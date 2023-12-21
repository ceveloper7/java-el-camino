package com.ceva.section25.xml;

import java.sql.Connection;
public class DBConnectionManagerTest {
    public static void main(String[] args) throws Exception {
        DBConnectionManager connectionManager = new DBConnectionManager(
                DBConnectionManagerTest.class.getResourceAsStream("/com/ceva/section25/xml/databaseConn.xml"));

        Connection conn = connectionManager.getConnection("dev");
        System.out.println("Conexion exitosa");
        /* ... */
        conn.close();
    }
}
