package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seniorproject.sauclubmanager.ClubManagerMainActivity;
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


    private EditText reg_id, reg_fname, reg_lname, reg_email, reg_pass;
    public String adduser;

    private UserRegisterTask mRegTask = null;






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg_frag);

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
        Statement statement = null;
        ResultSet resultSet = null;
        String dbUsername;

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




        // TODO: register the new account here.
        //return true;




        Button reg_button = (Button) findViewById(R.id.reg_button);
        final Statement finalStatement = statement;
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId;
                String fName,lName,password,email;

                //assign SQL placeholder variables
                userId = reg_id.getId();
                fName = reg_fname.toString();
                lName = reg_lname.toString();
                password = reg_pass.toString();
                email = reg_email.toString();

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
        int userID = reg_id.getId();


        mRegTask = new UserRegisterTask(email, password, userID, fName, lName);
            mRegTask.execute((Void) null);
        //}
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String FirstName;
        private final String LastName;
        private final int UserId;



        UserRegisterTask(String email, String password, int userId,String firstName, String lastName) {
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
            Statement statement = null;
            ResultSet resultSet = null;
            String dbUsername;

            try {
                // Simulate network access.
                Thread.sleep(2000);

                Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection");

                String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";
                //login to server
                response = DriverManager.getConnection(dbURL, "sa", "d1559563!");

                Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");

                statement = response.createStatement();

                resultSet = statement.executeQuery("Select * From Users");

                statement.executeUpdate("INSERT INTO Users (UserId, FirstName, LastName, Pass, Email) " +
                        "VALUES ("+UserId+", '"+FirstName+"','"+LastName+"' , '"+mPassword+"', '"+mEmail+"');");




            } catch (InterruptedException e) {
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }




            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegTask = null;
            //showProgress(false);

            if (success) {
                Intent myIntent = new Intent(reg_login_frag.this, LoginScreen.class);
                startActivity(myIntent);
                finish();
            } else {
                Log.d("GJGJKGJHHGLK", "unable to add user");

            }
        }


    }
}

















/*
public class reg_login_frag extends Activity{

    //UI References
    private EditText reg_id, reg_fname, reg_lname, reg_email, reg_pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg_frag);

        //Setup UI References
        reg_id = (EditText) findViewById(R.id.reg_id);
        reg_fname = (EditText) findViewById(R.id.reg_fname);
        reg_lname = (EditText) findViewById(R.id.reg_lname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
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
            Statement statement = null;
            ResultSet resultSet = null;
            String dbUsername;

            try {
                // Simulate network access.
                Thread.sleep(2000);

                Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection");

                String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";
                //login to server
                response = DriverManager.getConnection(dbURL, "sa", "d1559563!");

                Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");

                statement = response.createStatement();

                resultSet = statement.executeQuery("Select * From Users");


                */
/*while (rs.next()) {
                    System.out.println("User " + rs.getString(2) + " " + rs.getString(3) + " has ID "
                            + rs.getInt(1) + " & Email: " + rs.getString(5));*//*


                while (resultSet.next()){
                    //check if the email entered is in the database
                    if(resultSet.getString(5).equals(mEmail))
                    {
                        Log.d("JHGJGJKGHJGIHGHH","email found...checking password");
                        return resultSet.getString(4).equals(mPassword);

                    }
                    //return false;
                }


            } catch (InterruptedException e) {
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }




            // TODO: register the new account here.
            return true;
        }
*/




   /* @Override
    protected void onPostExecute(final Boolean success) {
        mAuthTask = null;
        showProgress(false);

        if (success) {
            Intent myIntent = new Intent(LoginScreen.this, ClubManagerMainActivity.class);
            startActivity(myIntent);
            finish();
        } else {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
        }
    }*/

   /* @Override
    protected void onCancelled() {
        mAuthTask = null;
        showProgress(false);
    }*/
//}





