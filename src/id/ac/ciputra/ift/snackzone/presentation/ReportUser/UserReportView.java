/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportUser;

import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import id.ac.ciputra.ift.snackzone.presentation.Controller.ManageUserPage;

/**
 *
 * @author Kevin
 */
public class UserReportView {

    private JPanel contentPane, centerPane, titlePane, newPagePane, southGridPane,
            southCenter;
    private JTable userReportTable;
    private JButton backButton;
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
        southGridPane = new JPanel(new GridLayout(2, 1));
        southCenter = new JPanel(new GridLayout(1, 7, 15, 15));

        backButton = new JButton(new ImageIcon("Resource/back.jpg"));

        titleLabel = new JLabel("User Report");
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

        southCenter.add(backButton);
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());
        southCenter.add(new JLabel());

        southGridPane.add(southCenter);
        southGridPane.add(new JLabel(new ImageIcon("Resource/bawah.png")));

        centerPane.add(titlePane, BorderLayout.NORTH);
        centerPane.add(tableScroll, BorderLayout.CENTER);
        centerPane.add(southGridPane, BorderLayout.SOUTH);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void createTable() {
        Vector dataVector = new Vector();
        Vector titleVector = new Vector();

        titleVector.add(0, "Username");
        titleVector.add(1, "Role");
        titleVector.add(2, "Status");

        tableModel = new DefaultTableModel(dataVector, titleVector){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        userReportTable = new JTable();
        tableScroll = new JScrollPane(userReportTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        userReportTable.setModel(tableModel);
        userReportTable.setAutoCreateRowSorter(true);
        addDataToTable();
    }

    public void addDataToTable() {
        UserReportModel uModel = new UserReportModel();
        Vector addToRow = null;
        Vector<Users> user = new Vector();
        user = uModel.readAll();
        for (int i = 0; i < user.size(); i++) {
            addToRow = new Vector();
            addToRow.add(user.get(i).getUsername());
            addToRow.add(user.get(i).getRole());
            addToRow.add(user.get(i).getStatus());
            tableModel.addRow(addToRow);
        }
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public void registerListener() {
        backButton.addActionListener(new backButtonListener());
    }

    public UserReportView() {

        buildLayout();

//        JFrame frame = new JFrame("User Report");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setContentPane(contentPane);
//        frame.setLocation(205, 5);
//        frame.setSize(900, 750);
//        frame.setResizable(false);
//        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        UserReportView u = new UserReportView();
//    }

    private class backButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ManageUserPage UserPage = new ManageUserPage();
            UserPage.setOnlineUser(getOnlineUser());
            newPagePane = UserPage.getContentPane();
            switchPane(newPagePane);
        }
    }
}
