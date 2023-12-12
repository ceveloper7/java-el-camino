package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Programa que demuesta que un Statement no puede abrir mas de un ResultSet a la vez.
// Si tratamos de abrir mas de un resultset con el mismo statement no se producira ningun error
// sin embargo al crear el segundo resultset hara que el primer resultset se cierre
// esto se soluciona utilizando dos statements
public class MultiResultSetTest {
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

        Statement stmt1 = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select id_tableA, code from tableA");
        ResultSet rs2 = stmt2.executeQuery("select id_tableB, code from tableB");

        while (rs1.next()) {
            String code1 = rs1.getString("code");
            String code2;
            if(rs2.next()){
                code2 = rs2.getString("code");
            }else{
                code2 = null;
            }
            System.out.print(code1 + " ");
            System.out.println(code2 + " ");
            //System.out.print(rs1.getString("code") + " " );
            //System.out.println(rs2.getString("code"));
        }
        rs1.close();
        rs2.close();
        stmt1.close();
        stmt2.close();
        conn.close();
    }
}
