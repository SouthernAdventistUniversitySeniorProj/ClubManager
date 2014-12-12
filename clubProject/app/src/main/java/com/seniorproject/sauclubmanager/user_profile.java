package com.seniorproject.sauclubmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;

public class user_profile extends DashboardActivity {
    /**
     * onCreate - called when the activity is first created.
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     */

    //Setup Ui reference variables
    private TextView name;//userpro_name
    private TextView classStand;//userpro_classStanding
    private TextView userEmail;//userpro_email

    //listing clubs
    private ListView listView;
    private String[] values;
    private String[] clubPics;

    //SERVER SIDE KEY VALUES
    private static final String firstName = "firstName";
    private static final String lastName = "lastName";
    private static final String email = "email";
    private static final String classStanding = "classStanding";
    public static String userphoto = "userPhoto";
    //private static final String mainClub = "mainClub";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ParseUser curUser = ParseUser.getCurrentUser();

        //connect ui widgets to their private variables
        name = (TextView) findViewById(R.id.userpro_name);
        classStand = (TextView) findViewById(R.id.userpro_classStanding);
        userEmail = (TextView) findViewById(R.id.userpro_email);

        //get and display the info from the web to the appropriate ui field

        //get and store
        String firstPart = curUser.get(firstName).toString();
        String lastPart = curUser.get(lastName).toString();
        String userPhoto = curUser.get(userphoto).toString();
        ParseFile getPic = (ParseFile) curUser.get(userPhoto);

        name.setText(firstPart + " " +lastPart);
        classStand.setText(curUser.get(classStanding).toString());
        userEmail.setText(curUser.get(email).toString());

        //gets current phone number from phone being used
        TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        TextView userNum = (TextView) findViewById(R.id.userpro_PhoneNum);
        userNum.setText(mPhoneNumber);

        final ImageView viewImage = (ImageView) findViewById(R.id.imageView);

            getPic.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        // data has the bytes for the resume
                        Bitmap bitpic = BitmapFactory.decodeByteArray(data, 0, data.length);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitpic.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        viewImage.setImageBitmap(bitpic);
                    } else {
                        // something went wrong
                    }

                }

            });

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
        ParseUser curUser = ParseUser.getCurrentUser();

        //connect ui widgets to their private variables
        name = (TextView) findViewById(R.id.userpro_name);
        classStand = (TextView) findViewById(R.id.userpro_classStanding);
        userEmail = (TextView) findViewById(R.id.userpro_email);

        //get and display the info from the web to the appropriate ui field

        //get and store
        String firstPart = curUser.get(firstName).toString();
        String lastPart = curUser.get(lastName).toString();

        name.setText(firstPart + " " +lastPart);
        classStand.setText(curUser.get(classStanding).toString());
        userEmail.setText(curUser.get(email).toString());
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
        ParseUser curUser = ParseUser.getCurrentUser();

        //connect ui widgets to their private variables
        name = (TextView) findViewById(R.id.userpro_name);
        classStand = (TextView) findViewById(R.id.userpro_classStanding);
        userEmail = (TextView) findViewById(R.id.userpro_email);

        //get and display the info from the web to the appropriate ui field

        //get and store
        String firstPart = curUser.get(firstName).toString();
        String lastPart = curUser.get(lastName).toString();

        name.setText(firstPart + " " +lastPart);
        classStand.setText(curUser.get(classStanding).toString());
        userEmail.setText(curUser.get(email).toString());
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
        // insert here your instructions
    }




}
