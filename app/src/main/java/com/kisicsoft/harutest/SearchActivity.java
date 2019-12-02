package com.kisicsoft.harutest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    final static int GEO_SEARCH = 1004;

    TextView location_search;
    double latitude;
    double longitude;
    List<Address> addr = null;
    Geocoder gCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        location_search = findViewById(R.id.location_search);
        Intent intent = getIntent();
        latitude = intent.getExtras().getDouble("lat");
        longitude = intent.getExtras().getDouble("lng");
        gCoder = new Geocoder(this, Locale.KOREA);

        try {
            addr = gCoder.getFromLocation(
                    latitude,
                    longitude,
                    1);
            Address a = addr.get(0);
            location_search.setText(a.getSubLocality() + " " + a.getThoroughfare() + a.getSubThoroughfare());
        } catch (IOException e) {
            e.printStackTrace();
        }
        location_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SearchActivity.this, MapActivity.class);
                i.putExtra("lat", latitude);
                i.putExtra("lng", longitude);
                startActivityForResult(i, GEO_SEARCH);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GEO_SEARCH){
            if(resultCode == RESULT_OK) {
                latitude = data.getExtras().getDouble("lat");
                longitude = data.getExtras().getDouble("lng");
                try {
                    addr = gCoder.getFromLocation(latitude, longitude, 1);
                    Address a = addr.get(0);
                    location_search.setText(a.getSubLocality() + " " + a.getThoroughfare() + a.getSubThoroughfare());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
