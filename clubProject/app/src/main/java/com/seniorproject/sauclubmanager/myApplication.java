package com.seniorproject.sauclubmanager;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseTwitterUtils;

/**
 * Created by Qwynn on 12/8/2014.
 */
public class myApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Required - Initialize the Parse SDK
        Parse.initialize(this, getString(R.string.parse_app_id),
                getString(R.string.parse_client_key));

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);



    }
}

