package com.friendship.talk.webservices;

import android.util.Log;

import com.friendship.talk.Model.RankList;
import com.friendship.talk.Model.UserData;
import com.friendship.talk.Request.FBRegisterRequest;
import com.friendship.talk.Request.GmailRegisterRequest;
import com.friendship.talk.Request.RegistrationRequest;
import com.friendship.talk.Request.TWRegisterRequest;
import com.friendship.talk.Response.BaseDataResponse;
import com.friendship.talk.Response.BlockWords;
import com.friendship.talk.Response.CheckInResponse;
import com.friendship.talk.Response.CoinPerMinutesResponse;
import com.friendship.talk.Response.ConnectCallResponse;
import com.friendship.talk.Response.EndCallRespose;
import com.friendship.talk.Response.FBRegisterResponse;
import com.friendship.talk.Response.FBTempResponse;
import com.friendship.talk.Response.GmailRegisterResponse;
import com.friendship.talk.Response.GmailTempResponse;
import com.friendship.talk.Response.InsertorFetchLikeCallResponse;
import com.friendship.talk.Response.InstagramRegisterResponse;
import com.friendship.talk.Response.InvitationCodeResponse;
import com.friendship.talk.Response.Is_blockedResponse;
import com.friendship.talk.Response.JoinCallRespose;
import com.friendship.talk.Response.RecevieGiftResponse;
import com.friendship.talk.Response.SendGiftResponse;
import com.friendship.talk.Response.SignUpResponse;
import com.friendship.talk.Response.TermsAndConditionResponse;
import com.friendship.talk.Response.TransactionlistResponse;
import com.friendship.talk.Response.TwitterRegisterResponse;
import com.friendship.talk.Response.UpdateCountryResponse;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iweenggs on 02/07/18.
 */

public class APIRequest {

    public static String baseURL = "https://www.friendshiptalk.com/api/";  //live

    private Retrofit retrofit;
    private APIInterface apiInterface;

    public APIRequest(final String token) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logging);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.MINUTES);
        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request;
                if (token != null) {
                    request = chain.request().newBuilder().addHeader("Authorization", token).build();
//                    request = chain.request().newBuilder().build();
                } else {
                    //request = chain.request().newBuilder().addHeader("key", "d494f494da133429b93abe139d324bfb").build();
                    request = chain.request().newBuilder().build();
                }

                return chain.proceed(request);
            }
        });


        OkHttpClient httpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiInterface = retrofit.create(APIInterface.class);
    }


    /*public void upload_user_video(UploadVideoRequest uploadVideoRequest, final ResponseCallback callback) {
        try {
            Call<UploadVideoDataResponse> requestCall = apiInterface.upload_user_video(uploadVideoRequest.getCreated_user_id(), uploadVideoRequest.getMerged_video(), uploadVideoRequest.getUser_recorded_clip(), uploadVideoRequest.getTemplate_video_id(), uploadVideoRequest.getDescription(), uploadVideoRequest.getTitle(), uploadVideoRequest.getMerged_video_thumb(), uploadVideoRequest.getShare_thumb());
            requestCall.enqueue(new Callback<UploadVideoDataResponse>() {
                @Override
                public void onResponse(Call<UploadVideoDataResponse> call, Response<UploadVideoDataResponse> response) {
                    if (response.isSuccessful()) {
                        callback.ResponseSuccessCallBack(response.body());
                    } else {
                        try {
                            String s = response.errorBody().string().toString().trim();
                            callback.onResponseFail(s);
                        } catch (IOException e) {
                        }
                    }
                }

                @Override
                public void onFailure(Call<UploadVideoDataResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public void api_send_otp(String otp_token, String phone, String country_code, final ResponseCallback callback) {
        try {
            Call<BaseDataResponse> requestCall = apiInterface.api_send_otp(otp_token, phone, country_code);
            requestCall.enqueue(new Callback<BaseDataResponse>() {
                @Override
                public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                    if (response.isSuccessful()) {
                        callback.ResponseSuccessCallBack(response.body());
                    } else {
                        try {
                            String s = response.errorBody().string().toString().trim();
                            callback.onResponseFail(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_registration(RegistrationRequest registrationRequest, final ResponseCallback callback) {
        try {
            Call<SignUpResponse> requestCall = apiInterface.api_registration(
                    registrationRequest.getReg_token(),
                    registrationRequest.getName(),
                    registrationRequest.getGender(),
                    registrationRequest.getCountry_code(),
                    registrationRequest.getPhone(),
                    registrationRequest.getPassword(),
                    registrationRequest.getOtp_code(),
                    registrationRequest.getFcm_token(),
                    registrationRequest.getProfile_pic());
            requestCall.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.isSuccessful()) {
                        callback.ResponseSuccessCallBack(response.body());
                    } else {
                        try {
                            String s = response.errorBody().string().toString().trim();
                            callback.onResponseFail(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
