package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SA_senate_frag#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class SA_senate_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SA_senate_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static SA_senate_frag newInstance() {
        SA_senate_frag fragment = new SA_senate_frag();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public SA_senate_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //Student Association Senate Home Page
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.senate_frag, container, false);
        View aboutSA = rootView.findViewById(R.id.textView);
        ((TextView)aboutSA).setText("Senate is the legislative branch of the Student Association responsible for representing the various needs and desires of our great student body. It meets every other Wednesday to discuss what we can do to improve students' Southern experience as well as the community around our university, and then to enact as many of those changes as is within Senate power.");

        //TextView textView = new TextView(getActivity());
        //textView.setText(R.string.hello_blank_fragment);
        return rootView;
    }
}
