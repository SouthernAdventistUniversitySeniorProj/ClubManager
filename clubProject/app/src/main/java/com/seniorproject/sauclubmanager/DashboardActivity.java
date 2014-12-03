package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity {

    /**
     * onCreate - called when the activity is first created.
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    //GLOBAL MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_logout){
            Log.d("salfjg;sajfjsagjsajg", "Logout Clicked");
            //get shared prefs and clear them
            LoginScreen.loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            //LoginScreen.loginPrefsEditor.remove("username");
            //LoginScreen.loginPrefsEditor.remove("password");
            LoginScreen.loginPrefsEditor.putBoolean("saveLogin", false);
            LoginScreen.loginPrefsEditor.putString("username", "email");
            LoginScreen.loginPrefsEditor.putString("password", "password");
            LoginScreen.loginPrefsEditor.commit();
            LoginScreen.saveLoginCheckbox.setChecked(false);
            Intent intent = new Intent(DashboardActivity.this, LoginScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else{
            // if a the new item is clicked show "Toast" message.
        }
////Home button pressed take me back to the main screen
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(DashboardActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else{
            // if a the new item is clicked show "Toast" message.
        }

        return super.onOptionsItemSelected(item);
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
    protected void onStop () {
        super.onStop ();
    }

// Click Methods

    /**
     * Handle the click on the home button.
     */

    public void onClickHome (View v) {
        goHome (this);
    }

    /**
     * Handle the click on the search button.
     */
    public void onClickSearch (View v) {
        //startActivity (new Intent(getApplicationContext(), SearchActivity.class));
    }

    /**
     * Handle the click on the About button.
     */
    public void onClickAbout (View v) {
       // startActivity (new Intent(getApplicationContext(), AboutActivity.class));
    }


// More Methods

    /**
     * Go back to the home activity.
     */
    public void goHome(Context context) {
        final Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity (intent);
    }

    /**
     * Use the activity label to set the text in the activity's title text view.
     * The argument gives the name of the view.
     *
     * <p> This method is needed because we have a custom title bar rather than the default Android title bar.
     * See the theme definitons in styles.xml.
     */
    public void setTitleFromActivityLabel (int textViewId) {
        TextView tv = (TextView) findViewById (textViewId);
        if (tv != null) tv.setText (getTitle ());
    } // end setTitleText

    /**
     * Show a string on the screen via Toast.
     */
    public void toast (String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    } // end toast

    /**
     * Send a message to the debug log and display it using Toast.
     */
    public void trace (String msg) {
        Log.d("Demo", msg);
        toast (msg);
    }
}