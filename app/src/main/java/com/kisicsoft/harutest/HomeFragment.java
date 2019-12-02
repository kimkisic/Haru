package com.kisicsoft.harutest;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;
import static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;

public class HomeFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager pager;
    FragmentAdapter adapter;
    String wt;
    ImageView widgetIv;
    TextView widgetTv;

    public HomeFragment() {

        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        widgetIv = (ImageView)view.findViewById(R.id.widgetIv);
        widgetTv = (TextView)view.findViewById(R.id.widgetTv);
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        pager = (ViewPager)view.findViewById(R.id.pager);
        adapter = new FragmentAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);


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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        widgetTv.setText("지금 서울은\n"+wt+"입니다.");
                        if(wt.equals("흐림")){
                            widgetIv.setImageResource(R.drawable.cloudy);
                        } else if(wt.equals("맑음")){
                            widgetIv.setImageResource(R.drawable.sun);
                        } else if(wt.equals("구름 많음")){
                            widgetIv.setImageResource(R.drawable.cloudy);
                        }
                    }
                });
            }
        }.start();

        return view;

    }


}
