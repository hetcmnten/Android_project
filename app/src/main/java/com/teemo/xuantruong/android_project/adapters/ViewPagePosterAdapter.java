package com.teemo.xuantruong.android_project.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.fragments.FragmentPoster;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.util.ArrayList;

public class ViewPagePosterAdapter extends FragmentPagerAdapter {
    //private ArrayList<Poster_entity> listPosters = new ArrayList<>();
    public ViewPagePosterAdapter(FragmentManager fm,ArrayList<Poster_entity> listPosters){
        super(fm);
        //this.listPosters=listPosters;

    }
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        FragmentPoster posterFragment = new FragmentPoster();
        bundle.putInt("positionPoster",position);
        posterFragment.setArguments(bundle);
        return posterFragment;
    }

    @Override
    public int getCount() {
        return Poster.selectListPost.size();
    }
}
