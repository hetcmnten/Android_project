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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.adapters.ConvertBase64;
import com.teemo.xuantruong.android_project.adapters.ListNewsAdapter;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentListNews extends Fragment implements Serializable {
    public ArrayList<Poster_entity> listNews = new ArrayList<>();
    public ListView ListViewNews;
    TextView txtTile1;
    ImageButton imgHeader;
    public ListNewsAdapter news;
    public FragmentListNews(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listpost,container,false);
        txtTile1 = (TextView) view.findViewById(R.id.txtTitle1);
        ListViewNews = (ListView) view.findViewById(R.id.listNews);
        txtTile1.setText(listNews.get(0).getTitle_poster().substring(0,90)+"...");
        ConvertBase64 convertBase64 = new ConvertBase64();
        imgHeader = view.findViewById(R.id.btnImage);
        imgHeader.setImageBitmap(convertBase64.StringToBitMap(listNews.get(0).getImage_poster()));
        news = new ListNewsAdapter(listNews,getActivity());
        ListViewNews.setAdapter(news);
        ListViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int sizePost = listNews.size();
                int realPosition=0;
                ArrayList<Poster_entity> selectListPost = new ArrayList<>();

                if((position)%3==0){
                    for (int i = position; i <position+3 ; i++) {
                        if(i>sizePost)
                            break;
                        selectListPost.add(listNews.get(i));
                    }
                    realPosition=0;
                }else if((position)%3==2){
                    for (int i = position-2; i <=position ; i++) {
                        selectListPost.add(listNews.get(i));
                    }
                    realPosition=2;
                }else {
                    int from = position/3;

                    for (int i = from*3; i <(from+1)*3 ; i++) {
                        if(i>sizePost)
                            break;
                        selectListPost.add(listNews.get(i));
                    }
                    realPosition=1;
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(), Poster.class);
                intent.putExtra("position", realPosition);
                Poster.selectListPost = selectListPost;
                startActivity(intent);
            }
        });
        Log.d("Creat","List New");
        return  view;
    }

}
