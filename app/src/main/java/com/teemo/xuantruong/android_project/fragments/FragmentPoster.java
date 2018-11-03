package com.teemo.xuantruong.android_project.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.poster.Poster;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentPoster extends Fragment {

    private Poster_entity poster = new Poster_entity();
    SeekBar seekBar;
    int totalTime;
    TextView elapsedTime,remainingTime;
    MediaPlayer mp;
    ImageButton btnPlay;
    public FragmentPoster() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mp.pause();
        btnPlay.setBackgroundResource(R.drawable.play);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        TextView txtPosterDate = view.findViewById(R.id.poster_datetime);
        TextView txtPosterTitle = view.findViewById(R.id.poster_title);
        TextView txtPosterContent = view.findViewById(R.id.poster_content);
        //Muusic
        seekBar = view.findViewById(R.id.seekbar_speark);
        elapsedTime = view.findViewById(R.id.txtStart);
        remainingTime = view.findViewById(R.id.txtPlaying);
        btnPlay = (ImageButton) view.findViewById(R.id.imageBut);
        mp = MediaPlayer.create(getActivity(),R.raw.nhac);
        mp.setLooping(true);
        mp.seekTo(0);
        totalTime=mp.getDuration();
        seekBar.setMax(totalTime);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mp.seekTo(i);
                    seekBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp!=null){
                    try{
                        Message msg = new Message();
                        msg.what=mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    }catch (InterruptedException e){}
                }
            }
        }).start();


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mp.isPlaying()){
                    mp.start();
                    startFadeIn();
                    btnPlay.setBackgroundResource(R.drawable.pause);
                }else{
                    mp.pause();
                    btnPlay.setBackgroundResource(R.drawable.play);
                }
            }
        });
       // mp = MediaPlayer.create(getActivity(),R.raw.nhac);
       // mp.start();

        //get bundel from view page
        Bundle bundle = this.getArguments();
        poster = (Poster_entity) bundle.getSerializable("poster");

        //set view
        txtPosterTitle.setText(poster.getTitle_poster());
        txtPosterDate.setText(poster.getTime_poster());
        txtPosterContent.setText(poster.getContent_poster());
        return view;
    }

    public Poster_entity getPoster() {
        return poster;
    }

    public void setPoster(Poster_entity poster) {
        this.poster = poster;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int currentposition = msg.what;
            seekBar.setProgress(currentposition);

            String elap = createTimelabel(currentposition);
            elapsedTime.setText(elap);

            String remain = createTimelabel(totalTime-currentposition);
            remainingTime.setText(remain);

        }
    };

    private String createTimelabel(int time) {
        String timelable="";
        int min = time/1000/60;
        int sec = time/1000%60;
        timelable = min + ":";
        if(sec<10) timelable+="0";
        timelable +=sec;
        return timelable;
    }

    float volume = 0;

    private void startFadeIn(){
        final int FADE_DURATION = 750; //The duration of the fade
        //The amount of time between volume changes. The smaller this is, the smoother the fade
        final int FADE_INTERVAL = 250;
        final int MAX_VOLUME = 2; //The volume will increase from 0 to 1
        int numberOfSteps = FADE_DURATION/FADE_INTERVAL; //Calculate the number of fade steps
        //Calculate by how much the volume changes each step
        final float deltaVolume = MAX_VOLUME / (float)numberOfSteps;

        //Create a new Timer and Timer task to run the fading outside the main UI thread
        final Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                fadeInStep(deltaVolume); //Do a fade step
                //Cancel and Purge the Timer if the desired volume has been reached
                if(volume>=1f){
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.schedule(timerTask,FADE_INTERVAL,FADE_INTERVAL);
    }

    private void fadeInStep(float deltaVolume){
        mp.setVolume(volume, volume);
        volume += deltaVolume;

    }

}
