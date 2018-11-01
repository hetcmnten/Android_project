package com.teemo.xuantruong.android_project.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.entity.News;
import com.teemo.xuantruong.android_project.R;

import java.util.ArrayList;

public class FragmentListNews extends Fragment{
    View view;
    ArrayList<News> listNews = new ArrayList<>();
    ListView ListNews;
    TextView txtTile1;
    public FragmentListNews(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listpost,container,false);
        txtTile1 = (TextView) view.findViewById(R.id.txtTitle1);
        setDataonArrayList();
        ListNews = (ListView) view.findViewById(R.id.listNews);
        txtTile1.setText(listNews.get(0).getTitle());
        NewsAdapter news = new NewsAdapter();
        ListNews.setAdapter(news);
        Log.e("Creat","One Creat");
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        txtTile1 = (TextView) view.findViewById(R.id.txtTitle1);
        setDataonArrayList();
        ListNews = (ListView) view.findViewById(R.id.listNews);
        txtTile1.setText(listNews.get(0).getTitle());
        NewsAdapter news = new NewsAdapter();
        ListNews.setAdapter(news);
        Log.e("Restore","One Restore");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Distroy","on destroy");
    }


    public void setDataonArrayList(){
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

    class NewsAdapter extends BaseAdapter {
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
            View itemView = view;
            News selectedNews = listNews.get(i);
                itemView = (itemView == null) ? getLayoutInflater().inflate(R.layout.customlayout,null) : itemView;
                ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
                TextView txtTitle = (TextView)itemView.findViewById(R.id.tvTitle);
                TextView txtDescription = (TextView)itemView.findViewById(R.id.tvTime);
                imageView.setImageResource(R.drawable.news);
                txtTitle.setText(selectedNews.getTitle());
                txtDescription.setText(selectedNews.getTime());
            return itemView;
        }
    }
}
