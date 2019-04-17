package com.friendship.talk.webservices;

import com.friendship.talk.Model.RankList;
import com.friendship.talk.Model.UserData;
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
import com.friendship.talk.Response.ReceiveGiftResponse;
import com.friendship.talk.Response.RecevieGiftResponse;
import com.friendship.talk.Response.SendGiftResponse;
import com.friendship.talk.Response.SignUpResponse;
import com.friendship.talk.Response.TermsAndConditionResponse;
import com.friendship.talk.Response.TransactionlistResponse;
import com.friendship.talk.Response.TwitterRegisterResponse;
import com.friendship.talk.Response.UpdateCountryResponse;
import com.friendship.talk.Utils.ConstantValue;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by iweenggs on 02/07/18.
 */

public interface APIInterface {

    @FormUrlEncoded
    @POST("api_country_list.php")
    Call<ResponseBody> api_country_list(@Field("country_token") String country_token);

    @FormUrlEncoded
    @POST("api_send_otp.php")
    Call<BaseDataResponse> api_send_otp(@Field("otp_token") String country_token,
                                        @Field("phone") String phone,
                                        @Field("country_code") String country_code);

    @Multipart
    @POST("api_registration.php")
    Call<SignUpResponse> api_registration(@Part("reg_token") RequestBody reg_token,
                                          @Part("name") RequestBody name,
                                          @Part("gender") RequestBody gender,
                                          @Part("country_code") RequestBody country_code,
                                          @Part("phone") RequestBody phone,
                                          @Part("password") RequestBody password,
                                          @Part("otp_code") RequestBody otp_code,
                                          @Part("fcm_token") RequestBody fcm_token,
                                          @Part("profile_pic\"; filename=\"profile_pic.jpeg\" ") RequestBody profile_pic);

    @FormUrlEncoded
    @POST("api_login.php")
    Call<UserData> api_login(@Field("login_token") String login_token,
                             @Field("phone") String phone,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("api_forgot_password.php")
    Call<BaseDataResponse> api_forgot_password(@Field("fp_token") String fp_token,
                                               @Field("phone") String phone,
                                               @Field("otp_code") String otp_code,
                                               @Field("password") String password);

    @FormUrlEncoded
    @POST("api_facebook_login.php")
    Call<UserData> api_facebook_login(@Field("fb_login_token") String fb_login_token,
                                      @Field("fb_id") String fb_id);

    @FormUrlEncoded
    @POST("api_gmail_login.php")
    Call<UserData> api_gmail_login(@Field("gm_login_token") String gm_login_token,
                                   @Field("gm_id") String gm_id);

    @FormUrlEncoded
    @POST("api_twitter_login.php")
    Call<UserData> api_twitter_login(@Field("tw_login_token") String tw_login_token,
                                     @Field("tw_id") String tw_id);

    @FormUrlEncoded
    @POST("api_instagram_login.php")
    Call<UserData> api_instagram_login(@Field("insta_login_token") String tw_login_token,
                                       @Field("insta_id") String tw_id);


    @Multipart
    @POST("api_fb_registration.php")
    Call<FBRegisterResponse> api_fb_registration(@Part("fb_reg_token") RequestBody fb_reg_token,
                                                 @Part("fb_id") RequestBody fb_id,
                                                 @Part("phone") RequestBody phone,
                                                 @Part("country_code") RequestBody country_code,
                                                 @Part("gender") RequestBody gender,
                                                 @Part("email") RequestBody email,
                                                 @Part("name") RequestBody name,
                                                 @Part("fcm_token") RequestBody fcm_token,
                                                 @Part("profile_pic\"; filename=\"profile_pic.jpeg\" ") RequestBody profile_pic);

    @Multipart
    @POST("api_gm_registration.php")
    Call<GmailRegisterResponse> api_gm_registration(@Part("gm_reg_token") RequestBody gm_reg_token,
                                                    @Part("gmail_id") RequestBody gmail_id,
                                                    @Part("phone") RequestBody phone,
                                                    @Part("country_code") RequestBody country_code,
                                                    @Part("gender") RequestBody gender,
                                                    @Part("email") RequestBody email,
                                                    @Part("name") RequestBody name,
                                                    @Part("fcm_token") RequestBody fcm_token,
                                                    @Part("profile_pic\"; filename=\"profile_pic.jpeg\" ") RequestBody profile_pic);

    @Multipart
    @POST("api_twitter_registration.php")
    Call<TwitterRegisterResponse> api_twitter_registration(@Part("tw_reg_token") RequestBody gm_reg_token,
                                                           @Part("tw_id") RequestBody gmail_id,
                                                           @Part("phone") RequestBody phone,
                                                           @Part("country_code") RequestBody country_code,
                                                           @Part("gender") RequestBody gender,
                                                           @Part("email") RequestBody email,
                                                           @Part("name") RequestBody name,
                                                           @Part("fcm_token") RequestBody fcm_token,
                                                           @Part("profile_pic\"; filename=\"profile_pic.jpeg\" ") RequestBody profile_pic);

    @FormUrlEncoded
    @POST("api_twitter_registration.php")
    Call<TwitterRegisterResponse> api_twitter_registration1(@Field("tw_reg_token") String gm_reg_token,
                                                            @Field("tw_id") String gmail_id,
                                                            @Field("phone") String phone,
                                                            @Field("country_code") String country_code,
                                                            @Field("gender") String gender,
                                                            @Field("email") String email,
                                                            @Field("name") String name,
                                                            @Field("fcm_token") String fcm_token);


    @FormUrlEncoded
    @POST("api_instagram_registration.php")
    Call<InstagramRegisterResponse> api_instagram_registration1(@Field("insta_reg_token") String insta_reg_token,
                                                                @Field("insta_id") String insta_id,
                                                                @Field("phone") String phone,
                                                                @Field("country_code") String country_code,
                                                                @Field("gender") String gender,
                                                                @Field("email") String email,
                                                                @Field("name") String name,
                                                                @Field("fcm_token") String fcm_token);
    /*    @Multipart
    @POST("api_instagram_registration.php")
    Call<InstagramRegisterResponse> api_instagram_registration(@Part("insta_reg_token") RequestBody insta_reg_token,
                                                               @Part("insta_id") RequestBody insta_id,
                                                               @Part("phone") RequestBody phone,
                                                               @Part("country_code") RequestBody country_code,
                                                               @Part("gender") RequestBody gender,
                                                               @Part("email") RequestBody email,
                                                               @Part("name") RequestBody name,
                                                               @Part("fcm_token") RequestBody fcm_token,
                                                               @Part("profile_pic\"; filename=\"profile_pic.jpeg\" ") RequestBody profile_pic);*/

    @FormUrlEncoded
    @POST("api_gm_login.php")
    Call<GmailTempResponse> api_gm_login(@Field("gm_tmp_token") String gm_tmp_token,
                                         @Field("gender") String gender,
                                         @Field("name") String name,
                                         @Field("email") String email,
                                         @Field("gmail_id") String gmail_id,
                                         @Field("fcm_token") String fcm_token);

    @FormUrlEncoded
    @POST("api_fb_login.php")
    Call<FBTempResponse> api_fb_login(@Field("fb_tmp_token") String fb_tmp_token,
                                      @Field("gender") String gender,
                                      @Field("name") String name,
                                      @Field("email") String email,
                                      @Field("fb_id") String fb_id,
                                      @Field("fcm_token") String fcm_token);




    /*@Streaming
    @GET
    Call<ResponseBody> getVideo(@Url String url);*/

    /*@Multipart
    @POST("service.php?op=upload_user_video")
    Call<UploadVideoDataResponse> upload_user_video(@Part("created_user_id") RequestBody created_user_id,
                                                    @Part("merged_video\"; filename=\"merged_video.mp4\" ") RequestBody merged_video,
                                                    @Part("user_recorded_clip\"; filename=\"user_recorded_clip.mp4\" ") RequestBody user_recorded_clip,
                                                    @Part("template_video_id") RequestBody template_video_id,
                                                    @Part("description") RequestBody description,
                                                    @Part("title") RequestBody title,
                                                    @Part("merged_video_thumb\"; filename=\"merged_video_thumb.png\" ") RequestBody merged_video_thumb,
                                                    @Part("share_thumb\"; filename=\"share_thumb.png\" ") RequestBody share_thumb);*/


    @FormUrlEncoded
    @POST("api_user_details.php")
    Call<UserData> api_user_details(@Field("user_token") String user_token,
                                    @Field("uid") String uid);


    @FormUrlEncoded
    @POST("api_update_profile.php")
    Call<BaseDataResponse> api_update_profile(
            @Field("profile_token") String user_token,
            @Field("uid") String uid,
            @Field("name") String name,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("user_link") String user_link,
            @Field("status") String status);


    @FormUrlEncoded
    @POST(ConstantValue.Api_user_daily_rewrard_status)
    Call<ResponseBody> Api_user_daily_rewrard_status(
            @Field("login_coin_token") String login_coin_token,
            @Field("uid") String uid,
            @Field("gender") String gender);

    @FormUrlEncoded
    @POST(ConstantValue.Api_daily_checkin)
    Call<ResponseBody> Api_Daily_chekin(@Field("login_coin_token") String token,
                                        @Field("uid") String uid);


    @FormUrlEncoded
    @POST("api_connect_call.php")
    Call<ConnectCallResponse> api_connect_call(@Field("connection_token") String connection_token,
                                               @Field("uid") String uid,
                                               @Field("gender") String gender,
                                               @Field("join_call_type") String join_call_type);


    @FormUrlEncoded
    @POST("api_is_block_user.php")
    Call<Is_blockedResponse> api_is_block_user(@Field("block_token") String gift_token,
                                               @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_delete_call_id.php")
    Call<Is_blockedResponse> api_is_delete_user(@Field("delete_token") String gift_token,
                                                @Field("uid") String uid);


    @FormUrlEncoded
    @POST("api_gift_list.php")
    Call<ResponseBody> api_gift_list(@Field("gift_token") String gift_token);

    @FormUrlEncoded
    @POST("api_insert_like.php")
    Call<InsertorFetchLikeCallResponse> api_insert_like(@Field("insert_like_token") String user_token,
                                                        @Field("liked_by") String uid,
                                                        @Field("liked_to") String liked_to);

    @FormUrlEncoded
    @POST("api_fetch_like.php")
    Call<InsertorFetchLikeCallResponse> api_fetch_like(@Field("fetch_like_token") String user_token,
                                                       @Field("uid") String uid);


    @FormUrlEncoded
    @POST("api_join_call.php")
    Call<JoinCallRespose> api_join_call(@Field("join_call_token") String join_call_token,
                                        @Field("uid") String uid,
                                        @Field("user_ip") String user_ip,
                                        @Field("to_user_id") String to_user_id);

    @FormUrlEncoded
    @POST("api_insert_live_details.php")
    Call<BaseDataResponse> api_insert_live_details(@Field("live_token") String live_token,
                                                   @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_update_live_details.php")
    Call<BaseDataResponse> api_update_live_details(@Field("live_token") String live_token,
                                                   @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_end_call.php")
    Call<EndCallRespose> api_end_call(@Field("end_call_token") String end_call_token,
                                      @Field("call_id") String call_id);

    @FormUrlEncoded
    @POST("api_packages_list.php")
    Call<ResponseBody> api_package_list(@Field("package_token") String token,
                                        @Field("country_id") String country_id);

    @FormUrlEncoded
    @POST("api_send_gift.php")
    Call<SendGiftResponse> api_send_gift(@Field("send_gift_token") String send_gift_token,
                                         @Field("call_id") String call_id,
                                         @Field("gift_id") String gift_id,
                                         @Field("no_of_gift") String no_of_gift,
                                         @Field("sender_id") String sender_id,
                                         @Field("receiver_id") String receiver_id);

    @FormUrlEncoded()
    @POST("api_receive_gift.php")
    Call<RecevieGiftResponse> api_receive_gift(@Field("rec_gift_token") String Token,
                                               @Field("uid") String uid,
                                               @Field("sender_id") String sender_id);


    @FormUrlEncoded
    @POST("api_receive_message.php")
    Call<ReceiveGiftResponse> api_receive_message(@Field("rec_message_token") String rec_message_token,
                                                  @Field("uid") String uid, @Field("sender_id") String sender_id);


    @FormUrlEncoded
    @POST("api_get_invitation_code.php")
    Call<InvitationCodeResponse> api_get_invitations_code(@Field("code_token") String Token,
                                                          @Field("uid") String UserID);

    @FormUrlEncoded
    @POST("api_deactivate_account.php")
    Call<BaseDataResponse> api_deactivate_account(@Field("deactivate_token") String deactivate_token,
                                                  @Field("uid") String uid);


    @FormUrlEncoded
    @POST("api_followers_list.php")
    Call<ResponseBody> api_followers_list(@Field("follower_token") String FollowerToken,
                                          @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_follow_user.php")
    Call<BaseDataResponse> api_follow_user(@Field("follow_token") String follow_token,
                                           @Field("followed_by") String followed_by,
                                           @Field("following_to") String following_to);


    @FormUrlEncoded
    @POST("api_report_user.php")
    Call<BaseDataResponse> api_report_user(@Field("report_token") String report_token,
                                           @Field("reported_by") String reported_by,
                                           @Field("reporting_to") String reporting_to,
                                           @Field("remarks") String remarks,
                                           @Field("reason_id") String reason_id,
                                           @Field("type") String report);

    @FormUrlEncoded
    @POST("api_report_user.php")
    Call<BaseDataResponse> api_report_block_user(@Field("report_token") String report_token,
                                                 @Field("reported_by") String reported_by,
                                                 @Field("reporting_to") String reporting_to,
                                                 @Field("remarks") String remarks,
                                                 @Field("reason_id") String reason_id,
                                                 @Field("type") String report_bolck);

    @Multipart
    @POST("api_feedback.php")
    Call<BaseDataResponse> api_feedback(@Part("feedback_token") RequestBody feedbackToken,
                                        @Part("uid") RequestBody Uid,
                                        @Part("feedback") RequestBody feedback,
                                        @Part("user_pic\"; filename=\"feedback_pic1.jpeg\" ") RequestBody user_pic,
                                        @Part("user_pic2\"; filename=\"feedback_pic2.jpeg\" ") RequestBody user_pic2);


    @FormUrlEncoded
    @POST("api_user_monthly_rewrard_status_list.php")
    Call<CheckInResponse> api_user_monthly_rewrard_status_list(@Field("monthly_reward_token") String reward_token,
                                                               @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_country_hide_or_show.php")
    Call<ResponseBody> api_country_hide_or_show(@Field("country_token") String country_token,
                                                @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_update_country.php")
    Call<UpdateCountryResponse> api_update_country(@Field("country_token") String country_token,
                                                   @Field("uid") String uid,
                                                   @Field("country_code") String country_code);

    @FormUrlEncoded
    @POST("api_following_list.php")
    Call<ResponseBody> api_following_list(@Field("following_token") String following_token,
                                          @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_terms_and_conditions.php")
    Call<TermsAndConditionResponse> api_terms_and_conditions(@Field("terms_token") String Token);

    @FormUrlEncoded
    @POST("api_verify_invitation_code.php")
    Call<BaseDataResponse> api_verify_invitation_code(@Field("verify_token") String verify_token,
                                                      @Field("invitation_code") String invitation_code);

    /* @FormUrlEncoded
     @POST("api_user_live_details.php")
     Call<ResponseBody> api_user_live_details(@Field("live_token") String Token,
                                              @Field("uid") String user_id,
                                              @Field("from_date") String from_date,
                                              @Field("to_date") String to_date);
 */
    @FormUrlEncoded
    @POST("api_user_live_details.php")
    Call<JsonObject> api_user_live_details(@Field("live_token") String Token,
                                           @Field("uid") String user_id,
                                           @Field("from_date") String from_date,
                                           @Field("to_date") String to_date);

    @FormUrlEncoded
    @POST("api_sticker_list.php")
    Call<JsonObject> api_sticker_list(@Field("sticker_token") String Token);

    @FormUrlEncoded
    @POST("api_send_sticker.php")
    Call<BaseDataResponse> api_send_sticker(@Field("sticker_token") String sticker_token,
                                            @Field("sticker_id") String sticker_id,
                                            @Field("sender_id") String sender_id,
                                            @Field("receiver_id") String receiver_id);

    @FormUrlEncoded
    @POST("api_weekly_rank.php")
    Call<RankList> api_weekly_rank(@Field("weekly_rank_token") String RankToken);

    @FormUrlEncoded
    @POST("api_daily_rank.php")
    Call<RankList> api_daily_rank(@Field("daily_rank_token") String RankToken);

    @FormUrlEncoded
    @POST("api_is_following_user.php")
    Call<BaseDataResponse> api_is_following_user(@Field("follow_token") String follow_token,
                                                 @Field("followed_by") String followed_by,
                                                 @Field("following_to") String following_to);

    @FormUrlEncoded
    @POST("api_close_app.php")
    Call<BaseDataResponse> api_close_app(@Field("close_app_token") String close_app_token,
                                         @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_logout.php")
    Call<BaseDataResponse> api_logout(@Field("logout_token") String logout_token,
                                      @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_join_call_with_channel_key.php")
    Call<JoinCallRespose> api_join_call_with_channel_key(@Field("join_call_token") String join_call_token,
                                                         @Field("uid") String uid,
                                                         @Field("user_ip") String user_ip,
                                                         @Field("to_user_id") String to_user_id);

    @FormUrlEncoded
    @POST("api_live_channel_key.php")
    Call<JoinCallRespose> api_live_channel_key(@Field("live_key_token") String live_key_token,
                                               @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_submit_invitation_code.php")
    Call<BaseDataResponse> api_submit_invitation_code(@Field("submit_token") String submit_token,
                                                      @Field("invitation_code") String invitation_code,
                                                      @Field("uid") String uid);


    @FormUrlEncoded
    @POST("api_set_preferred_gender.php")
    Call<BaseDataResponse> api_set_preferred_gender(@Field("preferred_token") String preferred_token,
                                                    @Field("uid") String uid,
                                                    @Field("preferred_gender") String preferred_gender);

    @FormUrlEncoded
    @POST("api_deduct_coin_per_minute.php")
    Call<CoinPerMinutesResponse> api_deduct_coin_per_minute(@Field("time_coin_token") String time_coin_token);

    @FormUrlEncoded
    @POST("api_update_fcm_token.php")
    Call<BaseDataResponse> api_update_fcm_token(@Field("token") String token,
                                                @Field("uid") String uid,
                                                @Field("fcm_token") String fcm_token);

    @FormUrlEncoded
    @POST("api_auto_login.php")
    Call<BaseDataResponse> api_auto_login(@Field("auto_login") String Token,
                                          @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_update_login_token.php")
    Call<BaseDataResponse> api_update_login_token(@Field("update_login") String update_login,
                                                  @Field("uid") String uid,
                                                  @Field("login_token") String login_token);

    @FormUrlEncoded
    @POST("api_send_gift_coin")
    Call<BaseDataResponse> api_send_gift_coin(@Field("coin_token") String Token,
                                              @Field("coin_no") String coin_no,
                                              @Field("call_id") String coin_token,
                                              @Field("sender_id") String sender_id,
                                              @Field("receiver_id") String receiver_id);

    @FormUrlEncoded
    @POST("api_user_block_list.php")
    Call<ResponseBody> api_user_block_list(@Field("block_token") String block_token,
                                           @Field("uid") String uid);

    @FormUrlEncoded
    @POST("api_unblock_user.php")
    Call<BaseDataResponse> api_unblock_user(@Field("unblock_token") String unblock_token,
                                            @Field("uid") String uid,
                                            @Field("uid_list") String uid_list);

    @FormUrlEncoded
    @POST("api_block_words_list.php")
    Call<BlockWords> api_block_words_list(@Field("block_token") String blockwords);

    @FormUrlEncoded
    @POST("api_transactions_list.php")
    Call<TransactionlistResponse> api_transactions_list (@Field("transaction_token") String transaction_token,
                                                         @Field("uid") String uid);






}
