/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewProduct;

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
public class InsertNewProductView {

    private JPanel contentPane, northPane, centerFieldPane, centerPane, newPagePane,
            fieldPane, buttonPane, needPane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField needField, productName, productPrice;
    private JComboBox rawMaterialCombo;
    private JLabel titleLabel, unitLabel;
    private Vector<RawMaterial> rawMaterial;
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
        needPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        southGridPane = new JPanel(new GridLayout(2, 1));
        southNorth = new JPanel(new GridLayout(1, 7, 15, 15));

        saveButton = new JButton("Save");
        backButton = new JButton(new ImageIcon("Resource/back.jpg"));

        needField = new JTextField();
        needField.setPreferredSize(new Dimension(50, 25));
        productName = new JTextField();
        productPrice = new JTextField();

        InsertNewProductModel inpm = new InsertNewProductModel();
        Vector<String> rawName = new Vector<String>();
        rawMaterial = inpm.readAllRawMateria();
        for (int i = 0; i < rawMaterial.size(); i++) {
            rawName.add(rawMaterial.get(i).getRawMaterialName());
        }

        rawMaterialCombo = new JComboBox(rawName);
        rawMaterialCombo.setSelectedIndex(-1);

        titleLabel = new JLabel("Insert New Product");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));

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
        fieldPane.add(productName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Price : "));
        fieldPane.add(productPrice);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Raw Material : "));
        fieldPane.add(rawMaterialCombo);
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
        rawMaterialCombo.addItemListener(new rawMaterialComboListener());
        backButton.addActionListener(new backButtonListener());
    }

    public InsertNewProductView() {

        buildLayout();

//        JFrame frame = new JFrame("Input New Product");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        InsertNewProductView i = new InsertNewProductView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewProductModel iModel = new InsertNewProductModel();
            Vector<Product> product = new Vector<Product>();
            product = iModel.readAll();
            boolean checkName = false;
            boolean checkPrice = false;
            boolean checkNeed = false;

            if (product.size() == 0) {
                checkName = true;
            } else {
                for (int i = 0; i < product.size(); i++) {
                    if (product.get(i).getProductName().equals(productName.getText())) {
                        checkName = false;
                        JOptionPane.showMessageDialog(centerFieldPane, "Product Name Already Exist");
                        break;
                    } else {
                        checkName = true;
                    }
                }
            }

            try {
                Double.parseDouble(productPrice.getText().toString());
                checkPrice = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(centerFieldPane, "Field Price Must Only Number");
                checkPrice = false;
            }

            try {
                Float.parseFloat(needField.getText());
                checkNeed = true;
            } catch (Exception es) {
                JOptionPane.showMessageDialog(centerFieldPane, "Field Need Must Only Number");
                checkNeed = false;
            }

            if (checkPrice && checkName && checkNeed) {
                if (!productName.getText().equals("")
                        && !productPrice.getText().equals("")
                        && !needField.getText().equals("")
                        && rawMaterialCombo.getSelectedIndex() != -1) {

                    short x = 0;
                    while (!rawMaterial.get(x).getRawMaterialName().equals(
                            rawMaterialCombo.getSelectedItem().toString())) {
                        x++;
                    }
                    double price = Double.parseDouble(productPrice.getText());
                    float need = Float.parseFloat(needField.getText());

                    iModel.inputProduct(productName.getText(), price, need, rawMaterial.get(x));
                    JOptionPane.showMessageDialog(centerFieldPane, "New Product Added Successfully");
                } else {
                    JOptionPane.showMessageDialog(centerFieldPane, "All field should be filled");
                }
            } else {
                System.out.println("do nothing");
            }
        }
    }

    private class rawMaterialComboListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            short i = 0;
            while (!rawMaterialCombo.getSelectedItem().toString().equals(
                    rawMaterial.get(i).getRawMaterialName())) {
                i++;
            }
            unitLabel.setText(rawMaterial.get(i).getUnit());
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
