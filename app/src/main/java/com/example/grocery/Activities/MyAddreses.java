package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.CartCountUtil;
import com.example.grocery.utils.CustomToast;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.Activities.ProductActivity.isViewWithCatalog;
import static com.example.grocery.interfaces.IConstants.URL_ADDNEWADDRESS;
import static com.example.grocery.interfaces.IConstants.URL_GETDELIVERYDETAILS;

public class MyAddreses extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout bar;
    public AppCompatEditText address_et;
    private Button addNewAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_my_addreses);
        
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        ToolbarSettings toolbarSettings = new ToolbarSettings(this);

        setToolBar();
        initUI();
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
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
                                        Color.parseColor("#" + Appearance.appSettings.getText_color()),
                                        Color.parseColor("#" + Appearance.appSettings.getText_color()),
                                        Color.parseColor("#" + Appearance.appSettings.getText_color()),
                                        Color.parseColor("#" + Appearance.appSettings.getText_color())
                                }
                );
                addNewAddress.setBackgroundTintList(stateListDrawable);
                addNewAddress.setTextColor( Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));
            }
            else{
                addNewAddress.setBackground(MyAddreses.this.getResources().getDrawable(R.drawable.buttonshape));
                GradientDrawable bgShape = (GradientDrawable) addNewAddress.getBackground();
                bgShape.setColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
                addNewAddress.setTextColor( Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));

            }
            String add = getString(R.string.add);
            String address = getString(R.string.address);
            addNewAddress.setText(add);
            address_et.setHighlightColor(Color.parseColor("#" + Appearance.appSettings.getText_color()));
            addNewAddress.setHint(address);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
      //  setUpUserDetails();
    }

    private void setUpUserDetails() {
        findViewById(R.id.whiteloader).setVisibility(View.VISIBLE);

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

        VolleyTask volleyTask = new VolleyTask(this, URL_GETDELIVERYDETAILS, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());

                if (!new ResponseHandler().validateResponse(MyAddreses.this, response)) {
                    return;
                }

                try {

                    JSONObject jsonObject3 = response.getJSONObject("data");
                   // PaymentDetailsActivity.selectedAddressId = response.getJSONObject("data").getJSONObject("data").getJSONObject("user").getInt("address_id");
                    loadAddresses(response.getJSONObject("data").getJSONObject("data").getJSONArray("addresses"));

                    findViewById(R.id.whiteloader).setVisibility(View.GONE);

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
                        bar = (RelativeLayout) findViewById(R.id.progressBar);
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarinner);
                        progressBar.setVisibility(View.GONE);

                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);

                        Button retryButton = (Button) findViewById(R.id.retrybutton);
                        retryButton.setVisibility(View.VISIBLE);
                        retryButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setUpUserDetails();
                            }
                        });
                    }
                }, 2000);


            }
        });


    }

    private void loadAddresses(JSONArray jsonArray) {
       /* Gson gson = new Gson();
        List<AddressModel> list = Arrays.asList(gson.fromJson(jsonArray.toString(), AddressModel[].class));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.addressrecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (list.size() > 3) {
            ViewGroup.LayoutParams params_new = recyclerView.getLayoutParams();
            params_new.height = 450;
            recyclerView.setLayoutParams(params_new);
        }
        AddressAdapter addressAdapter = new AddressAdapter(list, this);
        recyclerView.setAdapter(addressAdapter);*/

    }

    private void initUI() {
        address_et = (AppCompatEditText) findViewById(R.id.address_et);
        addNewAddress = (Button) findViewById(R.id.addnewAddress);
        addNewAddress.setOnClickListener(this);
        addNewAddress.setBackgroundColor(Color.parseColor("#" + Appearance.appSettings.getApp_back_color()));
        addNewAddress.setTextColor(Color.parseColor("#" + Appearance.appSettings.getApp_text_color()));


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
                Intent intent = new Intent(MyAddreses.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        if (appsettings.getInt("is_notification", 0) == 0) {
            toolbarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddreses.this, SearchActivity.class);
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
                Intent intent = new Intent(MyAddreses.this, CartActivity.class);
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
            String address = getString(R.string.address);
            toolBarTitle.setText(address);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void addnewAddress() {
        if (address_et.getText().toString().matches("")) {
            try {
                address_et.requestFocus();
                String address_empty = getString(R.string.address_empty);
                address_et.setError(address_empty);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
            JSONObject jsonObject = new JSONObject();
            SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

            String userid = prefs.getString("user_id", "");
            String email = prefs.getString("email", "");
            String pwd = prefs.getString("pwd", "");
            String languageid = prefs.getString("language", String.valueOf(1));

            try {
                jsonObject.put("business_id",IConstants.BUSINESS_ID);
                jsonObject.put("id", userid);
                jsonObject.put("password", pwd);
                jsonObject.put("address", address_et.getText().toString());
                jsonObject.put("language_id", languageid);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            VolleyTask volleyTask = new VolleyTask(this, URL_ADDNEWADDRESS, jsonObject, Request.Method.POST);
            volleyTask.setListener(new VolleyTask.IPostTaskListener() {
                @Override
                public void fnPostTaskCompleted(JSONArray response) {

                }

                @Override
                public void fnPostTaskCompletedJsonObject(JSONObject response) {
                    System.out.println("hhhs" + response.toString());
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                    if (!new ResponseHandler().validateResponse(MyAddreses.this, response)) {
                        return;
                    }

                    try {

                        new CustomToast(MyAddreses.this, response.getJSONObject("data").getString("msg"));
                       // PaymentDetailsActivity.selectedAddressId = response.getJSONObject("data").getJSONObject("data").getJSONObject("address").getInt("address_id");
                        loadAddresses(response.getJSONObject("data").getJSONObject("data").getJSONArray("addresses"));
                        finish();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void fnErrorOccurred(String error) {
                    findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                }
            });


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addnewAddress:
                addnewAddress();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(MyAddreses.this);


    }
}
