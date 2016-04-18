/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateSupplier;

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
import id.ac.ciputra.ift.snackzone.presentation.Controller.SupplierPage;

/**
 *
 * @author Kevin
 */
public class UpdateSupplierView {

    private JPanel contentPane, newPagePane, centerPane, northPane, centerFieldPane,
            fieldPane, buttonPane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField supplierNameF, supplierAddress, supplierPhone,
            supplierEmail;
    private JComboBox supplierName;
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

        supplierNameF = new JTextField();
        supplierAddress = new JTextField();
        supplierPhone = new JTextField();
        supplierEmail = new JTextField();

        UpdateSupplierModel sModel = new UpdateSupplierModel();
        supplierName = new JComboBox(sModel.getName());
        supplierName.setSelectedIndex(-1);

        titleLabel = new JLabel("Update Supplier");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 33));
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
        fieldPane.add(supplierName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Name : "));
        fieldPane.add(supplierNameF);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Address : "));
        fieldPane.add(supplierAddress);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Email : "));
        fieldPane.add(supplierEmail);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Phone Number : "));
        fieldPane.add(supplierPhone);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(buttonPane);
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
        supplierName.addItemListener(new employeeNameComboListener());
        backButton.addActionListener(new backButtonListener());
    }

    public UpdateSupplierView() {

        buildLayout();

//        JFrame frame = new JFrame("Update Employee");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        UpdateSupplierView s = new UpdateSupplierView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (supplierName.getSelectedIndex() != -1 && !supplierNameF.getText().equals("")
                    && !supplierAddress.getText().equals("") && !supplierEmail.getText().equals("")
                    && !supplierPhone.getText().equals("")) {
                Supplier supplier = new Supplier();
                supplier.setSupplierName(supplierNameF.getText());
                supplier.setAddress(supplierAddress.getText());
                supplier.setEmail(supplierEmail.getText());
                supplier.setPhoneNumber(supplierPhone.getText());
                UpdateSupplierModel sModel = new UpdateSupplierModel();
                sModel.updateSupplier(supplier, supplierName.getSelectedItem().toString());
                JOptionPane.showMessageDialog(centerFieldPane, "Supplier Data Updated Succesfully");
                for (int i = 0; i < sModel.getName().size(); i++) {
                    supplierName.addItem(sModel.getName().get(i));
                }
                for (int i = 0; i < sModel.getName().size(); i++) {
                    supplierName.removeItemAt(0);
                }
            } else {
                System.out.println("All Field Should Be Filled");
            }
        }
    }

    private class employeeNameComboListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            UpdateSupplierModel sModel = new UpdateSupplierModel();
            Vector<Supplier> supplierData = new Vector();
            supplierData = sModel.getAllData();
            short i = 0;
            while (!supplierData.get(i).getSupplierName().
                    equals(supplierName.getSelectedItem().toString())) {
                i++;
            }
            supplierNameF.setText(supplierData.get(i).getSupplierName());
            supplierAddress.setText(supplierData.get(i).getAddress());
            supplierEmail.setText(supplierData.get(i).getEmail());
            supplierPhone.setText(supplierData.get(i).getPhoneNumber());
        }
    }

    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SupplierPage UserPage = new SupplierPage();
            UserPage.setOnlineUser(getOnlineUser());
            newPagePane = UserPage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
