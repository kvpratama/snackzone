/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportSellingMonthly;

import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.Selling;
import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import id.ac.ciputra.ift.snackzone.presentation.Controller.ReportPage;

/**
 *
 * @author Kevin
 */
public class SellingReportMonthlyView {

    private JPanel contentPane, centerPane, northPane, titlePane, datePane, southPane,
            southNorthPane, southSouthPane, newPagePane, southSouth, southGrid;
    private JComboBox monthBox, yearBox;
    private JButton backButton, viewReportButton, viewDetailButton, printButton;
    private JTable sellingReportTable;
    private JLabel titleLabel, totalLabel;
    private DefaultTableModel tableModel;
    private JScrollPane tableScroll;
    private Vector<Selling> selling;
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
        northPane = new JPanel(new BorderLayout());
        titlePane = new JPanel(new GridLayout(2, 3));
        datePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPane = new JPanel(new BorderLayout());
        southNorthPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southSouthPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southSouth = new JPanel(new GridLayout(1, 2));
        southGrid = new JPanel(new GridLayout(1, 4));

        backButton = new JButton(new ImageIcon("Resource/back.jpg"));
        viewReportButton = new JButton("View Report");
        viewDetailButton = new JButton("View Detail");
        printButton = new JButton("Print");
        printButton.setEnabled(false);

        titleLabel = new JLabel("Monthly Sales Report");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        totalLabel = new JLabel("Total : Rp. 0");
        totalLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        SellingReportMonthlyModel s = new SellingReportMonthlyModel();

        Vector<Date> date = s.getDistinctDate();
        Vector month = new Vector();
        Vector year = new Vector();

        for (int i = 0; i < date.size(); i++) {
            if (!year.contains(date.get(i).getYear() + 1900)) {
                year.add(date.get(i).getYear() + 1900);
            }
        }

        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");

        monthBox = new JComboBox(month);
        monthBox.setSelectedIndex(-1);
        yearBox = new JComboBox(year);
        yearBox.setSelectedIndex(-1);

        createTable();

        selling = s.readAllSelling();
    }

    public void addComponent() {
        titlePane.add(new JLabel());
        titlePane.add(new JLabel());
        titlePane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));

        titlePane.add(new JLabel());
        titlePane.add(titleLabel);
        titlePane.add(new JLabel());

        datePane.add(new JLabel("Month :"));
        datePane.add(monthBox);
        datePane.add(new JLabel("Year :"));
        datePane.add(yearBox);
        datePane.add(viewReportButton);

        northPane.add(titlePane, BorderLayout.NORTH);
        northPane.add(datePane, BorderLayout.SOUTH);

        southGrid.add(backButton);
        southGrid.add(new JLabel());
        southGrid.add(new JLabel());
        southGrid.add(new JLabel());

        southSouthPane.add(totalLabel);

        southSouth.add(southGrid);
        southSouth.add(southSouthPane);

        southNorthPane.add(viewDetailButton);
        southNorthPane.add(printButton);

        southPane.add(southNorthPane, BorderLayout.NORTH);
        southPane.add(southSouth, BorderLayout.SOUTH);

        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(tableScroll, BorderLayout.CENTER);
        centerPane.add(southPane, BorderLayout.SOUTH);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void createTable() {
        Vector dataVector = new Vector();
        Vector titleVector = new Vector();

        titleVector.add(0, "Selling ID");
        titleVector.add(1, "Date");
        titleVector.add(2, "Cashier");
        titleVector.add(3, "Total Quantity");
        titleVector.add(4, "Total Price");
        titleVector.add(5, "Payment Method");
        titleVector.add(6, "Card Number");

        tableModel = new DefaultTableModel(dataVector, titleVector){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        sellingReportTable = new JTable();
        tableScroll = new JScrollPane(sellingReportTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sellingReportTable.setModel(tableModel);
        sellingReportTable.setAutoCreateRowSorter(true);
    }

    public void addDataToTable(Selling s) {
        Vector dataVector = new Vector();
        SimpleDateFormat d = new SimpleDateFormat("dd - MMMMM - yyyy", Locale.US);
        dataVector.add(s.getSellingId());
        dataVector.add(d.format(s.getSellingDate()));
        dataVector.add(s.getCashier());
        dataVector.add(s.getTotalQuantity());
        dataVector.add(Math.round(s.getTotalPrice()));
        dataVector.add(s.getPaymentMethod());
        dataVector.add(s.getCardNumber());
        tableModel.addRow(dataVector);
    }

    public Vector getProduct() {
        SellingReportMonthlyModel sModel = new SellingReportMonthlyModel();
        return sModel.readAllProduct();
    }

    public int convertMonth(String month) {
        int months = 0;
        if (month.equals("January")) {
            months = 0;
        } else if (month.equals("February")) {
            months = 1;
        } else if (month.equals("March")) {
            months = 2;
        } else if (month.equals("April")) {
            months = 3;
        } else if (month.equals("May")) {
            months = 4;
        } else if (month.equals("June")) {
            months = 5;
        } else if (month.equals("July")) {
            months = 6;
        } else if (month.equals("August")) {
            months = 7;
        } else if (month.equals("September")) {
            months = 8;
        } else if (month.equals("October")) {
            months = 9;
        } else if (month.equals("November")) {
            months = 10;
        } else if (month.equals("December")) {
            months = 11;
        }
        return months;
    }

    public void runReportByParameter(String databaseName, String userName, String password,
            String reportFile, int month, int year) {
        HashMap parameterMap = new HashMap();
        parameterMap.put("sellingMonth", month);
        parameterMap.put("sellingYear", year);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Connection jdbcConnection = connectDB(databaseName, userName, password);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameterMap, jdbcConnection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception ex) {
            String connectMsg = "Could not create the report"
                    + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }
    }

    public Connection connectDB(String databaseName, String userName, String password) {
        Connection jdbcConnection = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            jdbcConnection = DriverManager.getConnection(databaseName, userName, password);
        } catch (Exception ex) {
            String connectMsg = "Could not connect to the database "
                    + ex.getMessage() + " " + ex.getLocalizedMessage();
            System.out.println(connectMsg);
        }
        return jdbcConnection;
    }

    public void registerListener() {
        viewDetailButton.addActionListener(new viewDetailButtonListener());
        viewReportButton.addActionListener(new viewReportButtonListener());
        printButton.addActionListener(new printButtonListener());
        backButton.addActionListener(new backButtonListener());
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public SellingReportMonthlyView() {
        buildLayout();

//        JFrame frame = new JFrame("Sales Report");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        SellingReportMonthlyView c = new SellingReportMonthlyView();
//    }

    private class viewReportButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (monthBox.getSelectedIndex() != -1
                    && yearBox.getSelectedIndex() != -1) {
                int row = tableModel.getRowCount();
                for (int i = 0; i < row; i++) {
                    tableModel.removeRow(0);
                }
                boolean hasData = false;
                int year = Integer.parseInt(yearBox.getSelectedItem().toString()) - 1900;
                for (int i = 0; i < selling.size(); i++) {
                    if (selling.get(i).getSellingDate().getMonth()
                            == convertMonth(monthBox.getSelectedItem().toString())
                            && selling.get(i).getSellingDate().getYear() == year) {
                        addDataToTable(selling.get(i));
                        hasData = true;
                    }
                }
                if (hasData) {
                    printButton.setEnabled(true);
                } else if (hasData == false) {
                    JOptionPane.showMessageDialog(contentPane, "There is no selling");
                    printButton.setEnabled(false);
                }
                double totalPrice = 0;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    totalPrice = totalPrice + (Long) tableModel.getValueAt(i, 4);
                }
                totalLabel.setText("Total : Rp. " + Math.round(totalPrice));
            } else {
                JOptionPane.showMessageDialog(contentPane,
                        "Please Select Month and Year");
            }
        }
    }

    private class viewDetailButtonListener implements ActionListener {

        DefaultTableModel tModel;

        public void actionPerformed(ActionEvent e) {

            if (sellingReportTable.getRowCount() != 0
                    && sellingReportTable.getSelectedColumn() != -1) {

                JDialog detail = new JDialog();
                detail.setSize(500, 200);
                detail.add(createSellingDetailTable(), BorderLayout.CENTER);
                detail.setAlwaysOnTop(true);
                detail.setLocationRelativeTo(contentPane);
                detail.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                detail.setTitle("Selling Detail");
                

                Vector<SellingDetail> sellingDetail = getSellingDetail(
                        sellingReportTable.getValueAt(
                        sellingReportTable.getSelectedRow(), 0).toString());
                Vector<Product> product = getProduct();

                for (int i = 0; i < sellingDetail.size(); i++) {
                    for (int j = 0; j < product.size(); j++) {
                        if (sellingDetail.get(i).getProductId() == product.get(j).getProductId()) {
                            addDataToSellingDetailTable(product.get(j).getProductName(),
                                    sellingDetail.get(i).getQuantity(), product.get(j).getPrice(),
                                    sellingDetail.get(i).getDiscountProduct());
                        }
                    }
                }

                JLabel totalLabel = new JLabel();
                JPanel southPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                int total = 0;
                for (int i= 0; i < tModel.getRowCount(); i++) {
                    total = total + Integer.parseInt(tModel.getValueAt(i, 4).toString());
                }
                totalLabel.setText("Total Rp. " + total);
                totalLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
                
                southPane.add(totalLabel);

                detail.add(southPane, BorderLayout.SOUTH);
                detail.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(contentPane, "No data was selected");
            }
        }

        public void addDataToSellingDetailTable(String productName, int qty,
                double price, int discount) {
            Vector data = new Vector();
            data.add(productName);
            data.add(qty);
            data.add(Math.round(price));
            data.add(discount);
            double discountPrice = price * qty * discount / 100;
            data.add(Math.round(price * qty - discountPrice));
            tModel.addRow(data);
        }

        public JScrollPane createSellingDetailTable() {
            Vector dataVector = new Vector();
            Vector titleVector = new Vector();

            titleVector.add(0, "Product Name");
            titleVector.add(1, "Quantity");
            titleVector.add(2, "Price");
            titleVector.add(3, "Discount (%)");
            titleVector.add(4, "Total");

            tModel = new DefaultTableModel(dataVector, titleVector){

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

            };
            JTable sellingDetailTable = new JTable();
            JScrollPane tableScroll = new JScrollPane(sellingDetailTable,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sellingDetailTable.setModel(tModel);
            return tableScroll;
        }

        public Vector getSellingDetail(String sellingId) {
            SellingReportMonthlyModel sModel = new SellingReportMonthlyModel();
            return sModel.getSellingDetail(sellingId);
        }
    }

    private class printButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String databaseName = "jdbc:oracle:thin:@localhost:1521:xe";
            String reportFile = "D:/UC/Semester 4/Visual Programming/Tugas/"
                    + "SnackZone/Report/SellingReportMonthly.jrxml";
            runReportByParameter(databaseName, "snackzone", "snackzone", reportFile,
                    convertMonth(monthBox.getSelectedItem().toString()) + 1,
                    Integer.parseInt(yearBox.getSelectedItem().toString()));
        }
    }

    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ReportPage page = new ReportPage();
            page.setOnlineUser(getOnlineUser());
            newPagePane = page.getContentPane();
            switchPane(newPagePane);
        }
    }
}
