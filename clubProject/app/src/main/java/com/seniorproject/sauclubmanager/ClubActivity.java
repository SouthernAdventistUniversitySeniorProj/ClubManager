package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class ClubActivity extends DashboardActivity {

    public ClubActivity(){};

    private TextView textView;
    private ImageView imageView;
    public String CLUB_NAME = "default club name";
    public String CLUB_PIC = "drawable/accounting";
    private List<ParseObject> ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_layout);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final ParseUser curUser = ParseUser.getCurrentUser();



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

        final ParseQuery<ParseObject> lookupClub = ParseQuery.getQuery("Club");
        lookupClub.whereEqualTo("clubName", CLUB_NAME);

        //handling joining a club
        ImageButton join = (ImageButton) findViewById(R.id.join_button);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = ("Congratulations you have now joined " + CLUB_NAME);
                lookupClub.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> parseObjects, ParseException e) {
                        if (e == null) {
                            ParseRelation<ParseObject> relation = curUser.getRelation("clubs");
                            relation.add(parseObjects.get(parseObjects.size()-1));
                            curUser.saveInBackground();

                            ParseRelation<ParseObject> relation2 = parseObjects.get(parseObjects.size()-1).getRelation("Members");
                            relation2.add(curUser);
                            parseObjects.get(parseObjects.size()-1).saveInBackground();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
            }
        });

        //handling leaving a club
        ImageButton leave = (ImageButton) findViewById(R.id.leave_button);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String information = ("You are currently removed from " + CLUB_NAME);
                lookupClub.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> RMparseObjects, ParseException e) {
                        if (e == null) {
                            ParseRelation<ParseObject> relation = curUser.getRelation("clubs");
                            relation.remove(RMparseObjects.get(RMparseObjects.size()-1));
                            curUser.saveInBackground();

                            ParseRelation<ParseObject> relation2 = RMparseObjects.get(RMparseObjects.size()-1).getRelation("Members");
                            relation2.remove(curUser);
                            RMparseObjects.get(RMparseObjects.size()-1).saveInBackground();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
            }
        });

        //displaying list of clubs that user is in
        ListView clubMembers = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        ParseQuery<ParseObject> clubclass = ParseQuery.getQuery("Club");
        clubclass.whereEqualTo("clubName", CLUB_NAME);

        //ParseObject clubObject = new ParseObject("Club");
        //ParseRelation<ParseObject> relation = curUser.getRelation("Members");
        final ParseQuery query = curUser.getQuery();
        query.whereEqualTo("mainClub", CLUB_NAME);
                try {
                    ob = query.find();
                } catch (ParseException i) {
                    i.printStackTrace();
                }
                for (ParseObject members : ob) {
                    itemsAdapter.add(members.get("firstName") + " " + members.get("lastName"));
                }
        clubMembers.setAdapter(itemsAdapter);
    }

}