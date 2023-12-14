package com.ceva.section21.jdbc;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Mostramos la informacion de las columnas que contiene unResultSet
 */
public class DatabaseMetaDataTest3 {
    public static void main(String[] args) throws Exception {
        Connection conn = DBUtil.getConnection();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id_company, symbol, name from company");
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("Columnas de este query:");
        for (int n=0; n<rsmd.getColumnCount(); n++) {
            System.out.printf("COLUMN_NAME: %s, COLUMN_TYPE: %d, COLUMN_TYPENAME: %s\n",
                    rsmd.getColumnName(n+1), rsmd.getColumnType(n+1), rsmd.getColumnTypeName(n+1));
        }
        rs.close();
    }
}
