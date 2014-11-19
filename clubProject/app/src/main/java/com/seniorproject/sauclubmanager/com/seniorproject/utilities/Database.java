
package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;
import android.os.Bundle;

import com.seniorproject.sauclubmanager.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database extends Activity {

        public static void main(java.lang.String[] args)
        {

            // Suggestion:  Load these from a properties object.
            String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
            String URL    = "jdbc:jtds:sqlserver://216.249.119.136\\ClubProject;instance=SQLEXPRESS;DatabaseName=ClubDatabase";

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
                Properties properties = new Properties ();
                properties.put ("user", "sa");
                properties.put ("password", "d1559563!");

                // Connect to the local iSeries database.
                c = DriverManager.getConnection(URL, properties);

                // Create a Statement object.
                s = c.createStatement();

                // Add user to the table when user registers.
                s.executeUpdate("INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
                        "VALUES ('duvonneb', 'Duvonne', 'Berry', 'yolo', 'duvonneb@gmail.com');");

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
/*
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://216.249.119.136/ClubProject";
        String driver = "org.gjt.mm.mysql.Driver";
        String userName = "sa";
        String password = "d1559563!";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to the database!!! Getting table list...");
            DatabaseMetaData dbm = conn.getMetaData();
            rs = dbm.getTables(null, null, "%", new String[] { "TABLE" });
            while (rs.next()) { System.out.println(rs.getString("TABLE_NAME")); }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            rs.close();
        }
    }
}

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class Database extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ClubDatabase",
    TABLE_USER = "user",
    KEY_ID = "userid",
    KEY_NAME = "name",
    KEY_EMAIL = "email",
    KEY_PASS = "userpassword",

    TABLE_USERCLUB = "userclub",
    KEY_USERC = "userid",
    KEY_CLUBU = "clubid",
    KEY_DUE = "dues",

    TABLE_CLUB = "club",
    KEY_CID = "clubid",
    KEY_CNAME = "name";

    public Database (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT" + KEY_PASS + " INTEGER)");
       db.execSQL("CREATE TABLE " + TABLE_USERCLUB + "(" + KEY_USERC + " INTEGER FOREIGN KEY,"
                    + KEY_CLUBU + " INTEGER FOREIGN KEY," + KEY_DUE + " TEXT)");
       db.execSQL("CREATE TABLE " + TABLE_CLUB + "(" + KEY_CID + " INTEGER FOREIGN KEY,"
                    + KEY_CNAME + " TEXT)");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void createUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getID());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASS, user.getPass());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASS },
                KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        db.close();
        cursor.close();
        return user;
    }

    public void removeUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + "=?", new String[] { String.valueOf(user.getID()) });
        db.close();
    }

    public int updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getID());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASS, user.getPass());
        return db.update(TABLE_USER, values,KEY_ID + "=?", new String[] { String.valueOf(user.getID()) });

    }
}
 */