/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Controller;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.presentation.ProductOfTheMonth.ProductOfTheMonthView;
import id.ac.ciputra.ift.snackzone.presentation.ReportSellingDaily.SellingReportDailyView;
import id.ac.ciputra.ift.snackzone.presentation.ReportSellingMonthly.SellingReportMonthlyView;
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
public class ReportPage {

    private JPanel titlePane, flowTitlePane, centerGridPane, northPane, centerPane,
            contentPane, newPagePane;
    private JButton dailySellingReport, monthlySellingReport, productOfTheMonth,
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

        dailySellingReport = new JButton(new ImageIcon("Resource/dailysales.jpg"));
        monthlySellingReport = new JButton(new ImageIcon("Resource/monthlysales.jpg"));
        productOfTheMonth = new JButton(new ImageIcon("Resource/prodofmonth.jpg"));
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
        centerGridPane.add(dailySellingReport);
        centerGridPane.add(new JLabel());
        centerGridPane.add(monthlySellingReport);
        centerGridPane.add(new JLabel());
        centerGridPane.add(productOfTheMonth);
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
        dailySellingReport.addActionListener(new DailySellingReportButtonListener());
        monthlySellingReport.addActionListener(new MonthlySellingReportButtonListener());
        productOfTheMonth.addActionListener(new ProductOfTheMonthButtonListener());
        backButton.addActionListener(new BackButtonListener());
    }

    public ReportPage() {
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
        ReportPage r = new ReportPage();
    }

    private class DailySellingReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SellingReportDailyView dayView = new SellingReportDailyView();
            dayView.setOnlineUser(getOnlineUser());
            newPagePane = dayView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class MonthlySellingReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SellingReportMonthlyView monthView = new SellingReportMonthlyView();
            monthView.setOnlineUser(getOnlineUser());
            newPagePane = monthView.getContentPane();
            switchPane(newPagePane);
        }
    }

    private class ProductOfTheMonthButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ProductOfTheMonthView monthView = new ProductOfTheMonthView();
            monthView.setOnlineUser(getOnlineUser());
            newPagePane = monthView.getContentPane();
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
