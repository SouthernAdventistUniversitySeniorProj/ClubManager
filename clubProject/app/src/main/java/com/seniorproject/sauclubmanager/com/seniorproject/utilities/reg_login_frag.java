package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seniorproject.sauclubmanager.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class reg_login_frag extends Activity {

    EditText reg_id, reg_fname, reg_lname, reg_email, reg_pass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg_frag);

        reg_id = (EditText) findViewById(R.id.reg_id);
        reg_fname = (EditText) findViewById(R.id.reg_fname);
        reg_lname = (EditText) findViewById(R.id.reg_lname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);


        Button reg_button = (Button) findViewById(R.id.reg_button);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regusers();
                Toast.makeText(getApplicationContext(), reg_fname.getText().toString() + " " +
                        reg_lname.getText().toString() + "has been successfully register!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected Boolean regusers() {
        // TODO: attempt authentication against a network service.

        // Suggestion:  Load these from a properties object.
        String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

        // Register the native JDBC driver. If the driver cannot be
        // registered, the test cannot continue.
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.out.println("Driver failed to register.");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        Connection response = null;
        Statement statement = null;

        try {
            // Simulate network access.
            Thread.sleep(2000);

            Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection");

            String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";
            //login to server
            response = DriverManager.getConnection(dbURL, "sa", "d1559563!");

            Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");

            String sql = "INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
                    "VALUES ('" + reg_id + "', '" + reg_fname + "', '" + reg_lname + "', '" + reg_pass + "', '" + reg_email + "');";

            statement = response.createStatement();

            statement.executeUpdate(sql);


        } catch (InterruptedException e) {
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}