package com.friendship.talk.webservices;


import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;



public interface APIInterface {

 

    @FormUrlEncoded
    @POST("Normal api call.php")
    Call<BaseDataResponse> api_send_otp(@Field("otp_token") String country_token,
                                        @Field("phone") String phone,
                                        @Field("country_code") String country_code);

    @Multipart
    @POST("with imge.php")
    Call<SignUpResponse> api_registration(@Part("reg_token") RequestBody reg_token,
                                          @Part("name") RequestBody name,
                                          @Part("gender") RequestBody gender,
                                          @Part("country_code") RequestBody country_code,
                                          @Part("phone") RequestBody phone,
                                          @Part("password") RequestBody password,
                                          @Part("otp_code") RequestBody otp_code,
                                          @Part("fcm_token") RequestBody fcm_token,
                                          @Part("profile_pic\"; filename=\"profile_pic.jpeg\" ") RequestBody profile_pic);



}
