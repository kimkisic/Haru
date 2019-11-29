package com.kisicsoft.harutest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyPlaceFragment extends Fragment {

    TextView location;

    final static int GOOGLE_MAP_CODE = 101;

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;

    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;

    private boolean isAccessFineLocation = false;

    private boolean isAccessCoarseLocation = false;

    private boolean isPermission = false;


    public GPSInfo gps;
    double latitude;
    double longituede;
    Geocoder gCoder;
    List<Address> addr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myplace, container, false);

        location = (TextView) view.findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("lat", latitude);
                intent.putExtra("lng", longituede);
                startActivityForResult(intent, GOOGLE_MAP_CODE);
            }
        });
        gps = new GPSInfo(getContext());

        if (gps.isGetLocation) {
            latitude = gps.getLatitude();
            longituede = gps.getLongitude();

            gCoder = new Geocoder(getContext(), Locale.KOREA);
            addr = null;
            try {
                addr = gCoder.getFromLocation(latitude, longituede, 1);
                Address a = addr.get(0);
                location.setText(a.getSubLocality() + " " + a.getThoroughfare() + a.getSubThoroughfare());
        gps = new GPSInfo(getContext());

        if (gps.isGetLocation) {
            double latitude = gps.getLatitude();
            double longituede = gps.getLongitude();

            Geocoder gCoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addr = null;
            try {
                addr = gCoder.getFromLocation(latitude, longituede, 1);
                Address a = addr.get(0);

                for (int i = 0; i <= a.getMaxAddressLineIndex(); i++) {
                    location.setText(a.getAddressLine(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addr != null) {
                if (addr.size() == 0) {
                    Toast.makeText(getContext(), "주소정보 없음", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            gps.showSettingsAlert();
        }
    callPermission();

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new CardviewAdapter());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_MAP_CODE){
            latitude = data.getExtras().getDouble("lat");
            longituede = data.getExtras().getDouble("lng");
            try {
                addr = gCoder.getFromLocation(latitude, longituede, 1);
                Address a = addr.get(0);
                location.setText(a.getSubLocality() + " " + a.getThoroughfare() + a.getSubThoroughfare());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void callPermission() {

        // Check the SDK version and whether the permission is already granted or not.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {



            requestPermissions(

                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},

                    PERMISSIONS_ACCESS_FINE_LOCATION);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)

                != PackageManager.PERMISSION_GRANTED){


            requestPermissions(

                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},

                    PERMISSIONS_ACCESS_COARSE_LOCATION);

        } else {

            isPermission = true;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION

                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            isAccessFineLocation = true;


        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION

                && grantResults[0] == PackageManager.PERMISSION_GRANTED){


            isAccessCoarseLocation = true;

        }


        if (isAccessFineLocation && isAccessCoarseLocation) {

            isPermission = true;

        }
    }
}
