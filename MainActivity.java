package com.vmc.park;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vmc.park.Utils.PearlTextUtils;
import com.vmc.park.Utils.Preferences;
import com.vmc.park.Utils.StaticData;
import com.vmc.park.api.APIInterface;
import com.vmc.park.api.APIRequest;
import com.vmc.park.api.Dashboad;
import com.vmc.park.api.LoginModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button LoginBtn;
    TextView tvForgotPassword;
//    ProgressDialog progressDialog;
    EditText etEmail;
    EditText etPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginBtn = (Button) findViewById(R.id.loginBtn);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        LoginBtn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Please wait");

        etEmail = findViewById(R.id.et_email);
        etPassWord = findViewById(R.id.et_password);

        if (Preferences.getBoolean(StaticData.IsLogin)){
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn) {


//            api_Login("vmcuser", "vmcuser@1234");
            if (PearlTextUtils.isBlank(etEmail.getText().toString())){
                Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
            }else if (PearlTextUtils.isBlank(etPassWord.getText().toString())){
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }else {
                String userName = etEmail.getText().toString();
                String password= etPassWord.getText().toString();


//                progressDialog.show();
//                api_Login(userName, password);
                new LoginapiCall().execute(userName,password);

            }





//            startActivity(new Intent( MainActivity.this, HomeActivity.class));


        } else if (view.getId() == R.id.tv_forgot_password) {
            startActivity(new Intent(MainActivity.this, ForgetPasswordActivity.class));

        }


    }


    class LoginapiCall extends AsyncTask<String, Void, String> {
        okhttp3.Response response;


        ProgressDialog  dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client;
            try {


                client = getUnsafeOkHttpClient().build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Log.e("TAg", "doInBackground: "+"https://devapi.mpower-education.com/api/Authentication/Login?userName="+strings[0]+"&password="+strings[1]);

            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://devapi.mpower-education.com/api/Authentication/Login?userName="+strings[0]+"&password="+strings[1])
                    .method("GET", null)
                    .build();
            try {
                Log.e("TAG", "doInBackground: " );
                  response = client.newCall(request).execute();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "null";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if (response.isSuccessful()) {
                try {
                    Gson gson = new Gson();
                    LoginModel responseResult = gson.fromJson(response.body().string(), LoginModel.class);
                    responseResult.getStatus();
                    Log.e("TAG", "doInBackground: " + responseResult);
                    Preferences.setUserProfile(responseResult);
                    Preferences.setBoolean(StaticData.IsLogin, true);
//                progressDialog.dismiss();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
//                setData(responseResult);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {

                try {
                    Gson gson = new Gson();
                    LoginModel responseResult = gson.fromJson(response.body().string(), LoginModel.class);
//                    String s = response.errorBody().string().toString().trim();

//                    JSONObject object = new JSONObject(s.toString());



                    Log.e("Hardik", "onResponseFailss: " + s);
//                    Log.e("Hardik", "onResponseFailss: " + object.getString("message"));
//                    Toast.makeText(MainActivity.this, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                    // Create the object of AlertDialog Builder class
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setMessage(""+responseResult.getMessage());
                    // Set the message show for the Alert time
//                    builder.setMessage(""+object.getString("message").replace("[","").replace("]",""));

                    // Set Alert Title
                    builder.setTitle("Alert !");

                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false);

                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        // When the user click yes button then app will close
                        dialog.dismiss();
                    });



                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
//                           callback.onResponseFail(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String
                                authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String
                                authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } }

    public void api_Login(String UserName, String Password) {
        APIRequest apiRequest = new APIRequest();
        try {
            APIInterface apiInterface = apiRequest.ApiRequest(null);

            Call<LoginModel> requestCall = apiInterface.api_login(UserName, Password);
            requestCall.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    if (response.isSuccessful()) {
//                        callback.ResponseSuccessCallBack(response.body());
//                        Log.e("TAG", "onResponse: "+response.body() );

                        LoginModel loginModel = (LoginModel) response.body();
                        Log.e("TAG", "onResponse: " + loginModel.getMessage());
                        Preferences.setUserProfile(loginModel);
                        Preferences.setBoolean(StaticData.IsLogin,true);
//                        progressDialog.dismiss();
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    } else {
                        try {
                            String s = response.errorBody().string().toString().trim();

                            JSONObject object = new JSONObject(s.toString());



                            Log.e("Hardik", "onResponseFailss: " + s);
                            Log.e("Hardik", "onResponseFailss: " + object.getString("message"));
                            Toast.makeText(MainActivity.this, ""+object.getString("message"), Toast.LENGTH_SHORT).show();
                            // Create the object of AlertDialog Builder class
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            // Set the message show for the Alert time
                            builder.setMessage(""+object.getString("message").replace("[","").replace("]",""));

                            // Set Alert Title
                            builder.setTitle("Alert !");

                            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                            builder.setCancelable(false);

                            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                            builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                                // When the user click yes button then app will close
                               dialog.dismiss();
                            });



                            // Create the Alert dialog
                            AlertDialog alertDialog = builder.create();
                            // Show the Alert Dialog box
                            alertDialog.show();
//                           callback.onResponseFail(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
//                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
//                    callback.ResponseFailCallBack(t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}