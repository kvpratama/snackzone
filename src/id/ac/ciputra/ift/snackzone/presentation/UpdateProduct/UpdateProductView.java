/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateProduct;

import id.ac.ciputra.ift.snackzone.domain.Need;
import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class UpdateProductView {

    private JPanel contentPane, northPane, centerFieldPane, centerPane, newPagePane,
            fieldPane, buttonPane, needPane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField needField, productPrice;
    private JComboBox rawMaterialName, productNameCombo;
    private JLabel titleLabel, unitLabel;
    private UpdateProductModel pModel;
    private Vector<Product> product;
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
        needPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southGridPane = new JPanel(new GridLayout(2, 1));
        southNorth = new JPanel(new GridLayout(1, 7, 15, 15));

        saveButton = new JButton("Save");
        backButton = new JButton(new ImageIcon("Resource/back.jpg"));

        needField = new JTextField();
        needField.setPreferredSize(new Dimension(50, 30));
        productPrice = new JTextField();

        Vector<String> unitFill = new Vector<String>();
        unitFill.add("Pack");
        unitFill.add("Ounce");
        unitFill.add("Box");
        unitFill.add("Dozen");

        pModel = new UpdateProductModel();

        rawMaterialName = new JComboBox(pModel.getAllRawMaterialName());
        rawMaterialName.setSelectedIndex(-1);

        product = pModel.readAllProduct();
        Vector<String> productName = new Vector<String>();
        for (int i = 0; i < product.size(); i++) {
            productName.add(product.get(i).getProductName());
        }

        productNameCombo = new JComboBox(productName);
        productNameCombo.setSelectedIndex(-1);

        titleLabel = new JLabel("Update Product");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        unitLabel = new JLabel("Unit");
    }

    public void addComponent() {

        northPane.add(new JLabel());
        northPane.add(new JLabel());
        northPane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));
        northPane.add(new JLabel());
        northPane.add(titleLabel);
        northPane.add(new JLabel());

        buttonPane.add(saveButton);

        needPane.add(needField);
        needPane.add(unitLabel);

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Product Name : "));
        fieldPane.add(productNameCombo);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Price : "));
        fieldPane.add(productPrice);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Raw Material : "));
        fieldPane.add(rawMaterialName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Need : "));
        fieldPane.add(needPane);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(buttonPane);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
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
        productNameCombo.addItemListener(new productNameComboListener());
        backButton.addActionListener(new backButtonListener());
    }

    public UpdateProductView() {

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
//        UpdateProductView u = new UpdateProductView();
//    }

    private class productNameComboListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            UpdateProductModel uProduct = new UpdateProductModel();
            Vector<Product> product = uProduct.readAllProduct();
            short i = 0;
            while (!productNameCombo.getSelectedItem().toString().equals(
                    product.get(i).getProductName())) {
                i++;
            }
            productPrice.setText("" + product.get(i).getPrice());
            Vector<Need> need = uProduct.readAllNeed();
            short j = 0;
            while (need.get(j).getProductId() != product.get(i).getProductId()) {
                j++;
            }
            needField.setText("" + need.get(j).getNeed());
            Vector<RawMaterial> rawMaterial = uProduct.readAllRawMaterial();
            short k = 0;
            while (need.get(j).getRawMaterialId() != rawMaterial.get(k).getRawMaterialId()) {
                k++;
            }
            short l = 0;
            while (!rawMaterialName.getItemAt(l).toString().equals(
                    rawMaterial.get(k).getRawMaterialName())) {
                l++;
            }
            rawMaterialName.setSelectedIndex(l);
            unitLabel.setText(rawMaterial.get(k).getUnit());
        }
    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (productNameCombo.getSelectedIndex() != -1 && !productPrice.getText().equals("")
                    && rawMaterialName.getSelectedIndex() != -1
                    && !needField.getText().equals("")) {
                try {
                    UpdateProductModel model = new UpdateProductModel();
                    model.updateProduct(Double.parseDouble(productPrice.getText()),
                            productNameCombo.getSelectedItem().toString());
                    short i = 0;
                    while (!product.get(i).getProductName().equals(
                            productNameCombo.getSelectedItem().toString())) {
                        i++;
                    }
                    short j = 0;
                    Vector<RawMaterial> rawMaterial = model.readAllRawMaterial();
                    while (!rawMaterialName.getSelectedItem().toString().equals(
                            rawMaterial.get(j).getRawMaterialName())) {
                        j++;
                    }
                    model.updateNeed(product.get(i).getProductId(),
                            rawMaterial.get(j).getRawMaterialId(),
                            Float.parseFloat(needField.getText()));
                    JOptionPane.showMessageDialog(centerPane, "Raw Material Updated Successfully");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(centerFieldPane, "Price and Need must a Number!!");
                }
            } else {
                JOptionPane.showMessageDialog(centerFieldPane, "All field should be fill");
            }
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
