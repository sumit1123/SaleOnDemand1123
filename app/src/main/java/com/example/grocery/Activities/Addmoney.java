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
import com.example.grocery.utils.EditTextColorChanger;
import com.example.grocery.utils.LoaderColorChanger;
import com.example.grocery.utils.ResponseHandler;
import com.example.grocery.utils.ToolbarSettings;
import com.example.grocery.utils.VolleyTask;
import com.payumoney.sdkui.ui.activities.PayUmoneyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import static com.example.grocery.Appearances.Appearance.appSettings;
import static com.example.grocery.interfaces.IConstants.URL_GETUSERPROFILE;

public class Addmoney extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText addQuantityField;
    public static String wallet_amount;
    //private SharedPreferences labelsShared;
    private Button dialogButtonOk;
    private TextView one;
    private TextView two;
    private TextView five;
    private TextView ten;
    private TextView promocode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(prefs.getString("language_code", "en")));
        configuration.setLayoutDirection(new Locale(prefs.getString("language_code", "en")));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        setContentView(R.layout.activity_addmoney);
        addQuantityField = (AppCompatEditText) findViewById(R.id.quantityadd);
        new EditTextColorChanger(this, addQuantityField);

        dialogButtonOk = (Button) findViewById(R.id.cpupdate);
        one = (TextView) findViewById(R.id.one);
     //   promocode = (TextView) findViewById(R.id.promocode);

        two = (TextView) findViewById(R.id.two);
        five = (TextView) findViewById(R.id.five);
        ten = (TextView) findViewById(R.id.ten);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        five.setOnClickListener(this);
        ten.setOnClickListener(this);

        try {
          //  String promo = getString(R.string.have_a_promocode_Enter_here);
         //   promocode.setText(promo);
         //   promocode.setTextColor(Color.parseColor("#" + appSettings.getText_color()));
            String qty = getString(R.string.quantity);
            addQuantityField.setHint(qty);

            one.setText(Appearance.appTranslation.getCurrency() + "100");
            two.setText(Appearance.appTranslation.getCurrency() + "200");
            five.setText(Appearance.appTranslation.getCurrency() + "500");
            ten.setText(Appearance.appTranslation.getCurrency() + "1000");
            addQuantityField.setSelection(addQuantityField.getText().toString().length());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        ToolbarSettings toolbarSettings = new ToolbarSettings(this);
        LoaderColorChanger loaderColorChanger = new LoaderColorChanger(this);

        setToolBar();
        addMoneyToWallet();
        setUserDetails();
    }

    private void setUserDetails(){
        JSONObject jsonObject = new JSONObject();
        SharedPreferences prefs = getSharedPreferences("UserId", MODE_PRIVATE);

        String userId = prefs.getString("user_id", "");
        String email = prefs.getString("email", "");
        String password = prefs.getString("pwd", "");
        String languageid = prefs.getString("language", String.valueOf(1));

        try {
            jsonObject.put("business_id", IConstants.BUSINESS_ID);
            jsonObject.put("id", userId);
            jsonObject.put("password", password);
            jsonObject.put("language_id", languageid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyTask volleyTask = new VolleyTask(this, URL_GETUSERPROFILE, jsonObject, Request.Method.POST);
        volleyTask.setListener(new VolleyTask.IPostTaskListener() {
            @Override
            public void fnPostTaskCompleted(JSONArray response) {

            }

            @Override
            public void fnPostTaskCompletedJsonObject(JSONObject response) {
                findViewById(R.id.whiteloader).setVisibility(View.GONE);

                ResponseHandler responseHandler = new ResponseHandler();
              /*  if (!responseHandler.validateResponse(Addmoney.this, response)) {
                    return;
                }*/
                try {
                    JSONObject jsonObject1 = response.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data").getJSONObject("user");
                    Dashboard.cart_count = jsonObject1.getJSONObject("data").getInt("cart_count");
                    Dashboard.notification_count = jsonObject1.getJSONObject("data").getInt("notification_count");
                    new CartCountUtil(Addmoney.this);

                    String name = jsonObject2.getString("name");
                    String number = jsonObject2.getString("contact_number");
                    String address = "Not Required";
                    String email = jsonObject2.getString("email");



                    PayuActivity.name = jsonObject2.getString("name");
                    PayuActivity.email = jsonObject2.getString("email");
                    PayuActivity.mobile = jsonObject2.getString("contact_number");
                    /*PayuActivity.amount = cashtobepaid;*/
                    PayuActivity.address = address;
                    PayuActivity.pin = address;

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

    private void setToolBar() {
        {
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
                    Intent intent = new Intent(Addmoney.this, NotificationActivity.class);
                    startActivity(intent);
                }
            });

            if (appSettings.getIs_notification() == 0) {
                toolBarNotification.setVisibility(View.GONE);
            }
            RelativeLayout toolBarSearch = (RelativeLayout) findViewById(R.id.toolsearch);
            toolBarSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Addmoney.this, SearchActivity.class);
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
                    Intent intent = new Intent(Addmoney.this, CartActivity.class);
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
                String addmoney = getString(R.string.add_money);
                toolbarTitle.setText(addmoney);


            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private void addMoneyToWallet() {

        /*ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#" + colorSettings.getString("text_color", "#FFFFFF")));
        addQuantityField.setSupportBackgroundTintList(colorStateList);*/
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
                                    Color.parseColor("#" + appSettings.getApp_back_color()),
                                    Color.parseColor("#" + appSettings.getApp_back_color()),
                                    Color.parseColor("#" + appSettings.getApp_back_color()),
                                    Color.parseColor("#" + appSettings.getApp_back_color())
                            }
            );
            dialogButtonOk.setBackgroundTintList(stateListDrawable);
            dialogButtonOk.setTextColor( Color.parseColor("#" + appSettings.getApp_text_color()));
        }
        else{
            dialogButtonOk.setBackground(Addmoney.this.getResources().getDrawable(R.drawable.buttonshape));
            GradientDrawable bgShape = (GradientDrawable) dialogButtonOk.getBackground();
            bgShape.setColor(Color.parseColor("#" + appSettings.getApp_back_color()));
            dialogButtonOk.setTextColor( Color.parseColor("#" + appSettings.getApp_text_color()));

        }


        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addQuantityField.getText().toString().isEmpty()) {
                    try {
                        addQuantityField.requestFocus();
                        String error = getString(R.string.enter_amount);
                        addQuantityField.setError(error);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else if (Integer.parseInt(addQuantityField.getText().toString()) == 0) {
                    try {
                        addQuantityField.requestFocus();
                     String err = getString(R.string.enter_amount);
                        addQuantityField.setError(err);

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {


                    PaymentTypeActivity.payment_for = "wallet";

                    PayuActivity.amount = Double.parseDouble(addQuantityField.getText().toString());
                   RazorPay.amount =  Double.parseDouble(addQuantityField.getText().toString());

                  //  RazorPay.amounts = addQuantityField.getText().toString();
                    Intent intent = new Intent(Addmoney.this, PaymentTypeActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        new CartCountUtil(Addmoney.this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                addQuantityField.setText("100");
                addQuantityField.setSelection(addQuantityField.getText().toString().length());
                break;
            case R.id.two:
                addQuantityField.setText("200");
                addQuantityField.setSelection(addQuantityField.getText().toString().length());

                break;
            case R.id.five:
                addQuantityField.setText("500");
                addQuantityField.setSelection(addQuantityField.getText().toString().length());

                break;
            case R.id.ten:
                addQuantityField.setText("1000");
                addQuantityField.setSelection(addQuantityField.getText().toString().length());

                break;
        }
    }
}
