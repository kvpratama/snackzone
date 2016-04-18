/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewSupplier;

import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class InsertNewSupplierView {

    private JPanel contentPane, northPane, centerPane, centerFieldPane, fieldPane,
            buttonPane, newPagePane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField supplierName, supplierAddress, supplierPhone,
            supplierEmail;
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

        supplierName = new JTextField();
        supplierAddress = new JTextField();
        supplierPhone = new JTextField();
        supplierEmail = new JTextField();

        titleLabel = new JLabel("Insert New Supplier");
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
        fieldPane.add(new JLabel("Supplier Name : "));
        fieldPane.add(supplierName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Address : "));
        fieldPane.add(supplierAddress);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Phone Number : "));
        fieldPane.add(supplierPhone);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Email : "));
        fieldPane.add(supplierEmail);
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

    public InsertNewSupplierView() {

        buildLayout();

//        JFrame frame = new JFrame("Insert New Supplier");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        InsertNewSupplierView i = new InsertNewSupplierView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (!supplierName.getText().equals("") && !supplierAddress.getText().equals("")
                    && !supplierEmail.getText().equals("") && !supplierPhone.getText().equals("")) {
                InsertNewSupplierModel insertModel = new InsertNewSupplierModel();
                insertModel.insertSupplier(supplierName.getText(), supplierAddress.getText(),
                        supplierEmail.getText(), supplierPhone.getText());
                JOptionPane.showMessageDialog(centerFieldPane, "New Supplier Added Successfully");
            } else {
                JOptionPane.showMessageDialog(centerPane, "All field must be filled");
            }
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
