package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.OnlineChatAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.OnlineChatModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class OnlineChatActivity extends AppCompatActivity {

    private String complaint_id;
    private String email;
    private String pwd;
    private String status;
    private EditText messeage;
    private String toolbarTitle;
    private TextView cartCount;
    private boolean shouldExecuteOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        complaint_id = extras.getString("takemycomplaintid");
        toolbarTitle
                = extras.getString("takecomplaintname");
        setContentView(R.layout.activity_online_chat);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
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
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);

        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineChatActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineChatActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ApplyFilter.brandArrays = null;
                SearchActivity.sortedida = 0;
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        RelativeLayout toolbarCart = (RelativeLayout) findViewById(R.id.cart);
        toolbarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineChatActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolbarText = (TextView) findViewById(R.id.titlebar);
        toolbarText.setText(toolbarTitle);
        messeage = (EditText) findViewById(R.id.messege);

        try {
            String msg = getString(R.string.please_type_your_query);
            messeage.setHint(msg);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        ImageView sendButton = (ImageView) findViewById(R.id.sendmessege);
       /* final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                onResume();
                handler.postDelayed(this, delay);
            }
        }, delay);*/
        getData();
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (messeage.getText().toString().matches("")) {

                } else {
                    JSONObject jsonObject = new JSONObject();
                    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

                    String userid = prefs.getString("user_id", "");
                    email = prefs.getString("email", "");
                    pwd = prefs.getString("pwd", "");
                    String languageid = prefs.getString("language", String.valueOf(1));

                    try {
                        jsonObject.put("business_id",IConstants.BUSINESS_ID);
                        jsonObject.put("id", userid);
                        jsonObject.put("password", pwd);
                        jsonObject.put("complaint_id", complaint_id);
                        jsonObject.put("message", messeage.getText().toString());
                        jsonObject.put("language_id", languageid);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    VolleyTask volleyTask = new VolleyTask(OnlineChatActivity.this, IConstants.URLPOSTMESSEGE, jsonObject, Request.Method.POST);
                    volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                        @Override
                        public void fnPostTaskCompleted(JSONArray response) {

                        }

                        @Override
                        public void fnPostTaskCompletedJsonObject(JSONObject response) {
                            System.out.println("hhhsa" + response.toString());

                            if (!new ResponseHandler().validateResponse(OnlineChatActivity.this, response)) {

                                return;
                            }
                            onResume();
                            messeage.setText("");
                        }

                        @Override
                        public void fnErrorOccurred(String error) {
                        }
                    });
                }

            }
        });
    }

    protected void getData() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

        findViewById(R.id.retryImage).setVisibility(View.GONE);

        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);


        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String userid = prefs.getString("user_id", "");
        email = prefs.getString("email", "");
        pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("complaint_id", complaint_id);
            System.out.println("cdsxz" + userid);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URLgetMESSEGE, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.whiteloader).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(OnlineChatActivity.this, response)) {

                    return;
                }
                try {
                    System.out.println("response" + response);
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONArray("data");
                    setData(jsonArray1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);


                        Button imageView = (Button) findViewById(R.id.retrybutton);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData();
                            }
                        });
                    }
                }, 2000);


            }
        });
    }

    private void setData(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<OnlineChatModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), OnlineChatModel[].class));
        System.out.println("dada" + list);
        RecyclerView recyclerOnlineComment = (RecyclerView) findViewById(R.id.reconlinechat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OnlineChatActivity.this);

        recyclerOnlineComment.setLayoutManager(layoutManager);

        OnlineChatAdapter onlineChatAdapter = new OnlineChatAdapter(this, list);
        recyclerOnlineComment.scrollToPosition(list.size() - 1);
        layoutManager.scrollToPosition(list.size() - 1);

        recyclerOnlineComment.setAdapter(onlineChatAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(OnlineChatActivity.this);

        getData();


    }
}
