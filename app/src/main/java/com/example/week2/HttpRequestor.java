package com.example.week2;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestor {

    public static void GET(String baseurl, String data, HttpCallback callback) {
        Log.d("Procedure", "GET function Start");
        OkHttpClient client = new OkHttpClient();
        String url = baseurl;
        if (data != null) {
            url += "/" + data;
        }
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Log.d("Procedure", "Execute GET");
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("Procedure", e.toString());
                    callback.onFailure(e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String result = response.body().string();
                    Log.d("Procedure", "Get Result : " + result);
                    callback.onSuccess(result);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Procedure", e.toString());
        }
    }

    public static void POST(String baseurl, String data, HttpCallback callback) {
        Log.d("Procedure", "POST Function Start");
        String result = null;
        OkHttpClient client = new OkHttpClient();
        String url = baseurl;

        RequestBody requestBody = new FormBody.Builder().add("data", data).build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Log.d("Procedure", "Execute POST");
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("Procedure", e.toString());
                    callback.onFailure(e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String result = response.body().string();
                    Log.d("Procedure", "POST Response Result : " + result);
                    callback.onSuccess(result);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Procedure", e.toString());
        }
    }
}
