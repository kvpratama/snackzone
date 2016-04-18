/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewRawMaterial;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class InsertNewRawMaterialView {

    private JPanel contentPane, northPane, centerFieldPane, centerPane, newPagePane,
            fieldPane, buttonPane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField rawMaterialName, rawMaterialPrice;
    private JComboBox supplierCombo, rawMaterialUnitCombo;
    private JLabel titleLabel;
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

        Vector<String> unitFill = new Vector<String>();
        unitFill.add("Pack");
        unitFill.add("Ounce");
        unitFill.add("Box");
        unitFill.add("Dozen");

        rawMaterialUnitCombo = new JComboBox(unitFill);
        InsertNewRawMaterialModel iModel = new InsertNewRawMaterialModel();
        supplierCombo = new JComboBox(iModel.getAllSupplierName());

        rawMaterialName = new JTextField();
        rawMaterialPrice = new JTextField();

        titleLabel = new JLabel("Insert New Raw Material");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
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
        fieldPane.add(new JLabel("Unit : "));
        fieldPane.add(rawMaterialUnitCombo);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Price / unit : "));
        fieldPane.add(rawMaterialPrice);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Supplier : "));
        fieldPane.add(supplierCombo);
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
        backButton.addActionListener(new backButtonListener());
    }

    public InsertNewRawMaterialView() {

        buildLayout();

//        JFrame frame = new JFrame("Insert New Raw Material");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        InsertNewRawMaterialView i = new InsertNewRawMaterialView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewRawMaterialModel iModel = new InsertNewRawMaterialModel();
            Vector<RawMaterial> rawMaterial = new Vector<RawMaterial>();
            rawMaterial = iModel.readAll();
            boolean name = false;
            boolean number = false;

            if (rawMaterial.size() == 0) {
                name = true;
            } else {
                for (int i = 0; i < rawMaterial.size(); i++) {
                    if (rawMaterial.get(i).getRawMaterialName().equals(rawMaterialName.getText())) {
                        name = false;
                        JOptionPane.showMessageDialog(centerFieldPane, "Raw Material Name Already Exist");
                        break;
                    } else {
                        name = true;
                    }
                }
            }

            try {
                Double.parseDouble(rawMaterialPrice.getText().toString());
                number = true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(centerFieldPane, "Field Price Must Only Number");
                number = false;
            }

            if (number && name) {
                System.out.println("you can go");
                if (!rawMaterialName.getText().equals("")
                        && !rawMaterialPrice.getText().equals("")) {
                    double price = Double.parseDouble(rawMaterialPrice.getText());
                    iModel.insertRawMaterial(rawMaterialName.getText(),
                            rawMaterialUnitCombo.getSelectedItem().toString(),
                            price, supplierCombo.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(centerFieldPane, "New Raw Material Added Successfully");
                } else {
                    JOptionPane.showMessageDialog(centerFieldPane, "Name and Price Field should be Fill");
                }
            } else {
                System.out.println("do nothing");
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
