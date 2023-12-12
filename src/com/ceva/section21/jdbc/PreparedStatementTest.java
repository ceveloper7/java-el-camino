package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class PreparedStatementTest {
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            return null;
        }
        Connection conn =
                DriverManager.getConnection("jdbc:derby://localhost:1527/c:/java-development/javaPathDB");
        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();

            int id = 5;
            String code = "Peter's";

            PreparedStatement pstmt = conn.prepareStatement("insert into tableA (id_tableA, code) values (?, ?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, code);
            pstmt.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        }
    }
}
