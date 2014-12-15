package com.seniorproject.sauclubmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.parse.ParseObject;

import java.util.Calendar;

public class myevents extends DashboardActivity {
    // date variables
    private int mYear;
    private int mMonth;
    private int mDay;
    final private int DATE_DIALOG_ID = 2;
    private ParseObject event = new ParseObject("Event");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_event);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //when button clicked add event
        Button addEvent = (Button) findViewById(R.id.addEvent_button);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                final Calendar c = Calendar.getInstance();
                mYear  = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay   = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(this, datePickerListener,
                        mYear, mMonth,mDay);
        }
        return null;
    }

    // adds data collected to database
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;

            event.put("mDay", mDay);
            event.put("mMonth", mMonth);
            event.put("mDay", mDay);
            event.saveInBackground();
        }
    };
}

