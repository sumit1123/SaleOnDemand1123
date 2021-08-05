package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.NotificationAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.NotificationModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorChangerNoItemAvailable;
import com.example.grocery.utils.LoaderColorChanger;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class NotificationActivity extends AppCompatActivity {

    private TextView cartCount,notificationCount;
    private boolean shouldExecuteOnResume;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    public static int noti_id;
    List<NotificationModel> list;
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_notification);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
        cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
       notificationCount = (TextView) findViewById(R.id.notification_count_textview);
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
                Intent intent =new Intent(getApplicationContext(),NotificationActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolbarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;
                isViewWithCatalog = false;
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
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
                Intent intent = new Intent(NotificationActivity.this, CartActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String notification = getString(R.string.notifications);
            toolbarTitle.setText(notification);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading = true;
                getData();


            }
        });
        getData();
    }

    protected void getData() {
        if (!hideloading) {
            findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.retryImage).setVisibility(View.GONE);


        Button retryButton = (Button) findViewById(R.id.retrybutton);
        retryButton.setVisibility(View.GONE);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
        progressBar.setVisibility(View.VISIBLE);

        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String languageid = prefs.getString("language", String.valueOf(1));
        String userid = prefs.getString("user_id", "");
        String pwd = prefs.getString("pwd", "");
        try {
            jsonObject.put("notification_id",noti_id);
            jsonObject.put("business_id",IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("wedszx" + jsonObject);
        VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_NOtification, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                refreshLayout.setRefreshing(false);
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                try {
                    JSONArray jsonArray1 = response.getJSONObject("data").getJSONArray("data");
                    //Toast.makeText(NotificationActivity.this, ""+jsonArray1, Toast.LENGTH_SHORT).show();
                   // noti_id = response.getJSONObject("data").getInt("notification_id");
                    //NotificationActivity.noti_id  = response.getJSONObject("data").getInt("notification_id");
                     noti_id = jsonObject.getInt("notification_id");
                    //Toast.makeText(NotificationActivity.this, ""+noti_id, Toast.LENGTH_SHORT).show();

                    if (jsonArray1.length() == 0) {

                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);
                        Button button = (Button) findViewById(R.id.noitemavailablebutton);
                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                        String empty_notification = getString(R.string.empty_notification_title);
                        textView.setText(empty_notification);
                        findViewById(R.id.imagenoitemAvailable).setBackground(NotificationActivity.this.getResources().getDrawable(R.drawable.empty_notification_default));

                        TextView textView1 = (TextView) findViewById(R.id.extra);
                        String empty_notification_desc = getString(R.string.empty_notification_description);
                        textView1.setText(empty_notification_desc);
                        button.setBackground(NotificationActivity.this.getResources().getDrawable(R.drawable.buttonshape));

                        button.setBackground(NotificationActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                        GradientDrawable bgShape = (GradientDrawable) button.getBackground();
                        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        button.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

                        String back = getString(R.string.back);
                        button.setText(back);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                    } else {
                        setData(jsonArray1);
                    }
                } catch (JSONException e) {
                    refreshLayout.setRefreshing(false);

                    e.printStackTrace();
                }
            }

            @Override
            public void fnErrorOccurred(String error) {
                refreshLayout.setRefreshing(false);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);

                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);
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
        List<NotificationModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), NotificationModel[].class));
        System.out.println("dada" + list);
        RecyclerView notificationRecycler = (RecyclerView) findViewById(R.id.notificationrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        notificationRecycler.setLayoutManager(layoutManager);
        NotificationAdapter displayImageAdapter = new NotificationAdapter(this, list);
        notificationRecycler.setAdapter(displayImageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(NotificationActivity.this);

    }
}
