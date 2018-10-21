package com.teemo.xuantruong.android_project.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.Entity.Category;
import com.teemo.xuantruong.android_project.Entity.News;
import com.teemo.xuantruong.android_project.R;

import java.util.ArrayList;

public class FragmentCategories extends Fragment {
    View view;
    ListView ListNews;
    ArrayList<Category> listCat = new ArrayList<Category>();
    public FragmentCategories() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category_fragment,container,false);
//        SetDataCategories();
//        ListNews = (ListView) view.findViewById(R.id.recycler_id);
//        RecyclerViewAdapter news = new RecyclerViewAdapter();
//        ListNews.setAdapter(news);
        return view;
    }

//    public void SetDataCategories(){
//        listCat.add(new Category("ABCHSDGSDGSG","Abc"));
//        listCat.add(new Category("ABCSGAVBASD","Abc"));
//        listCat.add(new Category("SAGAXSAGVS","Abc"));
//        listCat.add(new Category("AFAFSGFSGSGG","Abc"));
//        listCat.add(new Category("ASGASGSGRNGHD","Abc"));
//        listCat.add(new Category("DFGXVBGSBAGBA","Abc"));
//    }
//
//    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
//
//        public static class MyViewHolder{
//
//        }
//
//
//        private Context mContext;
//        private ArrayList<Category> listCate;
//
//        @Override
//        public int getCount() {
//            return listCat.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return listCat.get(i);
//        }
//
//        @NonNull
//        @Override
//        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return null;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            View itemView = view;
//            Category selectedCat = listCat.get(i);
//            itemView = (itemView == null) ? getLayoutInflater().inflate(R.layout.cardviewcategory,null) : itemView;
//            ImageView imageView = (ImageView) itemView.findViewById(R.id.txtImage);
//            TextView txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
//            imageView.setImageResource(R.drawable.download);
//            txtTitle.setText(selectedCat.getTitle());
//            return itemView;
//        }
//    }

}
