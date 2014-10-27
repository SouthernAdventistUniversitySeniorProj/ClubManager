package com.seniorproject.sauclubmanager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 10/26/2014.
 */
public class sa_frag extends Fragment {

    public static sa_frag newInstance() {
        sa_frag fragment = new sa_frag();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public sa_frag() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sa_layout_frag, container, false);
        View aboutSA1 = rootView.findViewById(R.id.textView1);
        ((TextView)aboutSA1).setText("The Student Association is a team of people who love Southern and its students." +
                "Their main mission is to serve. They serve the community, the school, and most importantly, God." +
                "From listening to the student body's needs via the Senate to providing fun SA social events, each" +
                "officer serves a purpose. Whether writing articles for the Accent or writing financial reports to keep" +
                "the Student Association's spending in check, they are all functioning parts of a streamlined body. \n" +
                " \n" +
                "If you want to find out more about the roles each of them serve, click on the \"Officers\" tab," +
                "or better yet, come into the office during office hours and meet them!");
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
