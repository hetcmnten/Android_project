package com.teemo.xuantruong.android_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.teemo.xuantruong.android_project.adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.R;

public class FragmentHomePage extends Fragment {
    private AppBarLayout appBar;
    private TabLayout tabs;
    private ViewPager viewPager;
    public FragmentHomePage() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);
        tabs = (TabLayout) view.findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper_id);
        final ViewPageAdapter adapter = new ViewPageAdapter(getChildFragmentManager());
        adapter.AddFragment(new FragmentListNews(),"TIN HOT");
        adapter.AddFragment(new FragmentCategories(),"THỂ LOẠI");
        adapter.AddFragment(new FragmentCategories(),"NGUỒN BÁO");
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
