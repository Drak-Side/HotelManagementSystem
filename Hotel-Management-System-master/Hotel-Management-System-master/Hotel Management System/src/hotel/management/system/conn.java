package hotel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection c;
    Statement s;
    protected Object conn;

    public conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "2517");
            System.out.println("Connection established successfully.");

            // Create a statement object for executing SQL queries
            s = c.createStatement();
            System.out.println("Statement created successfully.");

        } catch (Exception e) {
            e.printStackTrace(); // Print the exception to the console
        }
    }

    public Connection getConnection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConnection'");
    }
}
