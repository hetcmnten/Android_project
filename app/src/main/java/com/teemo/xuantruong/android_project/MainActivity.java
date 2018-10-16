package com.teemo.xuantruong.android_project;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teemo.xuantruong.android_project.Adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.fragments.FragmentCategories;
import com.teemo.xuantruong.android_project.fragments.FragmentListNews;

public class MainActivity extends AppCompatActivity {
    public TabLayout tabLayout;
    public AppBarLayout appBarLayout;
    public ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mappings();
    }

    public void Mappings(){
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout =(AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpaper_id);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentListNews(),"News");
        adapter.AddFragment(new FragmentCategories(),"Categories");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
