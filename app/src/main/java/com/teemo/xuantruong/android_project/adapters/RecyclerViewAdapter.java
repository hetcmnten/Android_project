package com.teemo.xuantruong.android_project.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.entity.FlagCategorySource;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    public ArrayList<Category> listCate;
    CustomItemClickListener listener;
    ArrayList<View> itemViewList = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext, ArrayList<Category> listCate,CustomItemClickListener listener ) {
        this.mContext = mContext;
        this.listCate = listCate;
        this.listener =listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.cardviewcategory,parent,false);
        itemViewList.add(v);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvtextNew.setText(listCate.get(position).getTitle());
        holder.imageNew.setImageResource(Integer.parseInt(listCate.get(position).getImageName()));
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for(View tempItemView : itemViewList) {
//                    /** navigate through all the itemViews and change color
//                     of selected view to colorSelected and rest of the views to colorDefault **/
//                    if(itemViewList.get(holder.getAdapterPosition()) == tempItemView) {
//                        tempItemView.setBackgroundColor(Color.GRAY);
//                    }
//                    else{
//                        tempItemView.setBackgroundColor(Color.WHITE);
//                    }
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listCate.size();
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView tvtextNew;
        ImageView imageNew;
        public CardView cardView;
        public MyViewHolder(View v){
            super(v);
            tvtextNew = (TextView) v.findViewById(R.id.txtTitle);
            imageNew = (ImageView) v.findViewById(R.id.txtImage);
            cardView = (CardView) v.findViewById(R.id.cardview_idd);

        }
    }
}

