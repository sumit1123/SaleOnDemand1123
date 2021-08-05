package com.example.grocery.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.CustomViews.CustomEditText;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.grocery.Appearances.Appearance.appSettings;

public class Contact_Us extends AppCompatActivity {
    AppCompatEditText name, contact_no, email, subject, comment;
    Button submit;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        TextView cartCount=(TextView)findViewById(R.id.actionbar_notifcation_textview) ;
        TextView notificationCount = (TextView) findViewById(R.id.notification_count_textview);
        if (Dashboard.cart_count !=0){
            cartCount.setVisibility(View.VISIBLE);
            cartCount.setText(String.valueOf(Dashboard.cart_count));
        }
        if(Dashboard.notification_count!=0)
        {
            notificationCount.setVisibility(View.VISIBLE);
            notificationCount.setText(String.valueOf(Dashboard.notification_count));
        }
        RelativeLayout toolBarNotification=(RelativeLayout) findViewById(R.id.toolnotification) ;
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Contact_Us.this,NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (appSettings.getIs_notification() == 0) {
            toolBarNotification.setVisibility(View.GONE);
        }

        String contact_us = getString(R.string.contact_us);
        toolbarTitle.setText(contact_us);
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        name = (AppCompatEditText) findViewById(R.id.username);
        contact_no = (AppCompatEditText) findViewById(R.id.contact);
        email = (AppCompatEditText) findViewById(R.id.email);
        subject = (AppCompatEditText) findViewById(R.id.subject);
        comment = (AppCompatEditText) findViewById(R.id.comment);
        submit = (Button) findViewById(R.id.submit);
        new EditTextColorChanger(this, name);
        new EditTextColorChanger(this, contact_no);
        new EditTextColorChanger(this, email);
        new EditTextColorChanger(this, subject);
        new EditTextColorChanger(this, comment);
        submit.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
        submit.setBackground(Contact_Us.this.getResources().getDrawable(R.drawable.buttonshape));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().matches("")) {
                    try {
                        name.requestFocus();
                        name.setError("Please Enter Name");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else if (contact_no.getText().toString().matches("")) {
                    try {
                        contact_no.requestFocus();
                        contact_no.setError("Please Enter contact no");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
                else if (email.getText().toString().matches("")) {
                    try {
                        email.requestFocus();
                        email.setError("Please Enter email");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
                else if (subject.getText().toString().matches("")) {
                    try {
                        subject.requestFocus();
                        subject.setError("Please Enter  subject");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }
                else if (comment.getText().toString().matches("")) {
                    try {
                        comment.requestFocus();
                        comment.setError("Please Enter comment");
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
        //  String amount = redeem.getText().toString();
        if (token == null) {
            token = "";
            String languageid = prefs.getString("language", String.valueOf(1));
            try {
                jsonObject.put("business_id", IConstants.BUSINESS_ID);
                jsonObject.put("name", name.getText().toString());
                jsonObject.put("token", token);
                jsonObject.put("email", email.getText().toString());
                jsonObject.put("contact_number", contact_no.getText().toString());
                jsonObject.put("subject", subject.getText().toString());
                jsonObject.put("comment", comment.getText().toString());
                jsonObject.put("language_id", languageid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println("sdxcz" + jsonObject);
            findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
            VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_ContactUs, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {
                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);
                    //findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    if (!new ResponseHandler().validateResponse(Contact_Us.this, response)) {
                        return;
                    }
                    System.out.println("hhhs" + response.toString());
                    try {
                        String status = response.getString("status");
                       // Toast.makeText(getApplicationContext(), "" +status, Toast.LENGTH_SHORT).show();
                        if (status.matches("true")) {
                          //  new CustomToast(Contact_Us.this, response.getJSONObject("data").getString("msg"));
                            Toast.makeText(getApplicationContext(), "Contact Us Submitted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {

                }
            });
        }
    }
}

