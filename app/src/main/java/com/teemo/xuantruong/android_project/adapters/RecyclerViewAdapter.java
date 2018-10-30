package com.teemo.xuantruong.android_project.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.Entity.Category;
import com.teemo.xuantruong.android_project.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<com.teemo.xuantruong.android_project.Entity.Category> listCate;

    public RecyclerViewAdapter(Context mContext, ArrayList<Category> listCate) {
        this.mContext = mContext;
        this.listCate = listCate;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.cardviewcategory,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvtextNew.setText(listCate.get(position).getTitle());
//        holder.imageNew.setImageResource(listCate.get(position).getImageName());
        holder.imageNew.setImageResource(R.drawable.download);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"OK",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCate.size();
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View itemView = view;
//        com.teemo.xuantruong.android_project.Entity.Category selectedCat = listCat.get(i);
//        itemView = (itemView == null) ? getLayoutInflater().inflate(R.layout.cardviewcategory,null) : itemView;
//        ImageView imageView = (ImageView) itemView.findViewById(R.id.txtImage);
//        TextView txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
//        imageView.setImageResource(R.drawable.download);
//        txtTitle.setText(selectedCat.getTitle());
//        return itemView;
//    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView tvtextNew;
        ImageView imageNew;
        CardView cardView;
        public MyViewHolder(View v){
            super(v);
            tvtextNew = (TextView) v.findViewById(R.id.txtTitle);
            imageNew = (ImageView) v.findViewById(R.id.txtImage);
            cardView = (CardView) v.findViewById(R.id.cardview_idd);

        }
    }
}

