/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportProduct;

import id.ac.ciputra.ift.snackzone.domain.Need;
import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import id.ac.ciputra.ift.snackzone.presentation.Controller.ProductPage;

/**
 *
 * @author Kevin
 */
public class ReportProductView {

    private JPanel contentPane, titlePane, centerPane, newPagePane, southNorthPane,
            southPane, southGridPane, southCenter;
    private JTable reportProductTable;
    private JButton backButton, printButton;
    private DefaultTableModel tableModel;
    private JScrollPane tableScroll;
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
        titlePane = new JPanel(new GridLayout(2, 3));
        southPane = new JPanel(new BorderLayout());
        southNorthPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southGridPane = new JPanel(new GridLayout(2, 1));
        southCenter = new JPanel(new GridLayout(1, 7, 15, 15));

        backButton = new JButton(new ImageIcon("Resource/back.jpg"));
        printButton = new JButton("Print");

        titleLabel = new JLabel("Product Report");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));

        createTable();
    }

    public void addComponent() {
        titlePane.add(new JLabel());
        titlePane.add(new JLabel());
        titlePane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));

        titlePane.add(new JLabel());
        titlePane.add(titleLabel);
        titlePane.add(new JLabel());

        southNorthPane.add(printButton);

        southCenter.add(backButton);
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());

        southGridPane.add(southCenter);
        southGridPane.add(new JLabel(new ImageIcon("Resource/bawah.png")));

        southPane.add(southNorthPane, BorderLayout.NORTH);
        southPane.add(southGridPane, BorderLayout.SOUTH);

        centerPane.add(titlePane, BorderLayout.NORTH);
        centerPane.add(tableScroll, BorderLayout.CENTER);
        centerPane.add(southPane, BorderLayout.SOUTH);
        
        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void createTable() {
        Vector dataVector = new Vector();
        Vector titleVector = new Vector();

        titleVector.add(0, "Product Name");
        titleVector.add(1, "Price");
        titleVector.add(2, "Raw Material");
        titleVector.add(3, "Need");
        titleVector.add(4, "Unit");

        tableModel = new DefaultTableModel(dataVector, titleVector){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        reportProductTable = new JTable();
        tableScroll = new JScrollPane(reportProductTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        reportProductTable.setModel(tableModel);
        reportProductTable.setAutoCreateRowSorter(true);
        addDataToTable();
    }

    public void addDataToTable() {
        ReportProductModel model = new ReportProductModel();
        Vector addToRow = null;
        Vector<Product> product = model.readAllProduct();
        Vector<RawMaterial> rawMaterial = model.readAllRawMaterial();
        Vector<Need> need = model.readAllNeed();
        for (int i = 0; i < product.size(); i++) {
            addToRow = new Vector();
            addToRow.add(product.get(i).getProductName());
            addToRow.add(product.get(i).getPrice());
            short j = 0;
            while (product.get(i).getProductId() != need.get(j).getProductId()) {
                j++;
            }
            short k = 0;
            while (need.get(j).getRawMaterialId() != rawMaterial.get(k).getRawMaterialId()) {
                k++;
            }
            addToRow.add(rawMaterial.get(k).getRawMaterialName());
            addToRow.add(need.get(j).getNeed());
            addToRow.add(rawMaterial.get(k).getUnit());
            tableModel.addRow(addToRow);
        }
    }

    public void runReport(String databaseName, String userName, String password,
            String reportFile) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Connection jdbcConnection = connectDB(databaseName, userName, password);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    null, jdbcConnection);
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
        printButton.addActionListener(new printButtonListener());
        backButton.addActionListener(new backButtonListener());
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public ReportProductView() {

        buildLayout();

//        JFrame frame = new JFrame("Check Stock");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        ReportProductView p = new ReportProductView();
//    }

    private class printButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String databaseName = "jdbc:oracle:thin:@localhost:1521:xe";
            String reportFile = "D:/UC/Semester 4/Visual Programming/Tugas/"
                    + "SnackZone/Report/ProductReport.jrxml";
            runReport(databaseName, "snackzone", "snackzone", reportFile);
        }
    }
    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ProductPage productPage = new ProductPage();
            productPage.setOnlineUser(getOnlineUser());
            newPagePane = productPage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
