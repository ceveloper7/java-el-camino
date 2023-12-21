package com.ceva.section24.enums;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * solo un ejemplo de enum con tres constantes.. pero no para usarlo en produccion
 */
public enum DBConnection {
    DEV("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/c:/java-development/javaPathDB", null, null),
    TEST("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/c:/java-development/testdb", null,null),
    PROD("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/c:/java-development/proddb", null, null);
    /** Campos privador **/
    private final String driver;
    private final String url;
    private final String user;
    private final String pwd;

    // solo tenemos acceso al metodo getConnection
    public Connection getConnection()throws SQLException{
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){}
        return DriverManager.getConnection(url, user, pwd);
    }

    private DBConnection(String driver, String url, String user, String pwd){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }
}
