/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import net.proteanit.sql.DbUtils;

public class SearchRoom extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTable table;
	Choice c1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				SearchRoom frame = new SearchRoom();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void close() {
		this.dispose();
	}

	public SearchRoom() throws SQLException {
		// conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		contentPane.setLayout(gl_contentPane);

		JLabel lblSearchForRoom = new JLabel("Search For Room");
		lblSearchForRoom.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblRoomAvailable = new JLabel("Room Bed Type:");
		JLabel lblRoomType = new JLabel("Room Number");
		JLabel lblRoomAvailable_1 = new JLabel("Availability");
		JLabel lblPrice_1 = new JLabel("Price");
		JLabel l1 = new JLabel("Bed Type");
		JLabel lblCleanStatus = new JLabel("Clean Status");

		JCheckBox checkRoom = new JCheckBox("Only display Available");
		checkRoom.setBackground(Color.WHITE);

		c1 = new Choice();
		c1.add("Single Bed");
		c1.add("Double Bed");

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SQL = "SELECT * FROM room WHERE type = '" + c1.getSelectedItem() + "'";
				String SQL2 = "SELECT * FROM room WHERE available = 'available' AND type = '" + c1.getSelectedItem()
						+ "'";
				try {
					conn c = new conn();
					// Check if 'Only display Available' checkbox is selected
					if (checkRoom.isSelected()) {
						rs = c.s.executeQuery(SQL2);
					} else {
						rs = c.s.executeQuery(SQL);
					}
					// Update table with the result set
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException ss) {
					ss.printStackTrace();
				}
			}
		});

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});

		table = new JTable();

		// GroupLayout code to manage the layout
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lblSearchForRoom)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblRoomAvailable)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(c1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(checkRoom))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnSearch)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(btnExit))
										.addComponent(table, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblRoomType)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblRoomAvailable_1)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblPrice_1)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(l1))
										.addComponent(lblCleanStatus))
								.addContainerGap()));

		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblSearchForRoom)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblRoomAvailable)
										.addComponent(c1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(checkRoom))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(btnSearch)
										.addComponent(btnExit))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lblCleanStatus)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblRoomType)
										.addComponent(lblRoomAvailable_1)
										.addComponent(lblPrice_1)
										.addComponent(l1))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		getContentPane().setBackground(Color.WHITE);
	}
}
