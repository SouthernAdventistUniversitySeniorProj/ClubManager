package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class reg_login extends Activity {

    private EditText reg_id, reg_fname, reg_lname, reg_email, reg_pass;
  //  public String adduser;

    private UserRegisterTask mRegTask = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg);

        //Setup UI Elements
        reg_id = (EditText) findViewById(R.id.reg_id);
        reg_fname = (EditText) findViewById(R.id.reg_fname);
        reg_lname = (EditText) findViewById(R.id.reg_lname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);

        //Setup DB Connection
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

        Connection response;
        Statement statement;
      //  ResultSet resultSet = null;
      //  String dbUsername;

        try {
            // Simulate network access.
            Thread.sleep(2000);
            Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection");
            String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";

            //login to server
            response = DriverManager.getConnection(dbURL, "sa", "d1559563!");
            Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");
            statement = response.createStatement();

        } catch (InterruptedException e) {
           // return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Button reg_button = (Button) findViewById(R.id.reg_button);
       // final Statement finalStatement = statement;
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("IHGIHIJHIOH","ButtonClicked");
                attemptRegister();
            }
        });
    }

    public void attemptRegister() {
        String email = reg_email.getText().toString();
        String password = reg_pass.getText().toString();
        String fName = reg_fname.getText().toString();
        String lName = reg_lname.getText().toString();
        String userID = reg_id.getText().toString();
        mRegTask = new UserRegisterTask(email, password, userID, fName, lName);
            mRegTask.execute((Void) null);
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;
        private final String FirstName;
        private final String LastName;
        private final String UserId;

        UserRegisterTask(String email, String password, String userId,String firstName, String lastName) {
            UserId = userId;
            FirstName = firstName;
            LastName = lastName;
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
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

            Connection response;
            Statement statement;
        //    ResultSet resultSet = null;
        //    String dbUsername;

            try {
                // Simulate network access.
                Thread.sleep(2000);
                Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection");
                String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";

                //login to server
                response = DriverManager.getConnection(dbURL, "sa", "d1559563!");
                Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");
                statement = response.createStatement();
                statement.executeUpdate("INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
                        "VALUES ("+UserId+", '"+FirstName+"','"+LastName+"' , '"+mPassword+"', '"+mEmail+"');");

            } catch (InterruptedException e) {
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegTask = null;
            //showProgress(false);
            if (success) {
                Intent myIntent = new Intent(reg_login.this, LoginScreen.class);
                startActivity(myIntent);
                finish();
            } else {
                Log.d("GJGJKGJHHGLK", "unable to add user");
            }
        }
    }
}
