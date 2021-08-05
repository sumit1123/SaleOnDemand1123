package com.example.grocery.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.example.grocery.Appearances.Appearance;
import com.example.grocery.Labels.Label;
import com.example.grocery.R;
import com.example.grocery.interfaces.IConstants;
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.interfaces.IConstants.Url_GetTransfer;

public class WalletTransfer extends AppCompatActivity {

    private AppCompatEditText mobilenumber;
    private Button transferButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_transfer_money);
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);
        ((ProgressBar) findViewById(R.id.blurredloaderprogressbar)).getIndeterminateDrawable().setColorFilter(Color.parseColor("#" + Appearance.appSettings.getText_color()), PorterDuff.Mode.MULTIPLY);

        mobilenumber = (AppCompatEditText) findViewById(R.id.mobilenumber);
        new EditTextColorChanger(this,mobilenumber);

        transferButton = (Button) findViewById(R.id.transferButton);

        try {
            String mobile = getString(R.string.mobile_number);
            String transfer = getString(R.string.add);

            mobilenumber.setHint(mobile);
            transferButton.setText(transfer);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobilenumber.getText().toString().matches("")) {
                    try {
                        mobilenumber.requestFocus();
                        String err = getString(R.string.contact_number_empty);
                        mobilenumber.setError(err);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else if (mobilenumber.getText().toString().length() != 10) {
                    try {
                        mobilenumber.requestFocus();
                        String mobile = getString(R.string.please_enter_a_valid_number);
                        mobilenumber.setError(mobile);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {
                    sendMoney();
                }
            }
        });

        setToolBar();
    }

    public void sendMoney() {
        findViewById(R.id.loaderBlurred).setVisibility(View.VISIBLE);
        final JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userid = prefs.getString("user_id", "");
        String emaila = prefs.getString("email", "");
        String pwda = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userid);
            jsonObject.put("password", pwda);
            jsonObject.put("contact_number", mobilenumber.getText().toString());
            jsonObject.put("language_id", languageid);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("gghg" + jsonObject);

        VolleyTask volleyTask = new VolleyTask(this, Url_GetTransfer, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {
                System.out.println("sed" + response);
            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                System.out.println("hhhs" + response.toString());
                findViewById(R.id.loaderBlurred).setVisibility(View.GONE);

                if (!new ResponseHandler().validateResponse(WalletTransfer.this, response)) {
                    return;
                }
                Gson gson = new Gson();

                try {
                    JSONObject jsonObject1 = response.getJSONObject("data").getJSONObject("data").getJSONObject("user");
                    Intent intent = new Intent(WalletTransfer.this, WalletTransferConfirmation.class);
                    intent.putExtra("contact_number", jsonObject1.getString("contact_number"));
                    intent.putExtra("name", jsonObject1.getString("name"));
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                    findViewById(R.id.whiteloader).setVisibility(View.GONE);
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
                        findViewById(R.id.retryImage).setVisibility(View.VISIBLE);
                        Button imageView = (Button) findViewById(R.id.retrybutton);
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendMoney();
                            }
                        });
                    }
                }, 2000);


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
        RelativeLayout toolBarNotification = (RelativeLayout) findViewById(R.id.toolnotification);
        toolBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletTransfer.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences appsettings = getSharedPreferences("APPSETTINGS", MODE_PRIVATE);

        if (appsettings.getInt("is_notification", 0) == 0) {
            toolBarNotification.setVisibility(View.GONE);

        }
        RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
        toolBarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletTransfer.this, SearchActivity.class);
                SearchActivity.searchKeyword = "";
                SearchActivity.searchMax = "";
                ApplyFilter.maximumSeekBar = "5000";
                ApplyFilter.brandArrays = null;
                ProductDetails.productid = 0;

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
                Intent intent = new Intent(WalletTransfer.this, CartActivity.class);
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
        TextView toolbarTitle = (TextView) findViewById(R.id.titlebar);
        try {
            String mobile = getString(R.string.transfer_money);
            toolbarTitle.setText(mobile);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
