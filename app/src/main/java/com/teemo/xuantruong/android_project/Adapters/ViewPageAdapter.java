package com.teemo.xuantruong.android_project.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<Fragment>();
    private final List<String> FraListTitles = new ArrayList<>();
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return FraListTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FraListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        FraListTitles.add(Title);
    }
}
