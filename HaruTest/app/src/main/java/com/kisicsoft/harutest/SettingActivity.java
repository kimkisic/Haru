package com.kisicsoft.harutest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;

public class SettingActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.settingcontainer, new MySettingFragment()).commit();
    }
}
