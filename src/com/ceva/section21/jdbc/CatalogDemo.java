package com.ceva.section21.jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

/**
 * El constructor de CatalogDemo crea la interface de la app
 */
public class CatalogDemo extends JPanel {
    // nombre de las columnas
    private String columnNames[] = {"Symbol", "Name", "Status"};
    // mapa que mustra el id del financial status y la descripcion del financial status
    private Map<Integer,String> companyStatusMap;
    // lista que contiene toda la info del archivo
    private List<CompanyData> data = null;
    JTable table;
    TModel dataModel;
    JButton btnAdd;
    JButton btnEdit;
    JButton btnDelete;

    public CatalogDemo() {
        super();

        companyStatusMap = new HashMap<>();
        data = new ArrayList<>();

        initComponents(); // creacion de la UI
    }

    /**
     * clase donde representamos la informacion que se va a mostrar en pantalla
     * CompanyData representa un registro del archivo
     */
    private class CompanyData {
        private int id_company;
        private String symbol;
        private String companyName;
        private int id_companyStatus;
    }

    private class TModel extends AbstractTableModel {
        // dado un renglon y una columna, obtenemos el valor a mostrar en esa celda
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // obtenemos un registro y creamos el objeto CompanyData
            CompanyData d = data.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return d.symbol;
                case 1:
                    return d.companyName;
                case 2:
                    // pasamos el id del financial status y obtenemos el codigo del status
                    String status = companyStatusMap.get(d.id_companyStatus);
                    return status != null ? status : "?";
                default:
                    return "?";
            }
        }

        // retornamos la descripcion de una columna
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        // retornamos el nro de columnas de la tabla
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.size(); // retornamos el nro de renglones tendra la tabla
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }

    private void insertCompany(String symbol, String name, int id_companyStatus) {
        int id_company = -1;

        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("insert into company (symbol, name, id_companyStatus) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, symbol);
            pstmt.setString(2, name);
            if (id_companyStatus != -1)
                pstmt.setInt(3, id_companyStatus);
            else
                pstmt.setNull(3, java.sql.Types.INTEGER);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            id_company = rs.getInt(1);
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            return;
        }
        // una vez que los datos se insertaron correctamente en la base de datos
        // reflejamos ese dato en la Tabla
        // nuevo objeto CompanyData
        CompanyData c = new CompanyData();
        // guardamos todos sus valores
        c.id_company = id_company;
        c.symbol = symbol;
        c.companyName = name;
        c.id_companyStatus = id_companyStatus;
        data.add(c); // lo agregamos a la coleccion
        // informamos al modelo que se insertaron registros en la tabla
        // se insertaron desde data.size()-1 hasta data.size()-1
        dataModel.fireTableRowsInserted(data.size()-1, data.size()-1);
    }

    private void updateCompany(int id_company, String symbol, String name, int id_companyStatus) {
        int row = 0; // variable que controla el nro de renglon o fila
        // recorremos la coleecion y si encontramos id_company hacemos la operacion update
        for (CompanyData c : data) {
            if (c.id_company == id_company) {
                try (Connection conn = DBUtil.getConnection()) {
                    PreparedStatement pstmt = conn.prepareStatement("update company set symbol=?, name=?, id_companyStatus=? where id_company=?");
                    pstmt.setString(1, symbol);
                    pstmt.setString(2, name);
                    if (id_companyStatus != -1)
                        pstmt.setInt(3, id_companyStatus);
                    else
                        pstmt.setNull(3, java.sql.Types.INTEGER);
                    pstmt.setInt(4, id_company);
                    pstmt.executeUpdate();
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
                    return;
                }
                // una vez que la operacion de update en BD fue exitoso
                // actualizamos la info en memoria
                c.symbol = symbol;
                c.companyName = name;
                c.id_companyStatus = id_companyStatus;
                // el modelo informa a la tabla que se insertaron registros
                dataModel.fireTableRowsUpdated(row, row);
                return;
            }
            row++;
        }
    }

    private void deleteCompany(int id_company) {
        int row = 0; // ubicamos el nro de renglon o fila que contenga al id_company
        // recorremos la coleccion y buscamos la fila
        for (CompanyData cd : data) {
            if (cd.id_company == id_company)
                break;
            row++;
        }
        // encontramos la fila en la lista?
        if (row >= data.size()) {
            // Elemento no encontrado.
            System.out.println("Error: Elemento no encontrado.");
            return;
        }

        // si encontramos la fila, entonces tenemos el nro fila como el id a borrar
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("delete from company where id_company=?");
            pstmt.setInt(1, id_company);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            return;
        }
        // eliminamos el registro de la coleccion
        data.remove(row);
        // el modelo informa a la tabla que se elimino un rango de filas de la tabla
        dataModel.fireTableRowsDeleted(row, row);
    }

    private void editCompany() {
        // obtenemos la fila seleccionada
        int row = table.getSelectedRow();
        if (row >= 0) {
            // obtenemos de la coleccion ese numero de renglon
            CompanyData d = data.get(row);
            CompanyForm form = new CompanyForm(SwingUtilities.getWindowAncestor(this), companyStatusMap);
            // asignamos los datos al form
            form.setData(d.symbol, d.companyName, d.id_companyStatus);
            form.setVisible(true);
            if (form.okWasSelected()) {
                // actualizamos la info
                updateCompany(d.id_company, form.getSymbol(), form.getName(), form.getId_companyStatus());
            }
        }
    }

    private void initComponents() {
        setPreferredSize(new Dimension(600, 300)); // tamano preferido
        // add un borde de 16px
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        // Layout principal de la UI
        setLayout(new BorderLayout());
        // modelo de datos
        dataModel = new TModel();
        // inicializamos la tabla
        table = new JTable(dataModel);
        // permitimos que solo un renglon de tabla se pueda seleccionar
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // listener que permite editar una fila de la tabla, responde al evento doble click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // validamos si hacemos doble click
                if (e.getClickCount() == 2) {
                    editCompany(); // llamamos a la ventana para editar el registro seleccionado
                }
            }
        });
        // configuramos para que las columnas se adapten a la logitud de las columnas de la tabla
        Font tableFont = table.getFont(); // leemos el font que tiene la tabla
        // obtenemos el FontMetrics del tableFont
        FontMetrics fm = table.getFontMetrics(tableFont);
        // configuramos el tamano del columna 0
        int w0 = fm.stringWidth(columnNames[0]) + fm.getMaxAdvance();
        // obtenemos una columna para configurarla
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setPreferredWidth(w0); // aplicamos el tamano calculado
        col.setMinWidth(w0);

        // configuramos el tamano de la comuna 2
        int w2 = fm.stringWidth(columnNames[2]) + fm.getMaxAdvance();
        col = table.getColumnModel().getColumn(2);
        col.setPreferredWidth(w2);
        col.setMinWidth(w2);

        // la columna 1 va ocupar el tamano que resta, que es la columna con mayor necesidad de espacio
        int w1 = getPreferredSize().width - w0 - w2;
        col = table.getColumnModel().getColumn(1);
        col.setPreferredWidth(w1);

        // agregamos las barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);

        // lowerPanel para contener a los 3 botones de la UI
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());

        btnAdd = new JButton("Nuevo");
        btnAdd.addActionListener((e) -> {
            // objeto CompanyForm que expone la informacion del registro
            CompanyForm form = new CompanyForm(SwingUtilities.getWindowAncestor(this), companyStatusMap);
            form.setVisible(true); // como es un modal, el programa se queda aqui
            // validamos si el user presiono okWasSelected()
            if (form.okWasSelected()) {
                // insertamos el registro
                insertCompany(form.getSymbol(), form.getName(), form.getId_companyStatus());
            }
        });
        btnEdit = new JButton("Editar");
        btnEdit.addActionListener((e) -> {
            // editamos la informacion
            editCompany();
        });
        btnDelete = new JButton("Borrar");
        btnDelete.addActionListener((e) -> {
            // obtenemos la fila seleccionada
            int row = table.getSelectedRow();
            if (row < 0)
                // si no se selecciono nada, no hacemos nada
                return;
            // confirmamos con el usuario si lo que quiere hacer es eliminar
            if (JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this), "¿Deseas borrar este registro?", "Borrar registro", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                // si no quiere borrar, no hacemos nada
                return;
            }
            // leemos del reglon de datos la fila segun row y creamos el objeto CompanyData
            CompanyData d = data.get(row);
            // eliminamos el registro
            deleteCompany(d.id_company);
        });
        lowerPanel.add(btnAdd);
        lowerPanel.add(btnEdit);
        lowerPanel.add(btnDelete);

        add(scrollPane, BorderLayout.CENTER);// panel centrado
        add(lowerPanel, BorderLayout.SOUTH);// panel inferior

        disableButtons();
    }

    private void disableButtons() {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void enableButtons() {
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void loadData() {
        // el programa sigue funcionando y en segundo plano se carga la info en un Thread
        Thread t = new Thread(() -> {
            try {
                Connection conn = DBUtil.getConnection(); // abrimos conexion
                Statement stmt = conn.createStatement(); // creamos un statement
                // corremos un query
                ResultSet rs = stmt.executeQuery("select id_companyStatus, code, description from companyStatus");
                // procesamos el ResultSet de la tabla companyStatus
                while (rs.next()) {
                    // leemos cada linea o registro del ResultSet
                    int id_companyStatus = rs.getInt("id_companyStatus");
                    String code = rs.getString("code");
                    String description = rs.getString("description");
                    // validamos si la descripcion del financial status es null
                    if (description != null)
                        // al codigo del status le concatenamos la descripcion
                        code += " " + description;
                    companyStatusMap.put(id_companyStatus, code);
                }
                rs.close();

                // corremos un query a la tabla company
                rs = stmt.executeQuery("select id_company, symbol, name, id_companyStatus from company");
                while (rs.next()) {
                    // por cada iteracion creamos un objeto CompanyData
                    CompanyData company = new CompanyData();
                    company.id_company = rs.getInt("id_company");
                    company.symbol = rs.getString("symbol");
                    company.companyName = rs.getString("name");
                    company.id_companyStatus = rs.getInt("id_companyStatus");
                    // validamos si el ultimo registro leido es nulo
                    if (rs.wasNull())
                        company.id_companyStatus = -1; // -1 va a significar que es nulo
                    data.add(company); // lo agregamos a la coleccion
                }
                rs.close();

                System.out.println(data.size() + " rows read.");

                stmt.close();
                conn.close();

                // INVOCAMOS LOS METODO DENTRO DEL EDT por ello llamamos al SwingUtitlities
                SwingUtilities.invokeLater(() -> {
                    // informamos al JTable que toda la informacion del modelo ha cambiado
                    // como respuesta al evento el JTable se redibuja
                    dataModel.fireTableDataChanged();
                    enableButtons();
                });
            } catch (SQLException e) {
                SwingUtilities.invokeLater(() -> {
                    Window w = SwingUtilities.getWindowAncestor(this);
                    if (w.isVisible())
                        JOptionPane.showMessageDialog(w,
                                "No se puede obtener la información de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                });

                System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        // utilizamos el look and feel del sistema operativo
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println(e.getClass().getName() + " genrated: " + e.getMessage());
//        }

        SwingUtilities.invokeLater(() -> {
            CatalogDemo panel = new CatalogDemo();

            JFrame frame = new JFrame("Catalogo");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(panel);
            // listener: cuando se abra la ventana se ejecuta panel.loadData();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    panel.loadData();
                }
            });

            frame.pack();
            frame.setMinimumSize(new Dimension(panel.getWidth(), panel.getHeight()));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
