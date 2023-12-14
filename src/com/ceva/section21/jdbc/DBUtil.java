package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            return null;
        }
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/c:/java-development/javaPathDB");
        return conn;
    }

}
