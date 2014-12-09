package com.seniorproject.sauclubmanager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class settings extends DashboardActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, new settings_fragment());
        transaction.commit();
        //want to set default value of this text to the current users email
        //createEditTextPreference(edit_email);


    }

    public static class settings_fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            PreferenceManager manager = getPreferenceManager();
            manager.setSharedPreferencesName("settings_frag");
            addPreferencesFromResource(R.xml.settings_frag);

            final Preference myPref = findPreference("edit_email");
            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    myPref.setDefaultValue(LoginScreen.loginPreferences.getString("username", ""));
                    Toast.makeText(getActivity(), "You clicked it! ", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

    }
}
