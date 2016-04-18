/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateRawMaterial;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.domain.Supplier;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import id.ac.ciputra.ift.snackzone.presentation.Controller.ProductPage;

/**
 *
 * @author Kevin
 */
public class UpdateRawMaterialView {

    private JPanel contentPane, northPane, centerFieldPane, centerPane, newPagePane,
            fieldPane, buttonPane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField rawMaterialNameF, rawMaterialStock, rawMaterialPrice;
    private JComboBox rawMaterialName, rawMaterialUnit, rawMaterialSupplierName;
    private JLabel titleLabel;
    private Vector<Supplier> supplierData;
    private UpdateRawMaterialModel rModel;
    private Users onlineUser;

    public Users getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(Users onlineUser) {
        this.onlineUser = onlineUser;
    }

    public void switchPane(JPanel newPane) {
        contentPane.remove(centerPane);
        centerPane = newPane;
        contentPane.add(centerPane, BorderLayout.CENTER);
        contentPane.setVisible(false);
        contentPane.setVisible(true);
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void initialize() {
        contentPane = new JPanel(new BorderLayout());
        centerPane = new JPanel(new BorderLayout());
        northPane = new JPanel(new GridLayout(2, 3));
        centerFieldPane = new JPanel(new GridLayout(2, 1));
        fieldPane = new JPanel(new GridLayout(7, 4, 10, 10));
        buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southGridPane = new JPanel(new GridLayout(2, 1));
        southNorth = new JPanel(new GridLayout(1, 7, 15, 15));

        saveButton = new JButton("Save");
        backButton = new JButton(new ImageIcon("Resource/back.jpg"));

        rawMaterialNameF = new JTextField();
        rawMaterialStock = new JTextField();
        rawMaterialPrice = new JTextField();

        Vector<String> unitFill = new Vector<String>();
        unitFill.add("Pack");
        unitFill.add("Ounce");
        unitFill.add("Box");
        unitFill.add("Dozen");

        rawMaterialUnit = new JComboBox(unitFill);
        rawMaterialUnit.setSelectedIndex(-1);

        rModel = new UpdateRawMaterialModel();

        rawMaterialName = new JComboBox(rModel.getAllRawMaterialName());
        rawMaterialName.setSelectedIndex(-1);

        supplierData = rModel.getAllSupplier();
        Vector<String> supplierName = new Vector<String>();
        for (int i = 0; i < supplierData.size(); i++) {
            supplierName.add(supplierData.get(i).getSupplierName());
        }

        rawMaterialSupplierName = new JComboBox(supplierName);
        rawMaterialSupplierName.setSelectedIndex(-1);

        titleLabel = new JLabel("Update Raw Material");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
    }

    public void addComponent() {

        northPane.add(new JLabel());
        northPane.add(new JLabel());
        northPane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));
        northPane.add(new JLabel());
        northPane.add(titleLabel);
        northPane.add(new JLabel());

        buttonPane.add(saveButton);

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Name : "));
        fieldPane.add(rawMaterialName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Name : "));
        fieldPane.add(rawMaterialNameF);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Stock : "));
        fieldPane.add(rawMaterialStock);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Unit : "));
        fieldPane.add(rawMaterialUnit);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Price / Unit : "));
        fieldPane.add(rawMaterialPrice);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Supplier Name : "));
        fieldPane.add(rawMaterialSupplierName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(buttonPane);
        fieldPane.add(new JLabel());

        southNorth.add(backButton);
        southNorth.add(new JLabel());
        southNorth.add(new JLabel());
        southNorth.add(new JLabel());
        southNorth.add(new JLabel());
        southNorth.add(new JLabel());
        southNorth.add(new JLabel());

        southGridPane.add(southNorth);
        southGridPane.add(new JLabel(new ImageIcon("Resource/bawah.png")));

        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(fieldPane, BorderLayout.CENTER);
        centerPane.add(southGridPane, BorderLayout.SOUTH);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public void registerListener() {
        saveButton.addActionListener(new saveButtonListener());
        rawMaterialName.addItemListener(new rawMaterialNameComboListener());
        backButton.addActionListener(new backButtonListener());
    }

    public UpdateRawMaterialView() {

        buildLayout();

//        JFrame frame = new JFrame("Update Raw Material");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        UpdateRawMaterialView r = new UpdateRawMaterialView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (rawMaterialName.getSelectedIndex() != -1 && !rawMaterialNameF.getText().equals("")
                    && !rawMaterialStock.getText().equals("")
                    && rawMaterialSupplierName.getSelectedIndex() != -1
                    && !rawMaterialPrice.getText().equals("")
                    && rawMaterialUnit.getSelectedIndex() != -1) {
                try {
                    float stock = Float.parseFloat(rawMaterialStock.getText());
                    double price = Double.parseDouble(rawMaterialPrice.getText());
                    short i = 0;
                    while (!rawMaterialSupplierName.getSelectedItem().toString().
                            equals(supplierData.get(i).getSupplierName())) {
                        i++;
                    }
                    RawMaterial rawMaterial = new RawMaterial();
                    rawMaterial.setRawMaterialName(rawMaterialNameF.getText());
                    rawMaterial.setStock(stock);
                    rawMaterial.setUnit(rawMaterialUnit.getSelectedItem().toString());
                    rawMaterial.setPrice(price);
                    rawMaterial.setSupplier_id(supplierData.get(i).getSupplierId());
                    UpdateRawMaterialModel rModel = new UpdateRawMaterialModel();
                    rModel.updateRawMaterial(rawMaterial, rawMaterialName.getSelectedItem().
                            toString());
                    JOptionPane.showMessageDialog(centerFieldPane, "Raw Material Data Updated "
                            + "Succesfully");

                    for (int j = 0; j < rModel.getAllRawMaterialName().size(); j++) {
                        rawMaterialName.addItem(rModel.getAllRawMaterialName().get(j));
                    }
                    for (int j = 0; j < rModel.getAllRawMaterialName().size(); j++) {
                        rawMaterialName.removeItemAt(0);
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(centerFieldPane, "Price and Stock must a number!");
                }
            } else {
                System.out.println("All Field Should Be Filled");
            }
        }
    }

    private class rawMaterialNameComboListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            UpdateRawMaterialModel rModel = new UpdateRawMaterialModel();
            Vector<RawMaterial> rawMaterialData = new Vector();
            rawMaterialData = rModel.getAllData();
            short i = 0;
            while (!rawMaterialData.get(i).getRawMaterialName().
                    equals(rawMaterialName.getSelectedItem().toString())) {
                i++;
            }
            rawMaterialNameF.setText(rawMaterialData.get(i).getRawMaterialName());
            rawMaterialStock.setText(String.valueOf(rawMaterialData.get(i).getStock()));
            rawMaterialPrice.setText(String.valueOf(rawMaterialData.get(i).getPrice()));

            short j = 0;
            while (!rawMaterialUnit.getItemAt(j).toString().equals(rawMaterialData.get(i).
                    getUnit())) {
                j++;
            }
            rawMaterialUnit.setSelectedIndex(j);

            short k = 0;
            while (rawMaterialData.get(i).getSupplier_id()
                    != supplierData.get(k).getSupplierId()) {
                k++;
            }
            rawMaterialSupplierName.setSelectedIndex(k);
        }
    }
    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ProductPage productPage = new ProductPage();
            productPage.setOnlineUser(getOnlineUser());
            newPagePane = productPage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
