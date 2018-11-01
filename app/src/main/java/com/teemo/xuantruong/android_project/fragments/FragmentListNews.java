package com.teemo.xuantruong.android_project.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.teemo.xuantruong.android_project.entity.News;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentListNews extends Fragment implements Serializable {
    View view;
    ArrayList<News> listNews = new ArrayList<>();
    ListView ListNews;
    TextView txtTile1;
    NewsAdapter news;
    public FragmentListNews(){
        news = new NewsAdapter();
        setDataonArrayList();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listpost,container,false);
        txtTile1 = (TextView) view.findViewById(R.id.txtTitle1);
        ListNews = (ListView) view.findViewById(R.id.listNews);
        txtTile1.setText(listNews.get(0).getTitle());
        ListNews.setAdapter(news);
        ListNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String items = parent.getItemAtPosition(position).toString();
                int sizePost = listNews.size();
                int realPosition=0;
                ArrayList<News> selectListPost = new ArrayList<>();

                if((position+1)%3==1){
                    for (int i = 0; i <3 ; i++) {
                        selectListPost.add(listNews.get(position+i));
                    }
                    realPosition=0;
                }else if((position+1)%3==0){
                    for (int i = 0; i <3 ; i++) {
                        selectListPost.add(listNews.get(position-i));
                    }
                    realPosition=selectListPost.size()-1;
                }else {
                    int from = sizePost/position;

                    for (int i = from*3; i <(from+1)*3 ; i++) {
                        selectListPost.add(listNews.get(from));
                        if(i==position){
                            realPosition = selectListPost.size()-1;
                        }
                    }
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(), Poster.class);
                intent.getIntExtra("position", realPosition);
                intent.putExtra("Data",selectListPost);
                startActivity(intent);
            }
        });
        Log.d("Creat","One Creat");
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        txtTile1 = (TextView) view.findViewById(R.id.txtTitle1);
//        setDataonArrayList();
//        ListNews = (ListView) view.findViewById(R.id.listNews);
//        txtTile1.setText(listNews.get(0).getTitle());
//        NewsAdapter news = new NewsAdapter();
//        ListNews.setAdapter(news);
        Log.d("Restore","One Restore");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("Distroy","on destroy");
    }


    public void setDataonArrayList(){
        News eachNews = new News(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước","Trong số các nạn nhân bị thương, có 2 người bị thương nặng đang được cấp cứu tại Bệnh viện Nhân dân Gia Định."+
                "Đứng bên ngoài phòng cấp cứu, bà Trần Mỹ Lệ (63 tuổi, quận Bình Thạnh) mắt đỏ hoe, chắp tay cầu nguyện cho con bà là anh H.H.Đ.(42 tuổi) tai qua nạn khỏi." +

                "Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia Định" +

                "“Chạy đến bệnh viện thì y tá nói con tôi bị gãy chân, bị thương nặng ở cổ. Khi đó, tôi thấy nó nằm trên băng ca nhưng người lờ đờ lắm. Nó thì thào nói con không biết gì cả”, bà Lệ nói trong lo lắng.");
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
