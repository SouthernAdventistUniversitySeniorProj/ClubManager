package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClubsFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClubsFrag#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ClubsFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
ListView list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
    public static ClubsFrag newInstance(String param1) {
        ClubsFrag fragment = new ClubsFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public ClubsFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_clubs, container, false);

        clubPics = new Integer[]{R.drawable.accounting,R.drawable.african_student_union_club,R.drawable.allied_health_club,R.drawable.art_club,
                R.drawable.asian_club,R.drawable.biology_club,R.drawable.business_club,R.drawable.chemistry_club,R.drawable.communication_club,
                R.drawable.computer_club,R.drawable.education_club,R.drawable.enactus_club,R.drawable.encounter_club,R.drawable.english_club,
                R.drawable.expressions_of_praise_club,R.drawable.futbol_club,R.drawable.history_club,R.drawable.latin_america_club,
                R.drawable.long_term_care_club,R.drawable.management_club,R.drawable.marketing_club, R.drawable.nursing_club,R.drawable.pre_dental_club,
                R.drawable.pre_med_club,R.drawable.pre_optometry_club,R.drawable.psychology_club,R.drawable.southern_ringtones_club,R.drawable.student_missions_club,
                R.drawable.wellness_club};

        values = new String[]{"Accounting Club","African Student Union Club","Allied Health Club Club",
                "Art Club","Asian Club","Biology Club","Business Society","Chemistry Club",
                "Communications Club","Computer Club","Education Club","Enactus","Encounter","English Club/Sigma Tau Delta",
                "Expressions of Praise","Futbol Club","History Club","Latin American Club","Long-term Health Care Club",
                "Management Club","Marketing Club","Nursing Club","Pre-dental Club","Pre-med Club",
                "Pre-optometry Club","Psychology Club","Southern Ringtones Club","Student Missions Club","Wellness Club"};


            PicList adapter = new PicList(this.getActivity(), values, clubPics);
            list= (ListView)rootView.findViewById(R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(getActivity(), "You Clicked at " +values[+ position], Toast.LENGTH_SHORT).show();

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.container, myClub.newInstance(values[position],position));
                    ft.addToBackStack(null);
                    // super.onListItemClick(l, v, position, id);
                    ft.commit();



                }
           });







        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);*/

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //super.onAttach(activity);
        ((ClubManagerMainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_PARAM1));
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */



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




    public static class myClub extends Fragment {



        private static final String Club_Name = "club_name";//ARG_SECTION_NUMBER = "section_number";
        private static final String Position = "Position";//ARG_SECTION_NUMBER = "section_number";
        public static myClub newInstance(String clubName, int position ) {
            myClub fragment = new myClub();
            Bundle args = new Bundle();
            args.putString(Club_Name,clubName);
            args.putInt(Position,position);

           //clubPics.getResourceId()
            fragment.setArguments(args);
            return fragment;
        }

        public myClub() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            TypedArray clubPics = getResources().obtainTypedArray(R.array.club_pics);
            View rootView = inflater.inflate(R.layout.club_layout_frag, container, false);
            View club_name_view = rootView.findViewById(R.id.club_name);
            View club_descript = rootView.findViewById(R.id.club_description);
            View club_pic = rootView.findViewById(R.id.imageView);
            ((TextView)club_name_view).setText(this.getArguments().get(Club_Name).toString());
            ((TextView)club_descript).setText("A brief description and a few fun facts about The "+ this.getArguments().get(Club_Name).toString());

            ((ImageView)club_pic).setImageResource(clubPics.getResourceId(this.getArguments().getInt(Position),0));
            //leave join implementation
        /*    final Button joinButton = (Button) rootView.findViewById(R.id.join_button);
                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button leave_button = (Button)view.findViewById(R.id.leave_button);
                        leave_button.setEnabled(true);

                    }
                });*/

            return rootView;
        }



        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ClubManagerMainActivity) activity).onSectionAttached(
                    getArguments().getInt(Club_Name));
        }
    }


}
