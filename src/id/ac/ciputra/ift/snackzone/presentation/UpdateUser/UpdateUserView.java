/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateUser;

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
import id.ac.ciputra.ift.snackzone.presentation.Controller.ManageUserPage;

/**
 *
 * @author Kevin
 */
public class UpdateUserView {

    private JPanel contentPane, northPane, centerPane, centerFieldPane, fieldPane,
            buttonPane, newPagePane, southGridPane, southNorth;
    private JButton saveButton, backButton;
    private JComboBox username, role, status;
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

        Vector<String> roleFill = new Vector<String>();
        roleFill.add("Manager");
        roleFill.add("Accounting");
        roleFill.add("Inventory");
        roleFill.add("Cashier");
        Vector<String> statusFill = new Vector<String>();
        statusFill.add("Active");
        statusFill.add("Inactive");
        UpdateUserModel u = new UpdateUserModel();
        username = new JComboBox(u.getUsername());
        username.setSelectedIndex(-1);
        role = new JComboBox(roleFill);
        role.setSelectedIndex(-1);
        status = new JComboBox(statusFill);
        status.setSelectedIndex(-1);

        titleLabel = new JLabel("Update User");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
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
        fieldPane.add(username);
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
        username.addItemListener(new usernameComboListener());
        backButton.addActionListener(new backButtonListener());
    }

    public UpdateUserView() {

        buildLayout();

//        JFrame frame = new JFrame("Update User Role");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        UpdateUserView u = new UpdateUserView();
//    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (username.getSelectedIndex() != -1) {
                UpdateUserModel userModel = new UpdateUserModel();
                userModel.updateUser(role.getSelectedItem().toString(),
                        status.getSelectedItem().toString(), username.getSelectedItem().toString());
                JOptionPane.showMessageDialog(centerFieldPane, "User Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(centerPane, "Please Select UserName");
            }
        }
    }

    private class usernameComboListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            UpdateUserModel uModel = new UpdateUserModel();
            Vector<Users> user = new Vector<Users>();
            user = uModel.getAllData();
            short i = 0;
            while (!user.get(i).getUsername().equals(username.getSelectedItem().toString())) {
                i++;
            }

            short j = 0;
            while (!role.getItemAt(j).toString().equals(user.get(i).getRole())) {
                j++;
            }
            role.setSelectedIndex(j);

            short k = 0;
            while (!status.getItemAt(k).toString().equals(user.get(i).getStatus())) {
                k++;
            }
            status.setSelectedIndex(k);
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
