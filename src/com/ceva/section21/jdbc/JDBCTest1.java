package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCTest1 {
    public static void main(String[] args) throws Exception {
        Class.forName("org.apache.derby.jdbc.ClientDriver"); // cargamos driver en memoria
        //Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        try {
            Connection conn =
                    DriverManager.getConnection("jdbc:derby://localhost:1527/c:/java-development/javaPathDB");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        }
    }
}
