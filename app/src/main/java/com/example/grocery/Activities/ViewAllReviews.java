package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.ReviewAdpater;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.ReviewsModel;
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

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.BASE_URL;
import static com.example.grocery.interfaces.IConstants.URL_ALLReviews;

public class ViewAllReviews extends AppCompatActivity {

    private int productid;
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
        setContentView(R.layout.activity_view_all_reviews);
        //labelsShared = getSharedPreferences("labels", MODE_PRIVATE);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        RelativeLayout toolbarBack = (RelativeLayout) findViewById(R.id.toolimage);
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        TextView cartCount = (TextView) findViewById(R.id.actionbar_notifcation_textview);
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
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllReviews.this, NotificationActivity.class);
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
                Intent intent = new Intent(ViewAllReviews.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                SearchActivity.searchMin = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.minimumSeekBar = "0";
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

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
                Intent intent = new Intent(ViewAllReviews.this, CartActivity.class);
                startActivity(intent);
            }
        });
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String review = getString(R.string.view_all_reviews);
            toolbarTitle.setText(review);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Bundle extras = getIntent().getExtras();
        productid = extras.getInt("takeproductid");
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading=true;
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
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);
        String languageid = prefs.getString("language", String.valueOf(1));

        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String pwda = prefs.getString("pwd", "");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("product_id", productid);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, URL_ALLReviews, jsonObject, Request.Method.POST);
        System.out.println(BASE_URL + "getAllReviews/" + productid);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                refreshLayout.setRefreshing(false);
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(ViewAllReviews.this, response)) {
                    return;
                }
                try {

                    JSONArray jsonArray = response.getJSONObject("data").getJSONObject("data").getJSONArray("reviews");
                    Dashboard.cart_count = response.getJSONObject("data").getJSONObject("data").getInt("cart_count");
                    Dashboard.notification_count = response.getJSONObject("data").getJSONObject("data").getInt("notification_count");
                    setReviews(jsonArray);

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


                        Button toolbarButton = (Button) findViewById(R.id.retrybutton);
                        toolbarButton.setVisibility(View.VISIBLE);
                        toolbarButton.setOnClickListener(new View.OnClickListener() {
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

    private void setReviews(JSONArray jsonArray) {
        Gson gson = new Gson();
        List<ReviewsModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), ReviewsModel[].class));
        System.out.println("dada" + list);
        if (list.size() == 0) {
            TextView size = (TextView) findViewById(R.id.noreview);
            size.setVisibility(View.VISIBLE);
        }
        RecyclerView viewAllReviewRecycler = (RecyclerView) findViewById(R.id.reviewsrec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        ReviewAdpater viewAllReviewsAdapter = new ReviewAdpater(this, list);
        viewAllReviewRecycler.setLayoutManager(layoutManager);

        viewAllReviewRecycler.setAdapter(viewAllReviewsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(ViewAllReviews.this);

    }
}
