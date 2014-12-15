/**
 * Created by User on 10/12/2014.
 */
package com.seniorproject.sauclubmanager;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.Calendar;

/**
 * A simple {@link android.app.Fragment} subclass.
 * create an instance of this fragment.
 *
 */

public class myevents extends DashboardActivity {
    // date variables
    private int mYear;
    private int mMonth;
    private int mDay;
    private int selectedClub;
    final private int DATE_DIALOG_ID = 2;
    final private int CLUB_SELECTION = 1;
    private ParseUser curUser = ParseUser.getCurrentUser();
    private ParseQuery<ParseObject> queryEvent = ParseQuery.getQuery("Event");
    private ParseObject event = new ParseObject("Event");
    // time variables


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_event);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        ListView userEventList = (ListView) findViewById(R.id.userEventlistView);



        Button addEvent = (Button) findViewById(R.id.addEvent_button);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String information = ("You have successfully added an event ");
                showDialog(DATE_DIALOG_ID);
                //Toast.makeText(getApplicationContext(), information, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
//            case CLUB_SELECTION:
//
//                return new ListPreference(this, clubPickerListener, selectedClub);
            case DATE_DIALOG_ID:
                // set date picker as current date
                //Edit------------------------------------------//
                final Calendar c = Calendar.getInstance();
                mYear  = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay   = c.get(Calendar.DAY_OF_MONTH);
                //Edit------------------------------------------//
                return new DatePickerDialog(this, datePickerListener,
                        mYear, mMonth,mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;
            //String selmonth = cm.getMonth(mMonth);

            ParseRelation<ParseObject> relation = curUser.getRelation("userEvents");
            event.put("mDay", mDay);
            event.put("mMonth", mMonth);
            event.put("mDay", mDay);
            event.saveInBackground();

//            String bookingdate = String.valueOf(mYear) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mDay);
//            Toast.makeText(getApplicationContext(), bookingdate, Toast.LENGTH_SHORT).show();
        }
    };
}

