package com.teemo.xuantruong.android_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.entity.News;

import java.util.ArrayList;

public class ListNewsAdapter extends BaseAdapter {
    private ArrayList<News> listNews = new ArrayList<>();
    Context context;

    public ListNewsAdapter(ArrayList<News> listNews, Context context) {
        this.listNews = listNews;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listNews.size();
    }

    @Override
    public Object getItem(int i) {
        return listNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = view;
        News selectedNews = listNews.get(i);
        itemView = (itemView == null) ? inflater.inflate(R.layout.customlayout,null) : itemView;
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        TextView txtTitle = (TextView)itemView.findViewById(R.id.tvTitle);
        TextView txtDescription = (TextView)itemView.findViewById(R.id.tvTime);
        imageView.setImageResource(R.drawable.news);
        txtTitle.setText(selectedNews.getTitle());
        txtDescription.setText(selectedNews.getTime());
        return itemView;
    }
}