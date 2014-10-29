package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.seniorproject.sauclubmanager.R;
import com.seniorproject.sauclubmanager.sa_frag;

/**
 * Created by User on 10/29/2014.
 */
public class settings_frag extends Fragment {

    public static settings_frag newInstance() {
        settings_frag fragment = new settings_frag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_frag, container, false);

        //save button
        Button button = (Button) rootView.findViewById(R.id.settings_save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), sa_frag.class);
                startActivity(intent);
            }
        }) ;

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
