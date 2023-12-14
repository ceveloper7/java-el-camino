package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
public class DatabaseMetaDataTest1 {
    public static void main(String[] args) throws Exception {
        Connection conn = DBUtil.getConnection();

        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.println("Listado de todas las tablas:");
        ResultSet rs = dbmd.getTables(null, null, null, null);
        while (rs.next()) {
            System.out.printf("TABLE_CAT: %s, TABLE_SCHEM: %s, TABLE_NAME: %s, TABLE_TYPE: %s\n",
                    rs.getString("TABLE_CAT"), rs.getString("TABLE_SCHEM"), rs.getString("TABLE_NAME"), rs.getString("TABLE_TYPE"));
        }
        rs.close();

        System.out.println();
        System.out.println("Listado de todas las tablas en APP:");
        rs = dbmd.getTables(null, "APP", null, null);
        while (rs.next()) {
            System.out.printf("TABLE_CAT: %s, TABLE_SCHEM: %s, TABLE_NAME: %s, TABLE_TYPE: %s\n",
                    rs.getString("TABLE_CAT"), rs.getString("TABLE_SCHEM"), rs.getString("TABLE_NAME"), rs.getString("TABLE_TYPE"));
        }
        rs.close();
    }
}
