package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class sa_senate_ extends DashboardActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senate);
        Logout_button();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.senate, container, false);
        View aboutSA = rootView.findViewById(R.id.textView);
        ((TextView)aboutSA).setText("Senate is the legislative branch of the Student Association responsible for representing the various needs and desires of our great student body. It meets every other Wednesday to discuss what we can do to improve students' Southern experience as well as the community around our university, and then to enact as many of those changes as is within Senate power.");

        //sn_banner_imageView
        ImageButton button = (ImageButton) rootView.findViewById(R.id.sn_banner_imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/senate/Pages/senate.aspx"));
                startActivity(intent);
            }
        }) ;

        //sn_minutes_button
        Button button2 = (Button) rootView.findViewById(R.id.sn_minutes_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/senate/Pages/senateminutes.aspx"));
                startActivity(intent);
            }
        }) ;

        //sn_senators_button
        Button button3 = (Button) rootView.findViewById(R.id.sn_senators_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/senate/Pages/senators.aspx"));
                startActivity(intent);
            }
        }) ;

        return rootView;

    }

    public void Logout_button() {
        ImageButton Logout_button = (ImageButton) findViewById(R.id.logout_button);
        Logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginScreen.loginPreferences = getSharedPreferences("saveLogin", MODE_PRIVATE);
                LoginScreen.loginPrefsEditor.remove("username");
                LoginScreen.loginPrefsEditor.remove("password");
                LoginScreen.loginPrefsEditor.commit();
                LoginScreen.saveLoginCheckbox.setChecked(false);
                Intent intent = new Intent(sa_senate_.this, LoginScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
