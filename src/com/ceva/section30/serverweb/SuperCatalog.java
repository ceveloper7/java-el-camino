package com.ceva.section30.serverweb;

import com.ceva.section21.jdbc.DBUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashMap;

/**
 * Esta clase genera una Lista de Catalogs
 * Permite hacer una CRUD
 */
public class SuperCatalog {
    private static void reportException(Exception e) {
        System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        e.printStackTrace();
    }

    private InputStream handleSuperList(String path, Map<String,String> queryStringMap) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(4096); // stream de 4kb
        PrintWriter pr = new PrintWriter(bout);
        pr.println("<html><body>");
        pr.println("<h1>Lista de super</h1>");

        pr.println("<table border=\"1\"><tr><td>Nombre</td><td>Cantidad</td><td>&nbsp;</td><td>&nbsp;</td></tr>");

        try (Connection conn = DBUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id_super, name, qty from super");
            // escribimos la info del catalogo en la pagina web
            while (rs.next()) {
                int id_super = rs.getInt("id_super");
                String name = rs.getString("name");
                String qty = rs.getString("qty");

                pr.printf("<tr><td>%s</td><td>%s</td><td><a href=\"superEditor.cdj?id_super=%d\">[Ed]</a></td><td><a href=\"superEditor.cdj?id_super=%d&action=del\">[Del]</a></td></tr>\r\n", name, qty, id_super, id_super);
            }
            stmt.close();
        } catch (SQLException e) {
            reportException(e);
        }

        pr.println("</table>");

        pr.println("<p>&nbsp;</p>");

        pr.println("<a href=\"superEditor.cdj\">Nuevo</a>");


        pr.println("</body></html>");
        pr.close();

        ByteArrayInputStream in = new ByteArrayInputStream(bout.toByteArray());
        return in;
    }

    private static String nullToEmpty(String s) {
        return s != null ? s : "";
    }

    /**
     * Dado un map y un name, convertimos ese valor a un int, si no puede retorna defaultValue
     */
    private static int getInt(Map<String,String> map, String name, int defaultValue) {
        try {
            String s = map.get(name);
            if (s != null)
                return Integer.parseInt(s);
        } catch (NumberFormatException e) {
        }
        return defaultValue;
    }

    private InputStream handleSuperEditor(String path, Map<String,String> queryStringMap) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
        PrintWriter pr = new PrintWriter(bout);
        pr.println("<html><body>");
        pr.println("<h1>Editar</h1>");

        boolean showForm = true; // flag que indica si se muestra la pantalla de captura
        // si retorna 0 es como que no existe un id
        // si vamos a hacer save pero no id en la BD entonces eso es un insert
        // pero si existe el id entonces lo que debemos hacer es un update
        int id_super = getInt(queryStringMap, "id_super", 0);
        String name = queryStringMap.get("name");
        String qty = queryStringMap.get("qty");
        String action = queryStringMap.get("action");

        if ("save".equals(action)) {
            try (Connection conn = DBUtil.getConnection()) {
                PreparedStatement pstmt;
                if (id_super == 0)
                    pstmt = conn.prepareStatement("insert into super (name, qty) values (?,?)");
                else {
                    pstmt = conn.prepareStatement("update super set name=?, qty=? where id_super=?");
                    pstmt.setInt(3, id_super);
                }
                pstmt.setString(1, name);
                pstmt.setString(2, qty);
                pstmt.executeUpdate();
                pstmt.close();

                // respuesta al cliente
                pr.println("<font color=\"green\">Los cambios fueron guardados.</font><br><br>");
            } catch (SQLException e) {
                reportException(e);
            }
        } else if ("del".equals(action)) {
            try (Connection conn = DBUtil.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("delete from super where id_super=?");
                pstmt.setInt(1, id_super);
                pstmt.executeUpdate();
                pstmt.close();

                pr.println("<font color=\"green\">El registro ha sido borrado.</font><br><br>");
                showForm = false; // luego de eliminar el registro no mostramos la pantalla de captura
            } catch (SQLException e) {
                reportException(e);
            }

        } else if (id_super > 0) {
            try (Connection conn = DBUtil.getConnection()) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select id_super, name, qty from super where id_super=" + id_super);
                if (rs.next()) {
                    id_super = rs.getInt("id_super");
                    name = rs.getString("name");
                    qty = rs.getString("qty");
                }
                stmt.close();
            } catch (SQLException e) {
                reportException(e);
            }
        }

        if (showForm) {
            pr.println("<form method=\"POST\" action=\"superEditor.cdj?action=save\">");
            if (id_super != 0)
                pr.printf("<input type=\"hidden\" name=\"id_super\" value=\"%d\">\r\n", id_super);
            pr.printf("Nombre: <input type=\"text\" name=\"name\" value=\"%s\"><br>\r\n", nullToEmpty(name));
            pr.printf("Cantidad: <input type=\"text\" name=\"qty\" value=\"%s\"><br>\r\n", nullToEmpty(qty));
            pr.println("<input type=\"submit\">");
            pr.println("</form>");
        }

        pr.println("<br><br><a href=\"superList.cdj\">Regresar</a>");


        pr.println("</body></html>");
        pr.close();

        ByteArrayInputStream in = new ByteArrayInputStream(bout.toByteArray());
        return in;
    }

    public InputStream handleRequest(String path, Map<String,String> queryStringMap) {
        if (queryStringMap == null)
            queryStringMap = new HashMap<>();

        if (path.equals("superList.cdj"))
            return handleSuperList(path, queryStringMap);
        else if (path.equals("superEditor.cdj"))
            return handleSuperEditor(path, queryStringMap);

        return null;
    }
}
