package com.seniorproject.sauclubmanager;

import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class user_profile extends DashboardActivity {
    /**
     * onCreate - called when the activity is first created.
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     */

    public String id, fname, lname, Uemail;
    private AutoCompleteTextView email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        email = (AutoCompleteTextView) findViewById(R.id.email);


      //  public void onAttachedToWindow() {
          //  super.onAttachedToWindow();
           // this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);

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

        Connection response = null;
        Statement statement = null;
        ResultSet resultSet = null;
        //  String dbUsername;

        try {
            // Simulate network access.
            Thread.sleep(2000);
            String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";
            Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection ---> " + dbURL);
            //login to server
            response = DriverManager.getConnection(dbURL, "sa", "d1559563!");
            Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");
            statement = response.createStatement();
            resultSet = statement.executeQuery("Select * From Users Where Email = '"+email+"';");

            while (resultSet.next()) {
                //check if the email entered is in the database
                Log.d("JHGJGJKGHJGIHGHH", "checking email..........");
                fname = resultSet.getString(2);
                lname = resultSet.getString(3);
                Uemail = resultSet.getString(5);

            }

        } catch (InterruptedException e) {
            //return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TextView userpro_text = (TextView)findViewById(R.id.userpro_title);
        //userpro_text.setText("Welcome " + fname + " " + lname + "!");
    }

    /**
     * onDestroy
     * The final call you receive before your activity is destroyed.
     * This can happen either because the activity is finishing (someone called finish() on it,
     * or because the system is temporarily destroying this instance of the activity to save space.
     * You can distinguish between these two scenarios with the isFinishing() method.
     */
    protected void onDestroy () {
        super.onDestroy ();
    }

    /**
     * onPause
     * Called when the system is about to start resuming a previous activity.
     * This is typically used to commit unsaved changes to persistent data, stop animations
     * and other things that may be consuming CPU, etc.
     * Implementations of this method must be very quick because the next activity will not be resumed
     * until this method returns.
     * Followed by either onResume() if the activity returns back to the front,
     * or onStop() if it becomes invisible to the user.
     */
    protected void onPause () {
        super.onPause ();
    }

    /**
     * onRestart
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart().
     *
     */
    protected void onRestart () {
        super.onRestart ();
    }

    /**
     * onResume
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack, with user input going to it.
     * Always followed by onPause().
     *
     */
    protected void onResume () {
        super.onResume ();
    }

    /**
     * onStart
     * Called when the activity is becoming visible to the user.
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
     *
     */
    protected void onStart () {
        super.onStart ();
    }

    /**
     * onStop
     * Called when the activity is no longer visible to the user
     * because another activity has been resumed and is covering this one.
     * This may happen either because a new activity is being started, an existing one
     * is being brought in front of this one, or this one is being destroyed.
     *
     * Followed by either onRestart() if this activity is coming back to interact with the user,
     * or onDestroy() if this activity is going away.
     */
    @Override
    protected void onStop()
    {
        super.onStop();
        //Log.d("iHHIHIPHOUHOHOUHOHPUHO", "MYonStop is called");
        // insert here your instructions
    }




}
