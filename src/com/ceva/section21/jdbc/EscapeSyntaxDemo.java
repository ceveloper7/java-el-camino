package com.ceva.section21.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EscapeSyntaxDemo {
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

    public static void main(String[] args) throws Exception {
        Connection conn = getConnection();

        // retornamos maximo 10 registros a partir de una posicion especificada
        PreparedStatement pstmt = conn.prepareStatement("select symbol from company order by symbol {LIMIT 10 OFFSET ?}");
        // a parti de la posicion 1000
        pstmt.setInt(1, 1000);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();

        conn.close();
    }
}
