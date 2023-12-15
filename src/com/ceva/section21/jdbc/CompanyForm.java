package com.ceva.section21.jdbc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CompanyForm extends JDialog {
    // campos necesitados
    private JTextField txtSymbol;
    private JTextField txtName;
    private JComboBox cboStatus;
    private boolean okSelected = false;

    private Map<Integer,String> companyStatusMap;
    // array que nos ayuda en el caso de manejar la info por indices
    private Integer statusIndex[];

    /**
     *
     * @param owner - indicamos sobre que ventana se va a centrar el Dialog
     * @param companyStatusMap - info para el combo donde se selecciona el financial status
     */
    public CompanyForm(Window owner, Map<Integer,String> companyStatusMap) {
        super(owner, "Company Info");
        // guardamos referencia al companyStatusMap
        this.companyStatusMap = companyStatusMap;

        // cargamos el array
        statusIndex = new Integer[companyStatusMap.size()];
        int n = 0;
        // recorremos el mapa
        for (Integer i : companyStatusMap.keySet()) {
            // tomamos los indices del map y lo pasamos al array statusIndex
            statusIndex[n++] = i;
        }

        initComponents();
        setModal(true); // Dialog Modal
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        // GridBagLayout para el panel principal
        GridBagLayout gl = new GridBagLayout();
        // panel del centro que contiene los controles
        JPanel mainPanel = new JPanel(gl);
        // borde de espacio
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        GridBagConstraints gc = new GridBagConstraints();
        JLabel label = new JLabel("Symbol");
        txtSymbol = new JTextField(6);
        // establecemos los constraints para los componentes
        // si la celda es mas grande que el tamano del componente, ubicamos component a la izq
        gc.anchor = GridBagConstraints.LINE_START;
        // margen para cada celda de 4px top bottom, 6px left y right
        gc.insets = new Insets(4, 6, 4, 6);
        // coordenadas
        gc.gridx = 0;
        gc.gridy = 0;
        // agregamos el label
        mainPanel.add(label, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        mainPanel.add(txtSymbol, gc);

        label = new JLabel("Name");
        gc.gridx = 0;
        gc.gridy = 1;
        mainPanel.add(label, gc);
        txtName = new JTextField(30);
        gc.gridx = 1;
        gc.gridy = 1;
        mainPanel.add(txtName, gc);

        cboStatus = new JComboBox(new DefaultComboBoxModel() {
            @Override
            public int getSize() {
                // +1 porque le agregamos al como el elemento - select one -
                return companyStatusMap.size() + 1;
            }

            // le pasamos el index y obtenemos la etiqueta
            @Override
            public Object getElementAt(int index) {
                if (index == 0)
                    return "- Select one -";
                return companyStatusMap.get(statusIndex[index-1]);
            }
        });
        // seleccionamos por defecto el indice 0
        cboStatus.setSelectedIndex(0);
        label = new JLabel("Status");
        gc.gridx = 0;
        gc.gridy = 2;
        mainPanel.add(label, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        mainPanel.add(cboStatus, gc);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        btnOK.addActionListener((e) -> {
            // nos informa si la ventana se cerro
            okSelected = true;
            setVisible(false);
        });
        btnCancel.addActionListener((e) -> {
            okSelected = false;
            setVisible(false);
        });
        bottomPanel.add(btnOK);
        bottomPanel.add(btnCancel);

        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getOwner()); // mostramos centrado en su owner
    }

    // pasamos todos los datos de la forma
    public void setData(String symbol, String name, int id_companyStatus) {
        txtSymbol.setText(symbol);
        txtName.setText(name);

        // convertimos el id_companyStatus al nro de renglon que va a tener el combo
        int n = 0;
        // recorremos el array
        for (int id : statusIndex) {
            if (id == id_companyStatus)
                break;
            n++;
        }
        // si n es menor a la longitud de statusIndex significa que se encontro el index
        if (n < statusIndex.length) {
            cboStatus.setSelectedIndex(n+1);
        }
    }

    public boolean okWasSelected() {
        return okSelected;
    }

    public String getSymbol() {
        return txtSymbol.getText();
    }

    public String getName() {
        return txtName.getText();
    }

    public int getId_companyStatus() {
        // obtenemos el indice seleccionado
        int idx = cboStatus.getSelectedIndex();
        if (idx > 0)
            return statusIndex[idx-1];
        return -1;
    }
}
