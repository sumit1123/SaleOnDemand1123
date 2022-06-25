package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.SmsListener;
import com.example.grocery.utils.SmsReceiver;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.grocery.interfaces.IConstants.URL_FORGOTPAssword;
import static com.example.grocery.interfaces.IConstants.URL_OTP_FORGOTPASSWORD;

public class ForgotPassword extends AppCompatActivity {

    private AppCompatEditText email;
    private AppCompatEditText otp;
    private LinearLayout visibilityemail, visibilityotp;
    private int user_id;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        visibilityemail = (LinearLayout) findViewById(R.id.visibilityemail);
        email = (AppCompatEditText) findViewById(R.id.number);
        otp = (AppCompatEditText) findViewById(R.id.otp);
        visibilityemail.setVisibility(View.VISIBLE);
        visibilityotp = (LinearLayout) findViewById(R.id.visibilityotp);
        visibilityotp.setVisibility(View.VISIBLE);
        new EditTextColorChanger(this, email);
        new EditTextColorChanger(this, otp);
        SmsReceiver.bindListener(new SmsListener() {

            @Override

            public void messageReceived(String sender, String messageText) {

                Log.d("Text", messageText);
                if (messageText.contains("OTP")) {
                    otp.setText(messageText.replace("OTP : ", ""));
                    otp.setSelection(otp.getText().toString().length());
                }

/*String otp=messageText.replace("your otp is","");
                otpe.setText(otp);*/


            }
        });

        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + Appearance.appSettings.getApp_back_color())));
        getSupportActionBar().setTitle(Html.fromHtml("<font color=" + Color.parseColor("#" + Appearance.appSettings.getApp_text_color()) + ">Password Reset</font>"));
        initUI();


        getSupportActionBar().setTitle("Reset Password");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + Appearance.appSettings.getApp_status_bar_color()));
        }
    }

    private void initUI() {
        visibilityemail = (LinearLayout) findViewById(R.id.visibilityemail);
        visibilityotp = (LinearLayout) findViewById(R.id.visibilityotp);

        email = (AppCompatEditText) findViewById(R.id.number);
        Button button = (Button) findViewById(R.id.continuue);
        otp = (AppCompatEditText) findViewById(R.id.otp);

        Button submitOtp = (Button) findViewById(R.id.submitOtp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList stateListDrawable = new ColorStateList(
                    new int[][]
                            {
                                    new int[]{android.R.attr.state_pressed},
                                    new int[]{android.R.attr.state_focused},
                                    new int[]{android.R.attr.state_activated},
                                    new int[]{}
                            },
                    new int[]
                            {
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color()),
                                    Color.parseColor("#" + Appearance.appSettings.getApp_back_color())
                            }
            );
            button.setBackgroundTintList(stateListDrawable);
            button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
            submitOtp.setBackgroundTintList(stateListDrawable);
            submitOtp.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        } else {
            button.setBackground(ForgotPassword.this.getResources().getDrawable(R.drawable.buttonshape));

            submitOtp.setBackground(ForgotPassword.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) submitOtp.getBackground();
            bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
            button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
            submitOtp.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

        }

        submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postOTP();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReq();

            }
        });
    }

    private void postOTP() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();

        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String languageid = prefs.getString("language", String.valueOf(1));
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("otp", otp.getText().toString());
            jsonObject.put("user_id", user_id);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("sxz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(ForgotPassword.this, URL_OTP_FORGOTPASSWORD, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(ForgotPassword.this, response)) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    return;
                }

                try {
                    new CustomToast(ForgotPassword.this, response.getJSONObject("data").getString("msg"));
                    Intent intent = new Intent(ForgotPassword.this, PasswordResetActivity.class);
                    finish();
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                /*    JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                    String s = jsonObject1.getString("id");
                    String user_name = jsonObject1.getString("email");
                    // String pincode = jsonObject1.getString("pincode");
                    //String address=jsonObject1.getString("address");
                    SharedPreferences.Editor editor = getSharedPreferences("UserId", MODE_PRIVATE).edit();
                    editor.putString("user_id", s);
                    editor.putString("username", jsonObject1.getString("name"));

                    editor.putString("email", user_name);
                   editor.putString("pwd", jsonObject1.getString("password"));
                    //  editor.putString("pincode", pincode);


                    //System.out.println("wesdx" + user_name + password.getText().toString());
                    editor.apply();

                    SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                    editor1.putBoolean("login", true);
                    editor1.apply();
                    Intent intent = new Intent(ForgotPassword.this, Dashboard.class);
                    finish();*/
                    //startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);


            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });
    }


    private void sendReq() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String languageid = prefs.getString("language", String.valueOf(1));


        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("email", email.getText().toString());
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("sxz" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(ForgotPassword.this, URL_FORGOTPAssword, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(ForgotPassword.this, response)) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    return;
                }

                try {
                    new CustomToast(ForgotPassword.this, response.getJSONObject("data").getString("msg"));
                    user_id = response.getJSONObject("data").getJSONObject("data").getInt("id");
                    visibilityemail.setVisibility(View.GONE);
                    visibilityotp.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);


            }

            @Override
            public void fnErrorOccurred(String error) {
            }
        });
    }
}
