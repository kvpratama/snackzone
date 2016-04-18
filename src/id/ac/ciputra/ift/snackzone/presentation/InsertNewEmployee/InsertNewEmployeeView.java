/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewEmployee;

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
import id.ac.ciputra.ift.snackzone.presentation.Controller.EmployeePage;

/**
 *
 * @author Kevin
 */
public class InsertNewEmployeeView {

    private JPanel contentPane, northPane, centerPane, fieldPane, 
            buttonPane, newPagePane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField employeeName, employeeAddress, employeePhone,
            employeeEmail;
    private JComboBox position, status;
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

    public JPanel getContentPane(){
        return contentPane;
    }

    public void initialize() {
        contentPane = new JPanel(new BorderLayout());
        northPane = new JPanel(new GridLayout(2, 3));
        centerPane = new JPanel(new BorderLayout());
        fieldPane = new JPanel(new GridLayout(7, 4, 10, 10));
        buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southGridPane = new JPanel(new GridLayout(2, 1));
        southNorth = new JPanel(new GridLayout(1, 7, 15, 15));

        saveButton = new JButton("Save");
        backButton = new JButton(new ImageIcon("Resource/back.jpg"));

        employeeName = new JTextField();
        employeeAddress = new JTextField();
        employeePhone = new JTextField();
        employeeEmail = new JTextField();

        Vector<String> statusFill = new Vector<String>();
        statusFill.add("Active");
        statusFill.add("Inactive");

        Vector<String> positionFill = new Vector<String>();
        positionFill.add("Manager");
        positionFill.add("Production");
        positionFill.add("Inventory");
        positionFill.add("Accounting");
        positionFill.add("Marketing");
        positionFill.add("Waitress");

        position = new JComboBox(positionFill);
        status = new JComboBox(statusFill);

        titleLabel = new JLabel("Insert New Employee");
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
        fieldPane.add(employeeName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Address : "));
        fieldPane.add(employeeAddress);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Phone Number : "));
        fieldPane.add(employeePhone);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Email : "));
        fieldPane.add(employeeEmail);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Position : "));
        fieldPane.add(position);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Status : "));
        fieldPane.add(status);
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
        backButton.addActionListener(new backButtonListener());
    }

    public InsertNewEmployeeView() {

        buildLayout();

//        JFrame frame = new JFrame("Input New Employee");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        InsertNewEmployeeView i = new InsertNewEmployeeView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (!employeeName.getText().equals("") && !employeeAddress.getText().equals("")
                    && !employeeEmail.getText().equals("") && !employeePhone.getText().equals("")) {
                InsertNewEmployeeModel insertModel = new InsertNewEmployeeModel();
                insertModel.insertNewEmployee(employeeName.getText(), employeeAddress.getText(),
                        employeeEmail.getText(), employeePhone.getText(), position.getSelectedItem()
                        .toString(), status.getSelectedItem().toString());
                JOptionPane.showMessageDialog(contentPane, "New Employee Added Successfully");
            }else{
                System.out.println("All field must be filled");
            }
        }
    }

    private class backButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            EmployeePage employeePage = new EmployeePage();
            employeePage.setOnlineUser(getOnlineUser());
            newPagePane = employeePage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
