package com.kisicsoft.harutest;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class MySettingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
