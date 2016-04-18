/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Controller;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.presentation.Login.LoginView;
import id.ac.ciputra.ift.snackzone.presentation.POS.PointOfSaleView;
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
public class HomePage {

    private JPanel titlePane, flowTitlePane, centerGridPane, northPane, centerPane,
            contentPane, newPagePane;
    private JButton posButton, supplierButton, employeeButton,
            productButton, salesReportButton, manageUserButton, logoutButton;
    private Users onlineUser;

    public Users getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(Users onlineUser) {
        this.onlineUser = onlineUser;
        if(this.onlineUser.getRole().equals("Accounting")){
            posButton.setEnabled(false);
            supplierButton.setEnabled(false);
            employeeButton.setEnabled(false);
            productButton.setEnabled(false);
            manageUserButton.setEnabled(false);
        }else if(this.onlineUser.getRole().equals("Inventory")){
            posButton.setEnabled(false);
            supplierButton.setEnabled(false);
            employeeButton.setEnabled(false);
            salesReportButton.setEnabled(false);
            manageUserButton.setEnabled(false);
        }else if (this.onlineUser.getRole().equals("Cashier")) {
            salesReportButton.setEnabled(false);
            supplierButton.setEnabled(false);
            employeeButton.setEnabled(false);
            productButton.setEnabled(false);
            manageUserButton.setEnabled(false);
        }
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
        titlePane = new JPanel(new GridLayout(1, 3, 10, 10));
        flowTitlePane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        centerGridPane = new JPanel(new GridLayout(4, 7, 15, 15));

        northPane = new JPanel(new BorderLayout());
        centerPane = new JPanel(new BorderLayout());
        contentPane = new JPanel(new BorderLayout(30, 30));

        posButton = new JButton(new ImageIcon("Resource/pos.jpg"));
        posButton.setDisabledIcon(new ImageIcon("Resource/stoppos.jpg"));
        employeeButton = new JButton(new ImageIcon("Resource/employee.jpg"));
        employeeButton.setDisabledIcon(new ImageIcon("Resource/stopemployee.jpg"));
        productButton = new JButton(new ImageIcon("Resource/product.jpg"));
        productButton.setDisabledIcon(new ImageIcon("Resource/stopproduct.jpg"));
        manageUserButton = new JButton(new ImageIcon("Resource/user.jpg"));
        manageUserButton.setDisabledIcon(new ImageIcon("Resource/stopuser.jpg"));
        supplierButton = new JButton(new ImageIcon("Resource/supplier.jpg"));
        supplierButton.setDisabledIcon(new ImageIcon("Resource/stopsupplier.jpg"));
        salesReportButton = new JButton(new ImageIcon("Resource/salesreport.jpg"));
        salesReportButton.setDisabledIcon(new ImageIcon("Resource/stopsalesreport.jpg"));
        logoutButton = new JButton(new ImageIcon("Resource/logout.jpg"));
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
        centerGridPane.add(posButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(productButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(salesReportButton);
        centerGridPane.add(new JLabel());

        centerGridPane.add(new JLabel());
        centerGridPane.add(employeeButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(supplierButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(manageUserButton);
        centerGridPane.add(new JLabel());

        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(new JLabel());
        centerGridPane.add(logoutButton);

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
        posButton.addActionListener(new posButtonListener());
        employeeButton.addActionListener(new EmployeeButtonListener());
        productButton.addActionListener(new ProductButtonListener());
        manageUserButton.addActionListener(new ManageUserButtonListener());
        supplierButton.addActionListener(new SupplierButtonListener());
        salesReportButton.addActionListener(new SalesReportButtonListener());
        logoutButton.addActionListener(new LogoutButtonListener());
    }

    public HomePage() {
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
        HomePage p = new HomePage();
    }

    private class posButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            PointOfSaleView posView = new PointOfSaleView();
            posView.setOnlineUser(getOnlineUser());
            newPagePane = posView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class EmployeeButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EmployeePage employeePage = new EmployeePage();
            employeePage.setOnlineUser(getOnlineUser());
            newPagePane = employeePage.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class ProductButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ProductPage productPage = new ProductPage();
            productPage.setOnlineUser(getOnlineUser());
            newPagePane = productPage.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class ManageUserButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ManageUserPage manageUserPage = new ManageUserPage();
            manageUserPage.setOnlineUser(getOnlineUser());
            newPagePane = manageUserPage.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class SupplierButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SupplierPage supplierPage = new SupplierPage();
            supplierPage.setOnlineUser(getOnlineUser());
            newPagePane = supplierPage.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class SalesReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ReportPage reportPage = new ReportPage();
            reportPage.setOnlineUser(getOnlineUser());
            newPagePane = reportPage.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class LogoutButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            LoginView loginView = new LoginView(true);
            newPagePane = loginView.getContentPane();
            switchPane(newPagePane);
        }
    }
}
