/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewUser;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import id.ac.ciputra.ift.snackzone.presentation.Controller.ManageUserPage;

/**
 *
 * @author UserXP
 */
public class InsertNewUserView {

    private JPanel contentPane, northPane, centerPane, centerFieldPane, fieldPane, 
            buttonPane, newPagePane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JTextField userName;
    private JPasswordField password, verifyPass;
    private JComboBox role, status;
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

        userName = new JTextField();

        password = new JPasswordField();
        verifyPass = new JPasswordField();

        Vector<String> roleFill = new Vector<String>();
        roleFill.add("Manager");
        roleFill.add("Accounting");
        roleFill.add("Inventory");
        roleFill.add("Cashier");

        Vector<String> statusFill = new Vector<String>();
        statusFill.add("Active");
        statusFill.add("Inactive");

        role = new JComboBox(roleFill);
        status = new JComboBox(statusFill);

        titleLabel = new JLabel("Insert New User");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
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
        fieldPane.add(new JLabel("User Name : "));
        fieldPane.add(userName);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Role : "));
        fieldPane.add(role);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Status : "));
        fieldPane.add(status);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Password : "));
        fieldPane.add(password);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Verify Password : "));
        fieldPane.add(verifyPass);
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
        backButton.addActionListener(new backButtonListener());
    }

    public InsertNewUserView() {

        buildLayout();

//        JFrame frame = new JFrame("Add New User");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        InsertNewUserView a = new InsertNewUserView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewUserModel aModel = new InsertNewUserModel();
            Vector<Users> user = new Vector<Users>();
            boolean name = true;
            user = aModel.readAll();
            for (int i = 0; i < user.size(); i++) {
                if (user.get(i).getUsername().equals(userName.getText())) {
                    name = false;
                    JOptionPane.showMessageDialog(centerFieldPane, "Username Already Exist");
                    break;
                } else {
                }
            }

            if (name) {
                if (!userName.getText().equals("") && !String.valueOf(password.getPassword()).equals("")
                        && !String.valueOf(verifyPass.getPassword()).equals("")) {
                    if (String.valueOf(password.getPassword()).equals(String.valueOf(verifyPass.getPassword()))) {
                        aModel.insertNewUser(userName.getText(), role.getSelectedItem().toString(),
                                status.getSelectedItem().toString(), String.valueOf(password.getPassword()));
                        JOptionPane.showMessageDialog(centerFieldPane, "New User Added Successfully");
                    } else {
                        JOptionPane.showMessageDialog(centerFieldPane, "Password Not Match");
                    }
                } else {
                    JOptionPane.showMessageDialog(centerFieldPane, "All field Should Be Fill");
                }
            } else {
                System.out.println("do nothing");
            }
        }
    }

    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ManageUserPage UserPage = new ManageUserPage();
            UserPage.setOnlineUser(getOnlineUser());
            newPagePane = UserPage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
