package com.teemo.xuantruong.android_project.fragments;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.ListNewsAdapter;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewAdapter;
import com.teemo.xuantruong.android_project.adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.entity.FlagCategorySource;

public class FragmentHomePage extends Fragment implements Runnable {
    private AppBarLayout appBar;
    private TabLayout tabs;
    public ViewPager viewPager;
    static int flagSource = 0;
    static int flagCategory = 0;

    public FragmentHomePage() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper_id);
        tabs = (TabLayout) view.findViewById(R.id.tablayout_id);
        final ViewPageAdapter adapter = new ViewPageAdapter(getChildFragmentManager());
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int flagCurrentSource = FlagCategorySource.flagSource;
                int flagCurrentCategory = FlagCategorySource.flaCategory;
                // when change source -> change category
                if (flagSource != flagCurrentSource) {
                    adapter.fragmentCategories.listCat = adapter.fragmentSource.listSource.get(flagCurrentSource).getSource_categories();
                    adapter.fragmentListNews.listNews = adapter.fragmentCategories.listCat.get(0).getListPosters();
                    //update adapter list category
                    adapter.fragmentCategories.recyclerViewAdapter.listCate = adapter.fragmentCategories.listCat;
                    adapter.fragmentCategories.recyclerView.setAdapter(adapter.fragmentCategories.recyclerViewAdapter);
                    //update adapter list news
                    adapter.fragmentListNews.ListViewNews.setAdapter(new ListNewsAdapter(adapter.fragmentListNews.listNews, getActivity()));
                    flagSource = flagCurrentSource;
                    flagCategory = flagCurrentCategory;
                }
                //when change only category
                else {
                    if (flagCategory != flagCurrentCategory) {
                        adapter.fragmentCategories.listCat = adapter.fragmentSource.listSource.get(flagCurrentSource).getSource_categories();
                        adapter.fragmentListNews.listNews = adapter.fragmentCategories.listCat.get(flagCurrentCategory).getListPosters();
                        //update adapter list news
                        adapter.fragmentListNews.ListViewNews.setAdapter(new ListNewsAdapter(adapter.fragmentListNews.listNews, getActivity()));
                        flagCategory = flagCurrentCategory;
                    }
                }
                // viewPager.setCurrentItem(tab.getPosition());
                Toast.makeText(getContext(), "slected", Toast.LENGTH_SHORT).show();
                Log.d("Tab", "slected");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(getContext(), "onTabUnselected", Toast.LENGTH_SHORT).show();
                Log.d("Tab", "onTabUnselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.getAdapter().notifyDataSetChanged();
                Toast.makeText(getContext(), "onTabReselected", Toast.LENGTH_SHORT).show();
                Log.d("Tab", "onTabReselected");
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setupWithViewPager(viewPager);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabs.getSelectedTabPosition()!=0){
                    viewPager.setCurrentItem(0);
                }
            }
        });
        Log.d("Creat", "Home Page creat");
        return view;
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("restore", "onrestore");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("resume", "ONRESUME");

    }

    @Override
    public void run() {
//        while (true){
//            if(FlagCategorySource.flagSource!=flagSource || FlagCategorySource.flaCategory!=flagCategory){
//                viewPager.setCurrentItem(0);
//            }
//        }
    }
}
