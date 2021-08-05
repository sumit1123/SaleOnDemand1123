package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Adapter.AddressAdapter;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.Model.AddressModel;
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
import static com.example.grocery.interfaces.IConstants.Url_MyAddress;

public class MyAddress extends AppCompatActivity {

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
        setContentView(R.layout.activity_my_address);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        setToolBar();

        TextView addnewAddressText = (TextView) findViewById(R.id.addnewAddressText);
        try {
            String new_address =getString(R.string.new_address);
            addnewAddressText.setText(new_address);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        addnewAddressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAddress.this, AddNewAddreses.class);
                intent.putExtra("country_id", 1);
                intent.putExtra("state_id", 1);
                intent.putExtra("city_id", 1);
                intent.putExtra("pin_id", 1);
                intent.putExtra("name", "");
                intent.putExtra("address", "");
                intent.putExtra("contact_number", "");
                intent.putExtra("address_id", 0);
                intent.putExtra("email", "");
                intent.putExtra("area_id", 1);


                startActivity(intent);
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setColorSchemeColors((Color.parseColor("#" + Appearance.appSettings.getText_color())));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // Refresh items
                hideloading = true;
                onResume();


            }
        });
    }

    private void setToolBar() {
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
        RelativeLayout toolbarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolbarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        if (Appearance.appSettings.getIs_notification() == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;
                isViewWithCatalog = false;

                ApplyFilter.minimumSeekBar = "0";
                SearchActivity.searchMin = "";
                SearchActivity.sortedida = 0;
                SearchActivity.formatedStringSearch = "";
                SearchActivity.imagea = "true";
                startActivity(intent);
            }
        });
        RelativeLayout toolBarCart = (RelativeLayout) findViewById(R.id.cart);
        toolBarCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddress.this, CartActivity.class);
                startActivity(intent);
            }
        });
        RelativeLayout toolBarBack = (RelativeLayout) findViewById(R.id.toolimage);

        toolBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView toolBarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String getaddress = getString(R.string.address);
            toolBarTitle.setText(getaddress);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        new CartCountUtil(MyAddress.this);

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
        String email = prefs.getString("email", "");
        String pwd = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwd);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, Url_MyAddress, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.whiteloader).setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                if (!new ResponseHandler().validateResponse(MyAddress.this, response)) {
                    return;
                }
                try {

                    JSONArray jsonArray = response.getJSONObject("data").getJSONObject("data").getJSONArray("addresses");
                    loadAddresses(jsonArray);

                } catch (JSONException e) {
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

                        Button retryButton = (Button) findViewById(R.id.retrybutton);
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onResume();
                            }
                        });
                    }
                }, 2000);


            }
        });


    }

    private void loadAddresses(JSONArray jsonArray) {
        Gson gson = new Gson();
        List<AddressModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), AddressModel[].class));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myaddressRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AddressAdapter addressAdapter = new AddressAdapter(list, MyAddress.this);
        recyclerView.setAdapter(addressAdapter);
    }


}
