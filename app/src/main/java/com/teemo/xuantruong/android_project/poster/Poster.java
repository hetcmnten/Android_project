package com.teemo.xuantruong.android_project.poster;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.connectJson.MyHttpHandler;
import com.teemo.xuantruong.android_project.entity.Poster_entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class Poster extends AppCompatActivity implements View.OnClickListener{
    private String TAG = Poster.class.getSimpleName();
    private TextToSpeech speak;
    private int  result;
    private EditText edit;
    private String text;
    private ImageButton imageBut ;
    private String url="";
    private MyHttpHandler myhttp = new MyHttpHandler();
    private TextView name, content;
    private Poster_entity poster_entity =  new Poster_entity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
        imageBut= (ImageButton) findViewById(R.id.imageBut);
        edit= (EditText) findViewById(R.id.edit);
        name= (TextView) findViewById(R.id.name);
        content= (TextView) findViewById(R.id.content);
        MediaPlayer mediaPlayer= new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        speak= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS)
                {
                    result= speak.setLanguage(Locale.UK);
                }
                else {
                    Toast.makeText(getApplicationContext(),"feature not support  in your device",Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageBut.setOnClickListener(this);
        MyAsyncTask myAsyncTask = new  MyAsyncTask();
        myAsyncTask.execute();

    }


    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.imageBut)
        {
            text= edit.getText().toString().trim();
            speak.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            name.setText(poster_entity.getName_poster());
            content.setText(poster_entity.getContent_poster());
        }
    }

    @Override
    protected void onDestroy() {
        if (speak !=null)
        {
            speak.stop();
            speak.shutdown();
        }
        super.onDestroy();
    }

    // json ->  object
    class  MyAsyncTask extends AsyncTask<Void,Void,Void>{


            @Override
        protected Void doInBackground(Void... voids) {

                String url1 = "http://172.25.11.198:8082/select_element.php?limit=10002";
                String jsonStr= myhttp.makeServiceCall(url1);

                if (jsonStr != null) {
                    try {
                        JSONArray aray = new JSONArray(jsonStr);
                        for (int i = 0; i < aray.length(); i++) {
                            JSONObject object = aray.getJSONObject(i);
                            String id = object.getString("phone");
                            String name= object.getString("name");
                            poster_entity.setName_poster(id);
                            poster_entity.setContent_poster(name);
                        }
                } catch ( final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
                return null;
        }
    }

}
