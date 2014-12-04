package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 10/26/2014.
 */
public class sa extends DashboardActivity {

//    // stores the image database icons
//    private static Integer[] imageIconDatabase = { R.drawable.facebook,
//            R.drawable.twitter, R.drawable.instagram, R.drawable.email };
//
//    // stores the image database names
//    private String[] imageNameDatabase = { "facebook", "twitter", "instagram", "email"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sa);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner spin = (Spinner) findViewById(R.id.sa_spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
                //
            }
        });

        final Spinner spin_social = (Spinner) findViewById(R.id.sa_social_spinner);
        spin_social.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
                //
            }
        });

        //phonelink link
        ImageButton button9 = (ImageButton) findViewById(R.id.sa_phone_textView);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("tel:l4232362723"));
                startActivity(intent);
            }
        }) ;

        TextView txt1 = (TextView)findViewById(R.id.textView1);
        txt1.setMovementMethod(new ScrollingMovementMethod());
        View aboutSA1 = findViewById(R.id.textView1);
        ((TextView) aboutSA1).setText("Hey Guys!\n" +
                "Chances are that you have probably been to a party, an event, or a get together that you would consider to be wack. Maybe a song, a person, or a class. The reality is that there are a lot of things in life that we can define as wack. However, many wonder what defines a good person, song, activity, or event from a bad one. The truth is, it is you who controls whether something is wack or awesome.\n" +
                "The Student Association’s theme this year is Embrace The Duck. Many students dislike the fact that we are known for our community of ducks. The idea of having a duck as our mascot is repulsive to many, simply because in the grand scheme of things being a duck is not considered cool in society. This is very similar to the other insecurities we have as students. We are told what is cool, what is acceptable, and what is good.\n" +
                "Women are told what is pretty and what they have to be or do in order to be accepted by men. Men are told what a real man is and how it is defined by what sport they can play, how strong they are, and how many girlfriends they can acquire. You are told that you aren’t good enough for that job, that guy, that girl, or better yet, our God. But as SA President I am challenging you to experience a paradigm shift.\n" +
                "Instead of trying so hard to fit the framework that society sets, embrace who you are, realize that you have been fearfully and wonderfully made. Embrace who you are in Christ. Embrace your awkward personality, embrace that unique facial feature, embrace your talents, and embrace your classes.\n" +
                "This year SA will be providing opportunities and tools to grow socially, emotionally, and spiritually. This growth will only happen if you put your insecurities aside and take advantage of SA and embrace the duck that you are. So as we go throughout the school year, remember to embrace God, embrace yourself, and embrace the duck.");
        //Logout_button();
    }

    // MOVE THIS LOGIC TO A MENU FOR UNIFORMITY
   /* public void Logout_button() {
        ImageButton Logout_button = (ImageButton) findViewById(R.id.logout_button);
        Logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get shared prefs and clear them
                LoginScreen.loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                //LoginScreen.loginPrefsEditor.remove("username");
                //LoginScreen.loginPrefsEditor.remove("password");
                LoginScreen.loginPrefsEditor.putBoolean("saveLogin", false);
                LoginScreen.loginPrefsEditor.putString("username", "email");
                LoginScreen.loginPrefsEditor.putString("password", "password");
                LoginScreen.loginPrefsEditor.commit();
                LoginScreen.saveLoginCheckbox.setChecked(false);
                Intent intent = new Intent(user_profile.this, LoginScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sa, container, false);
        View aboutSA1 = rootView.findViewById(R.id.textView1);
        ((TextView) aboutSA1).setText("Hey Guys!\n" +
                "Chances are that you have probably been to a party, an event, or a get together that you would consider to be wack. Maybe a song, a person, or a class. The reality is that there are a lot of things in life that we can define as wack. However, many wonder what defines a good person, song, activity, or event from a bad one. The truth is, it is you who controls whether something is wack or awesome.\n" +
                "The Student Association’s theme this year is Embrace The Duck. Many students dislike the fact that we are known for our community of ducks. The idea of having a duck as our mascot is repulsive to many, simply because in the grand scheme of things being a duck is not considered cool in society. This is very similar to the other insecurities we have as students. We are told what is cool, what is acceptable, and what is good.\n" +
                "Women are told what is pretty and what they have to be or do in order to be accepted by men. Men are told what a real man is and how it is defined by what sport they can play, how strong they are, and how many girlfriends they can acquire. You are told that you aren’t good enough for that job, that guy, that girl, or better yet, our God. But as SA President I am challenging you to experience a paradigm shift.\n" +
                "Instead of trying so hard to fit the framework that society sets, embrace who you are, realize that you have been fearfully and wonderfully made. Embrace who you are in Christ. Embrace your awkward personality, embrace that unique facial feature, embrace your talents, and embrace your classes.\n" +
                "This year SA will be providing opportunities and tools to grow socially, emotionally, and spiritually. This growth will only happen if you put your insecurities aside and take advantage of SA and embrace the duck that you are. So as we go throughout the school year, remember to embrace God, embrace yourself, and embrace the duck.");

//        //sa_about_button
//        Button button1 = (Button) rootView.findViewById(R.id.sa_about_button);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.southern.edu/sa/Pages/about.aspx"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_officers_button
//        Button button2 = (Button) rootView.findViewById(R.id.sa_officers_button);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.southern.edu/sa/Pages/officers.aspx"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_senate_button
//        Button button4 = (Button) rootView.findViewById(R.id.sa_senate_button);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.southern.edu/sa/senate/Pages/senate.aspx"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_banner_imageView
//        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.sa_banner_imageView);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.southern.edu/sa"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_banner_imageView
//        ImageButton button5 = (ImageButton) rootView.findViewById(R.id.sa_fb_button);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://www.facebook.com/SAUStudentAssociation"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_banner_imageView
//        ImageButton button6 = (ImageButton) rootView.findViewById(R.id.sa_twitter_button);
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("https://twitter.com/southern_sa"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_banner_imageView
//        ImageButton button7 = (ImageButton) rootView.findViewById(R.id.sa_instagram_button);
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://instagram.com/sa_southern"));
//                startActivity(intent);
//            }
//        }) ;
//
//        //sa_banner_imageView
//        ImageButton button8 = (ImageButton) rootView.findViewById(R.id.sa_email_button);
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("mailto:sa@southern.edu?subject=%5BStudent%20Association%20-%20Contact%5D"));
//                startActivity(intent);
//            }
//        }) ;






        return rootView;
    }
}
