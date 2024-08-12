package hotel.management.system;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;
import java.awt.event.*;

public class update extends JFrame implements ActionListener {

    Choice ccoustomer;
    JTextField tfRoom, tfname, tfchickin, tfpaid, tfpending;

    JButton check, update, back;

    update() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 30, 200, 30);
        text.setForeground(Color.BLUE);
        setBounds(300, 200, 900, 500);
        add(text);

        ccoustomer = new Choice();

        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);

        ccoustomer = new Choice();
        ccoustomer.setBounds(200, 80, 150, 25);
        add(ccoustomer);

        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                ccoustomer.add(rs.getString("s2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room No :");
        lblroom.setBounds(30, 120, 100, 20);
        add(lblroom);

        tfRoom = new JTextField();
        tfRoom.setBounds(200, 120, 150, 25);
        add(tfRoom);

        JLabel lblname = new JLabel("Name :");
        lblname.setBounds(30, 160, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblchickin = new JLabel("Chick in Time :");
        lblchickin.setBounds(30, 200, 100, 20);
        add(lblchickin);

        tfchickin = new JTextField();
        tfchickin.setBounds(200, 200, 150, 25);
        add(tfchickin);

        JLabel lblpaid = new JLabel("Amount Piad :");
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);

        JLabel lblpending = new JLabel("Amount Pending :");
        lblpending.setBounds(30, 280, 100, 20);
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(200, 280, 150, 25);
        add(tfpending);

        check = new JButton("Check");
        check.setBackground(Color.black);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 340, 100, 30);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBackground(Color.black);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 340, 100, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.setBounds(270, 340, 100, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == check) {

            String id = ccoustomer.getSelectedItem();
            String query = "select * from customer where s2='" + id + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    tfRoom.setText(rs.getString("s6"));
                    tfname.setText(rs.getString("s3"));
                    tfchickin.setText(rs.getString("s7"));
                    tfpaid.setText(rs.getString("s8"));
                }

                ResultSet rs2 = c.s.executeQuery("select * from room where room = '" + tfRoom.getText() + "'");
                while (rs2.next()) {
                    String price = rs2.getString("price");
                    int ammountPaid = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                    tfpending.setText("" + ammountPaid);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == update) {

            String number = ccoustomer.getSelectedItem();
            String room = tfRoom.getText();
            String name = tfname.getText();
            String chickIn = tfchickin.getText();
            String deposit = tfpaid.getText();

            try {
                conn c = new conn();
                c.s.executeUpdate("update customer set s6 ='" + room + "',s3 ='" + name + "',s7='" + chickIn + "',s8='"
                        + deposit + "'");
                JOptionPane.showMessageDialog(null, "Data Updated Sucessfully");
                setVisible(false);
                new Reception();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
            new Reception();

        }
    }

    public static void main(String[] args) {
        new update();
    }
}
