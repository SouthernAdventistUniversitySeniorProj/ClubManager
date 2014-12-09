package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Qwynn on 12/3/2014.
 */
public class ClubActivity extends DashboardActivity {

    private TextView textView;
    private ImageView imageView;


    public String CLUB_NAME = "default club name";

    public String CLUB_PIC = "drawable/accounting";
    //public String UserId;
    private join_method joinclub = null;
    private leave_method leaveclub = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_layout);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize private variables
        textView = (TextView) findViewById(R.id.club_name);
        imageView = (ImageView) findViewById(R.id.clubImageView);
        //RETRIEVE NAME AND PIC PASSED FROM PREVIOUS ACTIVITIES
        Intent intent = getIntent();
        CLUB_NAME = intent.getExtras().getString("CLUB_NAME");
        CLUB_PIC = intent.getExtras().getString("CLUB_PIC");

        textView.setText(CLUB_NAME);

        int imageResource = this.getResources().getIdentifier(CLUB_PIC, null, this.getPackageName());
        Drawable image = this.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(image);

        //handling joining a club

        ImageButton join = (ImageButton) findViewById(R.id.join_button);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = ("Congratulations you have now joined " + CLUB_NAME);
                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
                //reg_login.UserRegisterTask UserId = new reg_login.UserRegisterTask();
                joinclub = new join_method(GetEmail, CLUB_NAME);
                joinclub.execute((Void) null);
            }
        }) ;

        //handling leaving a club
        ImageButton leave = (ImageButton) findViewById(R.id.leave_button);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = ("You are currently removed from " + CLUB_NAME);
                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
                leaveclub = new leave_method(GetEmail, CLUB_NAME);
                leaveclub.execute((Void) null);
            }
        }) ;

        //handling leaving a club
//        CheckedTextView fav = (CheckedTextView) findViewById(R.id.favorite);
//        fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String information = ("You have currently added " + CLUB_NAME + " to your favorites!");
//                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
//            }
//        }) ;

    }


    public static String GetEmail = LoginScreen.loginPreferences.getString("username", "");

    //enters user into ClubMember Table
    public class join_method extends AsyncTask<Void, Void, Boolean> {
        private final String UserEmail;
        private final String ClubName;


        join_method(String useremail, String clubname) {

            UserEmail = useremail;
            ClubName = clubname;
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
            ResultSet getUserId;
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
                statement.executeUpdate("INSERT INTO ClubMember (Email, ClubName) " +
                                            "VALUES ('"+UserEmail+"', '"+ClubName+"');");

            } catch (InterruptedException e) {
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

    }

    //removes user
    public class leave_method extends AsyncTask<Void, Void, Boolean> {
        private final String UserEmail;
        private final String ClubName;


        leave_method(String useremail, String clubname) {

            UserEmail = useremail;
            ClubName = clubname;
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
            ResultSet getUserId;
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
                statement.executeUpdate("DELETE FROM ClubMember " +
                        "WHERE (Email = '"+UserEmail+"' AND ClubName = '"+ClubName+"');");

            } catch (InterruptedException e) {
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }

    }
}

