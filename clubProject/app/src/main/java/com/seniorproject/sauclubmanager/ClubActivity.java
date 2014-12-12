package com.seniorproject.sauclubmanager;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Qwynn on 12/3/2014.
 */
public class ClubActivity extends DashboardActivity {

public ClubActivity(){};

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

        //handling joining a club

        ImageButton join = (ImageButton) findViewById(R.id.join_button);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = ("Congratulations you have now joined " + CLUB_NAME);
                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();

            }
        }) ;

        //handling leaving a club
        ImageButton leave = (ImageButton) findViewById(R.id.leave_button);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = ("You are currently removed from " + CLUB_NAME);
                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();

            }
        }) ;


    }




}

