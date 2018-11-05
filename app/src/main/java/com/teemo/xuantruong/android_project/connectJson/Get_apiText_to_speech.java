package com.teemo.xuantruong.android_project.connectJson;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Get_apiText_to_speech {
    Response response = null;

    public Get_apiText_to_speech() {
    }

    // use api  -> read json ( mp3)
    public  String apiChangetexttomp3(String text, int speech , String voice) throws  IOException{
        if(voice ==null)
        {
            voice ="leminh";
        }
        if(String.valueOf(speech) ==null)
        {
            speech=0;
        }

        String voice1[]= {"leminh", "hatieumai","ngoclam"};
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, text);
        Request request = new Request.Builder()
                .url("http://api.openfpt.vn/text2speech/v4?aip_key=e8b586d28b904fd290f813ef6ee05764&voice=female")
                .post(body)
                .addHeader("api_key", "e8b586d28b904fd290f813ef6ee05764")
                // set voice
                .addHeader("voice", voice)
                // set speed
                .addHeader("speed",String.valueOf(speech))
                //  set prosody
                .addHeader("prosody","0")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "a796a804-556f-4be3-bb77-37e972a9f039")
                .build();
        try {
            response= client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // get text response
        String texturl= response.body().string();
        return  texturl;
    }

}
