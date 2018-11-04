package com.teemo.xuantruong.android_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
   Spinner spinner;
    ArrayAdapter<String> listAdapter;
    Switch choicespeaker;
   String[] speaker ={"leminh","hatieumai","ngoclam"};
   ImageView imageView;
   Boolean checkedSpeaker=false;
   int speed;
   String voice;
   SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        choicespeaker = (Switch) findViewById(R.id.switch1);
        choicespeaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkedSpeaker = true;
                }else{
                    checkedSpeaker = false;
                }
                SaveData();
            }
        });
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                speed = i;
                SaveData();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        imageView = (ImageView) findViewById(R.id.imagesetting);
        imageView.setImageResource(R.drawable.setting);
        spinner = (Spinner) findViewById(R.id.spinner);
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,speaker);
        listAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(listAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                voice=item.toString();
                SaveData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        DisplayData();
    }

    public void SaveData(){
        SharedPreferences sharedPref = getSharedPreferences("infor",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("checkedSpeaker",checkedSpeaker);
        editor.putInt("Speed",speed);
        editor.putString("Voice",voice);
        editor.apply();

    }

    public void DisplayData(){
        SharedPreferences sharedPref = getSharedPreferences("infor", Context.MODE_PRIVATE);
        Boolean checkSpeaker = sharedPref.getBoolean("checkedSpeaker",false);
        int speed = sharedPref.getInt("Speed",0);
        String voice = sharedPref.getString("Voice","");
        choicespeaker.setChecked(checkSpeaker);
        int spinnerPosition = listAdapter.getPosition(voice);
        spinner.setSelection(spinnerPosition);
        seekBar.setProgress(speed);
    }
}
