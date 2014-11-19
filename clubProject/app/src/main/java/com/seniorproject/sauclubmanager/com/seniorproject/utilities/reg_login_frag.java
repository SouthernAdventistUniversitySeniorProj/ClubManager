package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seniorproject.sauclubmanager.R;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.Statement;

import static java.sql.Statement.*;

/**
 * Created by User on 11/11/2014.
 */
public class reg_login_frag extends Activity {

    public static void main(java.lang.String[] args) throws SQLException {
        Connection conn = null;
        Statement s = null;
        String url = "jdbc:jtds:sqlserver://216.249.119.136\\ClubProject;instance=SQLEXPRESS;DatabaseName=ClubDatabase";
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        String userName = "sa";
        String password = "d1559563!";
        conn = DriverManager.getConnection(url, userName, password);
        s = conn.createStatement();


       //     s.executeUpdate("INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
         //           "VALUES ('" + reg_id + "', '" + reg_fname + "', '" + reg_lname + "', '" + reg_pass + "', '" + reg_email + "');");
    }
    EditText reg_id, reg_fname, reg_lname, reg_email, reg_pass;
    public String adduser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg_frag);



        reg_id = (EditText) findViewById(R.id.reg_id);
        reg_fname = (EditText) findViewById(R.id.reg_fname);
        reg_lname = (EditText) findViewById(R.id.reg_lname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);

        final Button reg_button = (Button) findViewById(R.id.reg_button);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // String adduser = "INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
               //         "VALUES ('" + reg_id + "', '" + reg_fname + "', '" + reg_lname + "', '" + reg_pass + "', '" + reg_email + "');";

                Toast.makeText(getApplicationContext(), reg_fname.getText().toString() + " " +
                        reg_lname.getText().toString() + "has been successfully register!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}