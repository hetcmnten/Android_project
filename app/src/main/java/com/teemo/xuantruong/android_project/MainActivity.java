package com.teemo.xuantruong.android_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
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
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();

//                finish();
//            }
//        },SPLASH_TIME_OUT);
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



            for (Poster_entity pos: listroot
                    ) {
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


        //Fake data source
        listSource.add(new Source("CafeF","Abc",listgenk));
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // set data source
                SetDataCategories();
                FragmentSources.FirstSource = listSource;
                Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                //homeIntent.putExtra("FirstSource",listSource);
                startActivity(homeIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("process.....");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }

}
