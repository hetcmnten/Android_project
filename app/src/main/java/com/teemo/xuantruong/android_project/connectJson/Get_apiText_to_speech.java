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
    public  String apiChangetexttomp3() throws  IOException{
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "Đúng một tháng trôi qua, người dân Hà Nội và đặc biệt là những cư dân sống ở khu vực Đê La Thành vẫn chưa hết kinh hoàng khi nói về vụ cháy dữ dội xảy ra gần Bệnh viện Nhi Trung ương.\r\n\r\nNgọn lửa dữ dội đã thiêu sạch toàn bộ 19 ngôi nhà gần đó cùng rất nhiều tài sản có giá trị của các hộ kinh doanh trên địa bàn, bao gồm cả những dãy nhà cho bệnh nhân ngoại trú, điều trị dài ngày thuê gần Bệnh viện Nhi.Đúng một tháng trôi qua, người dân Hà Nội và đặc biệt là những cư dân sống ở khu vực Đê La Thành vẫn chưa hết kinh hoàng khi nói về vụ cháy dữ dội xảy ra gần Bệnh viện Nhi Trung ương.\r\n\r\nNgọn lửa dữ dội đã thiêu sạch toàn bộ 19 ngôi nhà gần đó cùng rất nhiều tài sản có giá trị của các hộ kinh doanh trên địa bàn, bao gồm cả những dãy nhà cho bệnh nhân ngoại trú, điều trị dài ngày thuê gần Bệnh viện Nhi.");
        Request request = new Request.Builder()
                .url("http://api.openfpt.vn/text2speech/v4?aip_key=e8b586d28b904fd290f813ef6ee05764&voice=female")
                .post(body)
                .addHeader("api_key", "e8b586d28b904fd290f813ef6ee05764")
                .addHeader("voice", "leminh")
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
