
package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database extends Activity {

    public static void main(java.lang.String[] args) {

        // Suggestion:  Load these from a properties object.
        String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
        String URL = "jdbc:jtds:sqlserver://216.249.119.136\\ClubProject;instance=SQLEXPRESS;DatabaseName=ClubDatabase";

        // Register the native JDBC driver. If the driver cannot be
        // registered, the test cannot continue.
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.out.println("Driver failed to register.");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        Connection c = null;
        Statement s = null;

        try {
            // Create the connection properties.
            Properties properties = new Properties();
            properties.put("user", "sa");
            properties.put("password", "d1559563!");

            // Connect to the local iSeries database.
            c = DriverManager.getConnection(URL, properties);

            // Create a Statement object.
            s = c.createStatement();

            // Add user to the table when user registers.
            s.executeUpdate("INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
                    "VALUES ('', 'Duvonne', 'Berry', 'yolo', 'duvonneb@gmail.com');");

            // Run an SQL query on the table.
            ResultSet rs = s.executeQuery("SELECT * FROM Users");

            // Display all the data in the table.
            while (rs.next()) {
                System.out.println("User " + rs.getString(2) + " " + rs.getString(3) + " has ID "
                        + rs.getInt(1) + " & Email: " + rs.getString(5));
            }

        } catch (SQLException sqle) {
            System.out.println("Database processing has failed.");
            System.out.println("Reason: " + sqle.getMessage());
        } finally {
            // Close database resources
            try {
                if (s != null) {
                    s.close();
                }
            } catch (SQLException e) {
                System.out.println("Cleanup failed to close Statement.");
            }
        }

        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println("Cleanup failed to close Connection.");
        }
    }
}