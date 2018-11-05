package com.teemo.xuantruong.android_project.connectJson;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReadJsonDB {
    // edit = ipconfig
    final String serverHost = "192.168.0.123";
    Socket socketOfClient = null;
    BufferedWriter os = null;
    BufferedReader is = null;
    public String ConnectJson() {
        String responseLine= null;
        try {

            // Gửi yêu cầu kết nối tới Server đang lắng nghe
            // trên máy 'localhost' cổng 7777.
            socketOfClient = new Socket(serverHost, 1234);

            // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

            // Ghi dữ liệu vào luồng đầu ra của Socket tại Client. get id
            os.write("genk");
            os.newLine();
            os.flush();

            // Đọc dữ liệu trả lời từ phía server
            // Bằng cách đọc luồng đầu vào của Socket tại Client.

            responseLine = is.readLine();
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
        return  responseLine;
    }
}
