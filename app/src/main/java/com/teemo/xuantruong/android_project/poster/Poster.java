package com.teemo.xuantruong.android_project.poster;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teemo.xuantruong.android_project.R;

public class Poster extends AppCompatActivity {
    TextToSpeech speak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
    }
}
