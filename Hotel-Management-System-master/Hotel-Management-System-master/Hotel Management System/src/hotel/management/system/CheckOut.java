package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CheckOut extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	private JPanel contentPane;
	private JTextField t1;
	private Choice c1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOut frame = new CheckOut();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void close() {
		this.dispose();
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public CheckOut() throws SQLException {
		conn = new conn().c; // Use the connection from the conn class

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 800, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/sixth.jpg"));
		Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel l1 = new JLabel(i2);
		l1.setBounds(300, 0, 500, 225);
		add(l1);

		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCheckOut.setBounds(70, 11, 140, 35);
		contentPane.add(lblCheckOut);

		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(20, 85, 80, 14);
		contentPane.add(lblNumber);

		c1 = new Choice();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_number FROM customer");
			while (rs.next()) {
				c1.add(rs.getString("id_number"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBounds(130, 82, 150, 20);
		contentPane.add(c1);

		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/tick.png"));
		Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JButton l2 = new JButton(i6);
		l2.setBounds(290, 82, 20, 20);
		add(l2);

		l2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String number = c1.getSelectedItem();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE id_number = '" + number + "'");
					if (rs.next()) {
						t1.setText(rs.getString("room_number"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		JLabel lblRoomNumber = new JLabel("Room Number:");
		lblRoomNumber.setBounds(20, 132, 100, 20);
		contentPane.add(lblRoomNumber);

		t1 = new JTextField();
		t1.setBounds(130, 132, 150, 20);
		contentPane.add(t1);

		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = c1.getSelectedItem();
				String roomNumber = t1.getText();
				String deleteSQL = "DELETE FROM customer WHERE id_number = ?";
				String updateSQL = "UPDATE room SET available = 'available' WHERE room = ?";

				try {
					PreparedStatement pstDelete = conn.prepareStatement(deleteSQL);
					pstDelete.setString(1, id);
					pstDelete.executeUpdate();

					PreparedStatement pstUpdate = conn.prepareStatement(updateSQL);
					pstUpdate.setString(1, roomNumber);
					pstUpdate.executeUpdate();

					JOptionPane.showMessageDialog(null, "Check Out Successful");
					new Reception().setVisible(true);
					setVisible(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCheckOut.setBounds(50, 200, 100, 25);
		btnCheckOut.setBackground(Color.BLACK);
		btnCheckOut.setForeground(Color.WHITE);
		contentPane.add(btnCheckOut);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(160, 200, 100, 25);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);

		getContentPane().setBackground(Color.WHITE);
	}
}
