/**
 * Created by User on 10/12/2014.
 */
package com.seniorproject.sauclubmanager;



        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CalendarView;
        import android.widget.Toast;

        import com.seniorproject.sauclubmanager.com.seniorproject.utilities.LoginScreen;
        import com.seniorproject.sauclubmanager.com.seniorproject.utilities.cal_event_frag;

        import java.util.Calendar;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link SA_senate_frag#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class CalendarFrag extends Fragment {
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SA_senate_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFrag newInstance() {
        CalendarFrag fragment = new CalendarFrag();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CalendarFrag() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar_activity, container, false);

        CalendarView calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        calendarView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getActivity(), "Viewing Current Events for Today...", Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(getActivity(), cal_event_frag.class);
                startActivity(myintent);

            }
        });


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

