package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;
import com.seniorproject.sauclubmanager.HomeActivity;
import com.seniorproject.sauclubmanager.clubs;
import com.seniorproject.sauclubmanager.unAuthHomeActivity;

/**
 * Created by Qwynn on 12/8/2014.
 */
public class DispatchActivity extends Activity {

    public DispatchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, unAuthHomeActivity.class));
        }
    }
}