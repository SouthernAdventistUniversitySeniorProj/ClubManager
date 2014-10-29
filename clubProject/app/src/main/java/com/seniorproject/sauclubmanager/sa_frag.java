package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.seniorproject.sauclubmanager.com.seniorproject.utilities.LoginScreen;

/**
 * Created by User on 10/26/2014.
 */
public class sa_frag extends Fragment {

    public static sa_frag newInstance() {
        sa_frag fragment = new sa_frag();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public sa_frag() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sa_layout_frag, container, false);
        View aboutSA1 = rootView.findViewById(R.id.textView1);
        ((TextView) aboutSA1).setText("Hey Guys!\n" +
                "Chances are that you have probably been to a party, an event, or a get together that you would consider to be wack. Maybe a song, a person, or a class. The reality is that there are a lot of things in life that we can define as wack. However, many wonder what defines a good person, song, activity, or event from a bad one. The truth is, it is you who controls whether something is wack or awesome.\n" +
                "The Student Association’s theme this year is Embrace The Duck. Many students dislike the fact that we are known for our community of ducks. The idea of having a duck as our mascot is repulsive to many, simply because in the grand scheme of things being a duck is not considered cool in society. This is very similar to the other insecurities we have as students. We are told what is cool, what is acceptable, and what is good.\n" +
                "Women are told what is pretty and what they have to be or do in order to be accepted by men. Men are told what a real man is and how it is defined by what sport they can play, how strong they are, and how many girlfriends they can acquire. You are told that you aren’t good enough for that job, that guy, that girl, or better yet, our God. But as SA President I am challenging you to experience a paradigm shift.\n" +
                "Instead of trying so hard to fit the framework that society sets, embrace who you are, realize that you have been fearfully and wonderfully made. Embrace who you are in Christ. Embrace your awkward personality, embrace that unique facial feature, embrace your talents, and embrace your classes.\n" +
                "This year SA will be providing opportunities and tools to grow socially, emotionally, and spiritually. This growth will only happen if you put your insecurities aside and take advantage of SA and embrace the duck that you are. So as we go throughout the school year, remember to embrace God, embrace yourself, and embrace the duck.");

        //sa_about_button
        Button button1 = (Button) rootView.findViewById(R.id.sa_about_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/Pages/about.aspx"));
                startActivity(intent);
            }
        }) ;

        //sa_officers_button
        Button button2 = (Button) rootView.findViewById(R.id.sa_officers_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/Pages/officers.aspx"));
                startActivity(intent);
            }
        }) ;

        //sa_senate_button
        Button button4 = (Button) rootView.findViewById(R.id.sa_senate_button);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa/senate/Pages/senate.aspx"));
                startActivity(intent);
            }
        }) ;

        //sa_banner_imageView
        ImageButton button3 = (ImageButton) rootView.findViewById(R.id.sa_banner_imageView);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.southern.edu/sa"));
                startActivity(intent);
            }
        }) ;

        //sa_banner_imageView
        ImageButton button5 = (ImageButton) rootView.findViewById(R.id.sa_fb_button);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/SAUStudentAssociation"));
                startActivity(intent);
            }
        }) ;

        //sa_banner_imageView
        ImageButton button6 = (ImageButton) rootView.findViewById(R.id.sa_twitter_button);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/southern_sa"));
                startActivity(intent);
            }
        }) ;

        //sa_banner_imageView
        ImageButton button7 = (ImageButton) rootView.findViewById(R.id.sa_instagram_button);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/sa_southern"));
                startActivity(intent);
            }
        }) ;

        //sa_banner_imageView
        ImageButton button8 = (ImageButton) rootView.findViewById(R.id.sa_email_button);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("mailto:sa@southern.edu?subject=%5BStudent%20Association%20-%20Contact%5D"));
                startActivity(intent);
            }
        }) ;

        TextView txt1 = (TextView)rootView.findViewById(R.id.textView1);
        txt1.setMovementMethod(new ScrollingMovementMethod());

        //phonelink link
        ImageButton button9 = (ImageButton) rootView.findViewById(R.id.sa_phone_textView);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("tel:l4232362723"));
                startActivity(intent);
            }
        }) ;

        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
}
