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
import android.widget.Toast;

import com.teemo.xuantruong.android_project.adapters.CustomItemClickListener;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewAdapter;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.entity.FlagCategorySource;

import java.util.ArrayList;

public class FragmentCategories extends Fragment {
    View view;
    ListView ListNews;
    public RecyclerView recyclerView;
    public ArrayList<Category> listCat = new ArrayList<Category>();
    public RecyclerViewAdapter recyclerViewAdapter;
    public FragmentCategories() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_id);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), listCat, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FlagCategorySource.flaCategory=position;
                ((FragmentHomePage)getParentFragment()).viewPager.setCurrentItem(0);
                Toast.makeText(getActivity(),"Categories"+position,Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

}
