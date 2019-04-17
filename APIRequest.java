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

    public void api_login(String login_token, String phone, String password, final ResponseCallback callback) {
        try {
            Call<UserData> requestCall = apiInterface.api_login(login_token, phone, password);
            requestCall.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
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
                public void onFailure(Call<UserData> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_forgot_password(String fp_token, String phone, String otp_code, String password, final ResponseCallback callback) {
        try {
            Call<BaseDataResponse> requestCall = apiInterface.api_forgot_password(fp_token, phone, otp_code, password);
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

    public void Api_user_daily_rewrard_status(String token, String uid, String gender, final ResponseCallback callback) {
        try {
            Call<ResponseBody> responseBodyCall = apiInterface.Api_user_daily_rewrard_status(token, uid, gender);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Api_Daily_Checkin(String token, String uid, final ResponseCallback callback) {
        try {
            Call<ResponseBody> responseBodyCall = apiInterface.Api_Daily_chekin(token, uid);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_facebook_login(String fb_login_token, String fb_id, final ResponseCallback callback) {
        try {
            Call<UserData> requestCall = apiInterface.api_facebook_login(fb_login_token, fb_id);
            requestCall.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
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
                public void onFailure(Call<UserData> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_gmail_login(String gm_login_token, String gm_id, final ResponseCallback callback) {
        try {
            Call<UserData> requestCall = apiInterface.api_gmail_login(gm_login_token, gm_id);
            requestCall.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
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
                public void onFailure(Call<UserData> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_twitter_login(String twlogin_token, String tw_id, final ResponseCallback callback) {
        Call<UserData> requestCall = apiInterface.api_twitter_login(twlogin_token, tw_id);
        requestCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        String str = response.errorBody().string().toString().trim();
                        callback.onResponseFail(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });


    }

    public void api_instagram_login(String insta_login_token, String insta_id, final ResponseCallback callback) {
        Call<UserData> requestCall = apiInterface.api_instagram_login(insta_login_token, insta_id);
        requestCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        String str = response.errorBody().string().toString().trim();
                        callback.onResponseFail(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });


    }

    public void api_fb_registration(FBRegisterRequest fbRegisterRequest, final ResponseCallback callback) {
        try {
            Call<FBRegisterResponse> requestCall = apiInterface.api_fb_registration(
                    fbRegisterRequest.getFb_reg_token(),
                    fbRegisterRequest.getFb_id(),
                    fbRegisterRequest.getPhone(),
                    fbRegisterRequest.getCountry_code(),
                    fbRegisterRequest.getGender(),
                    fbRegisterRequest.getEmail(),
                    fbRegisterRequest.getName(),
                    fbRegisterRequest.getFcm_token(),
                    fbRegisterRequest.getProfile_pic());
            requestCall.enqueue(new Callback<FBRegisterResponse>() {
                @Override
                public void onResponse(Call<FBRegisterResponse> call, Response<FBRegisterResponse> response) {
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
                public void onFailure(Call<FBRegisterResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_gm_registration(GmailRegisterRequest gmailRegisterRequest, final ResponseCallback callback) {
        try {
            Call<GmailRegisterResponse> requestCall = apiInterface.api_gm_registration(
                    gmailRegisterRequest.getGm_reg_token(),
                    gmailRegisterRequest.getGmail_id(),
                    gmailRegisterRequest.getPhone(),
                    gmailRegisterRequest.getCountry_code(),
                    gmailRegisterRequest.getGender(),
                    gmailRegisterRequest.getEmail(),
                    gmailRegisterRequest.getName(),
                    gmailRegisterRequest.getFcm_token(),
                    gmailRegisterRequest.getProfile_pic());
            requestCall.enqueue(new Callback<GmailRegisterResponse>() {
                @Override
                public void onResponse(Call<GmailRegisterResponse> call, Response<GmailRegisterResponse> response) {
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
                public void onFailure(Call<GmailRegisterResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /*  public void api_twitter_registration(TWRegisterRequest gmailRegisterRequest, final ResponseCallback callback) {
        try {
            Call<TwitterRegisterResponse> requestCall = apiInterface.api_twitter_registration(
                    gmailRegisterRequest.getTw_reg_token(),
                    gmailRegisterRequest.getTw_id(),
                    gmailRegisterRequest.getPhone(),
                    gmailRegisterRequest.getCountry_code(),
                    gmailRegisterRequest.getGender(),
                    gmailRegisterRequest.getEmail(),
                    gmailRegisterRequest.getName(),
                    gmailRegisterRequest.getFcm_token(),
                    gmailRegisterRequest.getProfile_pic());
                    Log.e("TAG", "api_twitter_registration: Tw_reg_token" + gmailRegisterRequest.getTw_reg_token().toString());
                    Log.e("TAG", "api_twitter_registration: Tw_id" + gmailRegisterRequest.getTw_id());
                    Log.e("TAG", "api_twitter_registration: Phone" + gmailRegisterRequest.getPhone());
                    Log.e("TAG", "api_twitter_registration: Country_code" + gmailRegisterRequest.getCountry_code());
                    Log.e("TAG", "api_twitter_registration: Gender" + gmailRegisterRequest.getGender().toString());
                    Log.e("TAG", "api_twitter_registration: Email" + gmailRegisterRequest.getEmail());
                    Log.e("TAG", "api_twitter_registration: Name" + gmailRegisterRequest.getName());
                    Log.e("TAG", "api_twitter_registration: Fcm_token" + gmailRegisterRequest.getFcm_token());
                    Log.e("TAG", "api_twitter_registration: Profile_pic" + gmailRegisterRequest.getProfile_pic());
            requestCall.enqueue(new Callback<TwitterRegisterResponse>() {

                @Override
                public void onResponse(Call<TwitterRegisterResponse> call, Response<TwitterRegisterResponse> response) {
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
                public void onFailure(Call<TwitterRegisterResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void api_twitter_registration1(TWRegisterRequest gmailRegisterRequest, final ResponseCallback callback) {
        try {
            Call<TwitterRegisterResponse> requestCall = apiInterface.api_twitter_registration1(
                    gmailRegisterRequest.getTw_reg_token(),
                    gmailRegisterRequest.getTw_id(),
                    gmailRegisterRequest.getPhone(),
                    gmailRegisterRequest.getCountry_code(),
                    gmailRegisterRequest.getGender(),
                    gmailRegisterRequest.getEmail(),
                    gmailRegisterRequest.getName(),
                    gmailRegisterRequest.getFcm_token());

            requestCall.enqueue(new Callback<TwitterRegisterResponse>() {

                @Override
                public void onResponse(Call<TwitterRegisterResponse> call, Response<TwitterRegisterResponse> response) {
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
                public void onFailure(Call<TwitterRegisterResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* public void api_instagram_registration(TWRegisterRequest gmailRegisterRequest, final ResponseCallback callback) {
         try {
             Call<InstagramRegisterResponse> requestCall = apiInterface.api_instagram_registration(
                     gmailRegisterRequest.getTw_reg_token(),
                     gmailRegisterRequest.getTw_id(),
                     gmailRegisterRequest.getPhone(),
                     gmailRegisterRequest.getCountry_code(),
                     gmailRegisterRequest.getGender(),
                     gmailRegisterRequest.getEmail(),
                     gmailRegisterRequest.getName(),
                     gmailRegisterRequest.getFcm_token(),
                     gmailRegisterRequest.getProfile_pic());

             requestCall.enqueue(new Callback<InstagramRegisterResponse>() {

                 @Override
                 public void onResponse(Call<InstagramRegisterResponse> call, Response<InstagramRegisterResponse> response) {
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
                 public void onFailure(Call<InstagramRegisterResponse> call, Throwable t) {
                     callback.ResponseFailCallBack(t.getMessage());
                 }
             });
         } catch (Exception e) {
             e.printStackTrace();
         }
     }*/
    public void api_instagram_registration1(TWRegisterRequest gmailRegisterRequest, final ResponseCallback callback) {
        try {
            Call<InstagramRegisterResponse> requestCall = apiInterface.api_instagram_registration1(
                    gmailRegisterRequest.getTw_reg_token(),
                    gmailRegisterRequest.getTw_id(),
                    gmailRegisterRequest.getPhone(),
                    gmailRegisterRequest.getCountry_code(),
                    gmailRegisterRequest.getGender(),
                    gmailRegisterRequest.getEmail(),
                    gmailRegisterRequest.getName(),
                    gmailRegisterRequest.getFcm_token());

            requestCall.enqueue(new Callback<InstagramRegisterResponse>() {

                @Override
                public void onResponse(Call<InstagramRegisterResponse> call, Response<InstagramRegisterResponse> response) {
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
                public void onFailure(Call<InstagramRegisterResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void api_gm_login(String gm_tmp_token, String gender, String name, String email, String gmail_id, String fcm_token, final ResponseCallback callback) {
        try {
            Call<GmailTempResponse> requestCall = apiInterface.api_gm_login(gm_tmp_token, gender, name, email, gmail_id, fcm_token);
            requestCall.enqueue(new Callback<GmailTempResponse>() {
                @Override
                public void onResponse(Call<GmailTempResponse> call, Response<GmailTempResponse> response) {
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
                public void onFailure(Call<GmailTempResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_fb_login(String fb_tmp_token, String gender, String name, String email, String fb_id, String fcm_token, final ResponseCallback callback) {
        try {
            Call<FBTempResponse> requestCall = apiInterface.api_fb_login(fb_tmp_token, gender, name, email, fb_id, fcm_token);
            requestCall.enqueue(new Callback<FBTempResponse>() {
                @Override
                public void onResponse(Call<FBTempResponse> call, Response<FBTempResponse> response) {
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
                public void onFailure(Call<FBTempResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_country_list(String country_token, final ResponseCallback callback) {
        try {
            Call<ResponseBody> requestCall = apiInterface.api_country_list(country_token);
            requestCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_user_details(String user_token, String uid, final ResponseCallback callback) {
        try {
            Call<UserData> requestCall = apiInterface.api_user_details(user_token, uid);
            requestCall.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
                    if (response.isSuccessful()) {
                        try {
                            callback.ResponseSuccessCallBack(response.body());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
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
                public void onFailure(Call<UserData> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_update_profile(UserData userData, String UpdateProfileToken, final ResponseCallback callback) {
        try {
            Call<BaseDataResponse> requestCall = apiInterface.api_update_profile(UpdateProfileToken, userData.getUid(), userData.getName(), userData.getGender(), userData.getBirth_date(), userData.getUser_link(), userData.getStatus());
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

    public void api_gift_list(String gift_token, final ResponseCallback callback) {
        try {
            Call<ResponseBody> requestCall = apiInterface.api_gift_list(gift_token);
            requestCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.d("response", "response" + response.body());

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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_connect_call(String token, String uid, String gender, String join_call_type, final ResponseCallback callback) {
        Call<ConnectCallResponse> connectCallResponse = apiInterface.api_connect_call(token, uid, gender, join_call_type);
        connectCallResponse.enqueue(new Callback<ConnectCallResponse>() {
            @Override
            public void onResponse(Call<ConnectCallResponse> call, Response<ConnectCallResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<ConnectCallResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_is_block_user(String token, String uid, final ResponseCallback callback) {
        Call<Is_blockedResponse> blockedResponseCall = apiInterface.api_is_block_user(token, uid);
        blockedResponseCall.enqueue(new Callback<Is_blockedResponse>() {
            @Override
            public void onResponse(Call<Is_blockedResponse> call, Response<Is_blockedResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<Is_blockedResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }


    public void api_is_deleteUser(String token, String uid, final ResponseCallback callback) {
        Call<Is_blockedResponse> blockedResponseCall = apiInterface.api_is_delete_user(token, uid);
        blockedResponseCall.enqueue(new Callback<Is_blockedResponse>() {
            @Override
            public void onResponse(Call<Is_blockedResponse> call, Response<Is_blockedResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<Is_blockedResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void ApiInsertorFetchLike(String Type, String token, String uid, final ResponseCallback callback) {
        Call<InsertorFetchLikeCallResponse> connectCallResponse = null;

        if (Type.equalsIgnoreCase("fetch"))
            connectCallResponse = apiInterface.api_fetch_like(token, uid);

     /*   else if (Type.equalsIgnoreCase("insert"))
//            connectCallResponse = apiInterface.api_insert_like(token, uid);*/


        connectCallResponse.enqueue(new Callback<InsertorFetchLikeCallResponse>() {
            @Override
            public void onResponse(Call<InsertorFetchLikeCallResponse> call, Response<InsertorFetchLikeCallResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<InsertorFetchLikeCallResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void Api_Insert_like(String token, String liked_by, String liked_to, final ResponseCallback callback) {
        Call<InsertorFetchLikeCallResponse> InsertLikeCallResponse = apiInterface.api_insert_like(token, liked_by, liked_to);
        InsertLikeCallResponse.enqueue(new Callback<InsertorFetchLikeCallResponse>() {
            @Override
            public void onResponse(Call<InsertorFetchLikeCallResponse> call, Response<InsertorFetchLikeCallResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<InsertorFetchLikeCallResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());

            }
        });
    }


    public void api_join_call(String join_call_token, String uid, String user_ip, String to_user_id, final ResponseCallback callback) {
        Call<JoinCallRespose> connectCallResponse = apiInterface.api_join_call(join_call_token, uid, user_ip, to_user_id);
        connectCallResponse.enqueue(new Callback<JoinCallRespose>() {
            @Override
            public void onResponse(Call<JoinCallRespose> call, Response<JoinCallRespose> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<JoinCallRespose> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_join_call_with_channel_key(String join_call_token, String uid, String user_ip, String to_user_id, final ResponseCallback callback) {
        Call<JoinCallRespose> connectCallResponse = apiInterface.api_join_call(join_call_token, uid, user_ip, to_user_id);
        connectCallResponse.enqueue(new Callback<JoinCallRespose>() {
            @Override
            public void onResponse(Call<JoinCallRespose> call, Response<JoinCallRespose> response) {
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
            public void onFailure(Call<JoinCallRespose> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_insert_live_details(String Token, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> dataResponseCall = apiInterface.api_insert_live_details(Token, uid);
        dataResponseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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

    }

    public void api_update_live_details(String Token, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> dataResponseCall = apiInterface.api_update_live_details(Token, uid);
        dataResponseCall.enqueue(new Callback<BaseDataResponse>() {
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

    }


    public void api_end_call(String end_call_token, String Call_id, final ResponseCallback callback) {
        Call<EndCallRespose> connectCallResponse = apiInterface.api_end_call(end_call_token, Call_id);
        connectCallResponse.enqueue(new Callback<EndCallRespose>() {
            @Override
            public void onResponse(Call<EndCallRespose> call, Response<EndCallRespose> response) {
                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

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
            public void onFailure(Call<EndCallRespose> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }


    public void api_package_list(String package_token, String Country_id, final ResponseCallback callback) {
        try {
            Call<ResponseBody> requestCall = apiInterface.api_package_list(package_token, Country_id);
            requestCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.d("response", "response" + response.body());

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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_send_gift(String send_gift_token, String call_id, String gift_id, String no_of_gift, String sender_id, String receiver_id, final ResponseCallback callback) {
        try {
            Call<SendGiftResponse> giftResponseCall = apiInterface.api_send_gift(send_gift_token, call_id, gift_id, no_of_gift, sender_id, receiver_id);
            giftResponseCall.enqueue(new Callback<SendGiftResponse>() {
                @Override
                public void onResponse(Call<SendGiftResponse> call, Response<SendGiftResponse> response) {
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
                public void onFailure(Call<SendGiftResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());

                }
            });
        } catch (Exception e) {

        }

    }

    public void api_receive_gift(String rec_message_token, String uid, String sender_id, final ResponseCallback callback) {

        Call<RecevieGiftResponse> receiveGiftResponseCall = apiInterface.api_receive_gift(rec_message_token, uid, sender_id);
        receiveGiftResponseCall.enqueue(new Callback<RecevieGiftResponse>() {
            @Override
            public void onResponse(Call<RecevieGiftResponse> call, Response<RecevieGiftResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        callback.onResponseFail(response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<RecevieGiftResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());

            }
        });
    }

    public void api_get_invitations_code(String code_token, String uid, final ResponseCallback callback) {

        try {
            Call<InvitationCodeResponse> requestCall = apiInterface.api_get_invitations_code(code_token, uid);
            requestCall.enqueue(new Callback<InvitationCodeResponse>() {
                @Override
                public void onResponse(Call<InvitationCodeResponse> call, Response<InvitationCodeResponse> response) {
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
                public void onFailure(Call<InvitationCodeResponse> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void api_deactivate_account(String deactivate_token, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> dataResponseCall = apiInterface.api_deactivate_account(deactivate_token, uid);
        dataResponseCall.enqueue(new Callback<BaseDataResponse>() {
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

    }

    public void api_report_user(String report_token, String reported_by, String reporting_to, String remarks, String reason_id, String report_user_and_block, final ResponseCallback callback) {

        Call<BaseDataResponse> dataResponseCall;
        if (report_user_and_block.equalsIgnoreCase("report")) {
            dataResponseCall = apiInterface.api_report_user(report_token, reported_by, reporting_to, remarks, reason_id, "1");
        } else {
            dataResponseCall = apiInterface.api_report_block_user(report_token, reported_by, reporting_to, remarks, reason_id, "2");
        }

        dataResponseCall.enqueue(new Callback<BaseDataResponse>() {
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

    }

    public void api_feedback(RequestBody FeedbackToken, RequestBody uid, RequestBody feedback, RequestBody pic1, RequestBody pic2, final ResponseCallback callback) {
        Call<BaseDataResponse> dataResponseCall = apiInterface.api_feedback(FeedbackToken, uid, feedback, pic1, pic2);
        dataResponseCall.enqueue(new Callback<BaseDataResponse>() {
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

    }

    public void api_followers_list(String FollowerTokden, String uid, final ResponseCallback callback) {
        Call<ResponseBody> bodyCall = apiInterface.api_followers_list(FollowerTokden, uid);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());

                } else {
                    try {
                        String error = response.errorBody().toString().trim();
                        callback.onResponseFail(error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_follow_user(String Token, String followed_by, String following_to, final ResponseCallback callback) {
        Call<BaseDataResponse> bodyCall = apiInterface.api_follow_user(Token, followed_by, following_to);
        bodyCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    String error = response.errorBody().toString().trim();
                    callback.onResponseFail(error);
                }

            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());

            }
        });

    }

    public void api_followings_list(String FollowerTokden, String uid, final ResponseCallback callback) {
        Call<ResponseBody> bodyCall = apiInterface.api_following_list(FollowerTokden, uid);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());

                } else {
                    try {
                        String error = response.errorBody().toString().trim();
                        callback.onResponseFail(error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_monthly_reward_list(String monthly_reward_token, String uid, final ResponseCallback callback) {
        Call<CheckInResponse> bodyCall = apiInterface.api_user_monthly_rewrard_status_list(monthly_reward_token, uid);
        bodyCall.enqueue(new Callback<CheckInResponse>() {
            @Override
            public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());

                } else {
                    try {
                        String error = response.errorBody().toString().trim();
                        callback.onResponseFail(error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckInResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_country_hide_or_show(String Country_Token, String uid, final ResponseCallback callback) {
        Call<ResponseBody> responseBodyCall = apiInterface.api_country_hide_or_show(Country_Token, uid);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());

                } else {
                    try {
                        String error = response.errorBody().toString().trim();
                        callback.onResponseFail(error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });

    }

    public void api_update_country(String Token, String uid, String countryName, final ResponseCallback callback) {
        Call<UpdateCountryResponse> responseCall = apiInterface.api_update_country(Token, uid, countryName);
        responseCall.enqueue(new Callback<UpdateCountryResponse>() {
            @Override
            public void onResponse(Call<UpdateCountryResponse> call, Response<UpdateCountryResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    String error = response.errorBody().toString().trim();
                    callback.onResponseFail(error);
                }
            }

            @Override
            public void onFailure(Call<UpdateCountryResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });

    }

    public void api_terms_conditions(String terms_token, final ResponseCallback callback) {
        Call<TermsAndConditionResponse> responseBodyCall = apiInterface.api_terms_and_conditions(terms_token);
        responseBodyCall.enqueue(new Callback<TermsAndConditionResponse>() {
            @Override
            public void onResponse(Call<TermsAndConditionResponse> call, Response<TermsAndConditionResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        callback.ResponseSuccessCallBack(response.body());
                    } else {
                        callback.ResponseFailCallBack(response.errorBody().string().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TermsAndConditionResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }

    public void api_user_live_details(String token, String uid, String from_date, String to_date, final ResponseCallback callback) {
        Call<JsonObject> dataResponseCall = apiInterface.api_user_live_details(token, uid, from_date, to_date);
        dataResponseCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                if (response.isSuccessful()) {
                    Log.d("response", "response" + response.body());

                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        callback.ResponseFailCallBack(response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }

    public void api_verify_invitecode(String Token, String invitionsCode, final ResponseCallback callback) {
        Call<BaseDataResponse> dataResponseCall = apiInterface.api_verify_invitation_code(Token, invitionsCode);
        dataResponseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.d("response", "response" + response.body());

                        callback.ResponseSuccessCallBack(response.body());
                    } else {
                        callback.ResponseFailCallBack(response.errorBody().string().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_sticker_list(String Token, final ResponseCallback callback) {
        Call<JsonObject> stickerItemCall = apiInterface.api_sticker_list(Token);
        stickerItemCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        callback.ResponseFailCallBack(response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onResponseFail(t.getMessage());

            }
        });


    }

    public void api_send_sticker(String sticker_token, String sticker_id, String sender_id, String receiver_id, final ResponseCallback callback) {
        Call<BaseDataResponse> send_stickerCAll = apiInterface.api_send_sticker(sticker_token, sticker_id, sender_id, receiver_id);
        send_stickerCAll.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }


            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });

    }

    public void api_weekly_rank(String RankToken, final ResponseCallback callback) {
        Call<RankList> rankListCall = apiInterface.api_weekly_rank(RankToken);
        rankListCall.enqueue(new Callback<RankList>() {
            @Override
            public void onResponse(Call<RankList> call, Response<RankList> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<RankList> call, Throwable t) {
                callback.onResponseFail(t.getMessage());

            }
        });
    }

    public void api_daily_rank(String RankToken, final ResponseCallback callback) {
        Call<RankList> rankListCall = apiInterface.api_daily_rank(RankToken);
        rankListCall.enqueue(new Callback<RankList>() {
            @Override
            public void onResponse(Call<RankList> call, Response<RankList> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<RankList> call, Throwable t) {
                callback.onResponseFail(t.getMessage());

            }
        });
    }

    public void api_is_following_user(String follow_token, String followed_by, String following_to, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_is_following_user(follow_token, followed_by, following_to);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }

    public void api_close_app(String Token, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_close_app(Token, uid);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });

    }

    public void api_live_channel_key(String Token, String uid, final ResponseCallback callback) {
        Call<JoinCallRespose> resposeCall = apiInterface.api_live_channel_key(Token, uid);
        resposeCall.enqueue(new Callback<JoinCallRespose>() {
            @Override
            public void onResponse(Call<JoinCallRespose> call, Response<JoinCallRespose> response) {
                if (response.isSuccessful()) {

                    Log.d("response", "response" + response.body());

                    callback.ResponseSuccessCallBack(response.body());

                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<JoinCallRespose> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });

    }

    public void api_submit_invitation_code(String Token, String invitation_code, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_submit_invitation_code(Token, invitation_code, uid);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("api_invitation_code", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());

            }
        });

    }

    public void api_set_preferred_gender(String Token, String uid, String preferedGender, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_set_preferred_gender(Token, uid, preferedGender);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("api_invitation_code", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });


    }

    public void api_deduct_coin_per_minute(String Token, final ResponseCallback callback) {
        Call<CoinPerMinutesResponse> responseCall = apiInterface.api_deduct_coin_per_minute(Token);
        responseCall.enqueue(new Callback<CoinPerMinutesResponse>() {
            @Override
            public void onResponse(Call<CoinPerMinutesResponse> call, Response<CoinPerMinutesResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("api_invitation_code", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<CoinPerMinutesResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });

    }

    public void api_update_fcm_token(String Token, String uid, String fcm_token, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_update_fcm_token(Token, uid, fcm_token);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("token update", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }

    public void api_auto_login_token(String Token, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_auto_login(Token, uid);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("token update", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }

    public void api_logout(String Token, String uid, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_logout(Token, uid);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("token update", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }

    public void api_update_login_token(String update_login, String uid, String login_token, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_update_login_token(update_login, uid, login_token);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("token update", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }

    public void api_send_gift_coin(String Token, String coin_no, String call_id, String sender_id, String receiver_id, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_send_gift_coin(Token, coin_no, call_id, sender_id, receiver_id);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("token update", "onResponse: " + response.body());
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    callback.ResponseFailCallBack(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse> call, Throwable t) {
                callback.onResponseFail(t.getMessage());
            }
        });
    }


    public void api_user_block_list(String token, String uid, final ResponseCallback callback) {
        try {
            Call<ResponseBody> requestCall = apiInterface.api_user_block_list(token, uid);
            requestCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void api_unblock_user(String token, String uid, String uid_list, final ResponseCallback callback) {
        Call<BaseDataResponse> responseCall = apiInterface.api_unblock_user(token, uid, uid_list);
        responseCall.enqueue(new Callback<BaseDataResponse>() {
            @Override
            public void onResponse(Call<BaseDataResponse> call, Response<BaseDataResponse> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        String s = response.errorBody().string().trim();
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
    }

    public void api_block_words_list(String token, final ResponseCallback callback) {
        Call<BlockWords> wordsCall = apiInterface.api_block_words_list(token);
        wordsCall.enqueue(new Callback<BlockWords>() {
            @Override
            public void onResponse(Call<BlockWords> call, Response<BlockWords> response) {
                if (response.isSuccessful()) {
                    callback.ResponseSuccessCallBack(response.body());
                } else {
                    try {
                        String s = response.errorBody().string().trim();
                        callback.onResponseFail(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BlockWords> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

    public void api_transactions_list(String token, String uid, final ResponseCallback callback) {
        Call<TransactionlistResponse> responseBodyCall = apiInterface.api_transactions_list(token, uid);
        responseBodyCall.enqueue(new Callback<TransactionlistResponse>() {
            @Override
            public void onResponse(Call<TransactionlistResponse> call, Response<TransactionlistResponse> response) {
                if (response.isSuccessful()){
                    callback.ResponseSuccessCallBack(response.body());
                }else {
                    try {
                        String s = response.errorBody().string();
                        callback.onResponseFail(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionlistResponse> call, Throwable t) {
                callback.ResponseFailCallBack(t.getMessage());
            }
        });
    }

}
