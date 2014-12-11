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


    }


}

