package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateCheck extends JFrame implements ActionListener {

    Choice ccoustomer;
    JTextField tfRoom, tfname, tfchickin, tfpaid, tfpending;

    JButton check, update, back;

    UpdateCheck() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Check Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 30, 200, 30);
        text.setForeground(Color.BLUE);
        setBounds(300, 200, 900, 500);
        add(text);

        JLabel lblid = new JLabel("Customer ID:");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);

        ccoustomer = new Choice();
        ccoustomer.setBounds(200, 80, 150, 25);
        add(ccoustomer);

        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT id_number FROM customer");
            while (rs.next()) {
                ccoustomer.add(rs.getString("id_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room No:");
        lblroom.setBounds(30, 120, 100, 20);
        add(lblroom);

        tfRoom = new JTextField();
        tfRoom.setBounds(200, 120, 150, 25);
        add(tfRoom);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(30, 160, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblchickin = new JLabel("Checked-in:");
        lblchickin.setBounds(30, 200, 100, 20);
        add(lblchickin);

        tfchickin = new JTextField();
        tfchickin.setBounds(200, 200, 150, 25);
        add(tfchickin);

        JLabel lblpaid = new JLabel("Amount Paid:");
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);

        JLabel lblpending = new JLabel("Amount Pending:");
        lblpending.setBounds(30, 280, 100, 20);
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(200, 280, 150, 25);
        add(tfpending);

        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 340, 100, 30);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update Amount");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 340, 150, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(320, 340, 100, 30);
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
            String query = "SELECT * FROM customer WHERE id_number = '" + id + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    tfRoom.setText(rs.getString("room_number"));
                    tfname.setText(rs.getString("name"));
                    tfchickin.setText(rs.getBoolean("checked_in") ? "Yes" : "No");
                    tfpaid.setText(rs.getString("deposit"));
                }

                ResultSet rs2 = c.s.executeQuery("SELECT * FROM room WHERE room = '" + tfRoom.getText() + "'");
                if (rs2.next()) {
                    String price = rs2.getString("price");
                    int amountPending = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
                    tfpending.setText(String.valueOf(amountPending));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == update) {

            String id = ccoustomer.getSelectedItem();
            String room = tfRoom.getText();
            String name = tfname.getText();
            boolean checkedIn = tfchickin.getText().equals("Yes");
            String deposit = tfpaid.getText();

            try {
                conn c = new conn();
                String updateQuery = "UPDATE customer SET room_number = '" + room + "', name = '" + name
                        + "', checked_in = " + checkedIn + ", deposit = '" + deposit + "' WHERE id_number = '" + id
                        + "'";
                c.s.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                setVisible(false);
                new Reception().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
