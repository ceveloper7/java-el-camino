package com.ceva.section36.rest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Ubica los componentes para armar el form para registrar un nuevo product
 */
public class ProductForm extends JDialog {
    private int id_product;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtDescription;

    private boolean okSelected = false;

    public ProductForm(Window owner) {
        super(owner, "Product Info");

        initComponents();
        setModal(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        GridBagLayout gl = new GridBagLayout();
        JPanel mainPanel = new JPanel(gl);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        GridBagConstraints gc = new GridBagConstraints();
        JLabel label = new JLabel("Name");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(4, 6, 4, 6);
        gc.gridx = 0;
        gc.gridy = 0;
        mainPanel.add(label, gc);
        txtName = new JTextField(32);
        gc.gridx = 1;
        gc.gridy = 0;
        mainPanel.add(txtName, gc);

        label = new JLabel("Price");
        gc.gridx = 0;
        gc.gridy = 1;
        mainPanel.add(label, gc);
        txtPrice = new JTextField(9);
        gc.gridx = 1;
        gc.gridy = 1;
        mainPanel.add(txtPrice, gc);

        label = new JLabel("Description");
        gc.gridx = 0;
        gc.gridy = 2;
        mainPanel.add(label, gc);
        txtDescription = new JTextField(32);
        gc.gridx = 1;
        gc.gridy = 2;
        mainPanel.add(txtDescription, gc);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        btnOK.addActionListener((e) -> {
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
        setMinimumSize(getSize());
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    public void setData(int id_product, String name, double price, String description) {
        this.id_product = id_product;
        txtName.setText(name);
        txtPrice.setText(String.format("%.2f", price));
        txtDescription.setText(description);
    }

    public boolean okWasSelected() {
        return okSelected;
    }

    public int getId_product() {
        return id_product;
    }

    public String getName() {
        return txtName.getText();
    }

    public double getPrice() {
        double d = Double.parseDouble(txtPrice.getText());
        return d;
    }

    public String getDescription() {
        return txtDescription.getText();
    }

}
