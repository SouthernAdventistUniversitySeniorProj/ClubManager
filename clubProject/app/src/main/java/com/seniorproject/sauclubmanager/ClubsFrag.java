package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
public class ClubsFrag extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

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


        values = new String[]{"Accounting Club","African Student Union","Allied Health Club",
                "Art Club","Asian Club","Biology Club","Business Society","Chemistry Club",
                "Communications Club","Computer Club","Education Club","Enactus","Encounter","English Club/Sigma Tau Delta",
                "Expressions of Praise","Finance Club","Kayak Club","Latin American Club",
                "Long-term Health Care Club","Marketing Club","Mathematics Club","Nursing Club",
                "Phi Alpha Theta History","Physical Therapy Club","Pre-dental Club","Pre-med Club",
                "Pre-optometry Club","Pre-pharmacy Club","Psi-Chi Club","SE Youth Conf Club (SEYC)",
                "Southern Ringtones Club","Southern Striders","Student Missions Club","Writers Club"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
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



    public static class myClub extends Fragment {

        private static final String Club_Name = "club_name";//ARG_SECTION_NUMBER = "section_number";

        public static myClub newInstance(String clubName) {
            myClub fragment = new myClub();
            Bundle args = new Bundle();
            args.putString(Club_Name,clubName);
            fragment.setArguments(args);
            return fragment;
        }

        public myClub() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.club_layout_frag, container, false);
            View club_name_view = rootView.findViewById(R.id.club_name);
            View club_descript = rootView.findViewById(R.id.club_description);
            ((TextView)club_name_view).setText(this.getArguments().get(Club_Name).toString());
            ((TextView)club_descript).setText("A brief description and a few fun facts about The "+ this.getArguments().get(Club_Name).toString());

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
