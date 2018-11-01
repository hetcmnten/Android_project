package com.teemo.xuantruong.android_project;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity {
   Spinner spinner;
   String[] speaker ={"Chị Google","Giọng Nam","Giọng Nữ"};
   ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        imageView = (ImageView) findViewById(R.id.imagesetting);
        imageView.setImageResource(R.drawable.setting);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,speaker);
        listAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(listAdapter);
    }
}
