/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Controller;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.presentation.InsertNewUser.InsertNewUserView;
import id.ac.ciputra.ift.snackzone.presentation.ReportUser.UserReportView;
import id.ac.ciputra.ift.snackzone.presentation.UpdateUser.UpdateUserView;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class ManageUserPage {

    private JPanel titlePane, flowTitlePane, centerGridPane, northPane, centerPane,
            contentPane, newPagePane;
    private JButton insertNewUserButton, updateUserButton, userReportButton, backButton;
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
        titlePane = new JPanel(new GridLayout(1, 3, 10, 10));
        flowTitlePane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        centerGridPane = new JPanel(new GridLayout(4, 7, 15, 15));

        northPane = new JPanel(new BorderLayout());
        centerPane = new JPanel(new BorderLayout());
        contentPane = new JPanel(new BorderLayout(30, 30));

        insertNewUserButton = new JButton(new ImageIcon("Resource/newuser.jpg"));
        updateUserButton = new JButton(new ImageIcon("Resource/updateuser.jpg"));
        userReportButton = new JButton(new ImageIcon("Resource/userreport.jpg"));
        backButton = new JButton(new ImageIcon("Resource/back.jpg"));
    }

    public void addComponent() {
        titlePane.add(new JLabel());
        titlePane.add(new JLabel(new ImageIcon("Resource/alamat.png")));
        titlePane.add(new JLabel());

        flowTitlePane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));

        northPane.add(flowTitlePane, BorderLayout.NORTH);
        northPane.add(titlePane, BorderLayout.SOUTH);

        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());

        centerGridPane.add(new JLabel());
        centerGridPane.add(insertNewUserButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(updateUserButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(userReportButton);
        centerGridPane.add(new JLabel());

        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());

        centerGridPane.add(backButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());

        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(centerGridPane, BorderLayout.CENTER);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public void registerListener() {
        insertNewUserButton.addActionListener(new InsertNewUserButtonListener());
        updateUserButton.addActionListener(new UpdateUserButtonListener());
        userReportButton.addActionListener(new UserReportButtonListener());
        backButton.addActionListener(new BackButtonListener());
    }

    public ManageUserPage() {
        buildLayout();

//        JFrame frame = new JFrame("Process Selection");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ManageUserPage m = new ManageUserPage();
    }

    private class InsertNewUserButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewUserView insertNewUserView = new InsertNewUserView();
            insertNewUserView.setOnlineUser(getOnlineUser());
            newPagePane = insertNewUserView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class UpdateUserButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            UpdateUserView updateUserView = new UpdateUserView();
            updateUserView.setOnlineUser(getOnlineUser());
            newPagePane = updateUserView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class UserReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            UserReportView reportView = new UserReportView();
            reportView.setOnlineUser(getOnlineUser());
            newPagePane = reportView.getContentPane();
            switchPane(newPagePane);
        }
    }
    private class BackButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            HomePage homePage = new HomePage();
            homePage.setOnlineUser(getOnlineUser());
            newPagePane = homePage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
