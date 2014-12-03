package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Qwynn on 10/29/2014.
 */
public class myfeed extends DashboardActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeed);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
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
/*
    ListView list;

    private static final String ARG_PARAM1 = "param1";

    private String[] clubNames;
    private Integer[] clubPics;
    private String[] post;

    public static myfeed newInstance(String param1) {
        myfeed fragment = new myfeed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public myfeed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_clubs, container, false);

        clubPics = new Integer[]{R.drawable.accounting, R.drawable.african_student_union_club, R.drawable.allied_health_club, R.drawable.art_club,
                R.drawable.asian_club, R.drawable.biology_club, R.drawable.business_club, R.drawable.chemistry_club, R.drawable.communication_club,
                R.drawable.computer_club, R.drawable.education_club, R.drawable.enactus_club, R.drawable.encounter_club, R.drawable.english_club,
                R.drawable.expressions_of_praise_club, R.drawable.futbol_club, R.drawable.history_club, R.drawable.latin_america_club,
                R.drawable.long_term_care_club, R.drawable.management_club, R.drawable.marketing_club, R.drawable.nursing_club, R.drawable.pre_dental_club,
                R.drawable.pre_med_club, R.drawable.pre_optometry_club, R.drawable.psychology_club, R.drawable.southern_ringtones_club, R.drawable.student_missions_club,
                R.drawable.wellness_club};

        clubNames = new String[]{"Accounting Club", "African Student Union Club", "Allied Health Club Club",
                "Art Club", "Asian Club", "Biology Club", "Business Society", "Chemistry Club",
                "Communications Club", "Computer Club", "Education Club", "Enactus", "Encounter", "English Club/Sigma Tau Delta",
                "Expressions of Praise", "Futbol Club", "History Club", "Latin American Club", "Long-term Health Care Club",
                "Management Club", "Marketing Club", "Nursing Club", "Pre-dental Club", "Pre-med Club",
                "Pre-optometry Club", "Psychology Club", "Southern Ringtones Club", "Student Missions Club", "Wellness Club"};


        FeedList adapter = new FeedList(this, clubNames, clubPics,clubNames);
        list = (ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + clubNames[+position], Toast.LENGTH_SHORT).show();

                //FragmentManager manager = getFragmentManager();
                //FragmentTransaction ft = manager.beginTransaction();
                //ft.replace(R.id.container, myClub.newInstance(clubNames[position], position));
                //ft.addToBackStack(null);
                // super.onListItemClick(l, v, position, id);
                //ft.commit();


            }
        });

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);*/
/*
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //super.onAttach(activity);
        /*((ClubManagerMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_PARAM1));*/
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


    public class FeedList extends ArrayAdapter<String> {
        private final Activity context;
        private final String[] clubName;
        private final Integer[] clubPics;
        private final String[] post;

        public FeedList(Activity context,
                        String[] clubName, Integer[] clubPics, String[] post) {
            super(context, R.layout.custom_feed_list_adapter_view, clubName);
            this.context = context;
            this.clubName = clubName;
            this.clubPics = clubPics;
            this.post = post;
        }
    }
    */
}