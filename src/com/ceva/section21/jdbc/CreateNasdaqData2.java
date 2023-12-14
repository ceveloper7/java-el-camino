package com.ceva.section21.jdbc;

import com.ceva.section20.sockets.NasdaqReader2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/**
 * Ejemplo donde utilizamos ResultSet para insertar registro utilizando la posicion especial INSERT
 */
public class CreateNasdaqData2 {
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
        conn.setAutoCommit(false);

        PreparedStatement getCompanyStatus = conn.prepareStatement("select id_companyStatus from companyStatus where code=?");
        PreparedStatement findCompany = conn.prepareStatement("select count(*) from company where symbol=?");
        PreparedStatement insCompanyStatus = conn.prepareStatement("insert into companyStatus(code) values (?)", Statement.RETURN_GENERATED_KEYS);
        // especificamos un ResultSet para insertar registros
        Statement stmtCompany = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ResultSet rsCompany = stmtCompany.executeQuery("select symbol, name, id_companyStatus from company");

        BufferedReader br = new BufferedReader(new InputStreamReader(NasdaqReader2.getInputStream()));
        String line = br.readLine();
        int nLine = -1;
        while (line != null) {
            nLine++;
            if (nLine > 0) {
                StringTokenizer st = new StringTokenizer(line, "|");
                int nToken = 0;
                String symbol = null;
                String name = null;
                String status = null;
                int id_companyStatus;
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    if (nToken == 0) {
                        if (tok.startsWith("File Creation Time: "))
                            break;
                        symbol = tok;
                    } else if (nToken == 1) {
                        name = tok;
                    } else if (nToken == 4) {
                        status = tok;

                        // Insertar registro
                        getCompanyStatus.setString(1, status);
                        ResultSet rs = getCompanyStatus.executeQuery();
                        if (rs.next()) {
                            id_companyStatus = rs.getInt(1);
                        } else {
                            // Agregar company status
                            insCompanyStatus.setString(1, status);
                            insCompanyStatus.executeUpdate();
                            ResultSet rsKey = insCompanyStatus.getGeneratedKeys();
                            rsKey.next();
                            id_companyStatus = rsKey.getInt(1);
                            rsKey.close();
                        }
                        rs.close();

                        findCompany.setString(1, symbol);
                        rs = findCompany.executeQuery();
                        rs.next();
                        if (rs.getInt(1) == 0) {
                            // insertamos el registro en la tabla company
                            // posicionamos el cursor del resultset en un espacio de memoria especial
                            rsCompany.moveToInsertRow();
                            rsCompany.updateString("symbol", symbol);
                            rsCompany.updateString("name", name);
                            rsCompany.updateInt("id_companyStatus", id_companyStatus);
                            rsCompany.insertRow();
                            System.out.printf("%s %s inserted.\n", symbol, name);
                        }
                        rs.close();

                        break;
                    }
                    nToken++;
                }
            }

            line = br.readLine();
        }
        br.close();

        conn.commit();
        conn.close();
    }
}
