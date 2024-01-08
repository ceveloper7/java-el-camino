package com.ceva.section36.rest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;

/**
 * Interface de Usuario
 */
public class RESTClient3 extends JPanel {
    JTable table;
    TModel dataModel;
    JButton btnAdd;
    JButton btnEdit;
    JButton btnDelete;
    JButton btnRefresh;

    private RESTClient3Model model;

    public RESTClient3() {
        super();
        model = new RESTClient3Model("http://localhost:8080/SimpleStore-RestJaxRs/rest");
        initComponents();
    }

    /**
     * Clase que retorna la informacion para poder mostrar la tabla
     */
    private class TModel extends AbstractTableModel {
        /**
         *
         * @param rowIndex        fila
         * @param columnIndex     columna
         * @return
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // obtenemos una fila del modelo
            ProductData p = model.getRow(rowIndex);
            switch(columnIndex) {
                case 0:
                    return p.id_product;
                case 1:
                    return p.name;
                case 2:
                    return p.price;
                case 3:
                    return p.description;
                default:
                    return "?";
            }
        }

        @Override
        public String getColumnName(int column) {
            return model.getColumnName(column);
        }

        @Override
        public int getColumnCount() {
            return model.getColumnCount();
        }

        @Override
        public int getRowCount() {
            return model.getRowCount();
        }
    }

    /**
     * Tarea pendiente: antes de editar un registro hay que verificar que dicho registro
     *                  existe en el servidor. Se puede dar el caso de que un producto
     *                  se elimine en el servidor pero aun se visualice en el cliente
     */
    private void editRow() {
        // obtenemos la fila seleccionada
        int row = table.getSelectedRow();
        if (row >= 0) {
            // leemos el product data de ese renglon
            ProductData p = model.getRow(row);

            ProductForm form = new ProductForm(SwingUtilities.getWindowAncestor(this));
            form.setData(p.id_product, p.name, p.price, p.description);
            form.setVisible(true);
            if (form.okWasSelected()) {
                model.updateData(form.getId_product(), form.getName(), form.getPrice(), form.getDescription());
            }
        }
    }

    private void initComponents() {
        // tamano preferido de la ventana
        setPreferredSize(new Dimension(600, 400));

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setLayout(new BorderLayout());

        dataModel = new TModel();
        table = new JTable(dataModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // reacciona al doble click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editRow();
                }
            }
        });
// column size
        Font tableFont = table.getFont();
        FontMetrics fm = table.getFontMetrics(tableFont);
        int w0 = fm.stringWidth(model.getColumnName(0)) + fm.getMaxAdvance();
        TableColumn col = table.getColumnModel().getColumn(0);
        col.setPreferredWidth(w0);
        col.setMinWidth(w0);

        int w2 = fm.stringWidth(model.getColumnName(2)) + fm.getMaxAdvance();
        col = table.getColumnModel().getColumn(2);
        col.setPreferredWidth(w2);
        col.setMinWidth(w2);

        int w1 = (getPreferredSize().width - (w0+w2))/2;
        int w3 = (getPreferredSize().width - (w0+w2)) - w1;

        table.getColumnModel().getColumn(1).setPreferredWidth(w1);
        table.getColumnModel().getColumn(3).setPreferredWidth(w3);
// end column size

        JScrollPane scrollPane = new JScrollPane(table);

        // panel de botones inferior
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());

        // al hacer click en los botones
        btnAdd = new JButton("Nuevo");
        // el codigo del action listener se ejecuta dentro del event disptach thread EDT
        btnAdd.addActionListener((e) -> {
            // creamos una product form
            ProductForm form = new ProductForm(SwingUtilities.getWindowAncestor(this));
            form.setVisible(true);
            if (form.okWasSelected())
                model.insertData(form.getName(), form.getPrice(), form.getDescription());
        });
        btnEdit = new JButton("Editar");
        btnEdit.addActionListener((e) -> {
            editRow();
        });
        btnDelete = new JButton("Borrar");
        btnDelete.addActionListener((e) -> {
            if (JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this), "¿Deseas borrar este registro?", "Borrar registro", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            int row = table.getSelectedRow();
            if (row >= 0) {
                ProductData p = model.getRow(row);
                model.deleteData(p.id_product);
            }
        });
        btnRefresh = new JButton("Refrescar");
        btnRefresh.addActionListener((e) -> {
            disableButtons();
            model.loadData();
        });
        lowerPanel.add(btnAdd);
        lowerPanel.add(btnEdit);
        lowerPanel.add(btnDelete);
        lowerPanel.add(btnRefresh);

        add(scrollPane, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);

        disableButtons();

        // el modelo funciona a base de listeners que se invocan cuando ocurre un evento para el modelo
// cambios en el jtable
        model.addListener(new RESTClient3ModelListener() {
            /**
             * En el momento que se llaman los metodos dataInsert, dataUpdate etc
             * No sabemos si se haran dentro del EDT o fuera, como sabemos swing siempre debe
             * ser invocado dentro del edt.
             * Cuando se inserta un registro, llamamos al dataModel que es el modelo del JTable
             * invocamos el metodo fireTableRowsInserted y JTable se encarga de actualizar la info
             */
            @Override
            public void dataInserted(ProductData data, int row) {
                Runnable r = () -> {
                    dataModel.fireTableRowsInserted(row, row);
                    // validamos si hay registros
                    if (model.getRowCount() > 0)
                        enableButtons();
                };

                // si retorna true es porque esta siendo ejecutado dentro del edt
                if (SwingUtilities.isEventDispatchThread())
                    r.run();
                else
                    // el runnable se ubica en una cola a esperar que el edt lo ejecute
                    SwingUtilities.invokeLater(r);
            }
            @Override
            public void dataUpdated(ProductData data, int row) {
                Runnable r = () -> dataModel.fireTableRowsUpdated(row, row);

                if (SwingUtilities.isEventDispatchThread())
                    r.run();
                else
                    SwingUtilities.invokeLater(r);
            }
            @Override
            public void dataDeleted(ProductData data, int row) {
                Runnable r = () -> {
                    dataModel.fireTableRowsDeleted(row, row);
                    if (model.getRowCount() == 0)
                        disableButtons();
                };

                if (SwingUtilities.isEventDispatchThread())
                    r.run();
                else
                    SwingUtilities.invokeLater(r);
            }
            @Override
            public void dataChanged() {
                Runnable r = () -> {
                    dataModel.fireTableDataChanged();
                    if (model.getRowCount() > 0) {
                        enableButtons();
                    } else
                        disableButtons();
                };

                if (SwingUtilities.isEventDispatchThread())
                    r.run();
                else
                    SwingUtilities.invokeLater(r);
            }
        });
    }
// fin cambios en el jtable

    private void disableButtons() {
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnRefresh.setEnabled(false);
    }

    private void enableButtons() {
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnRefresh.setEnabled(true);
    }

    private void onWindowOpened() {
        // el modelo de datos de la app, carga la informacion
        model.loadData();
    }

    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println(e.getClass().getName() + " genrated: " + e.getMessage());
//        }

        SwingUtilities.invokeLater(() -> {
            RESTClient3 panel = new RESTClient3();

            JFrame frame = new JFrame("Catalogo REST");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(panel);

            // listener que escucha cuando se abre la ventana
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    /**
                     * cuando se abre la ventana por primera vez
                     * llamamos al metodo loadData()
                     */
                    panel.onWindowOpened();
                }
            });

            frame.pack();
            frame.setMinimumSize(new Dimension(panel.getWidth(), panel.getHeight()));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
