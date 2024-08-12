/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField username;
    JPasswordField password;
    JButton login, cancle;

    Login() {

        super("Login");

        setLayout(null);

        l1 = new JLabel("Username");
        l1.setBounds(40, 20, 100, 30);
        add(l1);

        l2 = new JLabel("Password");
        l2.setBounds(40, 70, 100, 30);
        add(l2);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350, 10, 150, 150);
        add(l3);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setFont(new Font("serif", Font.BOLD, 15));
        login.addActionListener(this);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        add(login);

        cancle = new JButton("Cancel");
        cancle.setBounds(180, 140, 120, 30);
        cancle.setFont(new Font("serif", Font.BOLD, 15));
        cancle.setBackground(Color.BLACK);
        cancle.setForeground(Color.WHITE);
        add(cancle);

        cancle.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
        setSize(600, 300);
        setLocation(600, 350);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            try {
                conn c1 = new conn();
                String user = username.getText();
                String pass = password.getText();

                String q = "select * from login where username='" + user + "' and password='" + pass + "'";

                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    new Dashboard().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancle) {
            System.exit(0);
        }
    }

    public static void main(String[] arg) {
        new Login();
    }
}
