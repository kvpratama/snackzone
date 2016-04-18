/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ProductOfTheMonth;

import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.Selling;
import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import javax.swing.table.DefaultTableModel;
import id.ac.ciputra.ift.snackzone.presentation.Controller.ReportPage;

/**
 *
 * @author Kevin
 */
public class ProductOfTheMonthView {

    private JPanel contentPane, northPane, titlePane, datePane, centerFieldPane,
            centerNorthPane, centerSouthPane, centerPane, newPagePane, southPane,
            southGridPane, winnerPane1, winnerPane2, winnerPane3;
    private JTable checkStockTable;
    private JButton backButton, viewButton;
    private JComboBox monthBox, yearBox;
    private DefaultTableModel tableModel;
    private JScrollPane tableScroll;
    private JLabel titleLabel, number1, number2, number3;
    private Vector<Selling> selling;
    private Vector<Product> product;
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
        centerFieldPane = new JPanel(new BorderLayout());
        centerNorthPane = new JPanel(new GridLayout(1, 3));
        centerSouthPane = new JPanel(new GridLayout(1, 3));
        southPane = new JPanel(new BorderLayout());
        southGridPane = new JPanel(new GridLayout(1, 7, 15, 15));
        winnerPane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winnerPane2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winnerPane3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        backButton = new JButton(new ImageIcon("Resource/back.jpg"));
        viewButton = new JButton("View");

        titleLabel = new JLabel("Product Of The Month");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        number1 = new JLabel();
        number1.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 25));
        number2 = new JLabel();
        number2.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 25));
        number3 = new JLabel();
        number3.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 25));

        ProductOfTheMonthModel p = new ProductOfTheMonthModel();

        Vector<Date> date = p.getDistinctDate();
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

        selling = p.readAllSelling();
        product = p.readAllProduct();
    }

    public void addComponent() {
        titlePane.add(new JLabel());
        titlePane.add(new JLabel());
        titlePane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));

        titlePane.add(new JLabel());
        titlePane.add(titleLabel);
        titlePane.add(new JLabel());

        datePane.add(new JLabel("Month: "));
        datePane.add(monthBox);
        datePane.add(new JLabel("Year: "));
        datePane.add(yearBox);
        datePane.add(viewButton);

        northPane.add(titlePane, BorderLayout.NORTH);
        northPane.add(datePane, BorderLayout.SOUTH);

        winnerPane1.add(number1);
        winnerPane2.add(number2);
        winnerPane3.add(number3);

        centerNorthPane.add(winnerPane2);
        centerNorthPane.add(winnerPane1);
        centerNorthPane.add(winnerPane3);

        centerSouthPane.add(new JLabel(new ImageIcon("Resource/silvermedal.png")));
        centerSouthPane.add(new JLabel(new ImageIcon("Resource/goldmedal.png")));
        centerSouthPane.add(new JLabel(new ImageIcon("Resource/bronzemedal.png")));

        centerFieldPane.add(centerNorthPane, BorderLayout.NORTH);
        centerFieldPane.add(centerSouthPane, BorderLayout.CENTER);
        centerFieldPane.add(new JLabel(), BorderLayout.WEST);

        southGridPane.add(backButton);
        southGridPane.add(new JLabel());
        southGridPane.add(new JLabel());
        southGridPane.add(new JLabel());
        southGridPane.add(new JLabel());
        southGridPane.add(new JLabel());
        southGridPane.add(new JLabel());

        southPane.add(tableScroll, BorderLayout.NORTH);
        southPane.add(southGridPane, BorderLayout.SOUTH);

        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(centerFieldPane, BorderLayout.CENTER);
        centerPane.add(southPane, BorderLayout.SOUTH);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void createTable() {
        Vector dataVector = new Vector();
        Vector titleVector = new Vector();

        titleVector.add(0, "No");
        titleVector.add(1, "Product Name");
        titleVector.add(2, "Sold");

        tableModel = new DefaultTableModel(dataVector, titleVector){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        checkStockTable = new JTable();
        tableScroll = new JScrollPane(checkStockTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setPreferredSize(new Dimension(700, 130));
        checkStockTable.setModel(tableModel);
        checkStockTable.setAutoCreateRowSorter(true);
    }

    public void addDataToTable(int index, String productName, int sold) {
        Vector addToRow = new Vector();
        addToRow.add(index);
        addToRow.add(productName);
        addToRow.add(sold);
        tableModel.addRow(addToRow);
    }

    public void registerListener() {
        viewButton.addActionListener(new viewButtonListener());
        backButton.addActionListener(new backButtonListener());
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public ProductOfTheMonthView() {

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
//        ProductOfTheMonthView p = new ProductOfTheMonthView();
//    }

    private class viewButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (monthBox.getSelectedIndex() != -1
                    && yearBox.getSelectedIndex() != -1) {
                int month = 0;
                if (monthBox.getSelectedItem().toString().equals("January")) {
                    month = 0;
                } else if (monthBox.getSelectedItem().toString().equals("February")) {
                    month = 1;
                } else if (monthBox.getSelectedItem().toString().equals("March")) {
                    month = 2;
                } else if (monthBox.getSelectedItem().toString().equals("April")) {
                    month = 3;
                } else if (monthBox.getSelectedItem().toString().equals("May")) {
                    month = 4;
                } else if (monthBox.getSelectedItem().toString().equals("June")) {
                    month = 5;
                } else if (monthBox.getSelectedItem().toString().equals("July")) {
                    month = 6;
                } else if (monthBox.getSelectedItem().toString().equals("August")) {
                    month = 7;
                } else if (monthBox.getSelectedItem().toString().equals("September")) {
                    month = 8;
                } else if (monthBox.getSelectedItem().toString().equals("October")) {
                    month = 9;
                } else if (monthBox.getSelectedItem().toString().equals("November")) {
                    month = 10;
                } else if (monthBox.getSelectedItem().toString().equals("December")) {
                    month = 11;
                }

                int year = Integer.parseInt(yearBox.getSelectedItem().toString()) - 1900;
                ProductOfTheMonthModel pModel = new ProductOfTheMonthModel();
                Vector<String> sellingId = new Vector<String>();
                for (int i = 0; i < selling.size(); i++) {
                    if (selling.get(i).getSellingDate().getMonth() == month
                            && selling.get(i).getSellingDate().getYear() == year) {
                        sellingId.add("" + selling.get(i).getSellingId());
                    }
                }

                boolean hasData = false;
                if (!sellingId.isEmpty()) {
                    hasData = true;
                }

                if (hasData) {
                    Vector<SellingDetail> sellingDetail = new Vector<SellingDetail>();
                    for (int i = 0; i < sellingId.size(); i++) {
                        int size = pModel.getSellingDetail(sellingId.get(i)).size();
                        Vector<SellingDetail> temp = pModel.getSellingDetail(sellingId.get(i));
                        for (int j = 0; j < size; j++) {
                            sellingDetail.add(temp.get(j));
                        }
                    }

                    Vector<Champ> findChamp = new Vector<Champ>();
                    for (int i = 0; i < sellingDetail.size(); i++) {
                        if (findChamp.isEmpty()) {
                            Champ c = new Champ();
                            c.setProdId(sellingDetail.get(i).getProductId());
                            c.setSold(sellingDetail.get(i).getQuantity());
                            findChamp.add(c);
                        } else {
                            boolean found = false;
                            for (int j = 0; j < findChamp.size(); j++) {
                                if (findChamp.get(j).getProdId() == sellingDetail.get(i).getProductId()) {
                                    findChamp.get(j).setSold(findChamp.get(j).getSold()
                                            + sellingDetail.get(i).getQuantity());
                                    found = true;
                                    break;
                                }
                            }
                            if (found == false) {
                                Champ c = new Champ();
                                c.setProdId(sellingDetail.get(i).getProductId());
                                c.setSold(sellingDetail.get(i).getQuantity());
                                findChamp.add(c);
                            }
                        }
                    }
                    Vector<Champ> sort = new Vector<Champ>();
                    for (int i = 0; i < findChamp.size(); i++) {
                        if (sort.isEmpty()) {
                            sort.add(findChamp.get(i));
                        } else {
                            for (int j = 0; j < sort.size(); j++) {
                                if (findChamp.get(i).getSold() > sort.get(j).getSold()) {
                                    sort.add(j, findChamp.get(i));
                                    break;
                                } else if (j == sort.size() - 1) {
                                    sort.add(findChamp.get(i));
                                    break;
                                }
                            }
                        }
                    }
                    if (tableModel.getRowCount() != 0) {
                        int row = tableModel.getRowCount();
                        for (int i = 0; i < row; i++) {
                            tableModel.removeRow(0);
                        }
                    }
                    for (int i = 0; i < sort.size(); i++) {
                        for (int j = 0; j < product.size(); j++) {
                            if (product.get(j).getProductId() == sort.get(i).getProdId()) {
                                addDataToTable(i + 1, product.get(j).getProductName(),
                                        sort.get(i).getSold());
                                if (i == 0) {
                                    number1.setText(product.get(j).getProductName());
                                } else if (i == 1) {
                                    number2.setText(product.get(j).getProductName());
                                } else if (i == 2) {
                                    number3.setText(product.get(j).getProductName());
                                }
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "There is no selling");
                    int row = tableModel.getRowCount();
                    for (int i = 0; i < row; i++) {
                        tableModel.removeRow(0);
                    }
                    number1.setText("");
                    number2.setText("");
                    number3.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(centerPane, "Please Select Month and Year");
            }
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
