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

import android.app.Activity;
import android.content.Intent;
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

import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    FragmentAdapter adapter;
    SlidingUpPanelLayout layout;
    LinearLayout linearLayout;
    BottomNavigationView btnv;
    String wt;
    ImageView widgetIv;
    TextView widgetTv;
    long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (SlidingUpPanelLayout)findViewById(R.id.sliding);
        btnv = (BottomNavigationView)findViewById(R.id.btnv);
        linearLayout = (LinearLayout)findViewById(R.id.linear);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        widgetIv = (ImageView)findViewById(R.id.widgetIv);
        widgetTv = (TextView)findViewById(R.id.widgetTv);

        new Thread(){
            @Override
            public void run() {
                Document doc = null;
        try {
            doc = Jsoup.connect("https://weather.naver.com/rgn/cityWetrCity.nhn?cityRgnCd=CT001013").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements weather = doc.select("h4.first img");
        wt = weather.attr("alt");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                widgetTv.setText("오늘 서울은\n"+wt+"입니다.");
                if(wt.equals("흐림")){
                    widgetIv.setImageResource(R.drawable.cloudy);
                } else if(wt.equals("맑음")){
                    widgetIv.setImageResource(R.drawable.sun);
                }
            }
        });
            }
        }.start();

        layout.setTouchEnabled(false);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        layout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.more:
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        MoreFragment moreFragment = new MoreFragment();
                        transaction.show(moreFragment);
                        transaction.addToBackStack(null);
                        layout.setVisibility(View.INVISIBLE);
                        break;

                }
                return true;
            }
        });
    }

    public void moveSearch(View view) {
        btnv.setVisibility(View.INVISIBLE);
        layout.setPanelState(EXPANDED);
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

    public void closingSearch(View view) {layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        btnv.setVisibility(View.VISIBLE);
    }
}
