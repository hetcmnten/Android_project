package com.teemo.xuantruong.android_project.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.CustomItemClickListener;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewAdapter;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewSourceAdapter;
import com.teemo.xuantruong.android_project.connectJson.ReadJsonDB;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.entity.FlagCategorySource;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.entity.Source;
import com.teemo.xuantruong.android_project.poster.Poster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentSources extends Fragment implements View.OnClickListener {
    View view;
    public ArrayList<Source> listSource;
    public FragmentSources() {

//        SetDataCategories();
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_source, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_source);
        RecyclerViewSourceAdapter recyclerViewAdapter = new RecyclerViewSourceAdapter(getActivity(), listSource, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FlagCategorySource.flagSource = position;
                FlagCategorySource.flaCategory = 0;
              Toast.makeText(getActivity(), "Source" + position, Toast.LENGTH_LONG).show();
                ((FragmentHomePage)getParentFragment()).viewPager.setCurrentItem(0);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }


    private ArrayList<Poster_entity> readJson() throws Exception {

        ArrayList<Poster_entity> list = new ArrayList<>();
        try {
            ReadJsonDB readJsonDB = new ReadJsonDB();
            // fic id = 21141 need to fix
            String json = readJsonDB.ConnectJson();
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // get json object
                // newsType
                String  type =jsonObject.getString("newsType");
                // tittle
                String  title =jsonObject.getString("title");
                //category
                String  category =jsonObject.getString("category");
                // date
                String  time =jsonObject.getString("publishTime");
                // content
                String  content =jsonObject.getString("content");
                // String image
                String  image =jsonObject.getString("imgConverted");
                //author
                String  author =jsonObject.getString("author");

                Poster_entity poster = new Poster_entity(type,title,category,content,image,author,time);
                list.add(poster);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public void SetDataCategories(){
        listSource = new ArrayList<Source>();
        ArrayList<Poster_entity> listroot= new ArrayList<>();
        try {
            listroot =readJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // genk
        ArrayList<Poster_entity> listPosters1 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters2 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters3 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters4 = new ArrayList<>();
        ArrayList<Category> listgenk = new ArrayList<>();
        listgenk.add(new Category("Đồ chơi số","bcd",listPosters1));
        listgenk.add(new Category("Sống","bcd",listPosters2));
        listgenk.add(new Category("Mobile","bcd",listPosters3));
        listgenk.add(new Category("Tin ICT","bcd",listPosters4));
        //
        ArrayList<Category> listcafef = new ArrayList<>();
        //
        ArrayList<Category> listkenh14 = new ArrayList<>();


        for (Category category: listgenk
             ) {
            for (Poster_entity pos: listroot
                 ) {
                if(category.getTitle().equals(pos.getCategory_poster())){
                    listPosters1.add(pos);
                }
            }

        }
        //Fake data source
        listSource.add(new Source("CafeF","Abc",listgenk));
     //   listSource.add(new Source("VietnamNet","Abc",listcafef));
//        listSource.add(new Source("Kenh14","Abc",listkenh14));


//        //Fake data lisposters
//        listPosters1.add(new Poster_entity(R.drawable.download+"","Người đàn ông nuôi hàng trăm con rồng Nam Mỹ ở Sài gòn","11h Trước",
//                "Trong số các nạn nhân bị thương, có 2 người bị thương nặng đang được " +
//                "cấp cứu tại Bệnh viện Nhân dân Gia Định. Đứng bên ngoài phòng cấp cứu, bà Trần Mỹ Lệ (63 tuổi, quận Bình Thạnh) mắt đỏ hoe, chắp tay cầu nguyện cho con " +
//                "bà là anh H.H.Đ.(42 tuổi) tai qua nạn khỏi. Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia " +
//                "Định “Chạy đến bệnh viện thì y tá nói con tôi bị gãy chân, bị thương nặng ở cổ." +
//                " Khi đó, tôi thấy nó nằm trên băng ca nhưng người lờ đờ lắm. Nó thì thào nói con không biết gì cả”, bà Lệ nói trong lo lắng."));
//        listPosters1.add(new Poster_entity(R.drawable.news+"","Hôm nay tôi buồn-1 ","11h Trước","abc"));
//        listPosters1.add(new Poster_entity(R.drawable.news+"","Cuối tuần đi đâu chơi-1 ","11h Trước","mắt đỏ hoe, chắp tay cầu nguyện cho con"));
//        listPosters1.add(new Poster_entity(R.drawable.news+"","Cao thủ liên minh giải nghệ-1 ","11h Trước","Bệnh viện Nhân dân Gia"));
//
//
//        listPosters2.add(new Poster_entity(R.drawable.news+"","Còn điều gì chưa nói vs nhau-2 ","11h Trước","Người đàn ông nuôi hàng trăm con rồng"));
//        listPosters2.add(new Poster_entity(R.drawable.news+"","Cách tán gái từ đại gia-2 ","11h Trước","bà là anh H.H.Đ.(42 tuổi) tai qua nạn khỏi. Theo bà Lệ,"));
//        listPosters2.add(new Poster_entity(R.drawable.news+"","Bảo vệ đôi mắt của bạn-2 ","11h Trước","Đứng bên ngoài phòng cấp cứu, bà Trần Mỹ Lệ"));
//        listPosters2.add(new Poster_entity(R.drawable.news+"","Bao giờ em mới cưới-2","11h Trước","63 tuổi, quận Bình Thạnh"));
//
//        listPosters3.add(new Poster_entity(R.drawable.news+"","Ánh sang phía sau còn đường đen-3","11h Trước","Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia"));
//        listPosters3.add(new Poster_entity(R.drawable.news+"","Lee sin best mù-3","11h Trước","Theo bà Lệ, rạng sáng nay bà nhận được điện thoại thông báo anh Đ đang được cấp cứu tại Bệnh viện Nhân dân Gia"));
//
//        // Fake data catogeries
//        listgenk.add(new Category("Tin Hot","bcd",listPosters1));
//        listgenk.add(new Category("Kinh Tế","bcd",listPosters2));
//        listgenk.add(new Category("Nhân Văn","bcd",listPosters3));
//
//        listcafef.add(new Category("Sức Khỏe","bcd",listPosters1));
//        listcafef.add(new Category("Tình dục","bcd",listPosters2));
//        listcafef.add(new Category("Đời sống","bcd",listPosters3));
//
//
//        listkenh14.add(new Category("Xe hơi","bcd",listPosters1));
//        listkenh14.add(new Category("Thời sự","bcd",listPosters2));
//        listkenh14.add(new Category("Giải trí","bcd",listPosters3));



    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"fragment source click",Toast.LENGTH_LONG).show();

    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // set data source
                SetDataCategories();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}