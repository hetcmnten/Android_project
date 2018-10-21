package com.teemo.xuantruong.android_project.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teemo.xuantruong.android_project.R;

public class FragmentListNews extends Fragment{
    View view;
    public FragmentListNews(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lists_fragment,container,false);
        return  view;
    }

}
