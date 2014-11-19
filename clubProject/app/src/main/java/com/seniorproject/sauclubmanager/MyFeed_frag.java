package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Qwynn on 10/29/2014.
 */
public class MyFeed_frag  extends Fragment {

    ListView list;

    private static final String ARG_PARAM1 = "param1";

    private String[] clubNames;
    private Integer[] clubPics;
    private String[] post;

    public static MyFeed_frag newInstance(String param1) {
        MyFeed_frag fragment = new MyFeed_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFeed_frag() {
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


        FeedList adapter = new FeedList(this.getActivity(), clubNames, clubPics,clubNames);
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
        }*/
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
}