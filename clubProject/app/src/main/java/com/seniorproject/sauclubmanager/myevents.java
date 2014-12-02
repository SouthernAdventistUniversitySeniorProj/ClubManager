/**
 * Created by User on 10/12/2014.
 */
package com.seniorproject.sauclubmanager;



        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;

/**
 * A simple {@link android.app.Fragment} subclass.
 * create an instance of this fragment.
 *
 */

public class myevents extends DashboardActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_PICKER_ID = 1111;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        Logout_button();
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
                Intent intent = new Intent(myevents.this, LoginScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
/*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_activity, container, false);

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(this, "Viewing Current Events for Today...", Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(getActivity(), cal_event_frag.class);
                startActivity(myintent);
            }
        });
        return rootView;
    }
*/
}

