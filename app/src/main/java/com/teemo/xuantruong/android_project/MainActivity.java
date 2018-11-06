package com.teemo.xuantruong.android_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teemo.xuantruong.android_project.connectJson.ReadJsonDB;
import com.teemo.xuantruong.android_project.entity.Category;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.entity.Source;
import com.teemo.xuantruong.android_project.fragments.FragmentSources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT= 15000;
    public ArrayList<Source> listSource = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
    }

    private ArrayList<Poster_entity> readJson() throws Exception {

        ArrayList<Poster_entity> list = new ArrayList<>();
        try {
            ReadJsonDB readJsonDB = new ReadJsonDB("kenh14");
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
       // listSource = new ArrayList<Source>();
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
        ArrayList<Poster_entity> listPosters5 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters6 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters7 = new ArrayList<>();
        ArrayList<Poster_entity> listPosters8 = new ArrayList<>();
        ArrayList<Category> listgenk = new ArrayList<>();
        //
        ArrayList<Category> listcafef = new ArrayList<>();
        //
        ArrayList<Category> listkenh14 = new ArrayList<>();
        listkenh14.add(new Category("Star",""+R.drawable.fashion,listPosters2));
        listkenh14.add(new Category("Xã hội",""+R.drawable.xahoi,listPosters1));
        listkenh14.add(new Category("Đời sống",""+R.drawable.life,listPosters3));
        listkenh14.add(new Category("Học đường",""+R.drawable.hocduong,listPosters4));
        listkenh14.add(new Category("Sport",""+R.drawable.sport,listPosters5));
        listkenh14.add(new Category("Thế Giới",""+R.drawable.life,listPosters6));
        listkenh14.add(new Category("Musik",""+R.drawable.music,listPosters7));
        listkenh14.add(new Category("Fashion",""+R.drawable.fashion,listPosters8));

        if(type.equals("kenh14")){
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
        }


        //Fake data source
        listSource.add(new Source("kenh14",""+R.drawable.kenh,listkenh14));
        listSource.add(new Source("cafef",""+R.drawable.cafef,listcafef));
        listSource.add(new Source("genk",""+R.drawable.genk,listgenk));
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // set data source
                SetDataCategories("kenh14");
                FragmentSources.listSource = listSource;
                Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                //homeIntent.putExtra("listSource",listSource);
                startActivity(homeIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            dialog = new ProgressDialog(MainActivity.this);
//            dialog.setMessage("process.....");
//            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            dialog.dismiss();
        }
    }

}
