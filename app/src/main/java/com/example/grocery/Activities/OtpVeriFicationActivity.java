package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.SmsListener;
import com.example.grocery.utils.SmsReceiver;
import com.example.grocery.utils.VolleyTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class OtpVeriFicationActivity extends AppCompatActivity {

    private EditText otpe;
    private TextView retryButton;
    private FloatingActionButton approve;
    private String username;
    private TextView textView;
    private TextView otpvarificationText;
    private TextView otpVerificationdescripton;
    private TextView enterCodeHereText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_otp_veri_fication);
        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#" + Appearance.appSettings.getApp_status_bar_color()));
        }
        otpvarificationText = (TextView) findViewById(R.id.otpvarificationText);
        otpVerificationdescripton = (TextView) findViewById(R.id.otpVerificationdescripton);
        enterCodeHereText = (TextView) findViewById(R.id.enterCodeHereText);
        textView = (TextView) findViewById(R.id.texttimer);
        otpe = (EditText) findViewById(R.id.otpEditText);
        retryButton = (TextView) findViewById(R.id.retrybutton);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendreq();
            }
        });

        try {
            String otp = getString(R.string.otp);
            enterCodeHereText.setText(otp);
            String resendotp = getString(R.string.resend_otp);
            retryButton.setText(resendotp);
            String otpverify = getString(R.string.verification);
            otpvarificationText.setText(otpverify);
            String verifydesc = getString(R.string.otp_msg);
            otpVerificationdescripton.setText(verifydesc);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        FloatingActionButton addTicket = (FloatingActionButton) findViewById(R.id.btnotpverify);
        DrawableCompat.setTint(((FloatingActionButton) findViewById(R.id.btnotpverify)).getDrawable(),
                Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        // addTicket.setBackgroundColor(Color.parseColor("#" + colorSettings.getString("app_color", "#FFFFFF")));
        addTicket.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#" + Appearance.appSettings.getApp_back_color())));


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.otpBackground);
        linearLayout.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        TextView enterCodeHereText = (TextView) findViewById(R.id.enterCodeHereText);
        enterCodeHereText.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        retryButton.setTextColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                textView.setText("");
                retryButton.setVisibility(View.VISIBLE);
            }
        }.start();

        SmsReceiver.bindListener(new SmsListener() {

            @Override

            public void messageReceived(String sender, String messageText) {

                if (sender.equals("HP-" + Appearance.appSettings.getMsg_sender_id())) {

                    Log.i("OTP_Sender", sender);
                    Log.i("OTP_Text", messageText);

                    if (messageText.contains("OTP")) {
                        String otp = messageText.substring(0, 4);
                        otpe.setText(otp);
                        otpe.setSelection(otpe.getText().toString().length());
                    }
                }
            }
        });
        addTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!otpe.getText().toString().matches("")) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
                    JSONObject jsonObject = new JSONObject();
                    JSONArray js = new JSONArray();
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
                    String userid = prefs.getString("user_id", "");

                    try {
                        jsonObject.put("user_id", userid);
                        jsonObject.put("business_id",IConstants.BUSINESS_ID);
                        jsonObject.put("otp", otpe.getText().toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Toast.makeText(TestingActivity.this, "goy", Toast.LENGTH_SHORT).show();

                    VolleyTask volleyTask = new VolleyTask(OtpVeriFicationActivity.this, IConstants.BASE_URL + "verifyOtp", jsonObject, Request.Method.POST);

                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {
                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                            System.out.println("otpresponse" + response.toString());
                            try {
                                String status = response.getString("status");
                                if (status.matches("true")) {

                                    SharedPreferences.Editor editor1 = getSharedPreferences("cheapstuffs", MODE_PRIVATE).edit();
                                    editor1.putBoolean("login", true);
                                    editor1.apply();
                                    Intent intent1 = new Intent(OtpVeriFicationActivity.this, Dashboard.class);
                                    finish();
                                    startActivity(intent1);
                                } else {
                                    new CustomToast(OtpVeriFicationActivity.this, response.getString("data"));
                                }
                            } catch (JSONException e) {
                                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void fnErrorOccurred(String error) {
                            findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                        }
                    });


                } else {
                    new CustomToast(OtpVeriFicationActivity.this, "please enter otp");
                }
            }
        });

    }

    private void sendreq() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        JSONArray js = new JSONArray();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        try {
            jsonObject.put("id", userid);
            jsonObject.put("business_id",IConstants.BUSINESS_ID);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        VolleyTask volleyTask = new VolleyTask(OtpVeriFicationActivity.this, IConstants.BASE_URL + "resendOtp", jsonObject, Request.Method.POST);

        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);


            }

            @Override
            public void fnErrorOccurred(String error) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);


            }
        });
    }

}
