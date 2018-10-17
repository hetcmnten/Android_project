package com.teemo.xuantruong.android_project.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teemo.xuantruong.android_project.R;

public class FragmentCategories extends Fragment {
    View view;
    public FragmentCategories() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category_fragment,container,false);
        return view;
    }
}
