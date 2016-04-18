/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Login;

import id.ac.ciputra.ift.snackzone.domain.Users;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import id.ac.ciputra.ift.snackzone.presentation.Controller.HomePage;

/**
 *
 * @author Kevin
 */
public class LoginView {

    private JPanel contentPane, flowTitlePane, titlePane, centerPane, centerFieldPane,
            buttonPane, northPane, fieldPane, newPagePane, southGridPane;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;

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
        flowTitlePane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        titlePane = new JPanel(new GridLayout(1, 3, 10, 10));
        centerPane = new JPanel(new BorderLayout());
        centerFieldPane = new JPanel(new GridLayout(4, 1));
        fieldPane = new JPanel(new GridLayout(3, 4, 9, 9));
        northPane = new JPanel(new BorderLayout());
        buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southGridPane = new JPanel(new GridLayout(1, 1));

        userNameField = new JTextField(20);
        userNameField.setText("kpratama");
        passwordField = new JPasswordField(20);
        passwordField.setText("1234");

        loginButton = new JButton("Login");
    }

    public void addComponent() {
        titlePane.add(new JLabel());
        titlePane.add(new JLabel(new ImageIcon("Resource/alamat.png")));
        titlePane.add(new JLabel());

        flowTitlePane.add(new JLabel(new ImageIcon("Resource/logo Snack zone5.jpg")));

        northPane.add(flowTitlePane, BorderLayout.NORTH);
        northPane.add(titlePane, BorderLayout.SOUTH);

        buttonPane.add(loginButton);

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("User Name :"));
        fieldPane.add(userNameField);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel("Password :"));
        fieldPane.add(passwordField);
        fieldPane.add(new JLabel());

        fieldPane.add(new JLabel());
        fieldPane.add(new JLabel());
        fieldPane.add(buttonPane);
        fieldPane.add(new JLabel());

        centerFieldPane.add(new JLabel());
        centerFieldPane.add(fieldPane);
        centerFieldPane.add(new JLabel());
        centerFieldPane.add(new JLabel());

        southGridPane.add(new JLabel(new ImageIcon("Resource/bawah.png")));

        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(centerFieldPane, BorderLayout.CENTER);
        centerPane.add(southGridPane, BorderLayout.SOUTH);

        contentPane.add(centerPane, BorderLayout.CENTER);
    }

    public void buildLayout() {
        initialize();
        addComponent();
        registerListener();
    }

    public void registerListener() {
        loginButton.addActionListener(new loginButtonListener());
    }

    public LoginView() {
        buildLayout();

        JFrame frame = new JFrame("Snack Zone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(contentPane);
        frame.setLocation(205, 5);
        frame.setSize(900, 750);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public LoginView(Boolean logout) {
        buildLayout();
    }

    public static void main(String[] args) {
        LoginView l = new LoginView();
    }

    private class loginButtonListener implements ActionListener {

        public void changePane(Users onlineUser) {
            HomePage homePage = new HomePage();
            homePage.setOnlineUser(onlineUser);
            newPagePane = homePage.getContentPane();
            switchPane(newPagePane);
        }

        public void actionPerformed(ActionEvent e) {
            LoginModel lm = new LoginModel();
            Vector<Users> users = lm.readAllUser();
            boolean loginSucced = false;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(userNameField.getText())
                        && users.get(i).getPassword().equals(String.valueOf(passwordField.getPassword()))) {
                    if (users.get(i).getStatus().equals("Active")) {
                        JOptionPane.showMessageDialog(centerFieldPane, "Login Succedd");
                        changePane(users.get(i));
                    } else {
                        JOptionPane.showMessageDialog(centerFieldPane, "Your account is inactive."
                                + "\n Please contact your Admin for more information");
                    }
                    loginSucced = true;
                    break;
                }
            }
            if (!loginSucced) {
                JOptionPane.showMessageDialog(centerFieldPane, "Your username and "
                        + "password is incorect!");
            }
        }
    }
}
