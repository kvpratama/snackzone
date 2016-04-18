/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Controller;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.presentation.InsertNewEmployee.InsertNewEmployeeView;
import id.ac.ciputra.ift.snackzone.presentation.ReportEmployee.EmployeeReportView;
import id.ac.ciputra.ift.snackzone.presentation.UpdateEmployee.UpdateEmployeeView;
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
public class EmployeePage {

    private JPanel titlePane, flowTitlePane, centerGridPane, northPane, centerPane,
            contentPane, newPagePane;
    private JButton insertNewEmployeeButton, updateEmployeeButton, employeeReportButton,
            backButton;
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

        insertNewEmployeeButton = new JButton(new ImageIcon("Resource/newemp.jpg"));
        updateEmployeeButton = new JButton(new ImageIcon("Resource/updemp.jpg"));
        employeeReportButton = new JButton(new ImageIcon("Resource/emprep.jpg"));
        backButton = new JButton();
        backButton.setIcon(new ImageIcon("Resource/back.jpg"));
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
        centerGridPane.add(insertNewEmployeeButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(updateEmployeeButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(employeeReportButton);
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
        insertNewEmployeeButton.addActionListener(new InsertNewEmployeeButtonListener());
        updateEmployeeButton.addActionListener(new UpdateEmployeeButtonListener());
        employeeReportButton.addActionListener(new EmployeeReportButtonListener());
        backButton.addActionListener(new BackButtonListener());
    }

    public EmployeePage() {
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
        EmployeePage e = new EmployeePage();
    }

    private class InsertNewEmployeeButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewEmployeeView employeeView = new InsertNewEmployeeView();
            employeeView.setOnlineUser(getOnlineUser());
            newPagePane = employeeView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class UpdateEmployeeButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            UpdateEmployeeView employeeView = new UpdateEmployeeView();
            employeeView.setOnlineUser(getOnlineUser());
            newPagePane = employeeView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class EmployeeReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EmployeeReportView employeeReportView = new EmployeeReportView();
            employeeReportView.setOnlineUser(getOnlineUser());
            newPagePane = employeeReportView.getContentPane();
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
