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

public class CreateNasdaqData {
    // obtenemos una conexion a la base de datos derby
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            return null;
        }
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/c:/java-development/javaPathDB");
        return conn;
    }

    public static void main(String[] args) throws Exception {
        // obtenemos la conexion d la base de datos
        Connection conn = getConnection();
        conn.setAutoCommit(false); // habilitamos el modo transaction en la base de datos

        // select para validar o saber si el id_companyStatus del archivo ya esta en la tabla companyStatus
        PreparedStatement getCompanyStatus = conn.prepareStatement("select id_companyStatus from companyStatus where code=?");
        // agregamos un nuevo status a la tabla y configuramos el statement para que retorne el id que se genera al insertar un nuevo registro
        PreparedStatement insCompanyStatus = conn.prepareStatement("insert into companyStatus(code) values (?)", Statement.RETURN_GENERATED_KEYS);
        // obtenemos cuantas companias tienen el sgte symbol (deberia ser sola una)
        PreparedStatement findCompany = conn.prepareStatement("select count(*) from company where symbol=?");
        // insertamos una nueva company a la tabla
        PreparedStatement insCompany = conn.prepareStatement("insert into company (symbol, name, id_companyStatus) values (?,?,?)");

        // obtenemos la informacion del archivo nasdaqListFile.txt
        BufferedReader br = new BufferedReader(new InputStreamReader(NasdaqReader2.getInputStream()));
        // comenzamos a leer cada linea del archivo
        String line = br.readLine();
        int nLine = -1;
        // procesamos cada linea del archivo
        while (line != null) {
            nLine++; // nLine vale 0 (cero representa el nombre de las columnas del archivo)
            // validamos que no sea la linea 0 ya que no se va a leer el nombre de las columnas del archivo
            if (nLine > 0) {
                // estamos en la linea 1 del archivo
                // separamos la informacion basados en el toke pipe |
                StringTokenizer st = new StringTokenizer(line, "|");
                int nToken = 0;
                String symbol = null; // campo simbolo de la compania del archivo
                String name = null; // campo nombre de la compania del archivo
                String status = null; // campo financial status de la compania del archivo
                int id_companyStatus;
                // procesamos las columas requeridas de la linea del archivo en la que me encuentro
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    // validamos si es la columna Symbol
                    if (nToken == 0) {
                        // la ultima linea termina con File creation time...
                        // terminamos el while para no procesarla y no agregarla a la bd
                        if (tok.startsWith("File Creation Time: "))
                            break;
                        symbol = tok;
                    }
                    // validamos si es la columna Security Nae
                    else if (nToken == 1) {
                        name = tok;
                    }
                    // Validamos si es la columna Financial Status
                    else if (nToken == 4) {
                        status = tok;

                        // Insertar registro en tablas
                        // pasamos el parametro para ejecutar el query del preparedstatement getCompanuStatus
                        getCompanyStatus.setString(1, status);
                        // consultamos si el status de la linea procesada ya esta en la tabla companyStatus
                        ResultSet rs = getCompanyStatus.executeQuery();
                        // leemos el resultado del query
                        if (rs.next()) {
                            // hay info, encontro el id_companyStatus que consultamos
                            id_companyStatus = rs.getInt(1);
                        } else {
                            // si no obtuvimos registro entonces agregamos company status
                            // pasamos el dato a insertar
                            insCompanyStatus.setString(1, status);
                            // ejecutamos el insert into query
                            insCompanyStatus.executeUpdate();
                            // obtenemos el id que se genero para el insert into
                            ResultSet rsKey = insCompanyStatus.getGeneratedKeys();
                            rsKey.next();
                            // obtenemos el id generado
                            id_companyStatus = rsKey.getInt(1);
                            rsKey.close();// cerramos el ResultSet
                        }
                        rs.close();

                        // pasamos el symbol que nos retorna cuantas companias tiene ese symbol
                        findCompany.setString(1, symbol);
                        rs = findCompany.executeQuery();
                        rs.next(); // no usamos if ya que el resultset solo tendra un registro
                        // validamos que no exista una compania registrada
                        if (rs.getInt(1) == 0) {
                            // insertamos el registro
                            insCompany.setString(1, symbol);
                            insCompany.setString(2, name);
                            insCompany.setInt(3, id_companyStatus);
                            insCompany.executeUpdate();
                            System.out.printf("%s %s inserted.\n", symbol, name);
                        }
                        rs.close();
                        // habiendo leido los registros que necesitamos, terminamos el while interno
                        // para pasar a la siguiente linea (nline++)
                        break;
                    }
                    nToken++; // cada vez que hacemos un ciclo incrementamos un token
                }
            }

            line = br.readLine();
        }
        br.close();

        conn.commit(); // actualizamos las operaciones en la base de datos
        conn.close();
    }
}
