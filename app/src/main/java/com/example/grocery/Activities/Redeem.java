package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Redeem extends AppCompatActivity {
    Button Redeem;
    AppCompatEditText redeem,desc;
    public static boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);
        Redeem = (Button) findViewById(R.id.Redeem);
        Redeem.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       // toolbarTitle.setText("  " + Label.userLabel.getRedeem());
        String redeem_amt = getString(R.string.redeem);
           toolbarTitle.setText(redeem_amt);
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
        RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolBarNotification.setVisibility(View.GONE);
        }
        if (Dashboard.cart_count !=0){
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        if(Dashboard.notification_count!=0)
        {
            notificationCount.setVisibility(View.VISIBLE);
            notificationCount.setText(String.valueOf(Dashboard.notification_count));
        }
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Redeem.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
        Redeem.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        Redeem.setBackground(Redeem.this.getResources().getDrawable(R.drawable.buttonshape));



        Redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redeem = (AppCompatEditText) findViewById(R.id.amount);
                desc = (AppCompatEditText) findViewById(R.id.desc);
                if (redeem.getText().toString().matches("")) {
                    try {
                        redeem.requestFocus();
                        redeem.setError("Please Enter Amount");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else if (desc.getText().toString().matches("")) {
                    try {
                        desc.requestFocus();
                        desc.setError("Please Enter desc");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }

                getdata();
            }
                    });
    }


    private void getdata() {
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");

        String amount =redeem.getText().toString();
        String describe= desc.getText().toString();
        String languageid = prefs.getString("language", String.valueOf(1));
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("amount", amount);
            jsonObject.put("description", describe);
            jsonObject.put("language_id", languageid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("sdxcz" + jsonObject);
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);

        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_Redeem, jsonObject, Request.Method.POST);

        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }
            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                System.out.println("hhhsa" + response.toString());
                if (!new ResponseHandler().validateResponse(Redeem.this, response)) {
                    return;
                }
                try {
                    String status = response.getString("status");
                    if (status.matches("true")) {
                        Toast.makeText(getApplicationContext(),"Redeem successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WalletActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Amount should be less than wallet account",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void fnErrorOccurred(String error) {
              //  Toast.makeText(getApplicationContext(),"Redeem not successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

