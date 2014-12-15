package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class sa extends DashboardActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sa);
        if (ParseUser.getCurrentUser() != null) {
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true); }

        //on click each option of information tab,,, direct them to webpage
        final Spinner spin = (Spinner) findViewById(R.id.sa_spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String  mselection = spin.getSelectedItem().toString();
                if (mselection.equals("About")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://www.southern.edu/sa/Pages/about.aspx"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin.setSelection(0);
                }
                else if (mselection.equals("Officers")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://www.southern.edu/sa/Pages/officers.aspx"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin.setSelection(0);
                }
                else if (mselection.equals("More Information...")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://www.southern.edu/sa"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        /*
        *   when clicked on any social options,,, direct them to webpage or application that information
        *   relates too
        */
        final Spinner spin_social = (Spinner) findViewById(R.id.sa_social_spinner);
        spin_social.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String  mselection = spin_social.getSelectedItem().toString();
                if (mselection.equals("Facebook")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/SAUStudentAssociation"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin_social.setSelection(0);
                }
                else if (mselection.equals("Twitter")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("https://twitter.com/southern_sa"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin_social.setSelection(0);
                }
                else if (mselection.equals("Instagram")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/sa_southern"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin_social.setSelection(0);
                }
                else if (mselection.equals("Email")) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("mailto:sa@southern.edu?subject=%5BStudent%20Association%20-%20Contact%5D"));
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mselection, Toast.LENGTH_LONG).show();
                    spin_social.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //phonelink link,,, directs you to calling SA's phone number
        ImageButton button9 = (ImageButton) findViewById(R.id.sa_phone_textView);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("tel:l4232362723"));
                startActivity(intent);
            }
        }) ;

        //displaying sa introduction information
        TextView txt1 = (TextView)findViewById(R.id.textView1);
        txt1.setMovementMethod(new ScrollingMovementMethod());
        View aboutSA1 = findViewById(R.id.textView1);
        ((TextView) aboutSA1).setText("Hey Guys!\n" +
                "Chances are that you have probably been to a party, an event, or a get together that you would consider to be wack. Maybe a song, a person, or a class. The reality is that there are a lot of things in life that we can define as wack. However, many wonder what defines a good person, song, activity, or event from a bad one. The truth is, it is you who controls whether something is wack or awesome.\n" +
                "The Student Association’s theme this year is Embrace The Duck. Many students dislike the fact that we are known for our community of ducks. The idea of having a duck as our mascot is repulsive to many, simply because in the grand scheme of things being a duck is not considered cool in society. This is very similar to the other insecurities we have as students. We are told what is cool, what is acceptable, and what is good.\n" +
                "Women are told what is pretty and what they have to be or do in order to be accepted by men. Men are told what a real man is and how it is defined by what sport they can play, how strong they are, and how many girlfriends they can acquire. You are told that you aren’t good enough for that job, that guy, that girl, or better yet, our God. But as SA President I am challenging you to experience a paradigm shift.\n" +
                "Instead of trying so hard to fit the framework that society sets, embrace who you are, realize that you have been fearfully and wonderfully made. Embrace who you are in Christ. Embrace your awkward personality, embrace that unique facial feature, embrace your talents, and embrace your classes.\n" +
                "This year SA will be providing opportunities and tools to grow socially, emotionally, and spiritually. This growth will only happen if you put your insecurities aside and take advantage of SA and embrace the duck that you are. So as we go throughout the school year, remember to embrace God, embrace yourself, and embrace the duck.");
    }
}
