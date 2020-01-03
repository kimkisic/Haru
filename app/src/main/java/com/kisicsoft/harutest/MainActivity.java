package com.kisicsoft.harutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import android.graphics.Color;
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
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class MainActivity extends AppCompatActivity {

    MoreFragment moreFragment;
    HomeFragment homeFragment;
    MyPlaceFragment myPlaceFragment;
    MyInfoFragment myInfoFragment;
    ReviewFragment reviewFragment;
    TextView moveIntoMyPlace;

    TextView lineNumber1;
    TextView lineNumber2;
    TextView lineNumber3;
    TextView lineNumber4;
    TextView lineNumber5;
    TextView lineNumber6;
    TextView lineNumber7;
    TextView lineNumber8;
    TextView lineNumber9;

    TextView first;
    TextView second;
    TextView third;
    TextView fourth;
    TextView fifth;
    TextView sixth;
    TextView seventh;
    TextView eighth;
    TextView ninth;

    SlidingUpPanelLayout layout;
    BottomNavigationView btnv;
    long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fifth = findViewById(R.id.fifth);
        sixth = findViewById(R.id.sixth);
        seventh = findViewById(R.id.seventh);
        eighth = findViewById(R.id.eighth);
        ninth = findViewById(R.id.ninth);

        lineNumber1 = findViewById(R.id.line_number1);
        lineNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext() , R.color.lineNumber1));
                lineNumber1.setBackgroundColor(Color.WHITE);
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     서울역");
                second.setText("     시청역");
                third.setText("     종각역");
                fourth.setText("     종로3가역");
                fifth.setText("     종로5가역");
                sixth.setText("     동대문역");
                seventh.setText("     신설동역");
                eighth.setText("     제기동역");
                ninth.setText("     청량리역");
            }
        });
        lineNumber2 = findViewById(R.id.line_number2);
        lineNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber2));
                lineNumber2.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     시청역");
                second.setText("     을지로입구역");
                third.setText("     을지로3가역");
                fourth.setText("     을지로4가역");
                fifth.setText("     동대문역사문화공원역");
                sixth.setText("     신당역");
                seventh.setText("     상왕십리역");
                eighth.setText("     왕십리역");
                ninth.setText("     뚝섬역");
            }
        });
        lineNumber3 = findViewById(R.id.line_number3);
        lineNumber3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber3));
                lineNumber3.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     연신내역");
                second.setText("     불광역");
                third.setText("     녹번역");
                fourth.setText("     홍제역");
                fifth.setText("     경복궁역");
                sixth.setText("     안국역");
                seventh.setText("     종로3가역");
                eighth.setText("     을지로3가역");
                ninth.setText("     충무로역");
            }
        });
        lineNumber4 = findViewById(R.id.line_number4);
        lineNumber4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber4));
                lineNumber4.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     노원역");
                second.setText("     창동역");
                third.setText("     수유역");
                fourth.setText("     미아역");
                fifth.setText("     미아사거리역");
                sixth.setText("     길음역");
                seventh.setText("     성신여대입구역");
                eighth.setText("     한성대입구역");
                ninth.setText("     혜화역");
            }
        });
        lineNumber5 = findViewById(R.id.line_number5);
        lineNumber5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber5));
                lineNumber5.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     개화산역");
                second.setText("     김포공항역");
                third.setText("     송정역");
                fourth.setText("     마곡역");
                fifth.setText("     발산역");
                sixth.setText("     우장산역");
                seventh.setText("     화곡역");
                eighth.setText("     까치산역");
                ninth.setText("     신정역");
            }
        });
        lineNumber6 = findViewById(R.id.line_number6);
        lineNumber6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber6));
                lineNumber6.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     응암역");
                second.setText("     역촌역");
                third.setText("     불광역");
                fourth.setText("     독바위역");
                fifth.setText("     연신내역");
                sixth.setText("     구산역");
                seventh.setText("     마포구청역");
                eighth.setText("     망원역");
                ninth.setText("     합정역");
            }
        });
        lineNumber7 = findViewById(R.id.line_number7);
        lineNumber7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber7));
                lineNumber7.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     장암역");
                second.setText("     도봉산역");
                third.setText("     노원역");
                fourth.setText("     공릉역");
                fifth.setText("     태릉입구역");
                sixth.setText("     먹골역");
                seventh.setText("     중화역");
                eighth.setText("     상봉역");
                ninth.setText("     면목역");
            }
        });
        lineNumber8 = findViewById(R.id.line_number8);
        lineNumber8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber8));
                lineNumber8.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber9.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     암사역");
                second.setText("     천호역");
                third.setText("     몽촌토성역");
                fourth.setText("     잠실역");
                fifth.setText("     석촌역");
                sixth.setText("     송파역");
                seventh.setText("     가락시장역");
                eighth.setText("     문정역");
                ninth.setText("     장지역");
            }
        });
        lineNumber9 = findViewById(R.id.line_number9);
        lineNumber9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineNumber9.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lineNumber9));
                lineNumber9.setBackgroundColor(Color.WHITE);
                lineNumber1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber5.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber6.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber7.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                lineNumber8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.disable));
                lineNumber8.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.backDisable));
                first.setText("     김포공항역");
                second.setText("     공항시장역");
                third.setText("     신방화역");
                fourth.setText("     마곡나루역");
                fifth.setText("     양천향교역");
                sixth.setText("     가양역");
                seventh.setText("     증미역");
                eighth.setText("     등촌역");
                ninth.setText("     염창역");
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setPanelState(COLLAPSED);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("lat", 37.556201);
                intent.putExtra("lng", 126.972112);
                startActivity(intent);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setPanelState(COLLAPSED);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("lat", 37.565681);
                intent.putExtra("lng", 126.977133);
                startActivity(intent);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setPanelState(COLLAPSED);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("lat", 37.570229);
                intent.putExtra("lng", 126.983062);
                startActivity(intent);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setPanelState(COLLAPSED);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("lat", 37.570437);
                intent.putExtra("lng", 126.992203);
                startActivity(intent);
            }
        });


        moreFragment = new MoreFragment();
        homeFragment = new HomeFragment();
        myPlaceFragment = new MyPlaceFragment();
        myInfoFragment = new MyInfoFragment();
        reviewFragment = new ReviewFragment();
        moveIntoMyPlace = findViewById(R.id.moveintomyp);
        moveIntoMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                btnv.setSelectedItemId(R.id.myPlace);
            }
        });
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
