package com.kisicsoft.harutest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class SearchActivity extends AppCompatActivity {

    final static int GEO_SEARCH = 1004;

    TextView location_search;
    double latitude;
    double longitude;
    List<Address> addr = null;
    Geocoder gCoder;
    ImageView search;
    SlidingUpPanelLayout sliding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Cardviewitem> cardviewitemArrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recycler_search);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cardviewitemArrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Data");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cardviewitemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cardviewitem cardviewitem = snapshot.getValue(Cardviewitem.class);
                    cardviewitemArrayList.add(cardviewitem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MyPlaceFragment", String.valueOf(databaseError.toException()));
            }
        });

        adapter = new CardviewAdapter(this, cardviewitemArrayList);

        sliding = findViewById(R.id.sliding_search);
        search = findViewById(R.id.searchBtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliding.setPanelState(EXPANDED);
            }
        });
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
            if(a.getSubLocality() == null && a.getThoroughfare() != null && a.getSubThoroughfare() != null) {
                location_search.setText(a.getThoroughfare() + a.getSubThoroughfare());
            } else if(a.getThoroughfare() == null && a.getSubLocality() != null && a.getSubThoroughfare() != null){
                location_search.setText(a.getSubLocality() + " " + a.getSubThoroughfare());
            } else if(a.getSubThoroughfare() == null && a.getSubLocality() != null && a.getThoroughfare() != null){
                location_search.setText(a.getSubLocality() + " " + a.getThoroughfare());
            } else if(a.getSubLocality() == null && a.getThoroughfare() == null){
                location_search.setText("주소 미정");
            } else if(a.getSubLocality() == null && a.getSubThoroughfare() == null){
                location_search.setText(a.getThoroughfare());
            } else if(a.getThoroughfare() == null && a.getSubThoroughfare() == null){
                location_search.setText(a.getSubLocality());
            } else if(a.getSubLocality() == null && a.getThoroughfare() == null && a.getSubThoroughfare() == null){
                location_search.setText("주소 미정");
            } else {
                location_search.setText(a.getSubLocality() + " " + a.getThoroughfare() + a.getSubThoroughfare());
            }
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

    @Override
    public void onBackPressed() {
        if (sliding.getPanelState() == EXPANDED) {
            sliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    public void close(View view) {
        sliding.setPanelState(COLLAPSED);
    }
}
