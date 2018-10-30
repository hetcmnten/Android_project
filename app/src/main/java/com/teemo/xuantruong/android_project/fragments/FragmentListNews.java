package com.teemo.xuantruong.android_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.ListNewsAdapter;
import com.teemo.xuantruong.android_project.entity.News;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.util.ArrayList;

public class FragmentListNews extends Fragment{
    View view;
    ArrayList<News> listNews;
    ListView ListNews;
    TextView txtTile1;
    int i=0;
    public FragmentListNews(){
        setDataonArrayList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listpost, container, false);
        txtTile1 = (TextView) view.findViewById(R.id.txtTitle1);
        ListNews = (ListView) view.findViewById(R.id.listNews);
        txtTile1.setText(listNews.get(0).getTitle());
        ListNewsAdapter listNewsAdapter = new ListNewsAdapter(listNews, getContext());
        ListNews.setAdapter(listNewsAdapter);
        ListNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(getContext(), Poster.class);
                startActivity(in);
            }
        });
        return view;
    }

    public void setDataonArrayList(){
        listNews = new ArrayList<>();
        News eachNews = new News(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước");
        News eachNews2 = new News(R.drawable.news+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước");
        News eachNews3 = new News(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước");
        News eachNews4 = new News(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước");
        News eachNews5 = new News(R.drawable.news+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước");
        News eachNews6 = new News(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước");
        listNews.add(eachNews);
        listNews.add(eachNews2);
        listNews.add(eachNews3);
        listNews.add(eachNews4);
        listNews.add(eachNews5);
        listNews.add(eachNews6);
    }


}
