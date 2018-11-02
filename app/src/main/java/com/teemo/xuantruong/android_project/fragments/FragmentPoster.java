package com.teemo.xuantruong.android_project.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.util.ArrayList;

public class FragmentPoster extends Fragment {

    private Poster_entity poster = new Poster_entity();

    public FragmentPoster() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        TextView txtPosterDate = view.findViewById(R.id.poster_datetime);
        TextView txtPosterTitle = view.findViewById(R.id.poster_title);
        TextView txtPosterContent = view.findViewById(R.id.poster_content);

        //get bundel from view page
        Bundle bundle = this.getArguments();
        poster = (Poster_entity) bundle.getSerializable("poster");

        //set view
        txtPosterTitle.setText(poster.getTitle_poster());
        txtPosterDate.setText(poster.getTime_poster());
        txtPosterContent.setText(poster.getContent_poster());
        return view;
    }

    public Poster_entity getPoster() {
        return poster;
    }

    public void setPoster(Poster_entity poster) {
        this.poster = poster;
    }
}
