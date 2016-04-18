/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.POS;

import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import id.ac.ciputra.ift.snackzone.presentation.Controller.HomePage;

/**
 *
 * @author Kevin
 */
public class PointOfSaleView {

    private JPanel northPane, northSouthPane, inputPane, datePane, titlePane,
            southPane, contentPane, centerPane, totalPane, southNorth, southSouth, southGrid,
            buttonPane, paymentPane, cardNumberPane, newPagePane;
    private JButton addButton, saveButton, deleteButton, printButton, newButton,
            backButton;
    private JComboBox productNameCombo, paymentMethodCombo;
    private JTextField cardNumberField, qtyField, discountField;
    private JLabel totalLabel, titleLabel, sellingIdLabel, cashierLabel;
    private Font totalFont;
    private JTable posTable;
    private DefaultTableModel tableModel;
    private JScrollPane tableScroll;
    private Vector<Product> product;
    private Users onlineUser;

    public Users getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(Users onlineUser) {
        this.onlineUser = onlineUser;
        cashierLabel.setText(onlineUser.getUsername());
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
        northPane = new JPanel(new BorderLayout());
        northSouthPane = new JPanel(new GridLayout(2, 1, 10, 10));
        datePane = new JPanel(new GridLayout(3, 4, 10, 10));
        inputPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        titlePane = new JPanel(new GridLayout(2, 3));
        southPane = new JPanel(new BorderLayout());
        southSouth = new JPanel(new GridLayout(1, 2));
        southNorth = new JPanel(new GridLayout(3, 1));
        southGrid = new JPanel(new GridLayout(1, 4));
        totalPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        paymentPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cardNumberPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contentPane = new JPanel(new BorderLayout());
        centerPane = new JPanel(new BorderLayout());

        addButton = new JButton("Add");
        saveButton = new JButton("Save");
        printButton = new JButton("Print");
        printButton.setEnabled(false);
        deleteButton = new JButton("Delete");
        newButton = new JButton("New");
        newButton.setEnabled(false);
        backButton = new JButton(new ImageIcon("Resource/back.jpg"));

        PointOfSaleModel pModel = new PointOfSaleModel();
        product = pModel.readProduct();

        Vector<String> productName = new Vector<String>();
        for (int i = 0; i < product.size(); i++) {
            productName.add(product.get(i).getProductName());
        }
        productNameCombo = new JComboBox(productName);
        productNameCombo.setSelectedIndex(-1);

        Vector<String> paymentMethod = new Vector<String>();
        paymentMethod.add("Cash");
        paymentMethod.add("Card");
        paymentMethodCombo = new JComboBox(paymentMethod);

        cardNumberField = new JTextField();
        cardNumberField.setPreferredSize(new Dimension(120, 25));
        cardNumberField.setEnabled(false);
        qtyField = new JTextField();
        qtyField.setPreferredSize(new Dimension(40, 25));
        discountField = new JTextField("0");
        discountField.setPreferredSize(new Dimension(40, 25));

        totalFont = new Font("serif", Font.BOLD, 40);

        totalLabel = new JLabel("Rp. 0");
        totalLabel.setFont(totalFont);

        titleLabel = new JLabel("Point Of Sales");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));

        sellingIdLabel = new JLabel("" + getSellingId());

        cashierLabel = new JLabel();

        createTable();
    }

    public Date getDate() {
        java.util.Date d = new java.util.Date();
        Date sqlDate = new Date(d.getTime());
        return sqlDate;
    }

    public int getSellingId() {
        PointOfSaleModel model = new PointOfSaleModel();
        return model.getSellingId();
    }

    public void addComponent() {
        titlePane.add(new JLabel());
        titlePane.add(new JLabel());
        titlePane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));
        titlePane.add(new JLabel());
        titlePane.add(titleLabel);
        titlePane.add(new JLabel());

        SimpleDateFormat s = new SimpleDateFormat("dd - MMMMM - yyyy", Locale.US);

        datePane.add(new JLabel("Date :"));
        datePane.add(new JLabel("" + s.format(getDate())));
        datePane.add(new JLabel());
        datePane.add(new JLabel());

        datePane.add(new JLabel("Invoice Number :"));
        datePane.add(sellingIdLabel);
        datePane.add(new JLabel());
        datePane.add(new JLabel());

        datePane.add(new JLabel("Cashier :"));
        datePane.add(cashierLabel);
        datePane.add(new JLabel());
        datePane.add(new JLabel());

        inputPane.add(new JLabel("Item Name : "));
        inputPane.add(productNameCombo);
        inputPane.add(new JLabel("Quantity : "));
        inputPane.add(qtyField);
        inputPane.add(new JLabel("Discount : "));
        inputPane.add(discountField);
        inputPane.add(new JLabel(" %"));
        inputPane.add(addButton);

        northSouthPane.add(datePane);
        northSouthPane.add(inputPane);

        totalPane.add(totalLabel);

        buttonPane.add(saveButton);
        buttonPane.add(deleteButton);
        buttonPane.add(printButton);
        buttonPane.add(newButton);

        northPane.add(titlePane, BorderLayout.NORTH);
        northPane.add(northSouthPane, BorderLayout.SOUTH);

        paymentPane.add(new JLabel("Payment Method :"));
        paymentPane.add(paymentMethodCombo);

        cardNumberPane.add(new JLabel("Card Number :"));
        cardNumberPane.add(cardNumberField);

        southGrid.add(backButton);
        southGrid.add(new JLabel());
        southGrid.add(new JLabel());
        southGrid.add(new JLabel());

        southSouth.add(southGrid);
        southSouth.add(totalPane);

        southNorth.add(paymentPane);
        southNorth.add(cardNumberPane);
        southNorth.add(buttonPane);

        southPane.add(southNorth, BorderLayout.NORTH);
        southPane.add(southSouth, BorderLayout.SOUTH);

        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(tableScroll, BorderLayout.CENTER);
        centerPane.add(southPane, BorderLayout.SOUTH);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void createTable() {
        Vector titleVector = new Vector();

        titleVector.add(0, "Product Name");
        titleVector.add(1, "Quantity");
        titleVector.add(2, "Price");
        titleVector.add(3, "Discount (%)");
        titleVector.add(4, "Sub Total");

        tableModel = new DefaultTableModel(new Vector(), titleVector) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        posTable = new JTable();
        tableScroll = new JScrollPane(posTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        posTable.setModel(tableModel);
    }

    public void addDataToTable() {
        Vector dataVector = new Vector();
        dataVector.add(productNameCombo.getSelectedItem().toString());
        dataVector.add(qtyField.getText());
        short i = 0;
        while (!product.get(i).getProductName().equals(
                productNameCombo.getSelectedItem().toString())) {
            i++;
        }
        double price = product.get(i).getPrice();
        int discount = Integer.parseInt(discountField.getText());
        int quantity = Integer.parseInt(qtyField.getText());
        double discountPrice = price * quantity * discount / 100;
        dataVector.add(Math.round(price));
        dataVector.add(discount);
        dataVector.add(Math.round(price * quantity - discountPrice));

        tableModel.addRow(dataVector);
    }

    public void runReportByParameter(String databaseName, String userName, String password,
            String reportFile, String sellingId) {
        HashMap parameterMap = new HashMap();
        parameterMap.put("idSelling", new String(sellingId));
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

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public void registerListener() {
        addButton.addActionListener(new addButtonListener());
        saveButton.addActionListener(new saveButtonListener());
        deleteButton.addActionListener(new deleteButtonListener());
        printButton.addActionListener(new printButtonListener());
        newButton.addActionListener(new newButtonListener());
        paymentMethodCombo.addItemListener(new paymentMethodListener());
        backButton.addActionListener(new backButtonListener());
    }

    public PointOfSaleView() {
        buildLayout();

//        JFrame frame = new JFrame("Point of Sales");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        PointOfSaleView p = new PointOfSaleView();
//    }

    private class addButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (productNameCombo.getSelectedIndex() != -1
                    && !qtyField.getText().equals("")) {
                if (!qtyField.getText().equals("0")) {
                    boolean sameProduct = false;
                    if (tableModel.getRowCount() != 0) {
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            if (tableModel.getValueAt(i, 0).toString().equals(
                                    productNameCombo.getSelectedItem().toString())) {
                                JOptionPane.showMessageDialog(contentPane,
                                        "You already added this product");
                                sameProduct = true;
                                break;
                            }
                        }
                    }
                    if (!sameProduct) {
                        try {
                            int qty = Integer.parseInt(qtyField.getText());
                            int disc = Integer.parseInt(discountField.getText());
                            if (qty < 0) {
                                qty = qty * -1;
                                qtyField.setText("" + qty);
                            }
                            if (disc < 0) {
                                disc = disc * -1;
                                discountField.setText("" + disc);
                            }
                            addDataToTable();
                            productNameCombo.setSelectedIndex(-1);
                            qtyField.setText("");
                            discountField.setText("0");
                            double totalPrice = 0;
                            for (int i = 0; i < tableModel.getRowCount(); i++) {
                                totalPrice = totalPrice + (Long) tableModel.getValueAt(i, 4);
                            }
                            totalLabel.setText("Rp. " + (int) totalPrice);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(centerPane, "Quantity and "
                                    + "Discount Must a Number");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(centerPane, "Quantity must not 0");
                }
            } else {
                JOptionPane.showMessageDialog(centerPane, "All Field Must Be Fill");
            }
        }
    }

    private class saveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (posTable.getRowCount() != 0) {
                if (paymentMethodCombo.getSelectedItem().toString().equals("Card")
                        && cardNumberField.getText().equals("")) {
                    JOptionPane.showMessageDialog(centerPane, "Card Number Field must be Filled");
                } else {
                    int totalQuantity = 0;
                    double totalPrice = 0;
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        totalQuantity = totalQuantity + Integer.parseInt(tableModel.getValueAt(i, 1).
                                toString());
                    }
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        totalPrice = totalPrice + (Long) tableModel.getValueAt(i, 4);
                    }
                    Vector<Sold> sold = new Vector<Sold>();
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        Sold s = new Sold();
                        short j = 0;
                        while (!tableModel.getValueAt(i, 0).toString().equals(product.get(j).getProductName())) {
                            j++;
                        }
                        s.setProductId(product.get(j).getProductId());
                        s.setQuantity(Integer.parseInt(tableModel.getValueAt(i, 1).toString()));
                        s.setDiscount(Integer.parseInt(tableModel.getValueAt(i, 3).toString()));
                        sold.add(s);
                    }
                    PointOfSaleModel pModel = new PointOfSaleModel();
                    pModel.insertSelling(Integer.parseInt(sellingIdLabel.getText()),
                            getOnlineUser().getUsername(), getDate(), totalQuantity,
                            totalPrice, paymentMethodCombo.getSelectedItem().toString(),
                            cardNumberField.getText(), sold);
                    JOptionPane.showMessageDialog(centerPane, "Selling Inserted");
                    saveButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    addButton.setEnabled(false);
                    printButton.setEnabled(true);
                    newButton.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(centerPane, "No Product Inserted");
            }
        }
    }

    private class deleteButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                tableModel.removeRow(posTable.getSelectedRow());
                double totalPrice = 0;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    totalPrice = totalPrice + (Long) tableModel.getValueAt(i, 4);
                }
                totalLabel.setText("Rp. " + (int) totalPrice);
            } catch (ArrayIndexOutOfBoundsException a) {
                JOptionPane.showMessageDialog(centerPane, "No data was selected");
            }
        }
    }

    private class printButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String databaseName = "jdbc:oracle:thin:@localhost:1521:xe";
            String reportFile = "D:/UC/Semester 4/Visual Programming/Tugas/"
                    + "SnackZone/Report/SellingReport.jrxml";
            runReportByParameter(databaseName, "snackzone", "snackzone", reportFile,
                    sellingIdLabel.getText());
        }
    }

    private class newButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            saveButton.setEnabled(true);
            deleteButton.setEnabled(true);
            addButton.setEnabled(true);
            printButton.setEnabled(false);
            newButton.setEnabled(false);
            totalLabel.setText("Rp. 0");
            cardNumberField.setText("");
            paymentMethodCombo.setSelectedIndex(0);
            int row = tableModel.getRowCount();
            for (int i = 0; i < row; i++) {
                tableModel.removeRow(0);
            }
            sellingIdLabel.setText("" + getSellingId());
        }
    }

    private class paymentMethodListener implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            if (paymentMethodCombo.getSelectedItem().toString().equals("Card")) {
                cardNumberField.setEnabled(true);
            } else if (paymentMethodCombo.getSelectedItem().toString().equals("Cash")) {
                cardNumberField.setEnabled(false);
                cardNumberField.setText("");
            }
        }
    }

    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            HomePage homePage = new HomePage();
            homePage.setOnlineUser(getOnlineUser());
            newPagePane = homePage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
