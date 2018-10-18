package com.teemo.xuantruong.android_project.poster;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.teemo.xuantruong.android_project.R;

import java.util.Locale;

public class Poster extends AppCompatActivity implements View.OnClickListener{
    TextToSpeech speak;
    int  result;
    EditText edit;
    String text;
    ImageButton imageBut ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
        imageBut= (ImageButton) findViewById(R.id.imageBut);
        edit= (EditText) findViewById(R.id.edit);

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
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.imageBut)
        {
            text= edit.getText().toString().trim();
            speak.speak(text,TextToSpeech.QUEUE_FLUSH,null);

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
}
