package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickUp extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTable table;
	private Choice c1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PickUp frame = new PickUp();
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
	 */
	public PickUp() {
		// Establish database connection
		try {
			conn = new conn().c; // Use the connection from the conn class
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPickUpService = new JLabel("Pick Up Service");
		lblPickUpService.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPickUpService.setBounds(90, 11, 158, 25);
		contentPane.add(lblPickUpService);

		JLabel lblTypeOfCar = new JLabel("Type of Car");
		lblTypeOfCar.setBounds(32, 97, 89, 14);
		contentPane.add(lblTypeOfCar);

		c1 = new Choice();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT brand FROM driver");
			while (rs.next()) {
				c1.add(rs.getString("brand"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		c1.setBounds(123, 94, 150, 25);
		contentPane.add(c1);

		JLabel lblInfo = new JLabel("Information");
		lblInfo.setBounds(24, 208, 100, 14);
		contentPane.add(lblInfo);

		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SQL = "SELECT * FROM driver WHERE brand = ?";
				try {
					PreparedStatement pst = conn.prepareStatement(SQL);
					pst.setString(1, c1.getSelectedItem());
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
		});
		btnDisplay.setBounds(200, 500, 120, 30);
		btnDisplay.setBackground(Color.BLACK);
		btnDisplay.setForeground(Color.WHITE);
		contentPane.add(btnDisplay);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(420, 500, 120, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 233, 800, 250);
		contentPane.add(scrollPane);

		// Table column labels
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 208, 60, 14);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(165, 208, 46, 14);
		contentPane.add(lblAge);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(264, 208, 46, 14);
		contentPane.add(lblGender);

		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(366, 208, 80, 14);
		contentPane.add(lblCompany);

		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setBounds(486, 208, 60, 14);
		contentPane.add(lblBrand);

		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(600, 208, 86, 14);
		contentPane.add(lblAvailable);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(700, 208, 73, 14);
		contentPane.add(lblLocation);

		getContentPane().setBackground(Color.WHITE);
	}
}
