package com.teemo.xuantruong.android_project.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.teemo.xuantruong.android_project.adapters.RecyclerViewAdapter;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.R;

import java.util.ArrayList;

public class FragmentCategories extends Fragment {
    View view;
    ListView ListNews;
    ArrayList<Category> listCat = new ArrayList<Category>();
    public FragmentCategories() {
        SetDataCategories();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_id);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), listCat);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    public void SetDataCategories(){
        listCat.add(new Category("ABCHSDGSDGSG","Abc"));
        listCat.add(new Category("ABCSGAVBASD","Abc"));
        listCat.add(new Category("SAGAXSAGVS","Abc"));
        listCat.add(new Category("AFAFSGFSGSGG","Abc"));
        listCat.add(new Category("ASGASGSGRNGHD","Abc"));
        listCat.add(new Category("DFGXVBGSBAGBA","Abc"));
    }


}
