package com.friendship.talk.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Config;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;

public class MultipartRequest {

    public static final String TAG = MultipartRequest.class.getName();
    public Context caller;
    public MultipartBuilder builder;
    private OkHttpClient client;
    public MediaType MEDIA_TYPE_TEXT_PLAIN = MediaType.parse("text/plain");

    MediaType MEDIA_TYPE_FORM_DATA = MediaType.parse("multipart/form-data");
    String mResponse = "";

    public MultipartRequest(Context caller) {
        this.caller = caller;
        this.builder = new MultipartBuilder();
        this.builder.type(MultipartBuilder.FORM);
        this.client = new OkHttpClient();
    }

    public void addString(String name, String value) {
        this.builder.addFormDataPart(name, value);
    }

    public void addFile(String name, String filePath, String fileName) {
        this.builder.addFormDataPart(name, fileName, RequestBody.create(
                MediaType.parse("image/jpeg"), new File(filePath)));

    }

    public void addTXTFile(String name, String filePath, String fileName) {
        this.builder.addFormDataPart(name, fileName, RequestBody.create(
                MediaType.parse("text/plain"), new File(filePath)));
    }

    public void addZipFile(String name, String filePath, String fileName) {
        this.builder.addFormDataPart(name, fileName, RequestBody.create(
                MediaType.parse("application/zip"), new File(filePath)));
    }

    public String uplodeexecute(String url, String F_name, String F_path) {

        RequestBody requestBody = null;
        Request request = null;
        Response response = null;

        int code = 200;
        String strResponse = null;

        try {
            this.builder.addFormDataPart("profile_pic", F_name, RequestBody.create(MEDIA_TYPE_FORM_DATA, new File(F_path)));
            this.builder.addFormDataPart("pic_token", "sd3jg5or9tydcvb87d5qew5rfglkrt04dcsz");
            this.builder.addFormDataPart("uid", Preferences.getUserData().getUid());

            Log.e(TAG, "uplodeexecute: " + Preferences.getUserData().getUid());

            requestBody = this.builder.build();

            request = new Request.Builder().url(url).post(requestBody).build();

            //Log.print("::::::: REQ :: " + request);
            response = client.newCall(request).execute();
            //Log.print("::::::: response :: " + response);

            if (!response.isSuccessful())
                throw new IOException();

            code = response.networkResponse().code();

            if (response.isSuccessful()) {
                strResponse = response.body().string();
            } else if (code == HttpStatus.SC_NOT_FOUND) {
                // ** "Invalid URL or Server not available, please try again" */
                strResponse = "error";
            } else if (code == HttpStatus.SC_REQUEST_TIMEOUT) {
                // * "Connection timeout, please try again", */
                strResponse = "";
            } else if (code == HttpStatus.SC_SERVICE_UNAVAILABLE) {
                // *
                // "Invalid URL or Server is not responding, please try again",
                // */
                strResponse = "";
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            requestBody = null;
            request = null;
            response = null;
            builder = null;
            if (client != null)
                client = null;
            System.gc();
        }
        return strResponse;
    }

//    public String callProfileUpdate(String url, String F_name, String F_path) {
//
//        mResponse = "";
//        try {
//            //client
//            okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();
//
//            okHttpClient = new okhttp3.OkHttpClient.Builder()
//                    .connectTimeout(15, TimeUnit.MINUTES)
//                    .writeTimeout(15, TimeUnit.MINUTES)
//                    .readTimeout(30, TimeUnit.MINUTES)
//                    .build();
//
//            //request builder
//            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
//            builder.url(url);
//
//            //your original request body
//            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
//            bodyBuilder.setType(MultipartBody.FORM);
//
//            bodyBuilder.addFormDataPart("profile_pic", F_name, okhttp3.RequestBody.create(MEDIA_TYPE_FORM_DATA, new File(F_path)));
//            bodyBuilder.addFormDataPart("pic_token", "sd3jg5or9tydcvb87d5qew5rfglkrt04dcsz");
//            bodyBuilder.addFormDataPart("uid", ConstantValue.userData.getUid());
//
//            okhttp3.RequestBody requestBody = bodyBuilder.build();
//
//            //post the wrapped request body
//            builder.post(requestBody);
//            //call
//            Call call = okHttpClient.newCall(builder.build());
//            //enqueue
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                    if (response.isSuccessful()) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response.body().string());
//                            mResponse = jsonObject.toString();
//                            Log.e(TAG, "onResponse: " + jsonObject.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Log.e(TAG, "onResponse: UnSuccessful");
//                    }
//                }
//
//
//            });
//        } catch (Resources.NotFoundException e) {
//            e.printStackTrace();
//        }
//        return mResponse;
//    }
}