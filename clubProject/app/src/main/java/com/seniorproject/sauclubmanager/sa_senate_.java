package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class sa_senate_ extends DashboardActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ParseUser.getCurrentUser() != null) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true); }
        setContentView(R.layout.senate);

        View aboutSA = findViewById(R.id.textView);
        ((TextView)aboutSA).setText("Senate is the legislative branch of the Student Association responsible " +
                "for representing the various needs and desires of our great student body. " +
                "It meets every other Wednesday to discuss what we can do to improve students' Southern " +
                "experience as well as the community around our university, and then to enact as many of " +
                "those changes as is within Senate power.");

        //sn_banner_imageView
        ImageButton button = (ImageButton) findViewById(R.id.sn_banner_imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/senate/Pages/senate.aspx"));
                startActivity(intent);
            }
        }) ;

        //drop list for sa senate information directed to web
        final Spinner spin = (Spinner) findViewById(R.id.senate_spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                String  mselection = spin.getSelectedItem().toString();
                if (mselection.equals("Mintues")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://www.southern.edu/sa/senate/Pages/senateminutes.aspx"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin.setSelection(0);
                }
                else if (mselection.equals("Current Senators")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://www.southern.edu/sa/senate/Pages/senators.aspx"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
}
