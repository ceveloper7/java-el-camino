package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * Analzamos las columnas d la tabla company
 */
public class DatabaseMetaDataTest2 {
    public static void main(String[] args) throws Exception {
        Connection conn = DBUtil.getConnection();

        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.println("Columnas de la tabla company:");
        ResultSet rs = dbmd.getColumns(null, "APP", "COMPANY", null);
        while (rs.next()) {
            System.out.printf("TABLE_NAME: %s, COLUMN_NAME: %s, DATA_TYPE: %s, COLUMN_SIZE: %d, TYPE_NAME: %s\n",
                    rs.getString("TABLE_NAME"), rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"), rs.getInt("COLUMN_SIZE"), rs.getString("TYPE_NAME"));
        }
        rs.close();
    }
}
