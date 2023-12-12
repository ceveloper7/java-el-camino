package com.ceva.section21.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCTest2 {

    private static Connection getConnection()throws SQLException{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }
        catch(ClassNotFoundException ex){
            throw new SQLException(ex);
        }
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/c:/java-development/javaPathDB");
        return conn;
    }

    private static int getCountRecords(Connection conn, String query) throws SQLException{
        int result;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if(rs.next()){
            result = rs.getInt(1);
        }
        else{
            result = 0;
        }
        rs.close();
        stmt.close();
        return result;
    }
    public static void main(String[] args) throws Exception {

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id_tableA, code from tableA");
            while (rs.next()) {
                int id_tableA = rs.getInt("id_tableA");
                String code = rs.getString("code");
                System.out.println("id: " + id_tableA + ", code: " + code);
            }
            int count = getCountRecords(conn, "SELECT count(*) from tableA");
            System.out.println("Number of records: " + count);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        }
    }
}
