package com.seniorproject.sauclubmanager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

public class myfeed extends DashboardActivity {

    List<ParseObject> ob;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeed);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ParseUser curUser = ParseUser.getCurrentUser();

        //displaying list of clubs that user is in
        ListView userClubs = (ListView) findViewById(R.id.userFeedlistView);
        ParseRelation<ParseObject> relation = curUser.getRelation("clubs");
        ParseQuery<ParseObject> query = relation.getQuery();
        try {
            ob = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (ParseObject clubs : ob) {
            itemsAdapter.add((String) clubs.get("clubName"));
        }
        userClubs.setAdapter(itemsAdapter);
    }
}