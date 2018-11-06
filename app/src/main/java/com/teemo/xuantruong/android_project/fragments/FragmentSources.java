package com.teemo.xuantruong.android_project.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.CustomItemClickListener;
import com.teemo.xuantruong.android_project.adapters.RecyclerViewSourceAdapter;
import com.teemo.xuantruong.android_project.connectJson.ReadJsonDB;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.entity.FlagCategorySource;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.entity.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FragmentSources extends Fragment {
    View view;
    public static ArrayList<Source> listSource ;
    //public ArrayList<Source> listSource = new ArrayList<>();
    public FragmentSources() {

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
                MyAsyncTask myAsyncTask = new MyAsyncTask(listSource.get(position).getSource_title());
                myAsyncTask.execute();
                CardView cardView = v.findViewById(R.id.cardview_idd);


            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }


    private ArrayList<Poster_entity> readJson(String stype) throws Exception {

        ArrayList<Poster_entity> list = new ArrayList<>();
        try {
            ReadJsonDB readJsonDB = new ReadJsonDB(stype);
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

    public void SetDataCategories(String type){
        //listSource = new ArrayList<Source>();
        ArrayList<Poster_entity> listroot= new ArrayList<>();
        try {
            listroot =readJson(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // listposter
        ArrayList<Poster_entity> listPosters1 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters2 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters3 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters4 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters5 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters6 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters7 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters8 = new ArrayList<>();

        //kenh14
        if(type.equals("kenh14")){
            ArrayList<Category> listkenh14 = new ArrayList<>();
            listkenh14.add(new Category("Star",""+R.drawable.fashion,listPosters2));
            listkenh14.add(new Category("Xã hội",""+R.drawable.xahoi,listPosters1));
            listkenh14.add(new Category("Đời sống",""+R.drawable.life,listPosters3));
            listkenh14.add(new Category("Học đường",""+R.drawable.hocduong,listPosters4));
            listkenh14.add(new Category("Sport",""+R.drawable.sport,listPosters5));
            listkenh14.add(new Category("Thế Giới",""+R.drawable.life,listPosters6));
            listkenh14.add(new Category("Musik",""+R.drawable.music,listPosters7));
            listkenh14.add(new Category("Fashion",""+R.drawable.fashion,listPosters8));
            for (Poster_entity pos:listroot
                    ) {
                if(listkenh14.get(0).getTitle().equals(pos.getCategory_poster())){
                    listPosters2.add(pos);
                }
                if(listkenh14.get(1).getTitle().equals(pos.getCategory_poster())){
                    listPosters1.add(pos);
                }
                if(listkenh14.get(2).getTitle().equals(pos.getCategory_poster())){
                    listPosters3.add(pos);
                }if(listkenh14.get(3).getTitle().equals(pos.getCategory_poster())){
                    listPosters4.add(pos);
                }if(listkenh14.get(4).getTitle().equals(pos.getCategory_poster())){
                    listPosters5.add(pos);
                }if(listkenh14.get(5).getTitle().equals(pos.getCategory_poster())){
                    listPosters6.add(pos);
                }if(listkenh14.get(6).getTitle().equals(pos.getCategory_poster())){
                    listPosters7.add(pos);
                }if(listkenh14.get(7).getTitle().equals(pos.getCategory_poster())){
                    listPosters8.add(pos);
                }
            }
            listSource.get(0).setSource_categories(listkenh14);
        }

        // cafef
        if(type.equals("cafef")){
            ArrayList<Category> listcafef = new ArrayList<>();
            listcafef.add(new Category("Sống",""+R.drawable.life,listPosters1));
            listcafef.add(new Category("Thị trường",""+R.drawable.thitruong,listPosters2));
            listcafef.add(new Category("Tài chính - ngân hàng",""+R.drawable.tcnh,listPosters3));
            listcafef.add(new Category("Kinh tế vĩ mô - Đầu tư",""+R.drawable.ktvm,listPosters4));
            listcafef.add(new Category("Thời sự",""+R.drawable.thoisu,listPosters5));
            listcafef.add(new Category("Bất động sản",""+R.drawable.bds,listPosters6));
            listcafef.add(new Category("Doanh nghiệp",""+R.drawable.doanhnghiep,listPosters7));
            listcafef.add(new Category("Thị trường chứng khoán",""+R.drawable.thitruong,listPosters8));
            for (Poster_entity pos:listroot
                    ) {
                if(listcafef.get(0).getTitle().equals(pos.getCategory_poster())){
                    listPosters1.add(pos);
                }
                if(listcafef.get(1).getTitle().equals(pos.getCategory_poster())){
                    listPosters2.add(pos);
                }
                if(listcafef.get(2).getTitle().equals(pos.getCategory_poster())){
                    listPosters3.add(pos);
                }if(listcafef.get(3).getTitle().equals(pos.getCategory_poster())){
                    listPosters4.add(pos);
                }if(listcafef.get(4).getTitle().equals(pos.getCategory_poster())){
                    listPosters5.add(pos);
                }if(listcafef.get(5).getTitle().equals(pos.getCategory_poster())){
                    listPosters6.add(pos);
                }if(listcafef.get(6).getTitle().equals(pos.getCategory_poster())){
                    listPosters7.add(pos);
                }if(listcafef.get(7).getTitle().equals(pos.getCategory_poster())){
                    listPosters8.add(pos);
                }
            }
            listSource.get(1).setSource_categories(listcafef);
        }
        // genk
        if(type.equals("genk")){
            ArrayList<Category> listgenk = new ArrayList<>();
            listgenk.add(new Category("Đồ chơi số",""+R.drawable.toys,listPosters1));
            listgenk.add(new Category("Sống",""+R.drawable.life,listPosters2));
            listgenk.add(new Category("Mobile",""+R.drawable.mobile,listPosters3));
            listgenk.add(new Category("Tin ICT",""+R.drawable.itc,listPosters4));
            for (Poster_entity pos: listroot) {
                if(listgenk.get(0).getTitle().equals(pos.getCategory_poster())){
                    listPosters1.add(pos);
                }
                if(listgenk.get(1).getTitle().equals(pos.getCategory_poster())){
                    listPosters2.add(pos);
                }
                if(listgenk.get(2).getTitle().equals(pos.getCategory_poster())){
                    listPosters3.add(pos);
                }if(listgenk.get(3).getTitle().equals(pos.getCategory_poster())){
                    listPosters4.add(pos);
                }
            }
            listSource.get(2).setSource_categories(listgenk);
        }



        //Fake data source
    }



    class MyAsyncTask extends AsyncTask<Void, Void, Void> {
       private ProgressDialog dialog;
       private String type;
       public  MyAsyncTask(String type){
           this.type=type;
       }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // set data source
                SetDataCategories(type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("process.....");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            ((FragmentHomePage)getParentFragment()).viewPager.setCurrentItem(0);
        }
    }
}