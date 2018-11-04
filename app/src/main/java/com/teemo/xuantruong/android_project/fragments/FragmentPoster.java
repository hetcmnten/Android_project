package com.teemo.xuantruong.android_project.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;
import com.teemo.xuantruong.android_project.R;
import com.teemo.xuantruong.android_project.adapters.ViewPageAdapter;
import com.teemo.xuantruong.android_project.connectJson.Get_apiText_to_speech;
import com.teemo.xuantruong.android_project.connectJson.ReadJsonDB;
import com.teemo.xuantruong.android_project.entity.Poster_entity;
import com.teemo.xuantruong.android_project.poster.Poster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private  TextView txtPosterDate,txtPosterTitle,txtPosterContent;
    private ImageView image;
    private  String informationImage;
    private ProfilePictureView filePicture;
    // read File save login
    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "fileSaveLogin" ;
    private  SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        // date
        txtPosterDate = view.findViewById(R.id.poster_datetime);
        // title
        txtPosterTitle = view.findViewById(R.id.poster_title);
        // content
        txtPosterContent = view.findViewById(R.id.poster_content);

        // image poster
        image = (ImageView) view.findViewById(R.id.poster_image);

        //Muusic
        //get view seek bar
        seekBar = view.findViewById(R.id.seekbar_speark);
        // text time seek bar
        elapsedTime = view.findViewById(R.id.txtStart);
        remainingTime = view.findViewById(R.id.txtPlaying);

        btnPlay = (ImageButton) view.findViewById(R.id.imageBut);

        //get bundel from view page
        Bundle bundle = this.getArguments();
        poster = (Poster_entity) bundle.getSerializable("poster");

        //set view
        txtPosterTitle.setText(poster.getTitle_poster());
        txtPosterDate.setText(poster.getTime_poster());
        txtPosterContent.setText(poster.getContent_poster());

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

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

        //Thread change seekbar second
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
                    changeplayerSpeed(Float.parseFloat("1"));
                    btnPlay.setBackgroundResource(R.drawable.pause);
                }else{
                    mp.pause();
                    btnPlay.setBackgroundResource(R.drawable.play);
                }
            }
        });

        // save information login (id, name)
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        // set image avatar
        filePicture = (ProfilePictureView) view.findViewById(R.id.picture);
        // get id in sharedpreferences
        filePicture.setProfileId(sharedpreferences.getString("id", ""));
        GetDataFromSetting();
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

    private void changeplayerSpeed(float speed) {
        // this checks on API 23 and up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(speed));
        }
    }
    private  String voice;
    private  int speed;
    public void GetDataFromSetting(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("infor", Context.MODE_PRIVATE);
        Boolean checkSpeaker = sharedPref.getBoolean("checkedSpeaker",false);
        speed = sharedPref.getInt("Speed",0);
        voice = sharedPref.getString("Voice","");

        Toast.makeText(getContext(),checkSpeaker+"/"+speed+"/"+voice+"/",Toast.LENGTH_LONG).show();
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

    // read json in activity
    private void readJson() throws Exception {
        try {
            ReadJsonDB readJsonDB = new ReadJsonDB();
            // fic id = 21141 need to fix
            String json = readJsonDB.ConnectJson();
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // get json object
                // tittle
//                txtPosterTitle.setText(jsonObject.getString("title"));
//                // date
//                txtPosterDate.setText(jsonObject.getString("publishTime"));
//                // content
//                txtPosterContent.setText(jsonObject.getString("content"));
//                // String image
//                informationImage =  jsonObject.getString("imgConverted");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Get_apiText_to_speech getapiTexttospeech = new Get_apiText_to_speech();
    private String text1;

    //get file mp3 use api text to speech
    private void   getLinkMp3() throws IOException {

        try {
            // add text in layout
            String txt = txtPosterTitle.getText() + " " + txtPosterContent.getText();
            // get link file mp3
            String texturl = getapiTexttospeech.apiChangetexttomp3(txt, speed, voice);
            JSONObject jsonObject1 = new JSONObject(texturl);
            // get elemet json object
            text1 = jsonObject1.getString("async");
            // set mediaplayer in source
            //read mediaplayer online
            mp= new MediaPlayer();
            mp.setDataSource(text1);
            mp.prepare();
            mp.seekTo(0);
            totalTime=mp.getDuration();
            seekBar.setMax(totalTime);
        }catch ( Exception e)
        {
            e.printStackTrace();
        }
    }
    private Bitmap bm;
    class MyAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // get json poster
                //readJson();
                // get text link mp3
                getLinkMp3();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // process  image in databse
        @Override
        protected void onPostExecute(Void aVoid) {
            // read image with base64
            bm = StringToBitMap(informationImage);
            image.setImageBitmap(bm);
            super.onPostExecute(aVoid);
        }
    }

}
