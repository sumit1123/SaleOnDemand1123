package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.BusinessAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Model.BusinessModel;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.ColorChangerNoItemAvailable;
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

public class SellerActivity extends AppCompatActivity {

    private String email, pwd;
    private ProgressBar relativeLayout;
    private TextView cartCount;
    private boolean shouldExecuteOnResume;
    private SwipeRefreshLayout refreshLayout;
    private boolean hideloading;
    private Button details;
    public static int sellerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_seller);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        ColorChangerNoItemAvailable colorChangerNoItemAvailable = new ColorChangerNoItemAvailable(this);
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
                Intent intent = new Intent(SellerActivity.this, NotificationActivity.class);
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
                Intent intent = new Intent(SellerActivity.this, SearchActivity.class);
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
                Intent intent = new Intent(SellerActivity.this, CartActivity.class);
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
//            toolBarTitle.setText(Label.navigationLabels.getMy_orders());
            String sellers = getString(R.string.sellers);
            toolBarTitle.setText(sellers);
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
            jsonObject.put("token","");
            jsonObject.put("language_id", languageid);

            Log.v("jsonobjhhhhhhhhhhhh==",""+jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyTask volleyTask = new VolleyTask(this, IConstants.BASE_URL+"getBusinessList", jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhsa" + response.toString());
                refreshLayout.setRefreshing(false);
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                if (!new ResponseHandler().validateResponse(SellerActivity.this, response)) {
                    return;
                }
                try {

                    JSONObject jsonObject1 = response.getJSONObject("data");
                    JSONArray jsonArray = jsonObject1.getJSONObject("data").getJSONArray("seller");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //getting product object from json array
                        JSONObject data = jsonArray.getJSONObject(i);
                         sellerid = data.getInt("seller_detail_id");
                       // Toast.makeText(SellerActivity.this, ""+sellerid, Toast.LENGTH_SHORT).show();
                    }
                                      if (jsonArray.length() == 0) {
                        findViewById(R.id.noitemavalable).setVisibility(View.VISIBLE);

                        Button button1 = (Button) findViewById(R.id.noitemavailablebutton);

                        button1.setBackground(SellerActivity.this.getResources().getDrawable(R.drawable.buttonshape));
                        GradientDrawable bgShape = (GradientDrawable) button1.getBackground();
                        bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                        button1.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


                        TextView textView = (TextView) findViewById(R.id.noitemavailabletext);
                        TextView extra = (TextView) findViewById(R.id.extra);
                        String cart = getString(R.string.empty_cart_description);
                        String carts = getString(R.string.empty_cart_title);

                                extra.setText(cart);
                        findViewById(R.id.imagenoitemAvailable).setBackground(SellerActivity.this.getResources().getDrawable(R.drawable.empty_order_default));

                        textView.setText(carts);
                        button1.setText("Start Shopping Missing");
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(SellerActivity.this, Dashboard.class);
                                startActivity(intent);
                            }
                        });
                    } else {

                        setData(jsonArray);
                    }

                } catch (JSONException e)

                {
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

    public void setData(JSONArray jsonArray1) {
        Gson gson = new Gson();
        List<BusinessModel> list = Arrays.asList(gson.fromJson(jsonArray1.toString(), BusinessModel[].class));
        System.out.println("dada" + list);
        RecyclerView recyclerMyOrder = (RecyclerView) findViewById(R.id.recmyorder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMyOrder.setLayoutManager(layoutManager);
        BusinessAdapter displayImageAdapter = new BusinessAdapter(this, list);
        recyclerMyOrder.setAdapter(displayImageAdapter);
        findViewById(R.id.whiteloader).setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(SellerActivity.this);
      getData();
    }


}