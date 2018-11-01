package com.teemo.xuantruong.android_project.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewAdapter;
import com.teemo.xuantruong.android_project.entity.Category;

import java.util.ArrayList;

public class FragmentSources extends Fragment {
    View view;
    ArrayList<Category> listCat;
    public FragmentSources() {
        SetDataCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_source, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_source);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), listCat);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    public void SetDataCategories(){
        listCat = new ArrayList<Category>();
        listCat.add(new Category("CafeF","Abc"));
        listCat.add(new Category("VietnamNet","Abc"));
        listCat.add(new Category("Kenh14","Abc"));
    }

}