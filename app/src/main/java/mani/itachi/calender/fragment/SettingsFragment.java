package mani.itachi.calender.fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.kizitonwose.colorpreferencecompat.ColorPreferenceCompat;
import mani.itachi.calender.R;


public class SettingsFragment extends PreferenceFragmentCompat {


    public SettingsFragment(){}

    private final String CUSTOM_PICKER_PREF_KEY = "color_pref_lobster_compat";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

}