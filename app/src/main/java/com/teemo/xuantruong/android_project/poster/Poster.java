package com.teemo.xuantruong.android_project.poster;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import android.os.AsyncTask;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.adapters.ViewPagePosterAdapter;
import com.teemo.xuantruong.android_project.connectJson.Get_apiText_to_speech;
import com.teemo.xuantruong.android_project.connectJson.MyHttpHandler;
import com.teemo.xuantruong.android_project.connectJson.ReadJsonDB;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.fragments.FragmentPoster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Poster extends AppCompatActivity {
    private String TAG = Poster.class.getSimpleName();
    private ImageView imageView1;
    private ImageButton imageBut;
    private MyHttpHandler myhttp = new MyHttpHandler();
    private TextView name, content, username;
    private Poster_entity poster_entity = new Poster_entity();
    private Get_apiText_to_speech getapiTexttospeech = new Get_apiText_to_speech();
    private MediaPlayer mediaPlayer;
    private String text1;
    private ArrayList<Poster_entity> selectListPost = new ArrayList<>();
    boolean imagePlay = true;
    private ProfilePictureView profile;
    private String informationImage;
    private Bitmap bm;

    private ProfilePictureView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        //get data
        Bundle bdl = getIntent().getExtras();
        selectListPost = (ArrayList<Poster_entity>) bdl.getSerializable("Data");
        int realPost = bdl.getInt("position");

        // view page
        final ViewPager pager = (ViewPager) findViewById(R.id.poster_viewpager_id);
        ViewPagePosterAdapter adapter = new ViewPagePosterAdapter(getSupportFragmentManager(), selectListPost);
        pager.setAdapter(adapter);
        pager.setCurrentItem(realPost);

        //Tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.poster_tab_id);
        tabLayout.setupWithViewPager(pager, true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
