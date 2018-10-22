package com.teemo.xuantruong.android_project.poster;

import android.app.DownloadManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.connectJson.Get_apiText_to_speech;
import com.teemo.xuantruong.android_project.connectJson.MyHttpHandler;
import com.teemo.xuantruong.android_project.entity.Poster_entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import okhttp3.Request;
import okhttp3.Response;

public class Poster extends AppCompatActivity implements View.OnClickListener{
    private String TAG = Poster.class.getSimpleName();
    private TextToSpeech speak;
    private ImageButton imageBut ;
    private MyHttpHandler myhttp = new MyHttpHandler();
    private TextView name, content;
    private Poster_entity poster_entity =  new Poster_entity();
    private Get_apiText_to_speech getapiTexttospeech = new Get_apiText_to_speech();
    private  MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
        imageBut= (ImageButton) findViewById(R.id.imageBut);
        name= (TextView) findViewById(R.id.name);
        content= (TextView) findViewById(R.id.content);
        mediaPlayer = new MediaPlayer();
        //read mediaplayer online
        imageBut.setOnClickListener(this);
        MyAsyncTask myAsyncTask = new  MyAsyncTask();
        myAsyncTask.execute();
        try {
            mediaPlayer.setDataSource(poster_entity.getContent_poster());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if(view.getId()== R.id.imageBut)
        {
            name.setText(poster_entity.getName_poster());
            content.setText(poster_entity.getContent_poster());


            mediaPlayer.start();
        }
    }

    // json ->  object : show poster
    class  MyAsyncTask extends AsyncTask<Void,Void,Void>{
            @Override
        protected Void doInBackground(Void... voids) {
                try {
                    // get json poster
                    readJson();
                    // get link file mp3
                    String texturl = getapiTexttospeech.apiChangetexttomp3();
                        // get json as json object
                    JSONObject jsonObject1 = new JSONObject(texturl);
                    // get elemet json object
                    String text1 = jsonObject1.getString("async");
                    poster_entity.setContent_poster(text1);
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
