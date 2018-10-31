package com.teemo.xuantruong.android_project.poster;


import android.media.MediaPlayer;

import android.os.AsyncTask;

import android.speech.tts.TextToSpeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.connectJson.Get_apiText_to_speech;
import com.teemo.xuantruong.android_project.connectJson.MyHttpHandler;
import com.teemo.xuantruong.android_project.entity.News;
import com.teemo.xuantruong.android_project.connectJson.ReadJsonDB;
import com.teemo.xuantruong.android_project.entity.Poster_entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Poster extends AppCompatActivity implements View.OnClickListener{
    private String TAG = Poster.class.getSimpleName();
    private ImageView imageView1;
    private ImageButton imageBut;
    private MyHttpHandler myhttp = new MyHttpHandler();
    private TextView name, content;
    private Poster_entity poster_entity =  new Poster_entity();
    private Get_apiText_to_speech getapiTexttospeech = new Get_apiText_to_speech();
    private  MediaPlayer mediaPlayer;
    private  String text1;
    private ArrayList<News> selectListPost = new ArrayList<>() ;
    boolean imagePlay = true;

    private ProfilePictureView profile;
    private  String informationImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data
        Bundle bdl = getIntent().getExtras();
        selectListPost = (ArrayList<News>) bdl.getSerializable("Data");
        int realPost = bdl.getInt("position");


        setContentView(R.layout.activity_poster);
        MyAsyncTask myAsyncTask = new  MyAsyncTask();
        myAsyncTask.execute();
        imageBut= (ImageButton) findViewById(R.id.imageBut);

        imageView1 =(ImageView) findViewById(R.id.image1);
        // read image with base64
//        Bitmap  bm= StringToBitMap(informationImage);
//        imageView1.setImageBitmap(bm);
//        imageView1.setMaxHeight(100);
//        imageView1.setMaxWidth(100);

        name= (TextView) findViewById(R.id.name);

        name.setText(""+selectListPost.get(realPost).getTitle());


        content= (TextView) findViewById(R.id.content);
        content.setText(""+selectListPost.get(realPost).getConttent());
        mediaPlayer = new MediaPlayer();

        imageBut.setOnClickListener(this);

        Intent intent = getIntent();
        username = (TextView) findViewById(R.id.name_user);
        username.setText(intent.getStringExtra("name"));
        profile=  (ProfilePictureView) findViewById(R.id.picture);
        profile.setProfileId(intent.getStringExtra("id"));

    }

    @Override
    protected void onPause() {
        mediaPlayer.pause();
        mediaPlayer.stop();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        mediaPlayer.start();
        super.onRestart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageBut) {

            // play mediaplayer

            if(imagePlay){
                imageBut.setBackgroundResource(R.drawable.ic_speaker_red);
            }else {
                imageBut.setBackgroundResource(R.drawable.ic_speaker_black_24dp);
            }
            imagePlay=!imagePlay;
            if (mediaPlayer.isPlaying()) {
                imageBut.setBackgroundResource(R.drawable.chat);
                mediaPlayer.pause();
            }
            // pause mediaplayer
            else {
                imageBut.setBackgroundResource(R.drawable.speech_bubble);
                mediaPlayer.start();

            }

        }
    }

    // convert String to bitmap
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    // json ->  object : show poster
    class  MyAsyncTask extends AsyncTask<Void,Void,Void>{
            @Override
        protected Void doInBackground(Void... voids) {
                try {
                    // get json poster
                    readJson();
                    String txt = name.getText().toString()+ " "+content.getText().toString();
                    // get link file mp3
                    String texturl = getapiTexttospeech.apiChangetexttomp3(txt);
                        // get json as json object
                    JSONObject jsonObject1 = new JSONObject(texturl);
                    // get elemet json object
                    text1 = jsonObject1.getString("async");

                    // set mediaplayer in source
                    //read mediaplayer online
                    mediaPlayer.setDataSource(text1);
                    mediaPlayer.prepare();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
        }
    }
    // read json in activity
    private void readJson() throws  JSONException {
        // json test
        String url1 ="https://api.androidhive.info/contacts/";
            // get jproject
//        String url1 = "http://192.168.0.104:8082/select_element.php?limit=10002";
        String jsonStr = myhttp.makeServiceCall(url1);
//         json project
        if (jsonStr != null) {
            JSONArray aray = null;
            try {
                // test
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray jsonArray= jsonObject.getJSONArray("contacts");
                poster_entity.setName_poster(jsonArray.getJSONObject(3).get("email").toString());
                poster_entity.setContent_poster(jsonArray.getJSONObject(3).get("gender").toString());
                // get json ijnproject
//                aray = new JSONArray(jsonStr);
//                for (int i = 0; i < aray.length(); i++) {
//                    JSONObject object = aray.getJSONObject(i);
//                    String phone = object.getString("phone");
//                    String name = object.getString("name");
//                    poster_entity.setName_poster(phone);
//                    poster_entity.setContent_poster(name);
//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
