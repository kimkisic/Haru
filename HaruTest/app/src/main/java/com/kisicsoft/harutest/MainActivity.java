package com.kisicsoft.harutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class MainActivity extends AppCompatActivity {

    MoreFragment moreFragment;
    HomeFragment homeFragment;
    MyPlaceFragment myPlaceFragment;
    MyInfoFragment myInfoFragment;
    ReviewFragment reviewFragment;

    SlidingUpPanelLayout layout;
    BottomNavigationView btnv;
    long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moreFragment = new MoreFragment();
        homeFragment = new HomeFragment();
        myPlaceFragment = new MyPlaceFragment();
        myInfoFragment = new MyInfoFragment();
        reviewFragment = new ReviewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).addToBackStack(null).commit();

        layout = (SlidingUpPanelLayout)findViewById(R.id.sliding);

        btnv = (BottomNavigationView)findViewById(R.id.btnv);

        layout.setTouchEnabled(false);


        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).addToBackStack(null).commit();
                        break;
                    case R.id.review:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, reviewFragment).addToBackStack(null).commit();
                    case R.id.myPlace:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, myPlaceFragment).addToBackStack(null).commit();
                        break;
                    case R.id.my:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, myInfoFragment).addToBackStack(null).commit();
                        break;
                    case R.id.more:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, moreFragment).addToBackStack(null).commit();
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(layout.getPanelState() == EXPANDED){
            layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            btnv.setVisibility(View.VISIBLE);
        } else if (System.currentTimeMillis() - time >= 2000){
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버톤을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if(System.currentTimeMillis() - time < 2000){
            ActivityCompat.finishAffinity(this);
            System.runFinalizersOnExit(true);
            System.exit(0);
        }
    }

    public void moveSearch(View view) {
        layout.setPanelState(EXPANDED);
    }


    public void closingSearch(View view) {layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

}
