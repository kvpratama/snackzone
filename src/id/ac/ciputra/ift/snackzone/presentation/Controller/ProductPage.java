/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Controller;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.presentation.InsertNewProduct.InsertNewProductView;
import id.ac.ciputra.ift.snackzone.presentation.InsertNewRawMaterial.InsertNewRawMaterialView;
import id.ac.ciputra.ift.snackzone.presentation.ReportProduct.ReportProductView;
import id.ac.ciputra.ift.snackzone.presentation.ReportRawMaterial.RawMaterialReportView;
import id.ac.ciputra.ift.snackzone.presentation.UpdateProduct.UpdateProductView;
import id.ac.ciputra.ift.snackzone.presentation.UpdateRawMaterial.UpdateRawMaterialView;
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
public class ProductPage {

    private JPanel titlePane, flowTitlePane, centerGridPane, northPane, centerPane,
            contentPane, newPagePane;
    private JButton insertNewRawMaterialButton, updateRawMaterialButton, rawMaterialReportButton,
            insertNewProductButton, updateProductButton, productReportButton, backButton;
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

        insertNewRawMaterialButton = new JButton(new ImageIcon("Resource/insertnewraw.jpg"));
        updateRawMaterialButton = new JButton(new ImageIcon("Resource/updateraw.jpg"));
        rawMaterialReportButton = new JButton(new ImageIcon("Resource/rawreport.jpg"));
        insertNewProductButton = new JButton(new ImageIcon("Resource/insertprod.jpg"));
        updateProductButton = new JButton(new ImageIcon("Resource/updateprod.jpg"));
        productReportButton = new JButton(new ImageIcon("Resource/prodreport.jpg"));
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
        centerGridPane.add(insertNewRawMaterialButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(updateRawMaterialButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(rawMaterialReportButton);
        centerGridPane.add(new JLabel());

        centerGridPane.add(new JLabel());
        centerGridPane.add(insertNewProductButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(updateProductButton);
        centerGridPane.add(new JLabel());
        centerGridPane.add(productReportButton);
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
        insertNewRawMaterialButton.addActionListener(new InsertNewRawMateriaButtonListener());
        updateRawMaterialButton.addActionListener(new UpdateRawMateriaButtonListener());
        rawMaterialReportButton.addActionListener(new RawMaterialReportButtonListener());
        insertNewProductButton.addActionListener(new InsertNewProductButtonListener());
        updateProductButton.addActionListener(new UpdateProductButtonListener());
        productReportButton.addActionListener(new ProductReportButtonListener());
        backButton.addActionListener(new BackButtonListener());
    }

    public ProductPage() {
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
        ProductPage p = new ProductPage();
    }

    private class InsertNewRawMateriaButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewRawMaterialView inrmv = new InsertNewRawMaterialView();
            inrmv.setOnlineUser(getOnlineUser());
            newPagePane = inrmv.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class RawMaterialReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            RawMaterialReportView rwReport = new RawMaterialReportView();
            rwReport.setOnlineUser(getOnlineUser());
            newPagePane = rwReport.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class InsertNewProductButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertNewProductView newProduct = new InsertNewProductView();
            newProduct.setOnlineUser(getOnlineUser());
            newPagePane = newProduct.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class ProductReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ReportProductView productReport = new ReportProductView();
            productReport.setOnlineUser(getOnlineUser());
            newPagePane = productReport.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class UpdateRawMateriaButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            UpdateRawMaterialView uRawMateria = new UpdateRawMaterialView();
            uRawMateria.setOnlineUser(getOnlineUser());
            newPagePane = uRawMateria.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class UpdateProductButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            UpdateProductView uProduct = new UpdateProductView();
            uProduct.setOnlineUser(getOnlineUser());
            newPagePane = uProduct.getContentPane();
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
