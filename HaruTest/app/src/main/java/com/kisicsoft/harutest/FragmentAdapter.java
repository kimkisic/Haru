package com.kisicsoft.harutest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    Fragment[] fragments = new Fragment[5];
    String[] pageTitles = new String[]{"맛집", "PC방", "카페", "쇼핑", "노래방"};

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0] = new Page1Fragment();
        fragments[1] = new Page2Fragment();
        fragments[2] = new Page3Fragment();
        fragments[3] = new Page4Fragment();
        fragments[4] = new Page5Fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
    public CharSequence getPageTitle(int position){
        return pageTitles[position];
    }
}
