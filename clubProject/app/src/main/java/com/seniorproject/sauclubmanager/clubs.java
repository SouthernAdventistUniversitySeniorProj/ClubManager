package com.seniorproject.sauclubmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class clubs extends DashboardActivity {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    ListView list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubs);
    }

    // private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClubsFrag.
     */
    // TODO: Rename and change types and number of parameters

    private String[] values;
    private Integer[] clubPics;

    public void onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.clubs, container, false);

        clubPics = new Integer[]{R.drawable.accounting, R.drawable.african_student_union_club, R.drawable.allied_health_club, R.drawable.art_club,
                R.drawable.asian_club, R.drawable.biology_club, R.drawable.business_club, R.drawable.chemistry_club, R.drawable.communication_club,
                R.drawable.computer_club, R.drawable.education_club, R.drawable.enactus_club, R.drawable.encounter_club, R.drawable.english_club,
                R.drawable.expressions_of_praise_club, R.drawable.futbol_club, R.drawable.history_club, R.drawable.latin_america_club,
                R.drawable.long_term_care_club, R.drawable.management_club, R.drawable.marketing_club, R.drawable.nursing_club, R.drawable.pre_dental_club,
                R.drawable.pre_med_club, R.drawable.pre_optometry_club, R.drawable.psychology_club, R.drawable.southern_ringtones_club, R.drawable.student_missions_club,
                R.drawable.wellness_club};


        values = getResources().getStringArray(R.array.club_names);
/*
        PicList adapter = new PicList(this, values, clubPics);
        list= (ListView)rootView.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
              /*  Toast.makeText(this, "You Clicked at " +values[+ position], Toast.LENGTH_SHORT).show();

                FragmentManager manager = getFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.container, myClub.newInstance(values[position],position));
                ft.addToBackStack(null);
                // super.onListItemClick(l, v, position, id);
                ft.commit();



            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

/*    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Log.d("fhjkashfljsfjl;","item clicked "+values[position]);
        Toast.makeText(getActivity(), values[position]+" selected",Toast.LENGTH_LONG).show();

       FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.container, myClub.newInstance(values[position]));
        ft.addToBackStack(null);
       // super.onListItemClick(l, v, position, id);
        ft.commit();
    }

    @Override
    public void onAttach(Activity activity) {
       /* super.onAttach(this);
        //super.onAttach(activity);
        ((ClubManagerMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_PARAM1));
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

    public static class myClub extends Fragment {



        private static final String Club_Name = "club_name";//ARG_SECTION_NUMBER = "section_number";
        private static final String Position = "Position";//ARG_SECTION_NUMBER = "section_number";
        private String[] namesofClubs;
        private boolean isFavorite;


        //Shared Prefs/utilities
        private SharedPreferences clubPrefs;
        private SharedPreferences.Editor clubPrefsEditor;
        private CheckedTextView myFavorite;

        public myClub() {
        }

        public static myClub newInstance(String clubName, int position ) {
            myClub fragment = new myClub();
            Bundle args = new Bundle();
            args.putString(Club_Name,clubName);
            args.putInt(Position,position);

            //clubPics.getResourceId()
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            TypedArray clubPics = getResources().obtainTypedArray(R.array.club_pics);
            View rootView = inflater.inflate(R.layout.club_layout_frag, container, false);
            View club_name_view = rootView.findViewById(R.id.club_name);
            View club_descript = rootView.findViewById(R.id.club_description);
            View club_pic = rootView.findViewById(R.id.imageView);
            ((TextView) club_name_view).setText(this.getArguments().get(Club_Name).toString());
            ((TextView) club_descript).setText("A brief description and a few fun facts about The " + this.getArguments().get(Club_Name).toString());
            ((ImageView) club_pic).setImageResource(clubPics.getResourceId(this.getArguments().getInt(Position), 0));

            myFavorite = (CheckedTextView) rootView.findViewById(R.id.favorite);

            //saveLoginCheckbox = (CheckBox)findViewById(R.id.remember);

            namesofClubs = getActivity().getResources().getStringArray(R.array.club_names);

            clubPrefsEditor = clubPrefs.edit();


            // myFavorite


            myFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (v == myFavorite) {
                        //  myFavorite.toggle();
                        if (myFavorite.isChecked()) {
                            myFavorite.setChecked(false);
                            isFavorite = false;

                            //clubPrefsEditor.putBoolean("isFavorite", true);
                            //loginPrefsEditor.putString("username", useremail);
                            //loginPrefsEditor.putString("password", password);
                            //   clubPrefsEditor.commit();
                        } else {
                            myFavorite.setChecked(true);
                            isFavorite = true;
                            // clubPrefsEditor.clear();
                            //   clubPrefsEditor.commit();
                        }
                    }
                }
            });


            isFavorite = clubPrefs.getBoolean("isFavorite", false);
            if (isFavorite) {

                //mEmailView.setText(loginPreferences.getString("username", ""));
                //mPasswordView.setText(loginPreferences.getString("password",""));
                myFavorite.setChecked(true);
                //}


                //leave join implementation
        /*    final Button joinButton = (Button) rootView.findViewById(R.id.join_button);
                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button leave_button = (Button)view.findViewById(R.id.leave_button);
                        leave_button.setEnabled(true);

                    }
                });
            }

            return rootView;
        }


        public void setFav(int position)
        {
            clubPrefs = getActivity().getSharedPreferences(namesofClubs[position], Context.MODE_PRIVATE);
            if(getFav(position)==true) {
                clubPrefsEditor.putBoolean("isFav", false);
            }
            else
            {
                clubPrefsEditor.putBoolean("isFav", true);
            }
        }


        public boolean getFav(int position){
            return clubPrefs.getBoolean("isFav",false);
        }




        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ClubManagerMainActivity) activity).onSectionAttached(
                    getArguments().getInt(Club_Name));
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.




//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

//}

    public class PicList extends ArrayAdapter<String>{
        private final Activity context;
        private final String[] clubName;
        private final Integer[] clubPics;
        public PicList(Activity context,
                       String[] clubName, Integer[] clubPics) {
            super(context, R.layout.picture_list, clubName);
            this.context = context;
            this.clubName = clubName;
            this.clubPics = clubPics;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.picture_list, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
            txtTitle.setText(clubName[position]);
            imageView.setImageResource(clubPics[position]);
            return rowView;
        }
    }
*/
    }}
