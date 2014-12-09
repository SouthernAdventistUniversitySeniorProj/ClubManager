package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by User on 12/8/2014.
 */
public class unHomeActivity extends DashboardActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unhome_activity);
        LoginScreen.loginSuccess = "no";
    }


    protected void onDestroy() {
        super.onDestroy();
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
    protected void onPause() {
        super.onPause();
    }

    /**
     * onRestart
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart().
     */
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * onResume
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack, with user input going to it.
     * Always followed by onPause().
     */
    protected void onResume() {
        super.onResume();
    }

    /**
     * onStart
     * Called when the activity is becoming visible to the user.
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
     */
    protected void onStart() {
        super.onStart();
    }

    /**
     * onStop
     * Called when the activity is no longer visible to the user
     * because another activity has been resumed and is covering this one.
     * This may happen either because a new activity is being started, an existing one
     * is being brought in front of this one, or this one is being destroyed.
     * <p/>
     * Followed by either onRestart() if this activity is coming back to interact with the user,
     * or onDestroy() if this activity is going away.
     */
    protected void onStop() {
        super.onStop();
    }

    public void onClickunFeature(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_feature:
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                break;
            case R.id.home_btn_feature4 :
                startActivity (new Intent(getApplicationContext(), clubs.class));
                break;
            case R.id.home_btn_feature5 :
                startActivity (new Intent(getApplicationContext(), sa.class));
                break;
            case R.id.home_btn_feature6 :
                startActivity (new Intent(getApplicationContext(), sa_senate_.class));
                break;
            default:
                break;
        }
    }
}