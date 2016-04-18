/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Controller;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.presentation.InsertNewSupplier.InsertNewSupplierView;
import id.ac.ciputra.ift.snackzone.presentation.ReportSupplier.SupplierReportView;
import id.ac.ciputra.ift.snackzone.presentation.UpdateSupplier.UpdateSupplierView;
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
public class SupplierPage {

    private JPanel titlePane, flowTitlePane, centerGridPane, northPane, centerPane,
            contentPane, newPagePane;
    private JButton insertNewSupplierButton, updateSupplierButton, supplierReportButton,
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

        insertNewSupplierButton = new JButton(new ImageIcon("Resource/newsupplier.jpg"));
        updateSupplierButton = new JButton(new ImageIcon("Resource/updsup.jpg"));
        supplierReportButton = new JButton(new ImageIcon("Resource/supprep.jpg"));
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
        centerGridPane.add(insertNewSupplierButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(updateSupplierButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(supplierReportButton);
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
        insertNewSupplierButton.addActionListener(new InsertNewSupplierButtonListener());
        updateSupplierButton.addActionListener(new UpdateSupplierButtonListener());
        supplierReportButton.addActionListener(new SupplierReportButtonListener());
        backButton.addActionListener(new BackButtonListener());
    }

    public SupplierPage() {
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
        SupplierPage s = new SupplierPage();
    }

    private class InsertNewSupplierButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewSupplierView insertNewSupplierView = new InsertNewSupplierView();
            insertNewSupplierView.setOnlineUser(getOnlineUser());
            newPagePane = insertNewSupplierView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class UpdateSupplierButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            UpdateSupplierView updateSupplierView = new UpdateSupplierView();
            updateSupplierView.setOnlineUser(getOnlineUser());
            newPagePane = updateSupplierView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class SupplierReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SupplierReportView supplierReportView = new SupplierReportView();
            supplierReportView.setOnlineUser(getOnlineUser());
            newPagePane = supplierReportView.getContentPane();
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
