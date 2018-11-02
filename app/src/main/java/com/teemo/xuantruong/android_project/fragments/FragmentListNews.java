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
import android.widget.ListView;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.adapters.ListNewsAdapter;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentListNews extends Fragment implements Serializable {
    ArrayList<Poster_entity> listNews = new ArrayList<>();
    ListView ListNews;
    TextView txtTile1;
    ListNewsAdapter news;
    public FragmentListNews(){
        setDataonArrayList();
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
        ListNews = (ListView) view.findViewById(R.id.listNews);
        txtTile1.setText(listNews.get(0).getTitle_poster());
        news = new ListNewsAdapter(listNews,getActivity());
        ListNews.setAdapter(news);
        ListNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int sizePost = listNews.size();
                int realPosition=0;
                ArrayList<Poster_entity> selectListPost = new ArrayList<>();

                if((position+1)%3==1){
                    for (int i = 0; i <3 ; i++) {
                        selectListPost.add(listNews.get(position+i));
                    }
                    realPosition=0;
                }else if((position+1)%3==0){
                    for (int i = 2; i >=0 ; i--) {
                        selectListPost.add(listNews.get(position-i));
                    }
                    realPosition=selectListPost.size()-1;
                }else {
                    int from = position/3;

                    for (int i = from*3; i <(from+1)*3 ; i++) {
                        selectListPost.add(listNews.get(i));
                        if(i==position){
                            realPosition = selectListPost.size()-1;
                        }
                    }
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(), Poster.class);
                intent.putExtra("position", realPosition);
                intent.putExtra("Data",selectListPost);
                startActivity(intent);
            }
        });
        Log.d("Creat","One Creat");
        return  view;
    }

    public void setDataonArrayList(){
        Poster_entity eachNews = new Poster_entity(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","Trong số các nạn nhân bị thương, có 2 người bị thương nặng đang được cấp cứu tại Bệnh viện Nhân dân Gia Định."+
                "Đứng bên ngoài phòng cấp cứu, bà Trần Mỹ Lệ (63 tuổi, quận Bình Thạnh) mắt đỏ hoe, chắp tay cầu nguyện cho con bà là anh H.H.Đ.(42 tuổi) tai qua nạn khỏi." +
                "Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia Định" +
                "“Chạy đến bệnh viện thì y tá nói con tôi bị gãy chân, bị thương nặng ở cổ. Khi đó, tôi thấy nó nằm trên băng ca nhưng người lờ đờ lắm. Nó thì thào nói con không biết gì cả”, bà Lệ nói trong lo lắng.");
        Poster_entity eachNews2 = new Poster_entity(R.drawable.news+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","abc");
        Poster_entity eachNews3 = new Poster_entity(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","cde");
        Poster_entity eachNews4 = new Poster_entity(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","ebf");
        Poster_entity eachNews5 = new Poster_entity(R.drawable.news+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","jdk");
        Poster_entity eachNews6 = new Poster_entity(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","ngf");
        listNews.add(eachNews);
        listNews.add(eachNews2);
        listNews.add(eachNews3);
        listNews.add(eachNews4);
        listNews.add(eachNews5);
        listNews.add(eachNews6);
    }
}
