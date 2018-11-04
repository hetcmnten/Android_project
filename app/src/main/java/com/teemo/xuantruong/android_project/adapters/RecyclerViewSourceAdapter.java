package com.teemo.xuantruong.android_project.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.entity.Source;

import java.util.ArrayList;

public class RecyclerViewSourceAdapter extends RecyclerView.Adapter<RecyclerViewSourceAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Source> listSources;
    CustomItemClickListener listener;

    public RecyclerViewSourceAdapter(Context mContext, ArrayList<Source> listSources,CustomItemClickListener listener) {
        this.mContext = mContext;
        this.listSources = listSources;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.cardviewcategory, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvtextNew.setText(listSources.get(position).getSource_title());
        holder.imageNew.setImageResource(R.drawable.download);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FlagCategorySource.flagSource = position;
//                FlagCategorySource.flaCategory = 0;
//                Toast.makeText(mContext, "Source" + position, Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listSources.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvtextNew;
        ImageView imageNew;
        public CardView cardView;
        public MyViewHolder(View v) {
            super(v);
            tvtextNew = (TextView) v.findViewById(R.id.txtTitle);
            imageNew = (ImageView) v.findViewById(R.id.txtImage);
            cardView = (CardView) v.findViewById(R.id.cardview_idd);
        }
    }
}
