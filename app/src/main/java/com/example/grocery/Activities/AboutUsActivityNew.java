package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Html;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.BusinessAdapter;
import com.example.grocery.Adapter.OrderAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.BusinessModel;
import com.example.grocery.Model.MyOrderModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorChangerNoItemAvailable;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;

public class AboutUsActivityNew extends AppCompatActivity {

    private String email, pwd;
    private ProgressBar relativeLayout;
    private TextView cartCount;
    private boolean shouldExecuteOnResume;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.new_activity_about_us);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(AboutUsActivityNew.this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(AboutUsActivityNew.this);
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
                Intent intent = new Intent(AboutUsActivityNew.this, NotificationActivity.class);
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
                Intent intent = new Intent(AboutUsActivityNew.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

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
                Intent intent = new Intent(AboutUsActivityNew.this, CartActivity.class);

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
        TextView toolBarTitle = (TextView) findViewById(R.id.titlebar);
        try {

            String Aboutus = getString(R.string.about_us);
            toolBarTitle.setText(Aboutus);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        getData();
    }


    protected void getData() {

    findViewById(R.id.retryImage).setVisibility(View.GONE);
    Button retryButton = (Button) findViewById(R.id.retrybutton);

    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);

    JSONObject jsonObject = new JSONObject();
    SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
    String languageid = prefs.getString("language", String.valueOf(1));

    String userid = prefs.getString("user_id", "");
    String pwd = prefs.getString("pwd", "");
        try {
        jsonObject.put("business_id", IConstants.BUSINESS_ID);
        jsonObject.put("id", userid);
        jsonObject.put("password", pwd);
        jsonObject.put("language_id", languageid);

    } catch (JSONException e) {
        e.printStackTrace();
    }
    VolleyTask volleyTask = new VolleyTask(this, IConstants.URL_ABOUTUS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
        @Override
        public void fnPostTaskCompleted(JSONArray response) {

        }

        @Override
        public void fnPostTaskCompletedJsonObject(JSONObject response) {
            System.out.println("hhhsa" + response.toString());


           /* if (!new ResponseHandler().validateResponse(AboutUsActivityNew.this, response)) {
                return;
            }*/

            try {
                JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data");
                setData(jsonObject1.getJSONObject("aboutus"));

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


                    findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

                    Button retryButton = (Button) findViewById(R.id.retrybutton);
                    retryButton.setVisibility(View.VISIBLE);
                    retryButton.setOnClickListener(new View.OnClickListener() {
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

    private void setData(JSONObject response) {
        TextView textView = findViewById(R.id.textview_about_us);
        ImageView imgView = findViewById(R.id.imgview_about_us);
        try {

            String aboutText = Html.fromHtml(response.getJSONObject("default_aboutus_translation")
                    .getString("about_us_description")).toString();
//            textView.setText(response.getJSONObject("default_policy_translation").getString("policy_description").toString());
            textView.setText(aboutText);
            Picasso.with(AboutUsActivityNew.this)
                    .load(IConstants.BASE_IMAGE_URL+response.getJSONObject("default_aboutus_translation")
                            .getString("about_image") )
                    .into(imgView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //holder.image.setImageResource(R.drawable.gallery_default);
                        }
                    });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(AboutUsActivityNew.this);

    }
}