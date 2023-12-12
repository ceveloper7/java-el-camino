package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {
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
        boolean error = false;
        try {
            Connection conn = getConnection();
            /*
             * por defecto cada operacion que se realiza con la conexion se considera una sola
             * transaccion
             * conn.setAutoCommit(false) deshabilita esa caracteristica de jdbc y hace que todas las
             * operaciones hechas con la conexion sean tratadas como una sola transaccion
             */
            conn.setAutoCommit(false);

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into tableA (id_tableA, code) values (4, '4')");
            stmt.executeUpdate("insert into tableB (id_tableB, code) values (4, 'cuatro')");
            if (error) {
//                throw new SQLException("Error simulado");

                // descartamos todos los cambios y regresamos las tablas sus valores originales
                conn.rollback();
                System.out.println("Error!");
                return;
            }
            conn.commit(); // hacemos los cambios de la transaccion permanentes
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        }
    }
}
